package net.thevortex8196.scythes.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.thevortex8196.scythes.item.ModItems;

public record ScytheTableRecipe(boolean needEye, Ingredient connectorItem, Ingredient handleItem, Ingredient bladeItem, ItemStack output) implements Recipe<ScytheTableRecipeInput> {

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.of();
        list.add(needEye ? Ingredient.ofItems(ModItems.EYE_OF_THE_END) : Ingredient.EMPTY);
        list.add(this.connectorItem);
        list.add(this.handleItem);
        list.add(this.bladeItem);
        return list;
    }


    @Override
    public boolean matches(ScytheTableRecipeInput input, World world) {
        if (world.isClient()) return false;

        if (needEye) {
            if (!ModItems.EYE_OF_THE_END.equals(input.getStackInSlot(0).getItem())) return false;
        } else {
            if (!input.getStackInSlot(0).isEmpty()) return false;
        }

        return connectorItem.test(input.getStackInSlot(1)) &&
                handleItem.test(input.getStackInSlot(2)) &&
                bladeItem.test(input.getStackInSlot(3));
    }




    @Override
    public ItemStack craft(ScytheTableRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.SCYTHE_TABLE_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.SCYTHE_TABLE_TYPE;
    }

    public static class Serializer implements RecipeSerializer<ScytheTableRecipe> {
        public static final MapCodec<ScytheTableRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Codec.BOOL.optionalFieldOf("needs_eye", false).forGetter(ScytheTableRecipe::needEye),
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("connector").forGetter(ScytheTableRecipe::connectorItem),
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("handle").forGetter(ScytheTableRecipe::handleItem),
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("blade").forGetter(ScytheTableRecipe::bladeItem),
                ItemStack.CODEC.fieldOf("result").forGetter(ScytheTableRecipe::output)
        ).apply(inst, ScytheTableRecipe::new));

        public static final PacketCodec<RegistryByteBuf, ScytheTableRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        PacketCodecs.BOOL, ScytheTableRecipe::needEye,
                        Ingredient.PACKET_CODEC, ScytheTableRecipe::connectorItem,
                        Ingredient.PACKET_CODEC, ScytheTableRecipe::handleItem,
                        Ingredient.PACKET_CODEC, ScytheTableRecipe::bladeItem,
                        ItemStack.PACKET_CODEC, ScytheTableRecipe::output,
                        ScytheTableRecipe::new);

        @Override
        public MapCodec<ScytheTableRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, ScytheTableRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}
