package net.thevortex8196.scythes.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record ScytheTableRecipeInput(ItemStack eye_stack, ItemStack connector_stack, ItemStack handle_stack, ItemStack blade_stack) implements RecipeInput {

    @Override
    public ItemStack getStackInSlot(int slot) {
        return switch (slot) {
            case 0 -> eye_stack;
            case 1 -> blade_stack;
            case 2 -> handle_stack;
            case 3 -> connector_stack;
            default -> null;
        };
    }

    @Override
    public int getSize() {
        return 4;
    }
}
