package de.nexusrealms.riftup.client;

import de.nexusrealms.riftup.client.screen.AlloymakingScreen;
import de.nexusrealms.riftup.item.ModItems;
import de.nexusrealms.riftup.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.component.type.DyedColorComponent;

public class RiftupClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.ALLOYMAKING, AlloymakingScreen::new);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : DyedColorComponent.getColor(stack, 0xFFAAAAAA),
                ModItems.CLOTH_HELMET, ModItems.CLOTH_CHESTPLATE, ModItems.CLOTH_LEGGINGS, ModItems.CLOTH_BOOTS);
    }
}
