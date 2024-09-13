package net.mcreator.emeralnd.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.emeralnd.network.EmeralndModVariables;

public class GetInvoiceTimerProcedure {
	public static double execute(Entity entity) {
		if (entity == null)
			return 0;
		return Math.max(0, entity.getData(EmeralndModVariables.PLAYER_VARIABLES).player_invoice_expiration - System.currentTimeMillis());
	}
}
