package com.circle.magicalmarine.client.renderer;

import com.circle.magicalmarine.MagicalMarine;
import com.circle.magicalmarine.client.model.SquishModel;
import com.circle.magicalmarine.client.model.SquishlingModel;
import com.circle.magicalmarine.common.entity.Squish;
import com.circle.magicalmarine.common.entity.Squishling;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ambient.Bat;

public class SquishlingRenderer extends MobRenderer<Squishling, SquishlingModel<Squishling>> {
        private static final ResourceLocation SQUISHLING_LOCATION = new ResourceLocation(MagicalMarine.MOD_ID, "textures/entity/squishling/squishling.png");

   public SquishlingRenderer(EntityRendererProvider.Context contextProvider) {
        super(contextProvider, new SquishlingModel(contextProvider.bakeLayer(SQUISHLING)), 0.2F);
    }

        public ResourceLocation getTextureLocation(Squishling p_113876_) {
        return SQUISHLING_LOCATION;
    }

    protected void scale(Squishling p_113878_, PoseStack p_113879_, float p_113880_) {
        p_113879_.scale(0.75F, 0.75F, 0.75F);
    }
    public static final ModelLayerLocation SQUISHLING = register("squishling");

    private static ModelLayerLocation register(String name) {
        return new ModelLayerLocation(new ResourceLocation(MagicalMarine.MOD_ID, name), "main");
    }
}
