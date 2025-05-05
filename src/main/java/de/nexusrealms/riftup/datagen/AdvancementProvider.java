package de.nexusrealms.riftup.datagen;

import de.nexusrealms.riftup.Riftup;
import de.nexusrealms.riftup.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.data.server.advancement.vanilla.VanillaAdvancementProviders;
import net.minecraft.data.server.advancement.vanilla.VanillaStoryTabAdvancementGenerator;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class AdvancementProvider extends FabricAdvancementProvider {
    protected AdvancementProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup wrapperLookup, Consumer<AdvancementEntry> consumer) {
        Advancement.Builder.create()
                //Why would they deprecate it
                .display(
                        ModItems.COKE,
                        Text.translatable("advancement.riftup.coke.name"),
                        Text.translatable("advancement.riftup.coke.desc"),
                        Identifier.ofVanilla("textures/gui/advancements/backgrounds/adventure.png"), // Background image for the tab in the advancements page, if this is a root advancement (has no parent)
                        AdvancementFrame.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                .criterion("get_coke", InventoryChangedCriterion.Conditions.items(ModItems.COKE))
                .build(consumer, Riftup.MOD_ID + ":coke");
    }
}
