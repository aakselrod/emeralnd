
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.emeralnd.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.emeralnd.client.renderer.WanderingBitcoinerRenderer;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EmeralndModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(EmeralndModEntities.WANDERING_BITCOINER.get(), WanderingBitcoinerRenderer::new);
	}
}
