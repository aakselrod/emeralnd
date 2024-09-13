package net.mcreator.emeralnd.procedures;

import net.neoforged.neoforge.items.ItemHandlerHelper;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.client.gui.components.EditBox;

import net.mcreator.emeralnd.network.EmeralndModVariables;
import net.mcreator.emeralnd.EmeralndMod;

import java.util.HashMap;

public class SatsToEmeraldsOpenTickProcedure {
	public static void execute(LevelAccessor world, Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		boolean paid = false;
		String r_hash = "";
		if (!world.isClientSide()) {
			if (GetInvoiceTimerProcedure.execute(entity) == 0) {
				if (entity instanceof Player _player)
					_player.closeContainer();
			} else {
				r_hash = entity.getData(EmeralndModVariables.PLAYER_VARIABLES).player_invoice_hash;
				if (!(r_hash).equals("")) {
					try {
						java.net.http.HttpClient client = java.net.http.HttpClient.newHttpClient();
						EmeralndMod.LOGGER.info("Hex r_hash: " + r_hash);
						java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder().uri(java.net.URI.create("http://localhost:8080/v1/invoice/" + r_hash)).GET().build();
						java.net.http.HttpResponse<String> response = client.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
						EmeralndMod.LOGGER.info("Got response: " + response.body());
						com.google.gson.JsonElement invoiceEl = com.google.gson.JsonParser.parseString(response.body());
						com.google.gson.JsonObject invoiceObj = invoiceEl.getAsJsonObject();
						paid = invoiceObj.get("settled").getAsBoolean();
					} catch (Exception e) {
						java.io.StringWriter sw = new java.io.StringWriter();
						java.io.PrintWriter pw = new java.io.PrintWriter(sw);
						e.printStackTrace(pw);
						EmeralndMod.LOGGER.error(sw.toString());
						return;
					}
					if (paid == true) {
						if (entity instanceof Player _player) {
							ItemStack _setstack = new ItemStack(Items.EMERALD).copy();
							_setstack.setCount(10);
							ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
						}
						if (entity instanceof Player _player)
							_player.closeContainer();
					}
				}
			}
		}
		if (!(entity.getData(EmeralndModVariables.PLAYER_VARIABLES).player_invoice).equals(guistate.containsKey("text:bolt11_invoice") ? ((EditBox) guistate.get("text:bolt11_invoice")).getValue() : "")) {
			if (guistate.get("text:bolt11_invoice") instanceof EditBox _tf)
				_tf.setValue(entity.getData(EmeralndModVariables.PLAYER_VARIABLES).player_invoice);
		}
	}
}
