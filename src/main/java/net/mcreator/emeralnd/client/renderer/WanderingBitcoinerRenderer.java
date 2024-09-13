
package net.mcreator.emeralnd.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.VillagerModel;

import net.mcreator.emeralnd.entity.WanderingBitcoinerEntity;

import com.mojang.blaze3d.vertex.PoseStack;

public class WanderingBitcoinerRenderer extends MobRenderer<WanderingBitcoinerEntity, VillagerModel<WanderingBitcoinerEntity>> {
	public WanderingBitcoinerRenderer(EntityRendererProvider.Context context) {
		super(context, new VillagerModel(context.bakeLayer(ModelLayers.VILLAGER)), 0.5f);
	}

	@Override
	protected void scale(WanderingBitcoinerEntity entity, PoseStack poseStack, float f) {
		poseStack.scale(0.9375f, 0.9375f, 0.9375f);
	}

	@Override
	public ResourceLocation getTextureLocation(WanderingBitcoinerEntity entity) {
		return new ResourceLocation("emeralnd:textures/entities/wandering_bitcoiner_texture_64x.png");
	}
}
