package com.circle.magicalmarine;

import com.circle.magicalmarine.common.entity.*;
import com.circle.magicalmarine.registry.*;
import com.google.common.eventbus.Subscribe;
import com.mojang.logging.LogUtils;
import net.minecraft.data.worldgen.biome.NetherBiomes;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MagicalMarine.MOD_ID)
public class MagicalMarine
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "magicalmarine";
    public static final List<Runnable> CALLBACKS = new ArrayList<>();

    private static final Logger LOGGER = LogUtils.getLogger();

    public MagicalMarine() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::registerEntityAttributes);

        FMLJavaModLoadingContext.get().getModEventBus().register(this);

        MMEntities.ENTITIES.register(modEventBus);
        MMItems.ITEM.register(modEventBus);
        MMBlocks.BLOCK.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void addCreative(CreativeModeTabEvent.BuildContents event) {
        if(event.getTab() == MMTab.MM_TAB) {
            event.accept(MMItems.SQUISH_SPAWN_EGG.get());
            event.accept(MMItems.SQUISHLING_SPAWN_EGG.get());
            event.accept(MMItems.TEALSTRIPE_MINNOW_EGG.get());
            event.accept(MMItems.PURPLEFIN_TUNA_EGG.get());
            event.accept(MMItems.BURNTAIL_CATFISH_EGG.get());
            event.accept(MMItems.SQUISHLING_BUCKET.get());
            event.accept(MMItems.TEALSTRIPE_MINNOW_BUCKET.get());
            event.accept(MMItems.PURPLEFIN_TUNA_BUCKET.get());
            event.accept(MMItems.BURNTAIL_CATFISH_BUCKET.get());
            event.accept(MMItems.TEALSTRIPE_MINNOW.get());
            event.accept(MMItems.PURPLEFIN_TUNA.get());
            event.accept(MMItems.SQUISHSPAWN.get());
        }
    }


    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            SpawnPlacements.register(MMEntities.TEALSTRIPE_MINNOW.get(),
                    SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        });
        event.enqueueWork(() -> {
            SpawnPlacements.register(MMEntities.PURPLEFIN_TUNA.get(),
                    SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        });
        event.enqueueWork(() -> {
            SpawnPlacements.register(MMEntities.BURNTAIL_CATFISH.get(),
                    SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
        });
    }

    private void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(MMEntities.SQUISH.get(), Squish.createAttributes().build());
        event.put(MMEntities.SQUISHLING.get(), Squishling.createAttributes().build());
        event.put(MMEntities.TEALSTRIPE_MINNOW.get(), TealstripeMinnow.createAttributes().build());
        event.put(MMEntities.PURPLEFIN_TUNA.get(), PurplefinTuna.createAttributes().build());
        event.put(MMEntities.BURNTAIL_CATFISH.get(), BurntailCatfish.createAttributes().build());
    }
}
