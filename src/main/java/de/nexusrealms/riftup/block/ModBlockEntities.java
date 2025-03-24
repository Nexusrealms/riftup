package de.nexusrealms.riftup.block;

import de.nexusrealms.riftup.Riftup;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

public class ModBlockEntities {
    private static <T extends BlockEntity> BlockEntityType<T> create(String name, BlockEntityType<T> type){
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Riftup.id(name), type);
    }
    public static void init(){}
}
