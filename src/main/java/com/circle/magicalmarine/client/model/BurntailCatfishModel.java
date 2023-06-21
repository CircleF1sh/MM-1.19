package com.circle.magicalmarine.client.model;

import com.circle.magicalmarine.common.entity.BurntailCatfish;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class BurntailCatfishModel<T extends BurntailCatfish> extends EntityModel<T> {

    private final ModelPart body;
    private final ModelPart tailFin;
    private final ModelPart dorsalFin;
    private final ModelPart pectoralRight;
    private final ModelPart frontLeftWhisker;
    private final ModelPart frontRightWhisker;
    private final ModelPart backLeftWhisker;
    private final ModelPart backRightWhisker;

    public BurntailCatfishModel(ModelPart root) {
        this.body = root.getChild("body");
        this.tailFin = body.getChild("tailFin");
        this.dorsalFin = body.getChild("dorsalFin");
        this.pectoralRight = body.getChild("pectoralRight");
        this.frontLeftWhisker = body.getChild("frontLeftWhisker");
        this.frontRightWhisker = body.getChild("frontRightWhisker");
        this.backLeftWhisker = body.getChild("backLeftWhisker");
        this.backRightWhisker = body.getChild("backRightWhisker");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.0F, -4.0F, 4.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, 0.0F));

        PartDefinition tailFin = body.addOrReplaceChild("tailFin", CubeListBuilder.create().texOffs(0, 5).addBox(0.0F, -4.0F, 0.0F, 0.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.0F));

        PartDefinition dorsalFin = body.addOrReplaceChild("dorsalFin", CubeListBuilder.create().texOffs(12, 8).addBox(0.0F, -2.0F, -1.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

        PartDefinition pectoralLeft = body.addOrReplaceChild("pectoralLeft", CubeListBuilder.create(), PartPose.offset(2.0F, 2.0F, 2.0F));

        PartDefinition cube_r1 = pectoralLeft.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(9, 8).addBox(0.0F, 0.0F, -1.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 0.0F, 0.0F, -0.3491F));

        PartDefinition pectoralRight = body.addOrReplaceChild("pectoralRight", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, -1.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.3491F));

        PartDefinition frontLeftWhisker = body.addOrReplaceChild("frontLeftWhisker", CubeListBuilder.create().texOffs(13, 0).addBox(0.0F, 0.0F, -0.5F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 2.0F, -3.5F));

        PartDefinition frontRightWhisker = body.addOrReplaceChild("frontRightWhisker", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 0.0F, -0.5F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 2.0F, -3.5F));

        PartDefinition backLeftWhisker = body.addOrReplaceChild("backLeftWhisker", CubeListBuilder.create().texOffs(2, 2).addBox(0.0F, 0.0F, -0.5F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 2.0F, -0.5F));

        PartDefinition backRightWhisker = body.addOrReplaceChild("backRightWhisker", CubeListBuilder.create().texOffs(0, 2).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 2.0F, -0.5F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = 1.0F;
        if (!entity.isInWater()) {
            f = 1.5F;
        }

        this.tailFin.yRot = -f * 0.45F * Mth.sin(0.6F * ageInTicks);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
