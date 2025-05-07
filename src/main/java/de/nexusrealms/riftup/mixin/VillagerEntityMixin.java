package de.nexusrealms.riftup.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(VillagerEntity.class)
public class VillagerEntityMixin{
    @ModifyExpressionValue(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/village/TradeOfferList;isEmpty()Z"))
    public boolean preventInteract(boolean original, PlayerEntity player, Hand hand){
        return original || !player.hasStatusEffect(StatusEffects.HERO_OF_THE_VILLAGE);
    }
}
