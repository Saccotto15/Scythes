package net.thevortex8196.scythes.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.thevortex8196.scythes.Scythes;
import net.thevortex8196.scythes.block.custom.ScytheTable;

public class ModBlocks {

    public static final Block SCYTHE_TABLE = registerBlock("scythe_table",
            new ScytheTable(AbstractBlock.Settings.create().strength(2.5F).sounds(BlockSoundGroup.WOOD).burnable()));

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(Scythes.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Scythes.MOD_ID, name), block);
    }

    public static void  register() {
    }
}
