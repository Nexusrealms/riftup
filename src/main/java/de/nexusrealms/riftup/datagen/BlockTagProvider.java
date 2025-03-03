package de.nexusrealms.riftup.datagen;

import de.nexusrealms.riftup.block.ModBlockTags;
import de.nexusrealms.riftup.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class BlockTagProvider extends FabricTagProvider<Block> {
    public BlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.BLOCK, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        configureMineableTags(wrapperLookup);
        configureToolLevelTags(wrapperLookup);
    }
    private void configureMineableTags(RegistryWrapper.WrapperLookup wrapperLookup){
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.BRONZE_BLOCK, ModBlocks.STEEL_BLOCK, ModBlocks.TIN_BLOCK);
    }
    private void configureToolLevelTags(RegistryWrapper.WrapperLookup wrapperLookup){
        getOrCreateTagBuilder(ModBlockTags.INCORRECT_FOR_BRONZE_TOOL)
                .addOptionalTag(BlockTags.INCORRECT_FOR_STONE_TOOL);
        getOrCreateTagBuilder(ModBlockTags.INCORRECT_FOR_STEEL_TOOL)
                .addOptionalTag(BlockTags.INCORRECT_FOR_IRON_TOOL);

        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.TIN_BLOCK, ModBlocks.BRONZE_BLOCK);
        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.STEEL_BLOCK);
    }
}
