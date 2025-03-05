package de.nexusrealms.riftup.recipe;

import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.*;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public record AlloymakingRecipe(List<Ingredient> ingredients, ItemStack result) implements Recipe<ListRecipeInput> {

    @Override
    public boolean matches(ListRecipeInput input, World world) {
        if (input.getSize() != this.ingredients.size()) {
            return false;
        } else {
            for(Ingredient ingredient : ingredients){
                if(!removeOneIf(input.list(), ingredient)) return false;
            }
            return true;
        }
    }

    @Override
    public ItemStack craft(ListRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return result.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return result;
    }

    @Override
    public RecipeSerializer<AlloymakingRecipe> getSerializer() {
        return ModRecipes.ALLOYMAKING_RECIPE_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.ALLOYMAKING_RECIPE_TYPE;
    }
    public static class Serializer implements RecipeSerializer<AlloymakingRecipe> {
        private final MapCodec<AlloymakingRecipe> codec = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Ingredient.DISALLOW_EMPTY_CODEC.listOf(1, 16).fieldOf("ingredients").forGetter(AlloymakingRecipe::ingredients),
                ItemStack.VALIDATED_CODEC.fieldOf("result").forGetter(AlloymakingRecipe::result)
        ).apply(instance, AlloymakingRecipe::new));
        private final PacketCodec<RegistryByteBuf, AlloymakingRecipe> packetCodec = PacketCodec.tuple(
                Ingredient.PACKET_CODEC.collect(PacketCodecs.toList()), AlloymakingRecipe::ingredients,
                ItemStack.PACKET_CODEC, AlloymakingRecipe::result,
                AlloymakingRecipe::new);
        @Override
        public MapCodec<AlloymakingRecipe> codec() {
            return codec;
        }

        @Override
        public PacketCodec<RegistryByteBuf, AlloymakingRecipe> packetCodec() {
            return packetCodec;
        }
    }

    public static <T> boolean removeOneIf(Collection<T> collection, Predicate<T> predicate){
        final Iterator<T> each = collection.iterator();
        while (each.hasNext()) {
            if (predicate.test(each.next())) {
                each.remove();
                return true;
            }
        }
        return false;
    }
}
