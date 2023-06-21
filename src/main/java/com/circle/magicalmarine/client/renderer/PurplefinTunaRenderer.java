package com.circle.magicalmarine.client.renderer;

import com.circle.magicalmarine.MagicalMarine;
import com.circle.magicalmarine.client.model.PurplefinTunaModel;
import com.circle.magicalmarine.client.model.TealstripeMinnowModel;
import com.circle.magicalmarine.common.entity.PurplefinTuna;
import com.circle.magicalmarine.common.entity.TealstripeMinnow;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PurplefinTunaRenderer extends MobRenderer<PurplefinTuna, PurplefinTunaModel<PurplefinTuna>> {

    private static final ResourceLocation PURPLEFIN_TUNA_LOCATION = new ResourceLocation(MagicalMarine.MOD_ID, "textures/entity/purplefin_tuna/purplefin_tuna.png");

    public PurplefinTunaRenderer(EntityRendererProvider.Context contextProvider) {
        super(contextProvider, new PurplefinTunaModel(contextProvider.bakeLayer(PURPLEFIN_TUNA)), 0.3F);
    }

    public ResourceLocation getTextureLocation(PurplefinTuna p_113876_) {
        return PURPLEFIN_TUNA_LOCATION;
    }

    public static final ModelLayerLocation PURPLEFIN_TUNA = register("purplefin_tuna");

    private static ModelLayerLocation register(String name) {
        return new ModelLayerLocation(new ResourceLocation(MagicalMarine.MOD_ID, name), "main");
    }
}
