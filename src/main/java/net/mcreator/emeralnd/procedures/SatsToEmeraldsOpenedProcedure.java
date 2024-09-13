package net.mcreator.emeralnd.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

import net.mcreator.emeralnd.network.EmeralndModVariables;

public class SatsToEmeraldsOpenedProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (!world.isClientSide()) {
			{
				EmeralndModVariables.PlayerVariables _vars = entity.getData(EmeralndModVariables.PLAYER_VARIABLES);
				_vars.player_invoice = "";
				_vars.syncPlayerVariables(entity);
			}
			{
				EmeralndModVariables.PlayerVariables _vars = entity.getData(EmeralndModVariables.PLAYER_VARIABLES);
				_vars.player_invoice_expiration = System.currentTimeMillis() + 59000;
				_vars.syncPlayerVariables(entity);
			}
			AddInvoiceProcedure.execute(entity);
		}
	}
}
