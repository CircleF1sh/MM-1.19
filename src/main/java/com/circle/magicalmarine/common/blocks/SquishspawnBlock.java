package com.circle.magicalmarine.common.blocks;

import com.circle.magicalmarine.registry.MMEntities;
import com.google.common.annotations.VisibleForTesting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import com.circle.magicalmarine.common.entity.Squishling;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class SquishspawnBlock extends MultifaceBlock implements SimpleWaterloggedBlock {
    private static final int MIN_SQUISHLING_SPAWN = 1;
    private static final int MAX_SQUISHLING_SPAWN = 1;
    private static final int DEFAULT_MIN_HATCH_TICK_DELAY = 3600;
    private static final int DEFAULT_MAX_HATCH_TICK_DELAY = 12000;
    private static int minHatchTickDelay = 3600;
    private static int maxHatchTickDelay = 12000;
    private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private final MultifaceSpreader spreader = new MultifaceSpreader(this);

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldReader, BlockPos pos) {
        BlockState above = worldReader.getBlockState(pos.above());
        FluidState fluidState = worldReader.getFluidState(pos);
        return fluidState.is(FluidTags.WATER) && above.getMaterial().isReplaceable();
    }

    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState state2, boolean p_221231_) {
        world.scheduleTick(pos, this, getSquishlingHatchRate(world.getRandom()));
    }

    private static int getSquishlingHatchRate(RandomSource source) {
        return source.nextInt(minHatchTickDelay, maxHatchTickDelay);
    }

    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource source) {
        if (!this.canSurvive(state, level, pos)) {
            this.destroyBlock(level, pos);
        } else {
            this.hatchSquishspawn(level, pos, source);
        }
    }

    private void hatchSquishspawn(ServerLevel level, BlockPos pos, RandomSource source) {
        this.destroyBlock(level, pos);
        level.playSound((Player)null, pos, SoundEvents.FROGSPAWN_HATCH, SoundSource.BLOCKS, 1.0F, 1.0F);
        this.spawnTadpoles(level, pos, source);
    }

    private void spawnTadpoles(ServerLevel serverWorld, BlockPos pos, RandomSource source) {
        int i = source.nextInt(1, 4);

        for(int j = 1; j <= i; ++j) {
            Squishling squishling = MMEntities.SQUISHLING.get().create(serverWorld);
            if (squishling != null) {
                double d0 = (double)pos.getX() + this.getSquishlingPositionOffset(source);
                double d1 = (double)pos.getZ() + this.getSquishlingPositionOffset(source);
                int k = source.nextInt(1, 361);
                squishling.moveTo(d0, (double)pos.getY() - 0.5D, d1, (float)k, 0.0F);
                squishling.setPersistenceRequired();
                serverWorld.addFreshEntity(squishling);
            }
        }

    }

    private double getSquishlingPositionOffset(RandomSource p_221225_) {
        double d0 = (double)(Squishling.HITBOX_WIDTH / 2.0F);
        return Mth.clamp(p_221225_.nextDouble(), d0, 1.0D - d0);
    }


    public SquishspawnBlock(Properties p_153822_) {
        super(p_153822_);
        this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, Boolean.valueOf(false)));
    }

    @Override
    public MultifaceSpreader getSpreader() {
        return this.spreader;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> state) {
        super.createBlockStateDefinition(state);
        state.add(WATERLOGGED);
    }

    public BlockState updateShape(BlockState state, Direction dir, BlockState state2, LevelAccessor worldAccessor, BlockPos pos, BlockPos pos2) {
        if (state.getValue(WATERLOGGED)) {
            worldAccessor.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldAccessor));
        }

        return super.updateShape(state, dir, state2, worldAccessor, pos, pos2);
    }

    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
        if (entity.getType().equals(EntityType.FALLING_BLOCK) || entity.getType().equals(EntityType.ARROW) || entity.getType().equals(EntityType.PLAYER)) {
            this.destroyBlock(world, pos);
        }

    }

    private void destroyBlock(Level p_221191_, BlockPos p_221192_) {
        p_221191_.destroyBlock(p_221192_, false);
    }

    @VisibleForTesting
    public static void setHatchDelay(int p_221179_, int p_221180_) {
        minHatchTickDelay = p_221179_;
        maxHatchTickDelay = p_221180_;
    }

    @VisibleForTesting
    public static void setDefaultHatchDelay() {
        minHatchTickDelay = 3600;
        maxHatchTickDelay = 12000;
    }
}

