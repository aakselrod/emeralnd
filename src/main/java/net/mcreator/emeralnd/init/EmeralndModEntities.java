
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.emeralnd.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.event.entity.SpawnPlacementRegisterEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.EventPriority;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.registries.Registries;

import net.mcreator.emeralnd.entity.WanderingBitcoinerEntity;
import net.mcreator.emeralnd.EmeralndMod;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class EmeralndModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(Registries.ENTITY_TYPE, EmeralndMod.MODID);
	public static final DeferredHolder<EntityType<?>, EntityType<WanderingBitcoinerEntity>> WANDERING_BITCOINER = register("wandering_bitcoiner",
			EntityType.Builder.<WanderingBitcoinerEntity>of(WanderingBitcoinerEntity::new, MobCategory.AMBIENT).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.95f));

	private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.registerEntity(Capabilities.ItemHandler.ENTITY, WANDERING_BITCOINER.get(), (living, context) -> living.getCombinedInventory());
	}

	@SubscribeEvent
	public static void init(SpawnPlacementRegisterEvent event) {
		WanderingBitcoinerEntity.init(event);
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(WANDERING_BITCOINER.get(), WanderingBitcoinerEntity.createAttributes().build());
	}
}
