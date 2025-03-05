package de.nexusrealms.riftup.screen;

import de.nexusrealms.riftup.Riftup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

public class ModScreenHandlers {
    public static final ScreenHandlerType<AlloymakingScreenHandler> ALLOYMAKING = create("alloymaking", new ScreenHandlerType<>(AlloymakingScreenHandler::new, FeatureFlags.VANILLA_FEATURES));
    private static <T extends ScreenHandler> ScreenHandlerType<T> create(String name, ScreenHandlerType<T> type){
        return Registry.register(Registries.SCREEN_HANDLER, Riftup.id(name), type);
    }
    public static void init(){}
}
