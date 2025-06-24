package net.thevortex8196.scythes.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.thevortex8196.scythes.Scythes;
import net.thevortex8196.scythes.block.ModBlocks;
import net.thevortex8196.scythes.item.ModItems;
import net.thevortex8196.scythes.util.ModModels;

import static net.minecraft.data.client.BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        registerRotatable(ModBlocks.SCYTHE_TABLE, blockStateModelGenerator);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.EYE_OF_THE_END, Models.GENERATED);
        itemModelGenerator.register(ModItems.CELESTIUM_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.CELESTIUM_ROD, Models.GENERATED);
        registerScythe(ModItems.HEAVEN_SCYTHE, itemModelGenerator);
        itemModelGenerator.register(ModItems.NETHERITE_ROD, Models.GENERATED);
        registerScythe(ModItems.HELL_SCYTHE, itemModelGenerator);
        registerScythe(ModItems.WOODEN_SCYTHE, itemModelGenerator);
        registerScythe(ModItems.STONE_SCYTHE, itemModelGenerator);
        registerScythe(ModItems.IRON_SCYTHE, itemModelGenerator);
        registerScythe(ModItems.GOLDEN_SCYTHE, itemModelGenerator);
        registerScythe(ModItems.DIAMOND_SCYTHE, itemModelGenerator);
        registerScythe(ModItems.NETHERITE_SCYTHE, itemModelGenerator);
    }

    public void registerScythe(Item item, ItemModelGenerator itemModelGenerator) {
        Identifier id = ModelIds.getItemModelId(item); // scythes:item/heaven_scythe

        // Upload "generated" model with normal texture
        ModModels.FRONT_LIT.upload(id, TextureMap.layer0(id), itemModelGenerator.writer);

        // Upload custom model with _3d texture
        Identifier id3d = id.withPath(path -> path + "_3d");

        TextureMap textureMap = new TextureMap()
                .put(TextureKey.LAYER0, Identifier.of(id.getNamespace(), id.getPath() + "_3d"));

        Scythes.LOGGER.info(textureMap.toString());

        ModModels.SCYTHE.upload(id3d, textureMap, itemModelGenerator.writer);
    }

    public final void registerRotatable(Block block, BlockStateModelGenerator blockStateModelGenerator) {
        Identifier identifier = TexturedModel.CUBE_BOTTOM_TOP.upload(block, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector
                .accept(
                        VariantsBlockStateSupplier.create(block, BlockStateVariant.create().put(VariantSettings.MODEL, identifier))
                                .coordinate(createNorthDefaultHorizontalRotationStates())
                );
    }
}
