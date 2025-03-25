package de.nexusrealms.riftup.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import de.nexusrealms.riftup.recipe.FuelHolder;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.SingleStackRecipeInput;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractFurnaceBlockEntity.class)
public abstract class AbstractFurnaceBlockEntityMixin implements FuelHolder {
    @Shadow protected abstract boolean isBurning();

    @Unique
    private ItemStack lastUsedFuel = ItemStack.EMPTY;
    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"))
    private static void saveLastUsedFuel(World world, BlockPos pos, BlockState state, AbstractFurnaceBlockEntity blockEntity, CallbackInfo ci, @Local(ordinal = 0) ItemStack stack){
        ((FuelHolder) blockEntity).riftup$setFuel(stack.copy());
    }
    @ModifyExpressionValue(method = "tick", at = @At(value = "NEW", target = "Lnet/minecraft/recipe/input/SingleStackRecipeInput;"))
    private static SingleStackRecipeInput addSecondStackToInput(SingleStackRecipeInput original, World world, BlockPos pos, BlockState state, AbstractFurnaceBlockEntity blockEntity){
        ((FuelHolder) (Object) original).riftup$setFuel(((FuelHolder) blockEntity).riftup$getFuel());
        return original;
    }
    @ModifyExpressionValue(method = "getCookTime", at = @At(value = "NEW", target = "Lnet/minecraft/recipe/input/SingleStackRecipeInput;"))
    private static SingleStackRecipeInput alsoAddSecondStackToInput(SingleStackRecipeInput original, World world, AbstractFurnaceBlockEntity furnace){
        ((FuelHolder) (Object) original).riftup$setFuel(((FuelHolder) furnace).riftup$getFuel());
        return original;
    }
    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/collection/DefaultedList;get(I)Ljava/lang/Object;", ordinal = 0))
    private static void clearSavedFuel(World world, BlockPos pos, BlockState state, AbstractFurnaceBlockEntity blockEntity, CallbackInfo ci){
        if(!((IsBurningInvoker) blockEntity).callIsBurning()){
            ((FuelHolder) blockEntity).riftup$setFuel(ItemStack.EMPTY);
        }
    }
    @Override
    public ItemStack riftup$getFuel() {
        return lastUsedFuel;
    }

    @Override
    public void riftup$setFuel(ItemStack second) {
        lastUsedFuel = second;

    }
}
