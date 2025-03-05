package de.nexusrealms.riftup.screen;

import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.AbstractFurnaceScreenHandler;
import net.minecraft.screen.slot.Slot;

public class AlloymakingFuelSlot extends Slot {

    public AlloymakingFuelSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    public boolean canInsert(ItemStack stack) {
        return AbstractFurnaceBlockEntity.canUseAsFuel(stack) || isBucket(stack);
    }

    public int getMaxItemCount(ItemStack stack) {
        return isBucket(stack) ? 1 : super.getMaxItemCount(stack);
    }

    public static boolean isBucket(ItemStack stack) {
        return stack.isOf(Items.BUCKET);
    }
}
