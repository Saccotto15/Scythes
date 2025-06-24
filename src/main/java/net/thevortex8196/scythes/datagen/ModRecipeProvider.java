package net.thevortex8196.scythes.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.thevortex8196.scythes.block.ModBlocks;
import net.thevortex8196.scythes.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SCYTHE_TABLE)
                .pattern("#*#")
                .pattern("???")
                .pattern("???")
                .input('#', Items.IRON_INGOT)
                .input('?', ItemTags.PLANKS)
                .input('*', Items.LAVA_BUCKET)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CELESTIUM_INGOT)
                .pattern("???")
                .pattern("?*?")
                .pattern("???")
                .input('?', Items.QUARTZ)
                .input('*', Items.NETHERITE_INGOT)
                .criterion(hasItem(Items.NETHERITE_INGOT), conditionsFromItem(Items.NETHERITE_INGOT))
                .offerTo(recipeExporter);

        StickRecipeBuilder(ModItems.CELESTIUM_INGOT, ModItems.CELESTIUM_ROD, recipeExporter);
        StickRecipeBuilder(Items.NETHERITE_INGOT, ModItems.NETHERITE_ROD, recipeExporter);
    }

    void StickRecipeBuilder(Item material, Item result, RecipeExporter recipeExporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, result)
                .pattern("*")
                .pattern("*")
                .input('*', material)
                .criterion(hasItem(material), conditionsFromItem(material))
                .offerTo(recipeExporter);
    }
}
