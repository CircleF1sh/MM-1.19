package com.circle.magicalmarine.registry;

import com.circle.magicalmarine.MagicalMarine;
import com.circle.magicalmarine.common.items.MMBucketItem;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.WaterDropParticle;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.*;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Supplier;

public class MMItems {
    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(ForgeRegistries.ITEMS, MagicalMarine.MOD_ID);
    //Spawn Eggs
    public static final RegistryObject<Item> SQUISH_SPAWN_EGG = ITEM.register("squish_spawn_egg", () -> new ForgeSpawnEggItem(MMEntities.SQUISH, 0x864d3f, 0x41221f, new Item.Properties()));
    public static final RegistryObject<Item> SQUISHLING_SPAWN_EGG = ITEM.register("squishling_spawn_egg", () -> new ForgeSpawnEggItem(MMEntities.SQUISHLING, 0x725750, 0x3e3231, new Item.Properties()));
    public static final RegistryObject<Item> TEALSTRIPE_MINNOW_EGG = ITEM.register("tealstripe_minnow_egg", () -> new ForgeSpawnEggItem(MMEntities.TEALSTRIPE_MINNOW, 0x989184, 0x91e6c2, new Item.Properties()));
    public static final RegistryObject<Item> PURPLEFIN_TUNA_EGG = ITEM.register("purplefin_tuna_egg", () -> new ForgeSpawnEggItem(MMEntities.PURPLEFIN_TUNA, 0x575342, 0x6c405c, new Item.Properties()));
    public static final RegistryObject<Item> BURNTAIL_CATFISH_EGG = ITEM.register("burntail_catfish_egg", () -> new ForgeSpawnEggItem(MMEntities.BURNTAIL_CATFISH, 0x865e31, 0x421d0d, new Item.Properties()));
    //Buckets
    public static final RegistryObject<Item> SQUISHLING_BUCKET = ITEM.register("squishling_bucket", () -> new MMBucketItem(MMEntities.SQUISHLING, () -> Fluids.WATER, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> TEALSTRIPE_MINNOW_BUCKET = ITEM.register("tealstripe_minnow_bucket", () -> new MMBucketItem(MMEntities.TEALSTRIPE_MINNOW, () -> Fluids.WATER, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> PURPLEFIN_TUNA_BUCKET = ITEM.register("purplefin_tuna_bucket", () -> new MMBucketItem(MMEntities.PURPLEFIN_TUNA, () -> Fluids.WATER, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> BURNTAIL_CATFISH_BUCKET = ITEM.register("burntail_catfish_bucket", () -> new MMBucketItem(MMEntities.BURNTAIL_CATFISH, () -> Fluids.WATER, new Item.Properties().stacksTo(1)));

    //Items
    public static final RegistryObject<Item> TEALSTRIPE_MINNOW = ITEM.register("tealstripe_minnow", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> PURPLEFIN_TUNA = ITEM.register("purplefin_tuna", () -> new Item(new Item.Properties().stacksTo(64)));

    //Block Items
    public static final RegistryObject<BlockItem> SQUISHSPAWN = ITEM.register("squishspawn", () -> new BlockItem(MMBlocks.SQUISHSPAWN.get(), new Item.Properties()));


}