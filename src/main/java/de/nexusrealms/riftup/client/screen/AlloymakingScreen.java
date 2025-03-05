package de.nexusrealms.riftup.client.screen;

import de.nexusrealms.riftup.Riftup;
import de.nexusrealms.riftup.screen.AlloymakingScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.recipebook.RecipeBookWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.AbstractFurnaceScreenHandler;
import net.minecraft.screen.AbstractRecipeScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class AlloymakingScreen extends HandledScreen<AlloymakingScreenHandler> {
    private final Identifier background = Riftup.id("textures/gui/container/alloymaking_furnace.png");
    private final Identifier meltTexture = Riftup.id("container/alloymaking/melt");
    private final Identifier burnProgressTexture = Riftup.id("container/alloymaking/burn");;
    public AlloymakingScreen(AlloymakingScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }
    public void init() {
        super.init();
        this.titleX = (this.backgroundWidth - this.textRenderer.getWidth(this.title)) / 2;
    }
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(context, mouseX, mouseY);
    }

    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int i = this.x;
        int j = this.y;
        context.drawTexture(this.background, i, j, 0, 0, this.backgroundWidth, this.backgroundHeight);
        if ((this.handler).isBurning()) {
            context.drawGuiTexture(this.burnProgressTexture, 14, 14, 0, 0, i + 56, j + 36, 14, 14);
        }
    }
}
