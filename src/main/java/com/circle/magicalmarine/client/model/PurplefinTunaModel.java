package com.circle.magicalmarine.client.model;

import com.circle.magicalmarine.common.entity.PurplefinTuna;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.CodModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.SalmonModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class PurplefinTunaModel<T extends PurplefinTuna> extends EntityModel<T> {
    private final ModelPart body;
    private final ModelPart tailFIn;
    private final ModelPart dorsalFin;
    private final ModelPart pectoralLeft;
    private final ModelPart pectoralRight;

    public PurplefinTunaModel(ModelPart root) {
        this.body = root.getChild("body");
        this.tailFIn = body.getChild("tailFIn");
        this.dorsalFin = body.getChild("dorsalFin");
        this.pectoralLeft = body.getChild("pectoralLeft");
        this.pectoralRight = body.getChild("pectoralRight");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.5F, -3.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.5F, -2.0F));

        PartDefinition tailFIn = body.addOrReplaceChild("tailFIn", CubeListBuilder.create().texOffs(0, 5).addBox(0.0F, -1.5F, 0.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.0F));

        PartDefinition dorsalFin = body.addOrReplaceChild("dorsalFin", CubeListBuilder.create().texOffs(0, 8).addBox(0.0F, -2.0F, -1.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, 1.0F));

        PartDefinition pectoralLeft = body.addOrReplaceChild("pectoralLeft", CubeListBuilder.create(), PartPose.offset(0.9F, 1.5F, 0.0F));

        PartDefinition cube_r1 = pectoralLeft.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(8, 6).addBox(1.75F, -1.6F, -1.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 2.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

        PartDefinition pectoralRight = body.addOrReplaceChild("pectoralRight", CubeListBuilder.create().texOffs(8, 6).addBox(0.25F, -2.25F, -1.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 3.5F, 0.0F, 0.0F, 0.0F, 0.3491F));

        return LayerDefinition.create(meshdefinition, 16, 16);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = 1.0F;
        if (!entity.isInWater()) {
            f = 1.5F;
        }

        this.tailFIn.yRot = -f * 0.45F * Mth.sin(0.6F * ageInTicks);
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
