package de.nexusrealms.riftup.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import de.nexusrealms.riftup.item.ModItemTags;
import de.nexusrealms.riftup.recipe.FuelHolder;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.input.SingleStackRecipeInput;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractCookingRecipe.class)
public abstract class AbstractCookingRecipeMixin {
    @Shadow @Final protected ItemStack result;

    @ModifyReturnValue(method = "matches(Lnet/minecraft/recipe/input/SingleStackRecipeInput;Lnet/minecraft/world/World;)Z", at = @At("TAIL"))
    public boolean checkForCoke(boolean original, SingleStackRecipeInput input, World world){
        //noinspection ConstantValue
        if(result.isIn(ModItemTags.NEED_COKE_TO_SMELT) && (Object) input instanceof FuelHolder doubleStackRecipeInput){
            return original && (doubleStackRecipeInput.riftup$getFuel().isIn(ModItemTags.COKE) || doubleStackRecipeInput.riftup$getFuel().isEmpty()|| doubleStackRecipeInput.riftup$getFuel() == null);
        }
        return original;
    }
}