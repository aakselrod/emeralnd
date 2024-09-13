
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.emeralnd.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.core.registries.Registries;

import net.mcreator.emeralnd.world.inventory.SatsToEmeraldsMenu;
import net.mcreator.emeralnd.EmeralndMod;

public class EmeralndModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(Registries.MENU, EmeralndMod.MODID);
	public static final DeferredHolder<MenuType<?>, MenuType<SatsToEmeraldsMenu>> SATS_TO_EMERALDS = REGISTRY.register("sats_to_emeralds", () -> IMenuTypeExtension.create(SatsToEmeraldsMenu::new));
}
