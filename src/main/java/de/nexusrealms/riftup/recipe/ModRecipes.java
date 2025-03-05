package de.nexusrealms.riftup.recipe;

import com.mojang.datafixers.TypeRewriteRule;
import de.nexusrealms.riftup.Riftup;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModRecipes {
    public static final RecipeType<AlloymakingRecipe> ALLOYMAKING_RECIPE_TYPE = createRecipeType("alloymaking");
    public static final RecipeSerializer<AlloymakingRecipe> ALLOYMAKING_RECIPE_SERIALIZER = createRecipeSerializer("alloymaking", new AlloymakingRecipe.Serializer());
    private static <T extends Recipe<?>> RecipeType<T> createRecipeType(String name){
        return Registry.register(Registries.RECIPE_TYPE, Riftup.id(name), new RecipeType<T>() {
            @Override
            public String toString() {
                return name;
            }
        });
    }
    private static <S extends RecipeSerializer<T>, T extends Recipe<?>> S createRecipeSerializer(String name, S serializer) {
        return Registry.register(Registries.RECIPE_SERIALIZER, Riftup.id(name), serializer);
    }

    public static void init(){}
}
