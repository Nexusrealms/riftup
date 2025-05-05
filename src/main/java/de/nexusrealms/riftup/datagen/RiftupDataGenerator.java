package de.nexusrealms.riftup.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class RiftupDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ItemTagProvider::new);
		pack.addProvider(BlockTagProvider::new);
		pack.addProvider(BlockLootTableProvider::new);
		pack.addProvider(LangProvider::new);
		pack.addProvider(RecipeProvider::new);
		pack.addProvider(ModelProvider::new);
		pack.addProvider(AdvancementProvider::new);
	}
}
