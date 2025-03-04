package de.nexusrealms.riftup.block;

import de.nexusrealms.riftup.Riftup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;

public class ModBlocks {
    public static final Block TIN_BLOCK = createWithItem("tin_block", new Block(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)), ItemGroups.BUILDING_BLOCKS);
    public static final Block BRONZE_BLOCK = createWithItem("bronze_block", new Block(AbstractBlock.Settings.copy(Blocks.COPPER_BLOCK)), ItemGroups.BUILDING_BLOCKS);
    public static final Block STEEL_BLOCK = createWithItem("steel_block", new Block(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)), ItemGroups.BUILDING_BLOCKS);
    //TODO add this i dont have the time to do it in 30 mins
    public static final Block ALLOYMAKING_FURNACE = createWithItem("alloymaking_furnace", new AlloymakingFurnaceBlock(AbstractBlock.Settings.copy(Blocks.FURNACE)), ItemGroups.FUNCTIONAL);

    public static <T extends Block> T createWithItem(String name, T block, RegistryKey<ItemGroup> itemGroup) {
        Registry.register(Registries.BLOCK, Riftup.id(name), block);
        Registry.register(Registries.ITEM, Riftup.id(name), new BlockItem(block, new Item.Settings()));
        ItemGroupEvents.modifyEntriesEvent(itemGroup).register(fabricItemGroupEntries -> fabricItemGroupEntries.add(block));
        return block;
    }
    public static void init(){}
}
