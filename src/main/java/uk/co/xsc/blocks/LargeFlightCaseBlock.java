package uk.co.xsc.blocks;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.container.Container;
import net.minecraft.container.GenericContainer;
import net.minecraft.container.NameableContainerProvider;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.VerticalEntityPosition;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.DoubleInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stat;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.text.*;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import uk.co.xsc.blocks.enums.LargeFlightCasePart;
import uk.co.xsc.stat.TestStats;
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
        NORTH_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);
        SOUTH_SHAPE = Block.createCuboidShape(1.0D, 0.0D, 1.0D, 16.0D, 16.0D, 15.0D);
        WEST_SHAPE = Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 16.0D);
        EAST_SHAPE = Block.createCuboidShape(1.0D, 0.0D, 0.0D, 15.0D, 16.0D, 15.0D);
        INVENTORY_RETRIEVER = new FlightCasePropertyRetriever<Inventory>() {
            public Inventory getInventory(LargeFlightCaseBlockEntity flightCaseBlockEntity) {
                return flightCaseBlockEntity;
            }

            @Override
            public Inventory getFromLargeFlightCase(LargeFlightCaseBlockEntity flightCaseBlockEntity) {
                return new DoubleInventory(flightCaseBlockEntity,
                        (LargeFlightCaseBlockEntity)
                                flightCaseBlockEntity.getWorld()
                                        .getBlockEntity(flightCaseBlockEntity.getPos()
                                                .offset(getFacing(flightCaseBlockEntity.getWorld()
                                                        .getBlockState(flightCaseBlockEntity.getPos())))));
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
                final Inventory inventory = new DoubleInventory(flightCaseBlockEntity,
                        (LargeFlightCaseBlockEntity)
                                flightCaseBlockEntity.getWorld()
                                        .getBlockEntity(flightCaseBlockEntity.getPos()
                                                .offset(getFacing(flightCaseBlockEntity.getWorld()
                                                        .getBlockState(flightCaseBlockEntity.getPos())))));
                return new NameableContainerProvider() {
                    @Override
                    public TextComponent getDisplayName() {
                        return new TranslatableTextComponent("container.flight-case.large");
                    }

                    @Override
                    public Container createMenu(int var1, PlayerInventory var2, PlayerEntity var3) {
                        return new GenericContainer(ContainerType.);
                    }
                }
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

    // fixed no properties found 10/05/19
    @Override
    protected void appendProperties(StateFactory.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.with(FACING, PART, LATCHED);
    }

    public LargeFlightCaseBlock(DyeColor color) {
        super(FabricBlockSettings.of(Material.WOOD).hardness(1).resistance(1000).breakByHand(true).build());
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
        if (world.isClient)
            return true;
        else {
            //todo flight case key item
            if (player.isSneaking()) {
                if (state.get(LATCHED)) {
                    player.addChatMessage(new StringTextComponent("This container has been unlatched.").setStyle(new Style().setItalic(true).setColor(TextFormat.GRAY)), false);
                    world.setBlockState(blockPos, state.with(LATCHED, false));
                } else {
                    player.addChatMessage(new StringTextComponent("This container has been latched.").setStyle(new Style().setItalic(true).setColor(TextFormat.GRAY)), false);
                    world.setBlockState(blockPos, state.with(LATCHED, true));
                }
            } else {
                NameableContainerProvider containerProvider = this.createContainerProvider(state, world, blockPos);
                if (containerProvider != null) {
                    if (state.get(LATCHED)) {
                        player.addChatMessage(new StringTextComponent("This container is latched shut.").setStyle(new Style().setColor(TextFormat.GRAY).setItalic(true)), false);
                    } else {
                        player.openContainer(containerProvider);
                        player.incrementStat(this.getOpenStat());
                    }
                }
            }
        }
        return true;
    }

    protected Stat<Identifier> getOpenStat() {
        return Stats.CUSTOM.getOrCreateStat(TestStats.OPEN_FLIGHT_CASE_LARGE);
    }

    public static <T> T retrieve(BlockState state, IWorld world, BlockPos pos, boolean bool, FlightCasePropertyRetriever<T> propertyRetriever) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (!(blockEntity instanceof LargeFlightCaseBlockEntity)) {
            return null;
        } else if (!bool && hasBlockOnTop(world, pos)) {
            return null;
        } else {
            LargeFlightCaseBlockEntity flightCaseBlockEntity = (LargeFlightCaseBlockEntity) blockEntity;
            return propertyRetriever.getFromLargeFlightCase(flightCaseBlockEntity);
        }
        // maybe need to fix to accept two blockentities
    }

    @Override
    public NameableContainerProvider createContainerProvider(BlockState state, World world, BlockPos pos) {
        return retrieve(state, world, pos, false, NAME_RETRIEVER);
    }

    @Override
    public void onBreak(World world, BlockPos pos1, BlockState state1, PlayerEntity player) {
        LargeFlightCasePart casePart = state1.get(PART);
        BlockPos pos2 = pos1.offset(state1.get(FACING));
        BlockState state2 = world.getBlockState(pos2);
        if (state2.getBlock() == this && state2.get(PART) != casePart) {
            world.setBlockState(pos2, Blocks.AIR.getDefaultState(), 35);
            world.playLevelEvent(player, 2001, pos2, Block.getRawIdFromState(state2));
            if (!world.isClient && !player.isCreative()) {
                ItemStack is = player.getMainHandStack();
                dropStacks(state1, world, pos1, null, player, is);
                dropStacks(state2, world, pos1, null, player, is);
            }
            player.incrementStat(Stats.MINED.getOrCreateStat(this));
        }
        super.onBreak(world, pos1, state1, player);
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

    private static Direction getFacing(BlockState state) {
        Direction direction = state.get(FACING);
        return direction.rotateYClockwise();
        //return state.get(PART) == LargeFlightCasePart.LEFT ? direction.rotateYClockwise() : direction.rotateYCounterclockwise();
    }


    // fixed all cases placed in same direction 10/05/19
    @Override
    public BlockState getPlacementState(ItemPlacementContext placementContext) {
        Direction direction = placementContext.getPlayerHorizontalFacing().rotateYClockwise();
        BlockPos pos1 = placementContext.getBlockPos();
        BlockPos pos2 = pos1.offset(direction);
        return placementContext.getWorld().getBlockState(pos2).canReplace(placementContext) ? this.getDefaultState().with(FACING, direction) : null;
    }

    private static boolean hasBlockOnTop(BlockView blockView, BlockPos blockPos) {
        BlockPos blockPos1 = blockPos.up();
        return blockView.getBlockState(blockPos1).isSimpleFullBlock(blockView, blockPos1);
    }

    @Override
    public void onPlaced(World world_1, BlockPos blockPos_1, BlockState blockState_1, LivingEntity livingEntity_1, ItemStack itemStack_1) {
        super.onPlaced(world_1, blockPos_1, blockState_1, livingEntity_1, itemStack_1);

        if (!world_1.isClient) {
            BlockPos pos2 = blockPos_1.offset(blockState_1.get(FACING));
            world_1.setBlockState(pos2, blockState_1.with(PART, LargeFlightCasePart.RIGHT).with(FACING, blockState_1.get(FACING).getOpposite()), 3);
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
        return new LargeFlightCaseBlockEntity();
    }

}
