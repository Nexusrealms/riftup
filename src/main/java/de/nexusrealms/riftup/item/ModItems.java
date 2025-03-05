package de.nexusrealms.riftup.item;

import de.nexusrealms.riftup.Riftup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;

public class ModItems {
    //TIN
    public static final Item TIN_INGOT = create("tin_ingot", new Item(new Item.Settings()), ItemGroups.INGREDIENTS);
    //BRONZE
    public static final Item BRONZE_INGOT = create("bronze_ingot", new Item(new Item.Settings()), ItemGroups.INGREDIENTS);
    //STEEL
    public static final Item STEEL_INGOT = create("steel_ingot", new Item(new Item.Settings()), ItemGroups.INGREDIENTS);
    public static final Item JUNK_INGOT = create("junk_ingot", new Item(new Item.Settings()), ItemGroups.INGREDIENTS);

    public static <T extends Item> T create(String name, T item, RegistryKey<ItemGroup> itemGroup) {
        Registry.register(Registries.ITEM, Riftup.id(name), item);
        ItemGroupEvents.modifyEntriesEvent(itemGroup).register(fabricItemGroupEntries -> fabricItemGroupEntries.add(item));
        return item;
    }
    public static void init(){}
}
