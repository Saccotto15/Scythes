package net.thevortex8196.scythes.recipe;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.thevortex8196.scythes.Scythes;

public class ModRecipes {
    public static final RecipeSerializer<ScytheTableRecipe> SCYTHE_TABLE_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(Scythes.MOD_ID, "scythe_table"),
            new ScytheTableRecipe.Serializer());
    public static final RecipeType<ScytheTableRecipe> SCYTHE_TABLE_TYPE = Registry.register(
            Registries.RECIPE_TYPE, Identifier.of(Scythes.MOD_ID, "scythe_table"), new RecipeType<ScytheTableRecipe>() {
                @Override
                public String toString() {
                    return "scythe_table";
                }
            });

    public static void register() {
        
    }
}