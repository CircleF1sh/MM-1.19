package com.circle.magicalmarine.client.model;

import com.circle.magicalmarine.common.entity.TealstripeMinnow;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.CodModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class TealstripeMinnowModel<T extends TealstripeMinnow> extends EntityModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "custom_model"), "main");
    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart leftPelvicFin;
    private final ModelPart rightPelvicFin;
    private final ModelPart dorsalFin;

    public TealstripeMinnowModel(ModelPart root) {
        this.body = root.getChild("body");
        this.tail = body.getChild("tail");
        this.leftPelvicFin = body.getChild("leftPelvicFin");
        this.rightPelvicFin = body.getChild("rightPelvicFin");
        this.dorsalFin = body.getChild("dorsalFin");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 23.0F, -1.0F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 3).addBox(0.0F, -1.0F, -0.1F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 2.0F));

        PartDefinition leftPelvicFin = body.addOrReplaceChild("leftPelvicFin", CubeListBuilder.create().texOffs(0, 2).addBox(0.0F, 0.0F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 1.0F, 0.5F));

        PartDefinition rightPelvicFin = body.addOrReplaceChild("rightPelvicFin", CubeListBuilder.create().texOffs(0, 1).addBox(0.0F, 0.0F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 1.0F, 0.5F));

        PartDefinition dorsalFin = body.addOrReplaceChild("dorsalFin", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.5F));

        return LayerDefinition.create(meshdefinition, 16, 16);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = 1.0F;
        if (!entity.isInWater()) {
            f = 1.5F;
        }

        this.tail.yRot = -f * 0.45F * Mth.sin(0.6F * ageInTicks);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
