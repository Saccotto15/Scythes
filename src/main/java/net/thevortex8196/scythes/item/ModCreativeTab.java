package net.thevortex8196.scythes.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.thevortex8196.scythes.Scythes;
import net.thevortex8196.scythes.block.ModBlocks;

public class ModCreativeTab {
    public static final ItemGroup SCYTHES = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Scythes.MOD_ID, "scythes"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModItems.EYE_OF_THE_END))
                    .displayName(Text.translatable("creativetab.scythes"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.EYE_OF_THE_END);
                        entries.add(ModBlocks.SCYTHE_TABLE);
                        entries.add(ModItems.CELESTIUM_INGOT);
                        entries.add(ModItems.CELESTIUM_ROD);
                        entries.add(ModItems.HEAVEN_SCYTHE);
                        entries.add(Items.NETHERITE_INGOT);
                        entries.add(ModItems.NETHERITE_ROD);
                        entries.add(ModItems.HELL_SCYTHE);
                        entries.add(ModItems.WOODEN_SCYTHE);
                        entries.add(ModItems.STONE_SCYTHE);
                        entries.add(ModItems.IRON_SCYTHE);
                        entries.add(ModItems.GOLDEN_SCYTHE);
                        entries.add(ModItems.DIAMOND_SCYTHE);
                        entries.add(ModItems.NETHERITE_SCYTHE);
                    }).build());

    public static void register() {
    }

}
