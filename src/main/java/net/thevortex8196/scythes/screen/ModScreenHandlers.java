package net.thevortex8196.scythes.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.thevortex8196.scythes.Scythes;

public class ModScreenHandlers {
    public static final ScreenHandlerType<ScytheTableScreenHandler> SCYTHE_TABLE_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(Scythes.MOD_ID, "scythe_table_screen_handler"),
                    new ExtendedScreenHandlerType<>(ScytheTableScreenHandler::new, BlockPos.PACKET_CODEC));


    public static void register() {
    }
}