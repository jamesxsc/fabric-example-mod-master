package uk.co.xsc.blocks;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.container.Container;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TextComponent;
import net.minecraft.util.DefaultedList;

public class LargeFlightCaseBlockEntity extends LootableContainerBlockEntity {

    protected LargeFlightCaseBlockEntity(BlockEntityType<?> blockEntityType_1) {
        super(blockEntityType_1);
    }

    @Override
    protected DefaultedList<ItemStack> getInvStackList() {
        return null;
    }

    @Override
    protected void setInvStackList(DefaultedList<ItemStack> var1) {

    }

    @Override
    protected TextComponent getContainerName() {
        return null;
    }

    @Override
    protected Container createContainer(int var1, PlayerInventory var2) {
        return null;
    }

    @Override
    public int getInvSize() {
        return 0;
    }

    @Override
    public boolean isInvEmpty() {
        return false;
    }

}
