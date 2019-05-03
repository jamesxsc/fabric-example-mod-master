package uk.co.xsc.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.StringTextComponent;
import net.minecraft.text.Style;
import net.minecraft.text.TextFormat;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import uk.co.xsc.TestMod;

public class GlobglogabgolabItem extends Item {

    public GlobglogabgolabItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world_1, PlayerEntity playerEntity_1, Hand hand_1) {
        playerEntity_1.addExperience(1000);
        playerEntity_1.playSound(TestMod.GREETING_GLOBGLOGABGOLAB_SOUND, 1.0F, 1.0F);
        return new TypedActionResult<>(ActionResult.SUCCESS, playerEntity_1.getStackInHand(hand_1));
    }
}
