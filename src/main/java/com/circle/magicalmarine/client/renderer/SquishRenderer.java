package com.circle.magicalmarine.client.renderer;

import com.circle.magicalmarine.MagicalMarine;
import com.circle.magicalmarine.client.model.SquishModel;
import com.circle.magicalmarine.common.entity.Squish;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SquishRenderer extends MobRenderer<Squish, SquishModel<Squish>> {
        private static final ResourceLocation SQUISH_LOCATION = new ResourceLocation(MagicalMarine.MOD_ID, "textures/entity/squish/squish.png");

   public SquishRenderer(EntityRendererProvider.Context p_173929_) {
        super(p_173929_, new SquishModel(p_173929_.bakeLayer(SQUISH)), 0.25F);
    }

        public ResourceLocation getTextureLocation(Squish p_113876_) {
        return SQUISH_LOCATION;
    }

    public static final ModelLayerLocation SQUISH = register("squish");

    private static ModelLayerLocation register(String name) {
        return new ModelLayerLocation(new ResourceLocation(MagicalMarine.MOD_ID, name), "main");
    }
}
