package uk.co.xsc.blocks.enums;

import net.minecraft.util.StringRepresentable;

public enum LargeFlightCasePart implements StringRepresentable {
    LEFT("left"),
    RIGHT("right"),
    ;

    private final String name;

    private LargeFlightCasePart(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public String asString() {
        return this.name;
    }

}
