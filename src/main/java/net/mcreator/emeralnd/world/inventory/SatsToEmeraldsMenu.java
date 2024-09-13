
package net.mcreator.emeralnd.world.inventory;

import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.emeralnd.procedures.SatsToEmeraldsOpenedProcedure;
import net.mcreator.emeralnd.procedures.SatsToEmeraldsOpenTickProcedure;
import net.mcreator.emeralnd.procedures.SatsToEmeraldsClosedProcedure;
import net.mcreator.emeralnd.init.EmeralndModMenus;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

@EventBusSubscriber
public class SatsToEmeraldsMenu extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {
	public final static HashMap<String, Object> guistate = new HashMap<>();
	public final Level world;
	public final Player entity;
	public int x, y, z;
	private ContainerLevelAccess access = ContainerLevelAccess.NULL;
	private IItemHandler internal;
	private final Map<Integer, Slot> customSlots = new HashMap<>();
	private boolean bound = false;
	private Supplier<Boolean> boundItemMatcher = null;
	private Entity boundEntity = null;
	private BlockEntity boundBlockEntity = null;

	public SatsToEmeraldsMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
		super(EmeralndModMenus.SATS_TO_EMERALDS.get(), id);
		this.entity = inv.player;
		this.world = inv.player.level();
		this.internal = new ItemStackHandler(0);
		BlockPos pos = null;
		if (extraData != null) {
			pos = extraData.readBlockPos();
			this.x = pos.getX();
			this.y = pos.getY();
			this.z = pos.getZ();
			access = ContainerLevelAccess.create(world, pos);
		}
		SatsToEmeraldsOpenedProcedure.execute(world, entity);
	}

	@Override
	public boolean stillValid(Player player) {
		if (this.bound) {
			if (this.boundItemMatcher != null)
				return this.boundItemMatcher.get();
			else if (this.boundBlockEntity != null)
				return AbstractContainerMenu.stillValid(this.access, player, this.boundBlockEntity.getBlockState().getBlock());
			else if (this.boundEntity != null)
				return this.boundEntity.isAlive();
		}
		return true;
	}

	@Override
	public ItemStack quickMoveStack(Player playerIn, int index) {
		return ItemStack.EMPTY;
	}

	@Override
	public void removed(Player playerIn) {
		super.removed(playerIn);
		SatsToEmeraldsClosedProcedure.execute(entity);
	}

	public Map<Integer, Slot> get() {
		return customSlots;
	}

	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent.Post event) {
		Player entity = event.getEntity();
		if (entity.containerMenu instanceof SatsToEmeraldsMenu) {
			Level world = entity.level();
			double x = entity.getX();
			double y = entity.getY();
			double z = entity.getZ();
			SatsToEmeraldsOpenTickProcedure.execute(world, entity, guistate);
		}
	}
}
