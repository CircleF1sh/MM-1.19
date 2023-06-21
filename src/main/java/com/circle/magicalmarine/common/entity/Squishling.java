package com.circle.magicalmarine.common.entity;

import com.circle.magicalmarine.registry.MMEntities;
import com.circle.magicalmarine.registry.MMItems;
import net.minecraft.client.model.BatModel;
import net.minecraft.client.renderer.entity.BatRenderer;
import net.minecraft.client.renderer.entity.layers.TropicalFishPatternLayer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class Squishling extends AbstractFish {
    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(Squishling.class, EntityDataSerializers.BOOLEAN);
    public static float HITBOX_WIDTH = 0.25F;
    public static float HITBOX_HEIGHT = 0.25F;

    public static int ticksUntilTurnAdult = Math.abs(-24000);
    private int age;

    public Squishling(EntityType<? extends AbstractFish> p_27461_, Level p_27462_) {
        super(p_27461_, p_27462_);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
    }

    @Override
    protected SoundEvent getFlopSound() {
        return null;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(MMItems.SQUISHLING_BUCKET.get());
    }

    protected PathNavigation createNavigation(Level p_218694_) {
        return new WaterBoundPathNavigation(this, p_218694_);
    }

    private boolean isFood(ItemStack p_218727_) {
        return Squish.TEMPTATION_ITEM.test(p_218727_);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!this.level.isClientSide) this.setAge(this.age + 1);
        if (!this.isInWater() && this.onGround && this.verticalCollision) {
            this.setDeltaMovement(this.getDeltaMovement().add((double) ((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F), (double) 0.4F, (double) ((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F)));
            this.onGround = false;
            this.hasImpulse = true;
        }

        super.aiStep();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(FROM_BUCKET, false);
    }

    @Override
    public boolean fromBucket() {
        return this.entityData.get(FROM_BUCKET);
    }

    @Override
    public void loadFromBucketTag(CompoundTag compound) {
        Bucketable.loadDefaultDataFromBucketTag(this, compound);
        if (compound.contains("Age")) {
            this.setAge(compound.getInt("Age"));
        }
    }

    @Override
    public void saveToBucketTag(ItemStack bucket) {
        CompoundTag compoundnbt = bucket.getOrCreateTag();
        Bucketable.saveDefaultDataToBucketTag(this, bucket);
        compoundnbt.putFloat("Health", this.getHealth());
        compoundnbt.putInt("Age", this.getAge());
        if (this.hasCustomName()) {
            bucket.setHoverName(this.getCustomName());
        }
    }

    private void eatFood(Player player, ItemStack stack) {
        this.decrementItem(player, stack);
        this.increaseAge((int)((float)(this.getTicksUntilGrowth() / 20) * 0.1F));
        this.level.addParticle(ParticleTypes.HAPPY_VILLAGER, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D), 0.0D, 0.0D, 0.0D);
    }

    private void decrementItem(Player player, ItemStack stack) {
        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }
    }
    private int getAge() {
        return this.age;
    }

    private void increaseAge(int seconds) {
        this.setAge(this.age + seconds * 20);
    }

    private void setAge(int age) {
        this.age = age;
        if (this.age >= ticksUntilTurnAdult) this.growUp();
    }

    private void growUp() {
        if (this.level instanceof ServerLevel server) {
            Squish squish = MMEntities.SQUISH.get().create(this.level);
            if (squish == null) return;

            squish.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
            squish.finalizeSpawn(server, this.level.getCurrentDifficultyAt(squish.blockPosition()), MobSpawnType.CONVERSION, null, null);
            squish.setNoAi(this.isNoAi());
            if (this.hasCustomName()) {
                squish.setCustomName(this.getCustomName());
                squish.setCustomNameVisible(this.isCustomNameVisible());
            }

            squish.setPersistenceRequired();
            this.playSound(SoundEvents.PLAYER_LEVELUP, 0.15F, 1.0F);
            server.addFreshEntityWithPassengers(squish);
            this.discard();
        }
    }

    private int getTicksUntilGrowth() {
        return Math.max(0, ticksUntilTurnAdult - this.age);
    }

    @Override
    public boolean shouldDropExperience() {
        return false;
    }

    public boolean requiresCustomPersistence() {
        return super.requiresCustomPersistence() || this.fromBucket() || this.hasCustomName();
    }

    public boolean removeWhenFarAway(double p_213397_1_) {
        return !this.fromBucket() && !this.hasCustomName();
    }

    private boolean isFromBucket() {
        return this.entityData.get(FROM_BUCKET);
    }

    public void setFromBucket(boolean p_203706_1_) {
        this.entityData.set(FROM_BUCKET, p_203706_1_);
    }


    @Override
    public SoundEvent getPickupSound() {
        return SoundEvents.BUCKET_EMPTY_FISH;
    }

    protected InteractionResult mobInteract(Player p_27477_, InteractionHand p_27478_) {
        return Bucketable.bucketMobPickup(p_27477_, p_27478_, this).orElse(super.mobInteract(p_27477_, p_27478_));
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("FromBucket", this.isFromBucket());
        compound.putBoolean("Bucketed", this.fromBucket());
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setFromBucket(compound.getBoolean("FromBucket"));
        this.setFromBucket(compound.getBoolean("Bucketed"));
    }


    @Nullable
    @Override
    public ItemStack getPickResult() {
        return new ItemStack(MMItems.SQUISHLING_SPAWN_EGG.get());
    }
}
