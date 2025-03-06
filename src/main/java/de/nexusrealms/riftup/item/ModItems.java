package de.nexusrealms.riftup.item;

import de.nexusrealms.riftup.Riftup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
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
    //CLOTH ARMOR
    public static final ArmorItem CLOTH_HELMET = create("cloth_helmet", new ArmorItem(ModArmorMaterials.CLOTH, ArmorItem.Type.HELMET, new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(4))), ItemGroups.COMBAT);
    public static final ArmorItem CLOTH_CHESTPLATE = create("cloth_chestplate", new ArmorItem(ModArmorMaterials.CLOTH, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(4))), ItemGroups.COMBAT);
    public static final ArmorItem CLOTH_LEGGINGS = create("cloth_leggings", new ArmorItem(ModArmorMaterials.CLOTH, ArmorItem.Type.LEGGINGS, new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(4))), ItemGroups.COMBAT);
    public static final ArmorItem CLOTH_BOOTS = create("cloth_boots", new ArmorItem(ModArmorMaterials.CLOTH, ArmorItem.Type.BOOTS, new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(4))), ItemGroups.COMBAT);

    public static <T extends Item> T create(String name, T item, RegistryKey<ItemGroup> itemGroup) {
        Registry.register(Registries.ITEM, Riftup.id(name), item);
        ItemGroupEvents.modifyEntriesEvent(itemGroup).register(fabricItemGroupEntries -> fabricItemGroupEntries.add(item));
        return item;
    }
    public static void init(){}
}
