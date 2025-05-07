package de.nexusrealms.riftup.mixin;

import de.nexusrealms.riftup.recipe.FuelHolder;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.SingleStackRecipeInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(SingleStackRecipeInput.class)
public abstract class SingleStackRecipeInputMixin implements FuelHolder {
    @Unique
    private ItemStack secondStack = ItemStack.EMPTY;
    @Override
    public ItemStack riftup$getFuel() {
        return secondStack;
    }

    @Override
    public void riftup$setFuel(ItemStack second) {
        secondStack = second;
    }
}
