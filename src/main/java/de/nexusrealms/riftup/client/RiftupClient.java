package de.nexusrealms.riftup.client;

import de.nexusrealms.riftup.item.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.component.type.DyedColorComponent;

public class RiftupClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : DyedColorComponent.getColor(stack, 0xFFAAAAAA),
                ModItems.CLOTH_HELMET, ModItems.CLOTH_CHESTPLATE, ModItems.CLOTH_LEGGINGS, ModItems.CLOTH_BOOTS);
    }
}
