package uk.co.xsc.registry;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class TestBlocks {

    public static void init() {

    }

    private static void register(String id, Block block, ItemGroup group) {
        Registry.register(Registry.BLOCK, new Identifier("test_mod", id), block);
        Registry.register(Registry.ITEM, new Identifier("test_mod", id), new BlockItem(block, new BlockItem.Settings().itemGroup(group)));
    }

}
