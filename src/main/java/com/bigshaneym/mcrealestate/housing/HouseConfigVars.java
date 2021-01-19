package com.bigshaneym.mcrealestate.housing;

public class HouseConfigVars {

    private static double sell_price_value_decay = 0.5;
    private static int singleOwnerGuestLimit = 5;
    private static int multiOwnerToHouseLimit = 3;
    private static boolean shouldTerminateRental = true;
    private static long rentalNoShowLimit_ms = 604800000; //1 week in milliseconds
    private static boolean USE_SQL = false;



    public static double getSellPriceValueDecay() {

        return sell_price_value_decay;
    }

    public static void setSellPriceValueDecay(double sell_price_value_decay) {
        HouseConfigVars.sell_price_value_decay = sell_price_value_decay;
    }

    public static int getSingleOwnerGuestLimit() {
        return singleOwnerGuestLimit;
    }

    public static void setSingleOwnerGuestLimit(int singleOwnerGuestLimit) {
        HouseConfigVars.singleOwnerGuestLimit = singleOwnerGuestLimit;
    }

    public static int getMultiOwnerToHouseLimit() {
        return multiOwnerToHouseLimit;
    }

    public static void setMultiOwnerToHouseLimit(int multiOwnerToHouseLimit) {
        HouseConfigVars.multiOwnerToHouseLimit = multiOwnerToHouseLimit;
    }

    public static boolean shouldTerminateRental() {
        return shouldTerminateRental;
    }

    public static void setShouldTerminateRental(boolean shouldTerminateRental) {
        HouseConfigVars.shouldTerminateRental = shouldTerminateRental;
    }

    public static long getRentalNoShowLimitMs() {
        return rentalNoShowLimit_ms;
    }

    public static void setRentalNoShowLimitMs(long rentalNoShowLimit_ms) {
        HouseConfigVars.rentalNoShowLimit_ms = rentalNoShowLimit_ms;
    }

    public static boolean usingSql() {
        return USE_SQL;
    }

    public static void setUseSql(boolean useSql) {
        USE_SQL = useSql;
    }
}
