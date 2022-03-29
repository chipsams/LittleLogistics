package dev.murad.shipping.entity.models;// Made with Blockbench 4.1.1
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.murad.shipping.ShippingMod;
import dev.murad.shipping.entity.custom.train.wagon.ChestCarEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class ChestCarModel extends EntityModel<ChestCarEntity> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(ShippingMod.MOD_ID, "chestcarmodel"), "main");
	private final ModelPart bb_main;

	public ChestCarModel(ModelPart root) {
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();


		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -14.0F, -8.0F, 2.0F, 12.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(26, 18).addBox(-5.0F, -16.0F, -4.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.0F, -14.0F, -8.0F, 2.0F, 12.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(0, 28).addBox(-5.0F, -14.0F, -8.0F, 10.0F, 12.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 28).addBox(-5.0F, -14.0F, 6.0F, 10.0F, 12.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(20, 0).addBox(-5.0F, -6.0F, -6.0F, 10.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-6.0F, -2.0F, 4.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-6.0F, -2.0F, -6.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.0F, -2.0F, 4.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(5.0F, -2.0F, -6.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(ChestCarEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bb_main.render(poseStack, buffer, packedLight, packedOverlay);
	}
}