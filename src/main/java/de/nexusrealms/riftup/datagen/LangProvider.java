package de.nexusrealms.riftup.datagen;

import de.nexusrealms.riftup.block.ModBlocks;
import de.nexusrealms.riftup.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.entity.EntityType;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class LangProvider extends FabricLanguageProvider {
    protected LangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        // Specifying en_us is optional, as it's the default language code
        super(dataOutput, "en_us", registryLookup);
    }
    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        generateBlockTranslations(wrapperLookup, translationBuilder);
        generateItemTranslations(wrapperLookup, translationBuilder);
        generateMessageTranslations(wrapperLookup, translationBuilder);
    }
    private void generateBlockTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder){
        translationBuilder.add(ModBlocks.BRONZE_BLOCK, "Block of Bronze");
        translationBuilder.add(ModBlocks.TIN_BLOCK, "Block of Tin");
        translationBuilder.add(ModBlocks.STEEL_BLOCK, "Block of Steel");


    }
    private void generateItemTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder){
        translationBuilder.add(ModItems.BRONZE_INGOT, "Bronze Ingot");
        translationBuilder.add(ModItems.TIN_INGOT, "Tin Ingot");
        translationBuilder.add(ModItems.STEEL_INGOT, "Steel Ingot");

    }
    private void generateMessageTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder){

    }
}
