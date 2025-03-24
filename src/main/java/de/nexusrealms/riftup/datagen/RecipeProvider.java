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
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class RecipeProvider extends FabricRecipeProvider {
    public RecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.STEEL_INGOT, 9)
                .input(ModBlocks.STEEL_BLOCK)
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.STEEL_BLOCK), FabricRecipeProvider.conditionsFromItem(ModBlocks.STEEL_BLOCK))
                .offerTo(recipeExporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.STEEL_BLOCK, 1)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .input('#', ModItems.STEEL_INGOT)
                .criterion(FabricRecipeProvider.hasItem(ModItems.STEEL_INGOT), FabricRecipeProvider.conditionsFromItem(ModItems.STEEL_INGOT))
                .offerTo(recipeExporter);
        generateArmorRecipes(recipeExporter);
    }
    private void generateArmorRecipes(RecipeExporter recipeExporter){
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.STEEL_HELMET, 1)
                .pattern("###")
                .pattern("# #")
                .input('#', ModItems.STEEL_INGOT)
                .criterion(FabricRecipeProvider.hasItem(ModItems.STEEL_INGOT), FabricRecipeProvider.conditionsFromItem(ModItems.STEEL_INGOT))
                .offerTo(recipeExporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.STEEL_CHESTPLATE, 1)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .input('#', ModItems.STEEL_INGOT)
                .criterion(FabricRecipeProvider.hasItem(ModItems.STEEL_INGOT), FabricRecipeProvider.conditionsFromItem(ModItems.STEEL_INGOT))
                .offerTo(recipeExporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.STEEL_LEGGINGS, 1)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .input('#', ModItems.STEEL_INGOT)
                .criterion(FabricRecipeProvider.hasItem(ModItems.STEEL_INGOT), FabricRecipeProvider.conditionsFromItem(ModItems.STEEL_INGOT))
                .offerTo(recipeExporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.STEEL_BOOTS, 1)
                .pattern("# #")
                .pattern("# #")
                .input('#', ModItems.STEEL_INGOT)
                .criterion(FabricRecipeProvider.hasItem(ModItems.STEEL_INGOT), FabricRecipeProvider.conditionsFromItem(ModItems.STEEL_INGOT))
                .offerTo(recipeExporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.CLOTH_HELMET, 1)
                .pattern("###")
                .pattern("# #")
                .input('#', ItemTags.WOOL)
                .criterion("has_wool", FabricRecipeProvider.conditionsFromTag(ItemTags.WOOL))
                .offerTo(recipeExporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.CLOTH_CHESTPLATE, 1)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .input('#', ItemTags.WOOL)
                .criterion("has_wool", FabricRecipeProvider.conditionsFromTag(ItemTags.WOOL))
                .offerTo(recipeExporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.CLOTH_LEGGINGS, 1)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .input('#', ItemTags.WOOL)
                .criterion("has_wool", FabricRecipeProvider.conditionsFromTag(ItemTags.WOOL))
                .offerTo(recipeExporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.CLOTH_BOOTS, 1)
                .pattern("# #")
                .pattern("# #")
                .input('#', ItemTags.WOOL)
                .criterion("has_wool", FabricRecipeProvider.conditionsFromTag(ItemTags.WOOL))
                .offerTo(recipeExporter);
    }
}
