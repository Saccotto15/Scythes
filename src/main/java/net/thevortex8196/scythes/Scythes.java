package net.thevortex8196.scythes;

import net.fabricmc.api.ModInitializer;

import net.thevortex8196.scythes.block.ModBlocks;
import net.thevortex8196.scythes.block.entities.ModBlockEntities;
import net.thevortex8196.scythes.datagen.ModLootTableModifier;
import net.thevortex8196.scythes.item.ModCreativeTab;
import net.thevortex8196.scythes.item.ModItems;
import net.thevortex8196.scythes.recipe.ModRecipes;
import net.thevortex8196.scythes.screen.ModScreenHandlers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Scythes implements ModInitializer {
	public static final String MOD_ID = "scythes";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initialising Runestones by TheVortex8196.");

		ModCreativeTab.register();

		ModItems.register();
		ModBlocks.register();

		//ModDataComponents.register();

		ModLootTableModifier.register();

		ModBlockEntities.register();
		ModScreenHandlers.register();

		ModRecipes.register();
	}
}