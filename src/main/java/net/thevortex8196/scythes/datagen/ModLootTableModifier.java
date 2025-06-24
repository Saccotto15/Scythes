package net.thevortex8196.scythes.datagen;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModLootTableModifier {

    private static final RegistryKey<LootTable> SHULKER_KEY = EntityType.PIGLIN_BRUTE.getLootTableId();


    public static void register() {
        LootTableEvents.MODIFY.register((registryKey, builder, lootTableSource, wrapperLookup) -> {

        });
    }
}
