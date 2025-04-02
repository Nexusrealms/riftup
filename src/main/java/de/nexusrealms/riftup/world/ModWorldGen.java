package de.nexusrealms.riftup.world;

import de.nexusrealms.riftup.Riftup;
import net.fabricmc.fabric.api.biome.v1.*;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class ModWorldGen {
    private static final RegistryKey<PlacedFeature> NETHER_CEILING_DIAMOND_ORE = RegistryKey.of(
            RegistryKeys.PLACED_FEATURE,
            Riftup.id("nether_ceiling_diamond_ore")
    );
    private static final RegistryKey<PlacedFeature>[] DIAMOND_FEATURES = new RegistryKey[]{
            PlacedFeatures.of("ore_diamond_buried"), PlacedFeatures.of("ore_diamond_large"), PlacedFeatures.of("ore_diamond"), PlacedFeatures.of("ore_diamond_medium")
    };
    private static final Predicate<BiomeSelectionContext> PREDICATE = biomeSelectionContext -> {
        for (RegistryKey<PlacedFeature> feature : DIAMOND_FEATURES) {
            if(biomeSelectionContext.hasPlacedFeature(feature)) return true;
        }
        return false;
    };
    private final static Consumer<BiomeModificationContext> CONSUMER = biomeModificationContext -> {
        BiomeModificationContext.GenerationSettingsContext context = biomeModificationContext.getGenerationSettings();
        for (RegistryKey<PlacedFeature> feature : DIAMOND_FEATURES) {
            context.removeFeature(feature);
        }
    };

    public static void init() {
        // Remove vanilla diamond generation
        BiomeModifications.create(Riftup.id("test")).add(ModificationPhase.REMOVALS, PREDICATE, CONSUMER);
        BiomeModifications.addFeature(
                BiomeSelectors.foundInTheNether(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                NETHER_CEILING_DIAMOND_ORE
        );
    }
}