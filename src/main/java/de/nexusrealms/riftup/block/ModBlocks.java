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
import net.minecraft.sound.BlockSoundGroup;

public class ModBlocks {
    public static final Block STEEL_BLOCK = createWithItem("steel_block", new Block(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)), ItemGroups.BUILDING_BLOCKS);
    public static final Block NETHER_DIAMOND_ORE = createWithItem("nether_diamond_ore", new Block(AbstractBlock.Settings.copy(Blocks.DEEPSLATE_DIAMOND_ORE).sounds(BlockSoundGroup.NETHER_GOLD_ORE)), ItemGroups.NATURAL);
    public static <T extends Block> T createWithItem(String name, T block, RegistryKey<ItemGroup> itemGroup) {
        Registry.register(Registries.BLOCK, Riftup.id(name), block);
        Registry.register(Registries.ITEM, Riftup.id(name), new BlockItem(block, new Item.Settings()));
        ItemGroupEvents.modifyEntriesEvent(itemGroup).register(fabricItemGroupEntries -> fabricItemGroupEntries.add(block));
        return block;
    }
    public static void init(){}
}
