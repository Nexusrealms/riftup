package de.nexusrealms.riftup.client;

import de.nexusrealms.riftup.client.screen.AlloymakingScreen;
import de.nexusrealms.riftup.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class RiftupClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.ALLOYMAKING, AlloymakingScreen::new);
    }
}
