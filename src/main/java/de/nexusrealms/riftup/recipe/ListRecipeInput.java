package de.nexusrealms.riftup.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

import java.util.List;

public record ListRecipeInput(List<ItemStack> list) implements RecipeInput {
    @Override
    public ItemStack getStackInSlot(int slot) {
        return list.get(slot);
    }

    @Override
    public int getSize() {
        return list.size();
    }
}
