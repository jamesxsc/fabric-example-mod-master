package uk.co.xsc.blocks;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.container.Container;
import net.minecraft.container.GenericContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TextComponent;
import net.minecraft.text.TranslatableTextComponent;
import net.minecraft.util.DefaultedList;
import uk.co.xsc.registry.TestBlockEntities;

import java.util.Iterator;

public class LargeFlightCaseBlockEntity extends LootableContainerBlockEntity {

    private DefaultedList<ItemStack> inventory;

    protected LargeFlightCaseBlockEntity(BlockEntityType<?> blockEntityType_1) {
        super(blockEntityType_1);
        this.inventory = DefaultedList.create(54, ItemStack.EMPTY);
    }

    public LargeFlightCaseBlockEntity() {
        this(TestBlockEntities.FLIGHT_CASE_LARGE_BLOCK_ENTITY);
    }

    @Override
    protected DefaultedList<ItemStack> getInvStackList() {
        return this.inventory;
    }

    @Override
    protected void setInvStackList(DefaultedList<ItemStack> var1) {
        this.inventory = var1;
    }

    @Override
    protected TextComponent getContainerName() {
        return new TranslatableTextComponent("container.flight-case.large");
    }

    @Override
    protected Container createContainer(int var1, PlayerInventory var2) {
        return GenericContainer.createGeneric9x6(var1, var2, this);
    }

    @Override
    public int getInvSize() {
        return 54;
    }

    @Override
    public boolean isInvEmpty() {
        Iterator iterator = this.inventory.iterator();

        ItemStack is;
        do {
            if (!iterator.hasNext()) {
                return true;
            }
            is = (ItemStack) iterator.next();
        } while (is.isEmpty());

        return false;
    }

}
