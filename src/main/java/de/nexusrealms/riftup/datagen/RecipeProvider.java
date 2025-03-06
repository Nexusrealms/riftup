package de.nexusrealms.riftup.datagen;

import de.nexusrealms.riftup.Riftup;
import de.nexusrealms.riftup.block.ModBlocks;
import de.nexusrealms.riftup.item.ModItemTags;
import de.nexusrealms.riftup.item.ModItems;
import de.nexusrealms.riftup.recipe.AlloymakingRecipe;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ALLOYMAKING_FURNACE, 1)
                .pattern("#.#")
                .pattern(". .")
                .pattern("#.#")
                .input('#', ItemTags.STONE_CRAFTING_MATERIALS)
                .input('.', ModItemTags.CRAFTING_BRICK_BLOCKS)
                .criterion("has_crafting_brick_block", FabricRecipeProvider.conditionsFromTag(ModItemTags.CRAFTING_BRICK_BLOCKS))
                .offerTo(recipeExporter);
        createAlloyRecipe(recipeExporter,
                ModItems.BRONZE_INGOT, 2,
                "has_copper", FabricRecipeProvider.conditionsFromItem(Items.COPPER_INGOT),
                Ingredient.ofItems(Items.COPPER_INGOT), Ingredient.ofItems(ModItems.TIN_INGOT)
                );
    }
    private void createAlloyRecipe(RecipeExporter exporter, ItemConvertible output, int count, String advancementName, AdvancementCriterion<?> criterion, Ingredient... ingredients){
        Identifier recipeId = CraftingRecipeJsonBuilder.getItemId(output).withPrefixedPath("alloymaking_");
        Advancement.Builder builder = exporter.getAdvancementBuilder().criterion("has_the_recipe", RecipeUnlockedCriterion.create(recipeId)).rewards(AdvancementRewards.Builder.recipe(recipeId)).criteriaMerger(AdvancementRequirements.CriterionMerger.OR);
        Map<String, AdvancementCriterion<?>> criteria = Util.make(new LinkedHashMap<>(), map -> map.put(advancementName, criterion));
        criteria.forEach(builder::criterion);
        AlloymakingRecipe recipe = new AlloymakingRecipe(List.of(ingredients), new ItemStack(output, count));
        exporter.accept(recipeId, recipe, builder.build(recipeId.withPrefixedPath("recipes/" + "alloymaking" + "/")));
    }
}
