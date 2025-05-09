package de.nexusrealms.riftup.datagen;

import de.nexusrealms.riftup.block.ModBlocks;
import de.nexusrealms.riftup.item.ModItemTags;
import de.nexusrealms.riftup.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;

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
        translationBuilder.add(ModBlocks.STEEL_BLOCK, "Block of Steel");
        translationBuilder.add(ModBlocks.NETHER_DIAMOND_ORE, "Nether Diamond Ore");

        translationBuilder.add("container.riftup.alloymaking_furnace", "Alloy-making furnace");
        translationBuilder.add( "emi.category.riftup.alloymaking", "Alloymaking");
    }
    private void generateItemTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder){
        translationBuilder.add(ModItems.STEEL_INGOT, "Steel Ingot");
        translationBuilder.add(ModItems.STEEL_HELMET, "Steel Helmet");
        translationBuilder.add(ModItems.STEEL_CHESTPLATE, "Steel Chestplate");
        translationBuilder.add(ModItems.STEEL_LEGGINGS, "Steel Leggings");
        translationBuilder.add(ModItems.STEEL_BOOTS, "Steel Boots");
        translationBuilder.add(ModItems.CLOTH_HELMET, "Cloth Cap");
        translationBuilder.add(ModItems.CLOTH_CHESTPLATE, "Cloth Tunic");
        translationBuilder.add(ModItems.CLOTH_LEGGINGS, "Cloth Pants");
        translationBuilder.add(ModItems.CLOTH_BOOTS, "Cloth Shoes");
        translationBuilder.add(ModItems.STEEL_SWORD, "Steel Sword");
        translationBuilder.add(ModItems.STEEL_PICKAXE, "Steel Pickaxe");
        translationBuilder.add(ModItems.STEEL_AXE, "Steel Axe");
        translationBuilder.add(ModItems.STEEL_SHOVEL, "Steel Shovel");
        translationBuilder.add(ModItems.STEEL_HOE, "Steel Hoe");
        translationBuilder.add(ModItems.COKE, "Coke");

        translateTag(translationBuilder, ModItemTags.COKE, "Coke");
        translateTag(translationBuilder, ModItemTags.NEED_COKE_TO_SMELT, "Need coke to smelt");
    }
    private void translateTag(TranslationBuilder translationBuilder, TagKey<Item> tagKey, String name){
        translationBuilder.add(tagKey.getTranslationKey(), name);
    }
    private void generateMessageTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder){
        translationBuilder.add("advancement.riftup.coke.name", "Coal but hotter");
        translationBuilder.add("advancement.riftup.coke.desc", "Go make some fancier iron");

    }
}
