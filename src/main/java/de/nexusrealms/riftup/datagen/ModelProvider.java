package de.nexusrealms.riftup.datagen;

import de.nexusrealms.riftup.block.ModBlocks;
import de.nexusrealms.riftup.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.CropBlock;
import net.minecraft.data.client.*;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.Optional;

public class ModelProvider extends FabricModelProvider {
    protected ModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.STEEL_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.NETHER_DIAMOND_ORE);
    }
    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.STEEL_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.COKE, Models.GENERATED);

        itemModelGenerator.registerArmor(ModItems.CLOTH_HELMET);
        itemModelGenerator.registerArmor(ModItems.CLOTH_CHESTPLATE);
        itemModelGenerator.registerArmor(ModItems.CLOTH_LEGGINGS);
        itemModelGenerator.registerArmor(ModItems.CLOTH_BOOTS);
        itemModelGenerator.registerArmor(ModItems.STEEL_HELMET);
        itemModelGenerator.registerArmor(ModItems.STEEL_CHESTPLATE);
        itemModelGenerator.registerArmor(ModItems.STEEL_LEGGINGS);
        itemModelGenerator.registerArmor(ModItems.STEEL_BOOTS);
        itemModelGenerator.register(ModItems.STEEL_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STEEL_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STEEL_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STEEL_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STEEL_HOE, Models.HANDHELD);

    }
}
