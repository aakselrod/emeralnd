package net.mcreator.emeralnd.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.emeralnd.network.EmeralndModVariables;

public class SatsToEmeraldsClosedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			EmeralndModVariables.PlayerVariables _vars = entity.getData(EmeralndModVariables.PLAYER_VARIABLES);
			_vars.player_invoice = "";
			_vars.syncPlayerVariables(entity);
		}
		{
			EmeralndModVariables.PlayerVariables _vars = entity.getData(EmeralndModVariables.PLAYER_VARIABLES);
			_vars.player_invoice_hash = "";
			_vars.syncPlayerVariables(entity);
		}
	}
}
