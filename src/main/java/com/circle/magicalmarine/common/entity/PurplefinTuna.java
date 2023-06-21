package com.circle.magicalmarine.common.entity;

import com.circle.magicalmarine.registry.MMItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PurplefinTuna extends AbstractSchoolingFish {

    public PurplefinTuna(EntityType<? extends AbstractSchoolingFish> p_27523_, Level p_27524_) {
        super(p_27523_, p_27524_);
    }

    public int getMaxSchoolSize() {
        return 7;
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.SALMON_FLOP;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(MMItems.PURPLEFIN_TUNA_BUCKET.get());
    }
}
