package uk.co.xsc.blocks;

public interface FlightCasePropertyRetriever<T> {

    T getFromLargeFlightCase(LargeFlightCaseBlockEntity flightCaseBlockEntity);

    T getFromStandardFlightCase(StandardFlightCaseBlockEntity flightCaseBlockEntity);

    T getFromMiniFlightCase(MiniFlightCaseBlockEntity flightCaseBlockEntity);

}
