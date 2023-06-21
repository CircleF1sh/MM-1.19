package com.circle.magicalmarine.client.renderer;

import com.circle.magicalmarine.MagicalMarine;
import com.circle.magicalmarine.client.model.BurntailCatfishModel;
import com.circle.magicalmarine.common.entity.BurntailCatfish;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BurntailCatfishRenderer extends MobRenderer<BurntailCatfish, BurntailCatfishModel<BurntailCatfish>> {

    private static final ResourceLocation BURNTAIL_CATFISH_LOCATION = new ResourceLocation(MagicalMarine.MOD_ID, "textures/entity/burntail_catfish/burntail_catfish.png");

    public BurntailCatfishRenderer(EntityRendererProvider.Context contextProvider) {
        super(contextProvider, new BurntailCatfishModel(contextProvider.bakeLayer(BURNTAIL_CATFISH)), 0.3F);
    }

    public ResourceLocation getTextureLocation(BurntailCatfish p_113876_) {
        return BURNTAIL_CATFISH_LOCATION;
    }

    public static final ModelLayerLocation BURNTAIL_CATFISH = register("burntail_catfish");

    private static ModelLayerLocation register(String name) {
        return new ModelLayerLocation(new ResourceLocation(MagicalMarine.MOD_ID, name), "main");
    }
}
