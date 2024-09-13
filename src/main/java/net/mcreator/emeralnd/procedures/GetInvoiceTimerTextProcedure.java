package net.mcreator.emeralnd.procedures;

import net.minecraft.world.entity.Entity;

public class GetInvoiceTimerTextProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		double timeLeft = 0;
		timeLeft = GetInvoiceTimerProcedure.execute(entity) / 1000;
		java.text.DecimalFormat df = new java.text.DecimalFormat("00");
		df.setRoundingMode(java.math.RoundingMode.DOWN);
		return df.format(timeLeft / 60) + ":" + df.format(timeLeft % 60);
	}
}
