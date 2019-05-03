package uk.co.xsc.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.StringTextComponent;
import net.minecraft.text.Style;
import net.minecraft.text.TextFormat;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BasementWallBlock extends Block {

    public BasementWallBlock(Settings settings_1) {
        super(settings_1);
    }

    @Override
    public void onBreak(World world_1, BlockPos blockPos_1, BlockState blockState_1, PlayerEntity playerEntity_1) {
        playerEntity_1.addChatMessage(new StringTextComponent("That's not yours to touch").setStyle(new Style().setColor(TextFormat.GOLD).setBold(true)), false);
        super.onBreak(world_1, blockPos_1, blockState_1, playerEntity_1);
    }
}
