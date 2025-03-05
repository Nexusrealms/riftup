package de.nexusrealms.riftup.block;

import com.mojang.serialization.Codec;
import de.nexusrealms.riftup.item.ModItems;
import de.nexusrealms.riftup.recipe.AlloymakingRecipe;
import de.nexusrealms.riftup.recipe.ListRecipeInput;
import de.nexusrealms.riftup.recipe.ModRecipes;
import de.nexusrealms.riftup.screen.AlloymakingScreenHandler;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtOps;
import net.minecraft.recipe.*;
import net.minecraft.recipe.input.SingleStackRecipeInput;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static net.minecraft.block.entity.AbstractFurnaceBlockEntity.canUseAsFuel;

public class AlloymakingFurnaceBlockEntity extends LockableContainerBlockEntity implements SidedInventory, RecipeUnlocker, RecipeInputProvider {
    public static final Codec<List<ItemStack>> MUTABLE_ITEM_STACK_LIST = ItemStack.CODEC.listOf().xmap(ArrayList::new, Function.identity());

    protected DefaultedList<ItemStack> inventory;
    protected List<ItemStack> moltenStacks = new ArrayList<>();

    private static final int[] TOP_SLOTS = new int[]{0};
    private static final int[] BOTTOM_SLOTS = new int[]{2, 1};
    private static final int[] SIDE_SLOTS = new int[]{1};
    int burnTime;
    protected final PropertyDelegate propertyDelegate;
    private final Object2IntOpenHashMap<Identifier> recipesUsed;
    private final RecipeManager.MatchGetter<ListRecipeInput, AlloymakingRecipe> matchGetter;
    public static boolean isMeltable(ItemStack stack){
        return true;
    }
    protected AlloymakingFurnaceBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.ALLOYMAKING_FURNACE, blockPos, blockState);
        this.inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);
        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                switch (index) {
                    case 0 -> {
                        return burnTime;
                    }
                    case 1 -> {
                        return hasMoltenStacks() ? 1 : 0;
                    }
                    default -> {
                        return 0;
                    }
                }
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0 -> burnTime = value;
                    case 1 -> {

                    }
                }

            }

            public int size() {
                return 2;
            }
        };
        this.recipesUsed = new Object2IntOpenHashMap<>();
        this.matchGetter = RecipeManager.createCachedMatchGetter(ModRecipes.ALLOYMAKING_RECIPE_TYPE);

    }

    public boolean hasMoltenStacks() {
        return !moltenStacks.isEmpty();
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("container.riftup.alloymaking_furnace");
    }

    @Override
    protected DefaultedList<ItemStack> getHeldStacks() {
        return inventory;
    }

    @Override
    protected void setHeldStacks(DefaultedList<ItemStack> inventory) {
        this.inventory = inventory;
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new AlloymakingScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }

    private boolean isBurning() {
        return this.burnTime > 0;
    }

    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        Inventories.readNbt(nbt, this.inventory, registryLookup);
        this.moltenStacks = MUTABLE_ITEM_STACK_LIST.parse(registryLookup.getOps(NbtOps.INSTANCE), nbt.get("MoltenStacks")).getOrThrow();
        this.burnTime = nbt.getShort("BurnTime");
        NbtCompound nbtCompound = nbt.getCompound("RecipesUsed");
        nbtCompound.getKeys().forEach(s -> recipesUsed.put(Identifier.of(s), nbtCompound.getInt(s)));
    }

    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        nbt.putShort("BurnTime", (short) this.burnTime);
        Inventories.writeNbt(nbt, this.inventory, registryLookup);
        nbt.put("MoltenStacks", MUTABLE_ITEM_STACK_LIST.encodeStart(registryLookup.getOps(NbtOps.INSTANCE), moltenStacks).getOrThrow());
        NbtCompound nbtCompound = new NbtCompound();
        this.recipesUsed.forEach((identifier, count) -> {
            nbtCompound.putInt(identifier.toString(), count);
        });
        nbt.put("RecipesUsed", nbtCompound);
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        if (side == Direction.DOWN) {
            return BOTTOM_SLOTS;
        } else {
            return side == Direction.UP ? TOP_SLOTS : SIDE_SLOTS;
        }
    }

    public boolean isValid(int slot, ItemStack stack) {
        if (slot == 2) {
            return false;
        } else if (slot != 1) {
            return true;
        } else {
            ItemStack itemStack = this.inventory.get(1);
            return canUseAsFuel(stack) || stack.isOf(Items.BUCKET) && !itemStack.isOf(Items.BUCKET);
        }
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        return isValid(slot, stack);
    }

    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        if (dir == Direction.DOWN && slot == 1) {
            return stack.isOf(Items.WATER_BUCKET) || stack.isOf(Items.BUCKET);
        } else {
            return true;
        }
    }

    @Override
    public int size() {
        return 3;
    }

    public void provideRecipeInputs(RecipeMatcher finder) {
        this.inventory.forEach(finder::addInput);
    }

    public void setLastRecipe(@Nullable RecipeEntry<?> recipe) {
        if (recipe != null) {
            Identifier identifier = recipe.id();
            this.recipesUsed.addTo(identifier, 1);
        }

    }

    @Override
    public @Nullable RecipeEntry<?> getLastRecipe() {
        return null;
    }

    public static void tick(World world, BlockPos pos, BlockState state, AlloymakingFurnaceBlockEntity blockEntity) {
        boolean wasBurning = blockEntity.isBurning();
        boolean isDirty = false;
        if (blockEntity.isBurning()) {
            --blockEntity.burnTime;
        }
        ItemStack fuel = blockEntity.inventory.get(1);
        ItemStack input = blockEntity.inventory.get(0);
        if (!fuel.isEmpty()) {
            blockEntity.burnTime += blockEntity.getFuelTime(fuel);
            Item item = fuel.getItem();
            fuel.decrement(1);
            if (fuel.isEmpty()) {
                Item item2 = item.getRecipeRemainder();
                blockEntity.inventory.set(1, item2 == null ? ItemStack.EMPTY : new ItemStack(item2));
            }
        }

        if (blockEntity.isBurning() && !input.isEmpty()) {
            ItemStack toMelt = input.split(1);
            blockEntity.moltenStacks.add(toMelt);
        }
        state = state.with(AlloymakingFurnaceBlock.LIT, blockEntity.isBurning());
        if(!blockEntity.isBurning()){
            RecipeEntry<AlloymakingRecipe> recipeEntry = blockEntity.matchGetter.getFirstMatch(new ListRecipeInput(blockEntity.moltenStacks), world).orElse(null);
            if(recipeEntry == null){
                if(!blockEntity.moltenStacks.isEmpty()){
                    Item item = blockEntity.moltenStacks.getFirst().getItem();
                    if(blockEntity.moltenStacks.stream().allMatch(stack -> stack.isOf(item))){
                        ItemStack stack = new ItemStack(item, blockEntity.moltenStacks.size());
                        blockEntity.inventory.set(2, stack);
                        blockEntity.moltenStacks.clear();
                    } else {
                        ItemStack stack = new ItemStack(ModItems.JUNK_INGOT, blockEntity.moltenStacks.size());
                        blockEntity.inventory.set(2, stack);
                        blockEntity.moltenStacks.clear();
                    }
                    isDirty = true;
                }
            } else {
                int maxCount = blockEntity.getMaxCountPerStack();
                if(canAcceptRecipeOutput(world.getRegistryManager(), recipeEntry, blockEntity.inventory, maxCount)){
                    if (craftRecipe(world.getRegistryManager(), recipeEntry, blockEntity.inventory, maxCount, blockEntity.moltenStacks)) {
                        blockEntity.setLastRecipe(recipeEntry);
                    }
                    isDirty = true;
                }
            }
        }
        if(wasBurning != blockEntity.isBurning() || isDirty){
            markDirty(world, pos, state);
        }
    }

    private static boolean canAcceptRecipeOutput(DynamicRegistryManager registryManager, @Nullable RecipeEntry<?> recipe, DefaultedList<ItemStack> slots, int count) {
        if (recipe != null) {
            ItemStack itemStack = recipe.value().getResult(registryManager);
            if (itemStack.isEmpty()) {
                return false;
            } else {
                ItemStack itemStack2 = slots.get(2);
                if (itemStack2.isEmpty()) {
                    return true;
                } else if (!ItemStack.areItemsAndComponentsEqual(itemStack2, itemStack)) {
                    return false;
                } else if (itemStack2.getCount() < count && itemStack2.getCount() < itemStack2.getMaxCount()) {
                    return true;
                } else {
                    return itemStack2.getCount() < itemStack.getMaxCount();
                }
            }
        } else {
            return false;
        }
    }

    private static boolean craftRecipe(DynamicRegistryManager registryManager, @Nullable RecipeEntry<AlloymakingRecipe> recipe, DefaultedList<ItemStack> slots, int count, List<ItemStack> moltens) {
        if (canAcceptRecipeOutput(registryManager, recipe, slots, count)) {
            ItemStack itemStack2 = recipe.value().getResult(registryManager);
            ItemStack itemStack3 = slots.get(2);
            if (itemStack3.isEmpty()) {
                slots.set(2, itemStack2.copy());
            } else if (ItemStack.areItemsAndComponentsEqual(itemStack3, itemStack2)) {
                itemStack3.increment(1);
            }
            recipe.value().ingredients().forEach(ingredient -> AlloymakingRecipe.removeOneIf(moltens, ingredient));
            return true;
        } else {
            return false;
        }
    }

    protected int getFuelTime(ItemStack fuel) {
        if (fuel.isEmpty()) {
            return 0;
        } else {
            Item item = fuel.getItem();
            return AbstractFurnaceBlockEntity.createFuelTimeMap().getOrDefault(item, 0);
        }
    }
}
