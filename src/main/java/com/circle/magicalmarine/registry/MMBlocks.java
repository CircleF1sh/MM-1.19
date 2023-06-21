package com.circle.magicalmarine.registry;

import com.circle.magicalmarine.MagicalMarine;
import com.circle.magicalmarine.common.blocks.SquishspawnBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MMBlocks {
    public static final DeferredRegister<Block> BLOCK = DeferredRegister.create(ForgeRegistries.BLOCKS, MagicalMarine.MOD_ID);

    public static final RegistryObject<Block> SQUISHSPAWN = BLOCK.register("squishspawn", () -> new SquishspawnBlock(BlockBehaviour.Properties.copy(Blocks.FROGSPAWN)));
}
