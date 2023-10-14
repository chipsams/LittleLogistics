package dev.murad.shipping.entity.custom.train.wagon;

import dev.murad.shipping.setup.ModEntityTypes;
import dev.murad.shipping.setup.ModItems;
import dev.murad.shipping.util.ItemHandlerVanillaContainerWrapper;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.stream.IntStream;

public class ChestCarEntity extends AbstractWagonEntity implements ItemHandlerVanillaContainerWrapper, WorldlyContainer, MenuProvider {
    protected final ItemStackHandler itemHandler = createHandler();
    protected final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    public ChestCarEntity(EntityType<ChestCarEntity> type, Level level) {
        super(type, level);
    }

    public ChestCarEntity(EntityType<ChestCarEntity> type, Level level, Double x, Double y, Double z) {
        super(type, level, x, y, z);
    }

    @Override
    public void remove(RemovalReason r) {
        if (!this.level().isClientSide) {
            Containers.dropContents(this.level(), this, this);
        }
        super.remove(r);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(27);
    }

    @Override
    public @NotNull ItemStack getPickResult() {
        if (this.getType().equals(ModEntityTypes.BARREL_CAR.get())) {
            return new ItemStack(ModItems.BARREL_CAR.get());
        } else {
            return new ItemStack(ModItems.CHEST_CAR.get());
        }
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand){
        InteractionResult ret = super.interact(player, hand);
        if (ret.consumesAction()) return ret;

        if(!this.level().isClientSide){
            player.openMenu(this);
        }
        return InteractionResult.CONSUME;
    }

    @Nullable
    public AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pInventory, Player pPlayer) {
        if (pPlayer.isSpectator()) {
            return null;
        } else {
            return ChestMenu.threeRows(pContainerId, pInventory, this);
        }
    }

    @Override
    public boolean stillValid(@NotNull Player pPlayer) {
        if (this.isRemoved()) {
            return false;
        } else {
            return !(this.distanceToSqr(pPlayer) > 64.0D);
        }
    }


    @Override
    public ItemStackHandler getRawHandler() {
        return itemHandler;
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag t) {
        super.addAdditionalSaveData(t);
        t.put("inv", itemHandler.serializeNBT());
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag t) {
        super.readAdditionalSaveData(t);
        itemHandler.deserializeNBT(t.getCompound("inv"));
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return handler.cast();
        }

        return super.getCapability(cap, side);
    }

    // hack to disable hoppers before docking complete

    @Override
    public int @NotNull [] getSlotsForFace(@NotNull Direction p_180463_1_) {
        return IntStream.range(0, getContainerSize()).toArray();
    }

    @Override
    public boolean canPlaceItemThroughFace(int p_180462_1_, ItemStack p_180462_2_, @Nullable Direction p_180462_3_) {
        return isDockable();
    }

    @Override
    public boolean canTakeItemThroughFace(int p_180461_1_, ItemStack p_180461_2_, Direction p_180461_3_) {
        return isDockable();
    }
}
