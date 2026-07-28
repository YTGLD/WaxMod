package com.ytgld.the_wax.block.earth;

import com.ytgld.the_wax.block.WaxBlockEntityType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.ContainerUser;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import java.util.List;

public class GiantRhizomeBlockEntity extends RandomizableContainerBlockEntity {
    private static final Component DEFAULT_NAME = Component.translatable("block.the_wax.giant_rhizome");
    private NonNullList<ItemStack> items = NonNullList.withSize(9*6, ItemStack.EMPTY);
    private final ContainerOpenersCounter openersCounter  = new ContainerOpenersCounter() {
        @Override
        protected void onOpen(final Level level, final BlockPos pos, final BlockState state) {
            GiantRhizomeBlockEntity.this.playSound(SoundEvents.WOOD_BREAK);
        }

        @Override
        protected void onClose(final Level level, final BlockPos pos, final BlockState state) {
            GiantRhizomeBlockEntity.this.playSound(SoundEvents.WOOD_BREAK);
        }

        @Override
        protected void openerCountChanged(final Level level, final BlockPos pos, final BlockState blockState, final int previous, final int current) {
        }

        @Override
        public boolean isOwnContainer(final Player player) {
            if (player.containerMenu instanceof ChestMenu) {
                Container container = ((ChestMenu)player.containerMenu).getContainer();
                return container == GiantRhizomeBlockEntity.this;
            } else {
                return false;
            }
        }
    };

    public GiantRhizomeBlockEntity(final BlockPos worldPosition, final BlockState blockState) {
        super(WaxBlockEntityType.GiantRhizomeBlockEntity_, worldPosition, blockState);
    }

    @Override
    protected void saveAdditional(final ValueOutput output) {
        super.saveAdditional(output);
        if (!this.trySaveLootTable(output)) {
            ContainerHelper.saveAllItems(output, this.items);
        }

    }

    @Override
    protected void loadAdditional(final ValueInput input) {
        super.loadAdditional(input);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(input)) {
            ContainerHelper.loadAllItems(input, this.items);
        }

    }

    @Override
    public int getContainerSize() {
        return 9*6;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(final NonNullList<ItemStack> items) {
        this.items = items;
    }

    @Override
    protected Component getDefaultName() {
        return DEFAULT_NAME;
    }

    @Override
    protected AbstractContainerMenu createMenu(final int containerId, final Inventory inventory) {
        return new ChestMenu(MenuType.GENERIC_9x6, containerId, inventory, this, 6);
    }

    @Override
    public void startOpen(final ContainerUser containerUser) {
        if (!this.remove && !containerUser.getLivingEntity().isSpectator()) {
            this.openersCounter.incrementOpeners(containerUser.getLivingEntity(), this.getLevel(), this.getBlockPos(), this.getBlockState(), containerUser.getContainerInteractionRange());
        }

    }

    @Override
    public void stopOpen(final ContainerUser containerUser) {
        if (!this.remove && !containerUser.getLivingEntity().isSpectator()) {
            this.openersCounter.decrementOpeners(containerUser.getLivingEntity(), this.getLevel(), this.getBlockPos(), this.getBlockState());
        }

    }

    @Override
    public List<ContainerUser> getEntitiesWithContainerOpen() {
        return this.openersCounter.getEntitiesWithContainerOpen(this.getLevel(), this.getBlockPos());
    }

    public void recheckOpen() {
        if (!this.remove) {
            this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
        }

    }
    private void playSound(final SoundEvent event) {
        double x = (double)this.worldPosition.getX() + (double)0.5F;
        double y = (double)this.worldPosition.getY() + (double)0.5F;
        double z = (double)this.worldPosition.getZ() + (double)0.5F;
        this.level.playSound((Entity)null, x, y, z, event, SoundSource.BLOCKS, 0.5F, this.level.getRandom().nextFloat() * 0.1F + 0.9F);
    }
}

