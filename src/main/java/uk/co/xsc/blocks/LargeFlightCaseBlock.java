package uk.co.xsc.blocks;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.container.NameableContainerProvider;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.VerticalEntityPosition;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import uk.co.xsc.blocks.enums.LargeFlightCasePart;
import uk.co.xsc.state.property.TestProperties;

public class LargeFlightCaseBlock extends HorizontalFacingBlock implements BlockEntityProvider {

    public static final DirectionProperty FACING;
    public static final EnumProperty<LargeFlightCasePart> PART;
    public static final BooleanProperty LATCHED;

    protected static final VoxelShape NORTH_SHAPE;
    protected static final VoxelShape SOUTH_SHAPE;
    protected static final VoxelShape WEST_SHAPE;
    protected static final VoxelShape EAST_SHAPE;

    private static final FlightCasePropertyRetriever<Inventory> INVENTORY_RETRIEVER;
    private static final FlightCasePropertyRetriever<NameableContainerProvider> NAME_RETRIEVER;
    private final DyeColor color;

    static {
        FACING = HorizontalFacingBlock.FACING;
        PART = TestProperties.LARGE_FLIGHT_CASE_PART;
        LATCHED = TestProperties.LATCHED;
        NORTH_SHAPE = Block.createCuboidShape(1.0D, 0.0D, 0.0D, 15.0D, 16.0D, 15.0D);
        SOUTH_SHAPE = Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 16.0D);
        WEST_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
        EAST_SHAPE = Block.createCuboidShape(1.0D, 0.0D, 1.0D, 16.0D, 16.0D, 15.0D);
        INVENTORY_RETRIEVER = new FlightCasePropertyRetriever<Inventory>() {
            public Inventory getInventory(LargeFlightCaseBlockEntity flightCaseBlockEntity) {
                return flightCaseBlockEntity;
            }

            @Override
            public Inventory getFromLargeFlightCase(LargeFlightCaseBlockEntity flightCaseBlockEntity) {
                return getInventory(flightCaseBlockEntity);
            }

            @Override
            public Inventory getFromStandardFlightCase(StandardFlightCaseBlockEntity flightCaseBlockEntity) {
                return null;
            }

            @Override
            public Inventory getFromMiniFlightCase(MiniFlightCaseBlockEntity flightCaseBlockEntity) {
                return null;
            }
        };
        NAME_RETRIEVER = new FlightCasePropertyRetriever<NameableContainerProvider>() {
            @Override
            public NameableContainerProvider getFromLargeFlightCase(LargeFlightCaseBlockEntity flightCaseBlockEntity) {
                return flightCaseBlockEntity;
            }

            @Override
            public NameableContainerProvider getFromStandardFlightCase(StandardFlightCaseBlockEntity flightCaseBlockEntity) {
                return null;
            }

            @Override
            public NameableContainerProvider getFromMiniFlightCase(MiniFlightCaseBlockEntity flightCaseBlockEntity) {
                return null;
            }
        };
    }

    public LargeFlightCaseBlock(DyeColor color) {
        super(FabricBlockSettings.of(Material.METAL).hardness(10).resistance(1000).build());
        this.color = color;
        this.setDefaultState(this.stateFactory.getDefaultState().with(FACING, Direction.NORTH).with(PART, LargeFlightCasePart.LEFT).with(LATCHED, true));
    }

    @Override
    public MaterialColor getMapColor(BlockState state, BlockView view, BlockPos pos) {
        return this.color.getMaterialColor();
    }

    @Environment(EnvType.CLIENT)
    public static Direction getDirection(BlockView view, BlockPos pos) {
        BlockState state = view.getBlockState(pos);
        return state.getBlock() instanceof LargeFlightCaseBlock ? state.get(FACING) : null;
    }

    @Override
    public boolean activate(BlockState state, World world, BlockPos blockPos, PlayerEntity player, Hand hand, BlockHitResult blockHitResult) {
        //todo latch system shift click to unlatch/latch
        return true;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos blockPos, VerticalEntityPosition verticalEntityPosition) {
        switch (getFacing(state)) {
            case NORTH:
                return NORTH_SHAPE;
            case SOUTH:
                return SOUTH_SHAPE;
            case WEST:
                return WEST_SHAPE;
            case EAST:
                return EAST_SHAPE;
            default:
                return NORTH_SHAPE;
        }
    }

    public static Direction getFacing(BlockState state) {
        Direction direction = state.get(FACING);
        return state.get(PART) == LargeFlightCasePart.LEFT ? direction.rotateYClockwise() : direction.rotateYCounterclockwise();
    }

    @Override
    public void onPlaced(World world_1, BlockPos blockPos_1, BlockState blockState_1, LivingEntity livingEntity_1, ItemStack itemStack_1) {
        super.onPlaced(world_1, blockPos_1, blockState_1, livingEntity_1, itemStack_1);

        if (!world_1.isClient) {
            BlockPos pos2 = blockPos_1.offset(blockState_1.get(FACING));
            world_1.setBlockState(pos2, blockState_1.with(PART, LargeFlightCasePart.RIGHT), 3);
            world_1.updateNeighbors(blockPos_1, Blocks.AIR);
            blockState_1.updateNeighborStates(world_1, blockPos_1, 3);
        }

        if (itemStack_1.hasDisplayName()) {
            BlockEntity blockEntity = world_1.getBlockEntity(blockPos_1);
            if (blockEntity instanceof LargeFlightCaseBlockEntity) {
                ((LargeFlightCaseBlockEntity) blockEntity).setCustomName(itemStack_1.getDisplayName());
            }
        }
    }

    @Override
    public BlockEntity createBlockEntity(BlockView var1) {
        return null;
    }

}
