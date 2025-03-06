package de.nexusrealms.riftup.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import de.nexusrealms.riftup.item.ModArmorMaterials;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ArmorFeatureRenderer.class)
public abstract class ArmorFeatureRendererMixin {
    @WrapOperation(method = "renderArmor", at = @At(value = "INVOKE", target = "Lnet/minecraft/component/type/DyedColorComponent;getColor(Lnet/minecraft/item/ItemStack;I)I"))
    public int getProperDefaultColorForClothArmor(ItemStack stack, int defaultColor, Operation<Integer> original, @Local ArmorItem item){
        return item.getMaterial().matches(ModArmorMaterials.CLOTH) ? original.call(stack, 0xFFAAAAAA) : original.call(stack, defaultColor);
    }
}
