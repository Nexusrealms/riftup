package de.nexusrealms.riftup.datagen;

import de.nexusrealms.riftup.item.ModItemTags;
import de.nexusrealms.riftup.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ItemTagProvider extends FabricTagProvider<Item> {
    public ItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.ITEM, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ItemTags.DYEABLE)
                .add(ModItems.CLOTH_HELMET, ModItems.CLOTH_CHESTPLATE, ModItems.CLOTH_LEGGINGS, ModItems.CLOTH_BOOTS);
        getOrCreateTagBuilder(ModItemTags.NEED_COKE_TO_SMELT)
                .add(ModItems.STEEL_INGOT);
        getOrCreateTagBuilder(ModItemTags.COKE)
                .add(ModItems.COKE);
        getOrCreateTagBuilder(ItemTags.SWORDS)
                .add(ModItems.STEEL_SWORD);
        getOrCreateTagBuilder(ItemTags.PICKAXES)
                .add(ModItems.STEEL_PICKAXE);
        getOrCreateTagBuilder(ItemTags.AXES)
                .add(ModItems.STEEL_AXE);
        getOrCreateTagBuilder(ItemTags.SHOVELS)
                .add(ModItems.STEEL_SHOVEL);
        getOrCreateTagBuilder(ItemTags.HOES)
                .add(ModItems.STEEL_HOE);
    }



}
