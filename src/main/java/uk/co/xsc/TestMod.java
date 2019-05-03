package uk.co.xsc;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import uk.co.xsc.blocks.BasementWallBlock;
import uk.co.xsc.items.GlobglogabgolabItem;
import uk.co.xsc.sounds.GreetingGlobglogabgolabSound;

@SuppressWarnings("WeakerAccess")
public class TestMod implements ModInitializer {

    public static final ItemGroup GLOBGLOGABGOLAB_GROUP = FabricItemGroupBuilder.create(new Identifier("test_mod", "globglogabgolab")).icon(() -> new ItemStack(Items.ACACIA_PLANKS)).build();

    public static final GlobglogabgolabItem GLOBGLOGABGOLAB_ITEM = new GlobglogabgolabItem(new Item.Settings().itemGroup(GLOBGLOGABGOLAB_GROUP));

    public static final BasementWallBlock BASEMENT_WALL_BLOCK = new BasementWallBlock(Block.Settings.of(Material.STONE).friction(50));

    public static final GreetingGlobglogabgolabSound GREETING_GLOBGLOGABGOLAB_SOUND = new GreetingGlobglogabgolabSound(new Identifier("test_mod", "greeting_globglogabgolab_sound"));


    @Override
    public void onInitialize() {
        Registry.register(Registry.BLOCK, new Identifier("test_mod", "basement_wall_block"), BASEMENT_WALL_BLOCK);

        Registry.register(Registry.ITEM, new Identifier("test_mod", "basement_wall_block"), new BlockItem(BASEMENT_WALL_BLOCK, new Item.Settings().stackSize(128).itemGroup(GLOBGLOGABGOLAB_GROUP)));

        Registry.register(Registry.ITEM, new Identifier("test_mod", "globglogabgolab_item"), GLOBGLOGABGOLAB_ITEM);

        Registry.register(Registry.SOUND_EVENT, new Identifier("test_mod", "greeting_globglogabgolab_sound"), GREETING_GLOBGLOGABGOLAB_SOUND);
    }

}
