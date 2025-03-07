package de.nexusrealms.riftup.compat.emi;

import de.nexusrealms.riftup.Riftup;
import de.nexusrealms.riftup.block.ModBlocks;
import de.nexusrealms.riftup.recipe.AlloymakingRecipe;
import de.nexusrealms.riftup.recipe.ModRecipes;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.util.Identifier;

public class RiftupEmiPlugin implements EmiPlugin {
    public static final EmiStack ALLOYMAKING_WORKSTATION = EmiStack.of(ModBlocks.ALLOYMAKING_FURNACE);
    public static final EmiRecipeCategory ALLOYMAKING_CATEGORY
            = new EmiRecipeCategory(Riftup.id("alloymaking"), ALLOYMAKING_WORKSTATION, ALLOYMAKING_WORKSTATION);

    @Override
    public void register(EmiRegistry registry) {
        registry.addCategory(ALLOYMAKING_CATEGORY);

        // Add all the workstations your category uses
        registry.addWorkstation(ALLOYMAKING_CATEGORY, ALLOYMAKING_WORKSTATION);

        RecipeManager manager = registry.getRecipeManager();

        // Use vanilla's concept of your recipes and pass them to your EmiRecipe representation
        for (RecipeEntry<AlloymakingRecipe> recipe : manager.listAllOfType(ModRecipes.ALLOYMAKING_RECIPE_TYPE)) {
            registry.addRecipe(new AlloymakingEmiRecipe(recipe));
        }
    }
}
