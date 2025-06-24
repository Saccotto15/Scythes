package net.thevortex8196.scythes.item;

import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.thevortex8196.scythes.Scythes;
import net.thevortex8196.scythes.item.custom.HeavenScytheItem;
import net.thevortex8196.scythes.item.custom.ScytheItem;

public class ModItems {

    public static final Item EYE_OF_THE_END = registerItem("eye_of_the_end", new Item(new Item.Settings()));
    public static final Item CELESTIUM_INGOT = registerItem("celestium_ingot", new Item(new Item.Settings()));
    public static final Item CELESTIUM_ROD = registerItem("celestium_rod", new Item(new Item.Settings()));
    public static final Item HEAVEN_SCYTHE = registerItem("heaven_scythe", new HeavenScytheItem(ModToolMaterials.HEAVEN, new Item.Settings()));
    public static final Item NETHERITE_ROD = registerItem("netherite_rod", new Item(new Item.Settings()));
    public static final Item HELL_SCYTHE = registerItem("hell_scythe", new HeavenScytheItem(ModToolMaterials.HELL, new Item.Settings()));
    public static final Item WOODEN_SCYTHE = registerItem("wooden_scythe", new ScytheItem(ToolMaterials.WOOD, new Item.Settings()));
    public static final Item STONE_SCYTHE = registerItem("stone_scythe", new ScytheItem(ToolMaterials.STONE, new Item.Settings()));
    public static final Item IRON_SCYTHE = registerItem("iron_scythe", new ScytheItem(ToolMaterials.IRON, new Item.Settings()));
    public static final Item GOLDEN_SCYTHE = registerItem("golden_scythe", new ScytheItem(ToolMaterials.GOLD, new Item.Settings()));
    public static final Item DIAMOND_SCYTHE = registerItem("diamond_scythe", new ScytheItem(ToolMaterials.DIAMOND, new Item.Settings()));
    public static final Item NETHERITE_SCYTHE = registerItem("netherite_scythe", new ScytheItem(ToolMaterials.NETHERITE, new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Scythes.MOD_ID, name), item);
    }

    public static void register() {
    }
}
