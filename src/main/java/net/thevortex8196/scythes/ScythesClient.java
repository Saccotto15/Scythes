package net.thevortex8196.scythes;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.thevortex8196.scythes.screen.ModScreenHandlers;
import net.thevortex8196.scythes.screen.ScytheTableScreen;

public class ScythesClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		HandledScreens.register(ModScreenHandlers.SCYTHE_TABLE_SCREEN_HANDLER, ScytheTableScreen::new);
	}
}