package de.nexusrealms.riftup.datagen;

import de.nexusrealms.riftup.Riftup;
import de.nexusrealms.riftup.block.ModBlocks;
import de.nexusrealms.riftup.item.ModItemTags;
import de.nexusrealms.riftup.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
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
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(Items.COAL), RecipeCategory.MISC, ModItems.COKE, 1, 120)
                .criterion(FabricRecipeProvider.hasItem(Items.COAL), FabricRecipeProvider.conditionsFromItem(Items.COAL)).offerTo(recipeExporter, Riftup.id("coke_from_smelting"));
        CookingRecipeJsonBuilder.createBlasting(Ingredient.ofItems(Items.IRON_INGOT), RecipeCategory.MISC, ModItems.STEEL_INGOT, 1, 120)
                .criterion(FabricRecipeProvider.hasItem(ModItems.COKE), FabricRecipeProvider.conditionsFromTag(ModItemTags.COKE)).offerTo(recipeExporter, Riftup.id("steel_ingot_from_blasting"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.STEEL_SWORD, 1)
                .pattern("#")
                .pattern("#")
                .pattern(".")
                .input('#', ModItems.STEEL_INGOT)
                .input('.', Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(ModItems.STEEL_INGOT), FabricRecipeProvider.conditionsFromItem(ModItems.STEEL_INGOT))
                .offerTo(recipeExporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.STEEL_PICKAXE, 1)
                .pattern("###")
                .pattern(" . ")
                .pattern(" . ")
                .input('#', ModItems.STEEL_INGOT)
                .input('.', Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(ModItems.STEEL_INGOT), FabricRecipeProvider.conditionsFromItem(ModItems.STEEL_INGOT))
                .offerTo(recipeExporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.STEEL_AXE, 1)
                .pattern("##")
                .pattern("#.")
                .pattern(" .")
                .input('#', ModItems.STEEL_INGOT)
                .input('.', Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(ModItems.STEEL_INGOT), FabricRecipeProvider.conditionsFromItem(ModItems.STEEL_INGOT))
                .offerTo(recipeExporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.STEEL_SHOVEL, 1)
                .pattern("#")
                .pattern(".")
                .pattern(".")
                .input('#', ModItems.STEEL_INGOT)
                .input('.', Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(ModItems.STEEL_INGOT), FabricRecipeProvider.conditionsFromItem(ModItems.STEEL_INGOT))
                .offerTo(recipeExporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.STEEL_HOE, 1)
                .pattern("##")
                .pattern(" .")
                .pattern(" .")
                .input('#', ModItems.STEEL_INGOT)
                .input('.', Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(ModItems.STEEL_INGOT), FabricRecipeProvider.conditionsFromItem(ModItems.STEEL_INGOT))
                .offerTo(recipeExporter);
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
