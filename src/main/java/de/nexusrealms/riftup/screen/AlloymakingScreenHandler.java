package de.nexusrealms.riftup.screen;

import de.nexusrealms.riftup.block.AlloymakingFurnaceBlockEntity;
import de.nexusrealms.riftup.recipe.AlloymakingRecipe;
import de.nexusrealms.riftup.recipe.ListRecipeInput;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeInputProvider;
import net.minecraft.recipe.RecipeMatcher;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.FurnaceFuelSlot;
import net.minecraft.screen.slot.FurnaceOutputSlot;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class AlloymakingScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    protected final World world;
    public AlloymakingScreenHandler(int syncId, PlayerInventory playerInventory){
        this(syncId, playerInventory, new SimpleInventory(3), new ArrayPropertyDelegate(3));
    }
    public AlloymakingScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(ModScreenHandlers.ALLOYMAKING, syncId);
        this.world = playerInventory.player.getWorld();
        this.inventory = inventory;
        this.propertyDelegate = propertyDelegate;
        this.addSlot(new Slot(inventory, 0, 35, 17));
        this.addSlot(new AlloymakingFuelSlot(inventory, 1, 35, 53));
        this.addSlot(new FurnaceOutputSlot(playerInventory.player, inventory, 2, 125, 35));
        int i;
        for(i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }

        this.addProperties(propertyDelegate);
    }
    public boolean isBurning(){
        return propertyDelegate.get(0) > 0;
    }
    public boolean hasMelt(){
        return propertyDelegate.get(1) > 0;
    }
    public float getFuelProgress() {
        int i = this.propertyDelegate.get(2);
        if (i == 0) {
            i = 200;
        }

        return MathHelper.clamp((float)this.propertyDelegate.get(0) / (float)i, 0.0F, 1.0F);
    }
    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot2 = this.slots.get(slot);
        if (slot2.hasStack()) {
            ItemStack itemStack2 = slot2.getStack();
            itemStack = itemStack2.copy();
            if (slot == 2) {
                if (!this.insertItem(itemStack2, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }

                slot2.onQuickTransfer(itemStack2, itemStack);
            } else if (slot != 1 && slot != 0) {
                if (AlloymakingFurnaceBlockEntity.isMeltable(itemStack2)) {
                    if (!this.insertItem(itemStack2, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (AbstractFurnaceBlockEntity.canUseAsFuel(itemStack2)) {
                    if (!this.insertItem(itemStack2, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (slot >= 3 && slot < 30) {
                    if (!this.insertItem(itemStack2, 30, 39, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (slot >= 30 && slot < 39 && !this.insertItem(itemStack2, 3, 30, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(itemStack2, 3, 39, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack2.isEmpty()) {
                slot2.setStack(ItemStack.EMPTY);
            } else {
                slot2.markDirty();
            }

            if (itemStack2.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot2.onTakeItem(player, itemStack2);
        }

        return itemStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return inventory.canPlayerUse(player);
    }
}
