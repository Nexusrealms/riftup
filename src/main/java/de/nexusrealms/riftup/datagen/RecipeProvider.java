package de.nexusrealms.riftup.datagen;

import de.nexusrealms.riftup.block.ModBlocks;
import de.nexusrealms.riftup.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class RecipeProvider extends FabricRecipeProvider {
    public RecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.TIN_INGOT, 9)
                .input(ModBlocks.TIN_BLOCK)
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.TIN_BLOCK), FabricRecipeProvider.conditionsFromItem(ModBlocks.TIN_BLOCK))
                .offerTo(recipeExporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BRONZE_INGOT, 9)
                .input(ModBlocks.BRONZE_BLOCK)
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.BRONZE_BLOCK), FabricRecipeProvider.conditionsFromItem(ModBlocks.BRONZE_BLOCK))
                .offerTo(recipeExporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.STEEL_INGOT, 9)
                .input(ModBlocks.STEEL_BLOCK)
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.STEEL_BLOCK), FabricRecipeProvider.conditionsFromItem(ModBlocks.STEEL_BLOCK))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.TIN_BLOCK, 1)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .input('#', ModItems.TIN_INGOT)
                .criterion(FabricRecipeProvider.hasItem(ModItems.TIN_INGOT), FabricRecipeProvider.conditionsFromItem(ModItems.TIN_INGOT))
                .offerTo(recipeExporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BRONZE_BLOCK, 1)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .input('#', ModItems.BRONZE_INGOT)
                .criterion(FabricRecipeProvider.hasItem(ModItems.BRONZE_INGOT), FabricRecipeProvider.conditionsFromItem(ModItems.BRONZE_INGOT))
                .offerTo(recipeExporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.STEEL_BLOCK, 1)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .input('#', ModItems.STEEL_INGOT)
                .criterion(FabricRecipeProvider.hasItem(ModItems.STEEL_INGOT), FabricRecipeProvider.conditionsFromItem(ModItems.STEEL_INGOT))
                .offerTo(recipeExporter);
    }
}
