package uk.co.xsc.state.property;

import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import uk.co.xsc.blocks.enums.LargeFlightCasePart;

public class TestProperties {

    public static final EnumProperty<LargeFlightCasePart> LARGE_FLIGHT_CASE_PART;
    public static final BooleanProperty LATCHED;

    static {
        LARGE_FLIGHT_CASE_PART = EnumProperty.create("part", LargeFlightCasePart.class);
        LATCHED = BooleanProperty.create("latched");
    }

}
