package net.mcreator.emeralnd.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import net.mcreator.emeralnd.world.inventory.SatsToEmeraldsMenu;
import net.mcreator.emeralnd.procedures.GetInvoiceTimerTextProcedure;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class SatsToEmeraldsScreen extends AbstractContainerScreen<SatsToEmeraldsMenu> {
	private final static HashMap<String, Object> guistate = SatsToEmeraldsMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox bolt11_invoice;

	public SatsToEmeraldsScreen(SatsToEmeraldsMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 177;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("emeralnd:textures/screens/sats_to_emeralds.png");

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		bolt11_invoice.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);

		guiGraphics.blit(new ResourceLocation("emeralnd:textures/screens/fake_qr.png"), this.leftPos + 55, this.topPos + 51, 0, 0, 64, 64, 64, 64);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (bolt11_invoice.isFocused())
			return bolt11_invoice.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String bolt11_invoiceValue = bolt11_invoice.getValue();
		super.resize(minecraft, width, height);
		bolt11_invoice.setValue(bolt11_invoiceValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.emeralnd.sats_to_emeralds.label_trade_sats_for_emeralds"), 26, 4, -12829636, false);
		guiGraphics.drawString(this.font,

				GetInvoiceTimerTextProcedure.execute(entity), 74, 32, -6750055, false);
	}

	@Override
	public void init() {
		super.init();
		bolt11_invoice = new EditBox(this.font, this.leftPos + 27, this.topPos + 129, 118, 18, Component.translatable("gui.emeralnd.sats_to_emeralds.bolt11_invoice"));
		bolt11_invoice.setMaxLength(32767);
		guistate.put("text:bolt11_invoice", bolt11_invoice);
		this.addWidget(this.bolt11_invoice);
	}
}
