package net.thevortex8196.scythes.compat;

import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.thevortex8196.scythes.block.ModBlocks;
import net.thevortex8196.scythes.recipe.ModRecipes;
import net.thevortex8196.scythes.recipe.ScytheTableRecipe;
import net.thevortex8196.scythes.screen.ScytheTableScreen;

public class ScythesREIClient implements REIClientPlugin {
    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new ScytheTableCategory());

        registry.addWorkstations(ScytheTableCategory.SCYTHE_TABLE, EntryStacks.of(ModBlocks.SCYTHE_TABLE));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(ScytheTableRecipe.class, ModRecipes.SCYTHE_TABLE_TYPE,
                ScytheTableDisplay::new);
    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerClickArea(screen -> new Rectangle(((screen.width - 176) / 2) + 90,
                        ((screen.height - 166) / 2) + 35, 22, 15), ScytheTableScreen.class,
                ScytheTableCategory.SCYTHE_TABLE);
    }
}
