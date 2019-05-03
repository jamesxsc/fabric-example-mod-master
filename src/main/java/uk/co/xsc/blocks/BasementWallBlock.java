package uk.co.xsc.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.StringTextComponent;
import net.minecraft.text.Style;
import net.minecraft.text.TextFormat;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.loot.context.LootContext;
import uk.co.xsc.TestMod;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BasementWallBlock extends Block {

    public BasementWallBlock(Settings settings_1) {
        super(settings_1);
    }

    @Override
    public void afterBreak(World world_1, PlayerEntity playerEntity_1, BlockPos blockPos_1, BlockState blockState_1, BlockEntity blockEntity_1, ItemStack itemStack_1) {
        playerEntity_1.addChatMessage(new StringTextComponent("That's not yours to touch").setStyle(new Style().setColor(TextFormat.GOLD).setBold(true)), false);
        super.afterBreak(world_1, playerEntity_1, blockPos_1, blockState_1, blockEntity_1, itemStack_1);
    }

}
