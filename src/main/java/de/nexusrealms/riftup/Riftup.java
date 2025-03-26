package de.nexusrealms.riftup;

import de.nexusrealms.riftup.block.ModBlockEntities;
import de.nexusrealms.riftup.block.ModBlocks;
import de.nexusrealms.riftup.item.ModArmorMaterials;
import de.nexusrealms.riftup.item.ModItems;
import de.nexusrealms.riftup.recipe.ModRecipes;
import de.nexusrealms.riftup.screen.ModScreenHandlers;
import de.nexusrealms.riftup.world.ModWorldGen;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Riftup implements ModInitializer {
	public static final String MOD_ID = "riftup";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier id(String name) {
		return Identifier.of(MOD_ID, name);
	}
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ModRecipes.init();
		ModBlockEntities.init();
		ModScreenHandlers.init();
		ModArmorMaterials.init();
		ModItems.init();
		ModBlocks.init();
		ModWorldGen.register();
		LOGGER.info("Hello Fabric world!");
	}
}