package net.thevortex8196.scythes.block.entities;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.thevortex8196.scythes.Scythes;
import net.thevortex8196.scythes.block.ModBlocks;
import net.thevortex8196.scythes.block.entities.custom.ScytheTableBlockEntity;

public class ModBlockEntities {

    public static final BlockEntityType<ScytheTableBlockEntity> SCYTHE_TABLE_BE =
            Registry.register(
                    Registries.BLOCK_ENTITY_TYPE,
                    Identifier.of(Scythes.MOD_ID, "scythe_table"),
                    BlockEntityType.Builder.create(ScytheTableBlockEntity::new, ModBlocks.SCYTHE_TABLE).build(null)
            );



    public static void register() {
    }
}