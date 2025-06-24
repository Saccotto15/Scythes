package net.thevortex8196.scythes.compat;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.recipe.RecipeEntry;
import net.thevortex8196.scythes.recipe.ScytheTableRecipe;

import java.util.List;

public class ScytheTableDisplay extends BasicDisplay {
    public ScytheTableDisplay(RecipeEntry<ScytheTableRecipe> recipe) {
        super(recipe.value().getIngredients().stream()
                        .map(EntryIngredients::ofIngredient)
                        .toList(),
                List.of(EntryIngredients.of(recipe.value().getResult(null))));
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return ScytheTableCategory.SCYTHE_TABLE;
    }
}