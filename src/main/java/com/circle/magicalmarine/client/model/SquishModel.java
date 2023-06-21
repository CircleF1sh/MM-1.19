package com.circle.magicalmarine.client.model;

import com.circle.magicalmarine.MagicalMarine;
import com.circle.magicalmarine.common.entity.Squish;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.DolphinModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.Dolphin;

public class SquishModel<T extends Squish> extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(MagicalMarine.MOD_ID, "squish"), "main");
    private final ModelPart body;
    private final ModelPart mouth;
    private final ModelPart leftEye;
    private final ModelPart rightEye;
    private final ModelPart tail;
    private final ModelPart frontLeftFins;
    private final ModelPart frontRightFins;
    private final ModelPart backLeftFins;
    private final ModelPart backRightFins;
    public SquishModel(ModelPart root) {
        this.body = root.getChild("body");
        this.mouth = body.getChild("mouth");
        this.leftEye = body.getChild("leftEye");
        this.rightEye = body.getChild("rightEye");
        this.tail = body.getChild("tail");
        this.frontLeftFins = body.getChild("frontLeftFins");
        this.frontRightFins = body.getChild("frontRightFins");
        this.backLeftFins = body.getChild("backLeftFins");
        this.backRightFins = body.getChild("backRightFins");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -1.5F, -4.0F, 5.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 19.5F, -1.0F));

        PartDefinition mouth = body.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(0, 10).addBox(-2.5F, 0.0F, -1.0F, 5.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.5F, -3.0F));

        PartDefinition leftEye = body.addOrReplaceChild("leftEye", CubeListBuilder.create().texOffs(17, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.75F, -1.0F, -2.0F));

        PartDefinition rightEye = body.addOrReplaceChild("rightEye", CubeListBuilder.create().texOffs(11, 12).addBox(-1.0F, -2.25F, -1.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.75F, -0.75F, -2.0F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 10).addBox(-1.0F, -3.0F, -1.5F, 0.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 0.5F, 4.0F));

        PartDefinition frontLeftFins = body.addOrReplaceChild("frontLeftFins", CubeListBuilder.create().texOffs(8, 17).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 1.5F, -1.0F));

        PartDefinition frontRightFins = body.addOrReplaceChild("frontRightFins", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 1.5F, -1.0F));

        PartDefinition backLeftFins = body.addOrReplaceChild("backLeftFins", CubeListBuilder.create().texOffs(0, 2).addBox(1.0F, 0.0F, 0.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.25F, 0.5F, 2.0F));

        PartDefinition backRightFins = body.addOrReplaceChild("backRightFins", CubeListBuilder.create().texOffs(12, 8).addBox(1.0F, 0.0F, 0.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.25F, 0.5F, 2.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void setupAnim(Squish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 0.5F;
        float speedMultiplier = 1.5F;

        //tilting
        this.body.xRot = headPitch * ((float) Math.PI / 180F);
        this.body.yRot = netHeadYaw * ((float) Math.PI / 180F);

        // oscillate the tail up and down
        float tailAngle = Mth.sin(ageInTicks * 0.2f) * 0.2f;
        this.tail.zRot = tailAngle;
        this.tail.yRot = -tailAngle;

        //rotates the body
        float bodyRotate = Mth.sin(ageInTicks * 0.1f) * 0.15f;
        this.body.zRot = tailAngle;
        this.body.yRot = tailAngle;

        // swing the fins from side to side
        float finAngle = Mth.cos(ageInTicks * 0.1f) * 0.2f;
        this.frontRightFins.yRot = -finAngle;
        this.frontLeftFins.yRot = finAngle;

        // stop animation if entity is not moving
        if (entity.getDeltaMovement().lengthSqr() < 0.001) {
            this.tail.zRot = 0;
            this.tail.yRot = 0;
            this.body.zRot = 0;
            this.body.yRot = 0;
            this.frontRightFins.yRot = 0;
            this.frontLeftFins.yRot = 0;
        }
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}