package uk.co.xsc.registry;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import uk.co.xsc.blocks.LargeFlightCaseBlockEntity;

public class TestBlockEntities {

    public static final BlockEntityType<LargeFlightCaseBlockEntity> FLIGHT_CASE_LARGE_BLOCK_ENTITY = BlockEntityType.Builder.create(LargeFlightCaseBlockEntity::new).build(null);

    public static void init() {
        register("flight_case_large", FLIGHT_CASE_LARGE_BLOCK_ENTITY);
    }

    private static void register(String id, BlockEntityType<? extends BlockEntity> type) {
        Registry.register(Registry.BLOCK_ENTITY, new Identifier("test_mod", id), type);
    }

}
