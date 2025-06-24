package net.thevortex8196.scythes.screen;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;
import net.thevortex8196.scythes.block.entities.custom.ScytheTableBlockEntity;
import net.thevortex8196.scythes.item.ModItems;

public class ScytheTableScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    public final ScytheTableBlockEntity blockEntity;

    public ScytheTableScreenHandler(int syncId, PlayerInventory inventory, BlockPos pos) {
        this(syncId, inventory, inventory.player.getWorld().getBlockEntity(pos));
    }

    public ScytheTableScreenHandler(int syncId, PlayerInventory playerInventory,
                                    BlockEntity blockEntity) {
        super(ModScreenHandlers.SCYTHE_TABLE_SCREEN_HANDLER, syncId);
        this.inventory = ((Inventory) blockEntity);
        this.blockEntity = ((ScytheTableBlockEntity) blockEntity);

        this.addSlot(new Slot(inventory, 0, 39, 34) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(ModItems.EYE_OF_THE_END);
            }
        });
        this.addSlot(new Slot(inventory, 1, 68, 16));
        this.addSlot(new Slot(inventory, 2, 68, 34));
        this.addSlot(new Slot(inventory, 3, 68, 52));
        this.addSlot(new Slot(inventory, 4, 120, 34) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack() && slot.id != 4) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
