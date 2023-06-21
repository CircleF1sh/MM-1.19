package com.circle.magicalmarine.client.renderer;

import com.circle.magicalmarine.MagicalMarine;
import com.circle.magicalmarine.client.model.SquishModel;
import com.circle.magicalmarine.client.model.SquishlingModel;
import com.circle.magicalmarine.client.model.TealstripeMinnowModel;
import com.circle.magicalmarine.common.entity.Squish;
import com.circle.magicalmarine.common.entity.Squishling;
import com.circle.magicalmarine.common.entity.TealstripeMinnow;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class TealstripeMinnowRenderer extends MobRenderer<TealstripeMinnow, TealstripeMinnowModel<TealstripeMinnow>>  {

    private static final ResourceLocation TEALSTRIPE_MINNOW_LOCATION = new ResourceLocation(MagicalMarine.MOD_ID, "textures/entity/tealstripe_minnow/tealstripe_minnow.png");

    public TealstripeMinnowRenderer(EntityRendererProvider.Context contextProvider) {
        super(contextProvider, new TealstripeMinnowModel(contextProvider.bakeLayer(TEALSTRIPE_MINNOW)), 0.3F);
    }

    public ResourceLocation getTextureLocation(TealstripeMinnow p_113876_) {
        return TEALSTRIPE_MINNOW_LOCATION;
    }

    public static final ModelLayerLocation TEALSTRIPE_MINNOW = register("tealstripe_minnow");

    private static ModelLayerLocation register(String name) {
        return new ModelLayerLocation(new ResourceLocation(MagicalMarine.MOD_ID, name), "main");
    }
}
