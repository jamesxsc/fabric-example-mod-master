package uk.co.xsc.stat;

import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class TestStats {

    public static final Identifier OPEN_FLIGHT_CASE_LARGE;

    private static Identifier register(String id, StatFormatter format) {
        Identifier identifier = new Identifier(id);
        Registry.register(Registry.CUSTOM_STAT, id, identifier);
        Stats.CUSTOM.getOrCreateStat(identifier, format);
        return identifier;
    }

    static {
        OPEN_FLIGHT_CASE_LARGE = register("open_flight_case_large", StatFormatter.DEFAULT);
    }

}
