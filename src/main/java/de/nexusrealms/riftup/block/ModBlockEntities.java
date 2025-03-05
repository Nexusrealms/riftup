package de.nexusrealms.riftup.block;

import de.nexusrealms.riftup.Riftup;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

public class ModBlockEntities {
    public static final BlockEntityType<AlloymakingFurnaceBlockEntity> ALLOYMAKING_FURNACE = create("alloymaking_furnace", BlockEntityType.Builder.create(AlloymakingFurnaceBlockEntity::new, ModBlocks.ALLOYMAKING_FURNACE).build());
    private static <T extends BlockEntity> BlockEntityType<T> create(String name, BlockEntityType<T> type){
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Riftup.id(name), type);
    }
    public static void init(){}
}
