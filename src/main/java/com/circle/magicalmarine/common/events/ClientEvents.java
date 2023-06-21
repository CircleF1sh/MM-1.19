package com.circle.magicalmarine.common.events;

import com.circle.magicalmarine.MagicalMarine;
import com.circle.magicalmarine.client.model.*;
import com.circle.magicalmarine.client.renderer.*;
import com.circle.magicalmarine.common.blocks.SquishspawnBlock;
import com.circle.magicalmarine.registry.MMBlocks;
import com.circle.magicalmarine.registry.MMEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber(modid = MagicalMarine.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(MMEntities.SQUISH.get(), SquishRenderer::new);
        event.registerEntityRenderer(MMEntities.SQUISHLING.get(), SquishlingRenderer::new);
        event.registerEntityRenderer(MMEntities.TEALSTRIPE_MINNOW.get(), TealstripeMinnowRenderer::new);
        event.registerEntityRenderer(MMEntities.PURPLEFIN_TUNA.get(), PurplefinTunaRenderer::new);
        event.registerEntityRenderer(MMEntities.BURNTAIL_CATFISH.get(), BurntailCatfishRenderer::new);
    }

    @SubscribeEvent
    public static void registerEntityDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(SquishRenderer.SQUISH, SquishModel::createBodyLayer);
        event.registerLayerDefinition(SquishlingRenderer.SQUISHLING, SquishlingModel::createBodyLayer);
        event.registerLayerDefinition(TealstripeMinnowRenderer.TEALSTRIPE_MINNOW, TealstripeMinnowModel::createBodyLayer);
        event.registerLayerDefinition(PurplefinTunaRenderer.PURPLEFIN_TUNA, PurplefinTunaModel::createBodyLayer);
        event.registerLayerDefinition(BurntailCatfishRenderer.BURNTAIL_CATFISH, BurntailCatfishModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void setRenderLayers(FMLClientSetupEvent event) {
        Set<RegistryObject<Block>> blocks = new HashSet<>(MMBlocks.BLOCK.getEntries());

        blocks.stream().filter(b -> {
            final Block block = b.get();
            return block instanceof SquishspawnBlock;
        }).forEach(ClientEvents::setCutout);
    }

    public static void setCutout(RegistryObject<Block> b) {
        ItemBlockRenderTypes.setRenderLayer(b.get(), RenderType.cutout());
    }

 
}
