package de.nexusrealms.riftup.world;

import de.nexusrealms.riftup.Riftup;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

public class ModWorldGen {
    private static final RegistryKey<PlacedFeature> NETHER_CEILING_DIAMOND_ORE = RegistryKey.of(
            RegistryKeys.PLACED_FEATURE,
            Riftup.id("nether_ceiling_diamond_ore")
    );

    public static void register() {
        // Remove vanilla diamond generation
        BiomeModifications.addFeature(
                BiomeSelectors.foundInTheNether(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                NETHER_CEILING_DIAMOND_ORE
        );
    }
}