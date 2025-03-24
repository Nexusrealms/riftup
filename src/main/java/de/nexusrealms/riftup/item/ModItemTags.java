package de.nexusrealms.riftup.item;

import de.nexusrealms.riftup.Riftup;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class ModItemTags {


    private static TagKey<Item> create(String name){
        return TagKey.of(RegistryKeys.ITEM, Riftup.id(name));
    }
}
