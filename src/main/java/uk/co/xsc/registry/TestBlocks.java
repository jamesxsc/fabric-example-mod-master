package uk.co.xsc.registry;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import uk.co.xsc.TestMod;
import uk.co.xsc.blocks.LargeFlightCaseBlock;

public class TestBlocks {

    public static final Block FLIGHT_CASE_LARGE_BLOCK = new LargeFlightCaseBlock(DyeColor.GREEN);

    public static void init() {
        register("flight_case_large", FLIGHT_CASE_LARGE_BLOCK, TestMod.GLOBGLOGABGOLAB_GROUP);
    }

    private static void register(String id, Block block, ItemGroup group) {
        Registry.register(Registry.BLOCK, new Identifier("test_mod", id), block);
        Registry.register(Registry.ITEM, new Identifier("test_mod", id), new BlockItem(block, new BlockItem.Settings().itemGroup(group)));
    }

}
