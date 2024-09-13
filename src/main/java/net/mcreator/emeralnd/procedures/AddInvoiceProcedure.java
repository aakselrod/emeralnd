package net.mcreator.emeralnd.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.emeralnd.network.EmeralndModVariables;
import net.mcreator.emeralnd.EmeralndMod;

public class AddInvoiceProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		String payment_request = "";
		String r_hash = "";
		try {
			java.net.http.HttpClient client = java.net.http.HttpClient.newHttpClient();
			java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder().uri(java.net.URI.create("http://localhost:8080/v1/invoices")).POST(java.net.http.HttpRequest.BodyPublishers.ofString("{\"value\": 1000, \"expiry\": 60}")).build();
			java.net.http.HttpResponse<String> response = client.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
			EmeralndMod.LOGGER.info("Got response: " + response.body());
			com.google.gson.JsonElement invoiceEl = com.google.gson.JsonParser.parseString(response.body());
			com.google.gson.JsonObject invoiceObj = invoiceEl.getAsJsonObject();
			payment_request = invoiceObj.get("payment_request").getAsString();
			r_hash = String.format("%064x", new java.math.BigInteger(1, java.util.Base64.getDecoder().decode(invoiceObj.get("r_hash").getAsString())));
			EmeralndMod.LOGGER.info("Got r_hash: " + r_hash);
		} catch (Exception e) {
			java.io.StringWriter sw = new java.io.StringWriter();
			java.io.PrintWriter pw = new java.io.PrintWriter(sw);
			e.printStackTrace(pw);
			EmeralndMod.LOGGER.error(sw.toString());
			return;
		}
		{
			EmeralndModVariables.PlayerVariables _vars = entity.getData(EmeralndModVariables.PLAYER_VARIABLES);
			_vars.player_invoice = payment_request;
			_vars.syncPlayerVariables(entity);
		}
		{
			EmeralndModVariables.PlayerVariables _vars = entity.getData(EmeralndModVariables.PLAYER_VARIABLES);
			_vars.player_invoice_hash = r_hash;
			_vars.syncPlayerVariables(entity);
		}
	}
}
