
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.emeralnd.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;

import net.minecraft.world.item.Item;

import net.mcreator.emeralnd.EmeralndMod;

public class EmeralndModItems {
	public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(EmeralndMod.MODID);
	public static final DeferredHolder<Item, Item> WANDERING_BITCOINER_SPAWN_EGG = REGISTRY.register("wandering_bitcoiner_spawn_egg", () -> new DeferredSpawnEggItem(EmeralndModEntities.WANDERING_BITCOINER, -1, -1, new Item.Properties()));
	// Start of user code block custom items
	// End of user code block custom items
}
