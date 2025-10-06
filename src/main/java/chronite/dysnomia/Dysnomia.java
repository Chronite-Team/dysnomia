package chronite.dysnomia;

import chronite.dysnomia.component.ModDataComponentTypes;
import chronite.dysnomia.item.ModItemGroups;
import chronite.dysnomia.item.ModItems;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import eu.pb4.polymer.resourcepack.impl.client.rendering.PolymerResourcePack;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Dysnomia implements ModInitializer {
	public static final String MOD_ID = "dysnomia";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		Dysnomia.LOGGER.info("Hello Fabric world!");
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModDataComponentTypes.registerDataComponentTypes();

		Dysnomia.this.generatePolymerResourcePack();
	}

	public void generatePolymerResourcePack() {
		Dysnomia.LOGGER.info("Generating Resource Pack for " + Dysnomia.MOD_ID
		+ " with UUID " + PolymerResourcePackUtils.getMainUuid().toString());
		PolymerResourcePackUtils.addModAssets(Dysnomia.MOD_ID);
		PolymerResourcePackUtils.buildMain();
	}
}