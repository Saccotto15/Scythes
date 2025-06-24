package net.thevortex8196.scythes.block.entities.custom;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.thevortex8196.scythes.block.ImplementedInventory;
import net.thevortex8196.scythes.block.entities.ModBlockEntities;
import net.thevortex8196.scythes.recipe.ModRecipes;
import net.thevortex8196.scythes.recipe.ScytheTableRecipe;
import net.thevortex8196.scythes.recipe.ScytheTableRecipeInput;
import net.thevortex8196.scythes.screen.ScytheTableScreenHandler;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class ScytheTableBlockEntity extends BlockEntity
        implements ExtendedScreenHandlerFactory<BlockPos>, ImplementedInventory, SidedInventory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(5, ItemStack.EMPTY);

    private static final int EYE_SLOT = 0;
    private static final int BLADE_SLOT = 1;
    private static final int HANDLE_SLOT = 2;
    private static final int CONNECTOR_SLOT = 3;
    private static final int OUTPUT_SLOT = 4;

    public ScytheTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SCYTHE_TABLE_BE, pos, state);
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity player) {
        return this.pos;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.scythes.scythe_table");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new ScytheTableScreenHandler(syncId, inv, this);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, inventory, registryLookup);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (hasRecipe()) {
            craftItem();
        } else if (!getStack(OUTPUT_SLOT).isEmpty()) {
            setStack(OUTPUT_SLOT, ItemStack.EMPTY);
            markDirty();
            world.updateListeners(pos, state, state, 3);
        }
    }

    private void craftItem() {
        Optional<RecipeEntry<ScytheTableRecipe>> recipe = getCurrentRecipe();

        ItemStack output = recipe.get().value().output();

        this.setStack(OUTPUT_SLOT, new ItemStack(output.getItem(), 1));
    }


    private boolean hasRecipe() {
        Optional<RecipeEntry<ScytheTableRecipe>> recipe = getCurrentRecipe();
        if(recipe.isEmpty()) {
            return false;
        }

        return true;
    }

    private Optional<RecipeEntry<ScytheTableRecipe>> getCurrentRecipe() {
        return this.getWorld().getRecipeManager()
                .getFirstMatch(ModRecipes.SCYTHE_TABLE_TYPE, new ScytheTableRecipeInput(inventory.get(EYE_SLOT), inventory.get(BLADE_SLOT), inventory.get(HANDLE_SLOT), inventory.get(CONNECTOR_SLOT)), this.getWorld());
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack result = Inventories.splitStack(this.inventory, slot, amount);

        if (slot == OUTPUT_SLOT && !result.isEmpty()) {
            this.getStack(EYE_SLOT).decrement(1);
            this.getStack(BLADE_SLOT).decrement(1);
            this.getStack(HANDLE_SLOT).decrement(1);
            this.getStack(CONNECTOR_SLOT).decrement(1);
            markDirty();

            if (world != null) {
                world.updateListeners(pos, getCachedState(), getCachedState(), 3);
            }
        }
        return result;
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }
}
