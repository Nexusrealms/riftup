package de.nexusrealms.riftup.compat.emi;

import de.nexusrealms.riftup.recipe.AlloymakingRecipe;
import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AlloymakingEmiRecipe implements EmiRecipe {
    private final Identifier id;
    private final List<EmiIngredient> input;
    private final List<EmiStack> output;
    public AlloymakingEmiRecipe(RecipeEntry<AlloymakingRecipe> recipe) {
        id = recipe.id();
        input = recipe.value().ingredients().stream().map(EmiIngredient::of).toList();
        output = List.of(EmiStack.of(recipe.value().result()));
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return RiftupEmiPlugin.ALLOYMAKING_CATEGORY;
    }

    @Override
    public @Nullable Identifier getId() {
        return id;
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return input;
    }

    @Override
    public List<EmiStack> getOutputs() {
        return output;
    }

    @Override
    public int getDisplayWidth() {
        return 96;
    }

    @Override
    public int getDisplayHeight() {
        return 48;
    }

    @Override
    public void addWidgets(WidgetHolder widgetHolder) {
        widgetHolder.addTexture(EmiTexture.EMPTY_ARROW, 32, 15);

        // Adds an input slot on the left
        widgetHolder.addSlot(input.getFirst(), 0, 7);

        // Adds an output slot on the right
        // Note that output slots need to call `recipeContext` to inform EMI about their recipe context
        // This includes being able to resolve recipe trees, favorite stacks with recipe context, and more
        widgetHolder.addSlot(output.getFirst(), 64, 10).large(true).recipeContext(this);
    }
}
