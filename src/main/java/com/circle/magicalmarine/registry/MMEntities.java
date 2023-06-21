package com.circle.magicalmarine.registry;

import com.circle.magicalmarine.MagicalMarine;
import com.circle.magicalmarine.common.entity.*;
import net.minecraft.client.particle.WaterCurrentDownParticle;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.data.worldgen.features.MiscOverworldFeatures;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MMEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MagicalMarine.MOD_ID);


    public static final RegistryObject<EntityType<Squish>> SQUISH = ENTITIES.register("squish", () -> EntityType.Builder.of(Squish::new, MobCategory.WATER_CREATURE).sized(0.45F, 0.45F).build("squish"));
    public static final RegistryObject<EntityType<Squishling>> SQUISHLING = ENTITIES.register("squishling", () -> EntityType.Builder.of(Squishling::new, MobCategory.WATER_AMBIENT).sized(0.25F, 0.25F).build("squishling"));
    public static final RegistryObject<EntityType<TealstripeMinnow>> TEALSTRIPE_MINNOW = ENTITIES.register("tealstripe_minnow", () -> EntityType.Builder.of(TealstripeMinnow::new, MobCategory.WATER_AMBIENT).sized(0.25F, 0.35F).build("tealstripe_minnow"));
    public static final RegistryObject<EntityType<PurplefinTuna>> PURPLEFIN_TUNA = ENTITIES.register("purplefin_tuna", () -> EntityType.Builder.of(PurplefinTuna::new, MobCategory.WATER_AMBIENT).sized(0.35F, 0.5F).build("purplefin_tuna"));
    public static final RegistryObject<EntityType<BurntailCatfish>> BURNTAIL_CATFISH = ENTITIES.register("burntail_catfish", () -> EntityType.Builder.of(BurntailCatfish::new, MobCategory.WATER_AMBIENT).sized(0.4F, 0.5F).build("burntail_catfish"));


}
