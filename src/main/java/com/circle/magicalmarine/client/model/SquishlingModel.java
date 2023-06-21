package com.circle.magicalmarine.client.model;

import com.circle.magicalmarine.common.entity.Squishling;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.TurtleModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.TurtleRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.Turtle;

public class SquishlingModel <T extends Squishling> extends EntityModel<T> {
    private final ModelPart body;
    private final ModelPart mouth;
    private final ModelPart leftEye;
    private final ModelPart rightEye;
    private final ModelPart frontRightFin;
    private final ModelPart frontLeftFin;
    private final ModelPart backLeftFin;
    private final ModelPart backRightFin;
    private final ModelPart tail;

    public SquishlingModel(ModelPart root) {
        this.body = root.getChild("body");
        this.mouth = body.getChild("mouth");
        this.leftEye = body.getChild("leftEye");
        this.rightEye = body.getChild("rightEye");
        this.frontRightFin = body.getChild("frontRightFin");
        this.frontLeftFin = body.getChild("frontLeftFin");
        this.backLeftFin = body.getChild("backLeftFin");
        this.backRightFin = body.getChild("backRightFin");
        this.tail = body.getChild("tail");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -1.0F, -2.5F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 21.0F, 0.5F));

        PartDefinition mouth = body.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(0, 7).addBox(-2.5F, 0.0F, -0.5F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -2.0F));

        PartDefinition leftEye = body.addOrReplaceChild("leftEye", CubeListBuilder.create().texOffs(8, 10).addBox(0.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.75F, -0.75F, -0.5F));

        PartDefinition rightEye = body.addOrReplaceChild("rightEye", CubeListBuilder.create().texOffs(0, 10).addBox(-2.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.75F, -0.75F, -0.5F));

        PartDefinition frontLeftFin = body.addOrReplaceChild("frontLeftFin", CubeListBuilder.create().texOffs(2, 0).addBox(1.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 1.0F, -0.5F));

        PartDefinition frontRightFin = body.addOrReplaceChild("frontRightFin", CubeListBuilder.create().texOffs(0, 2).addBox(1.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, 1.0F, -0.5F));

        PartDefinition backLeftFin = body.addOrReplaceChild("backLeftFin", CubeListBuilder.create().texOffs(0, 1).addBox(1.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 1.0F, 1.5F));

        PartDefinition backRightFin = body.addOrReplaceChild("backRightFin", CubeListBuilder.create().texOffs(0, 0).addBox(1.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, 1.0F, 1.5F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 11).addBox(-1.0F, 0.0F, 0.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -1.0F, 2.25F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        //tilting
        this.body.xRot = headPitch * ((float) Math.PI / 180F);
        this.body.yRot = netHeadYaw * ((float) Math.PI / 180F);

        // oscillate the tail up and down
        float tailAngle = Mth.sin(ageInTicks * 0.1f) * 0.1f;
        this.tail.zRot = tailAngle;
        this.tail.yRot = -tailAngle;

        //rotates the body
        float bodyRotate = Mth.sin(ageInTicks * 0.05f) * 0.75f;
        this.body.zRot = tailAngle;
        this.body.yRot = tailAngle;

        // swing the fins from side to side
        float finAngle = Mth.cos(ageInTicks * 0.05f) * 0.1f;
        this.frontRightFin.yRot = -finAngle;
        this.frontLeftFin.yRot = finAngle;

        // stop animation if entity is not moving
        if (entity.getDeltaMovement().lengthSqr() < 0.001) {
            this.tail.zRot = 0;
            this.tail.yRot = 0;
            this.body.zRot = 0;
            this.body.yRot = 0;
            this.frontRightFin.yRot = 0;
            this.frontLeftFin.yRot = 0;
        }
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
