package de.nexusrealms.riftup.item;

import de.nexusrealms.riftup.Riftup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;

public class ModItems {

    public static final Item STEEL_INGOT = create("steel_ingot", new Item(new Item.Settings()), ItemGroups.INGREDIENTS);
    public static final Item COKE = create("coke", new Item(new Item.Settings()), ItemGroups.INGREDIENTS);
    //CLOTH ARMOR
    public static final ArmorItem CLOTH_HELMET = create("cloth_helmet", new ArmorItem(ModArmorMaterials.CLOTH, ArmorItem.Type.HELMET, new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(4))), ItemGroups.COMBAT);
    public static final ArmorItem CLOTH_CHESTPLATE = create("cloth_chestplate", new ArmorItem(ModArmorMaterials.CLOTH, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(4))), ItemGroups.COMBAT);
    public static final ArmorItem CLOTH_LEGGINGS = create("cloth_leggings", new ArmorItem(ModArmorMaterials.CLOTH, ArmorItem.Type.LEGGINGS, new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(4))), ItemGroups.COMBAT);
    public static final ArmorItem CLOTH_BOOTS = create("cloth_boots", new ArmorItem(ModArmorMaterials.CLOTH, ArmorItem.Type.BOOTS, new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(4))), ItemGroups.COMBAT);
    //STEEL ARMOR
    public static final ArmorItem STEEL_HELMET = create("steel_helmet", new ArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.HELMET, new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(4))), ItemGroups.COMBAT);
    public static final ArmorItem STEEL_CHESTPLATE = create("steel_chestplate", new ArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(4))), ItemGroups.COMBAT);
    public static final ArmorItem STEEL_LEGGINGS = create("steel_leggings", new ArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.LEGGINGS, new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(4))), ItemGroups.COMBAT);
    public static final ArmorItem STEEL_BOOTS = create("steel_boots", new ArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.BOOTS, new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(4))), ItemGroups.COMBAT);

    public static final SwordItem STEEL_SWORD = create("steel_sword", new SwordItem(ModToolMaterials.STEEL, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.STEEL, 3, -2.4F))), ItemGroups.COMBAT);
    public static final PickaxeItem STEEL_PICKAXE = create("steel_pickaxe", new PickaxeItem(ModToolMaterials.STEEL, new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.STEEL, 1.0F, -2.8F))), ItemGroups.TOOLS);
    public static final AxeItem STEEL_AXE = create("steel_axe", new AxeItem(ModToolMaterials.STEEL, new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.STEEL, 6.0F, -3.1F))), ItemGroups.TOOLS);
    public static final ShovelItem STEEL_SHOVEL = create("steel_shovel", new ShovelItem(ModToolMaterials.STEEL, new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.STEEL, 1.5F, -3.0F))), ItemGroups.TOOLS);
    public static final HoeItem STEEL_HOE = create("steel_hoe", new HoeItem(ModToolMaterials.STEEL, new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ToolMaterials.IRON, -2.0F, -1.0F))), ItemGroups.TOOLS);

    public static <T extends Item> T create(String name, T item, RegistryKey<ItemGroup> itemGroup) {
        Registry.register(Registries.ITEM, Riftup.id(name), item);
        ItemGroupEvents.modifyEntriesEvent(itemGroup).register(fabricItemGroupEntries -> fabricItemGroupEntries.add(item));
        return item;
    }
    public static void init(){
        FuelRegistry.INSTANCE.add(COKE, 2000);
    }
}
