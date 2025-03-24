package de.nexusrealms.riftup.block;

import de.nexusrealms.riftup.Riftup;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class ModBlockTags {
    public static final TagKey<Block> INCORRECT_FOR_STEEL_TOOL = create("incorrect_for_steel_tool");

    private static TagKey<Block> create(String name){
        return TagKey.of(RegistryKeys.BLOCK, Riftup.id(name));
    }
}
