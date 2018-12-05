package com.chadcover.bikeridz.database;

public class BikesDBContract {
    public static final String DB_NAME = "bikes.db";
    public static final int DB_VERSION = 1;

    public static final class BikeContract {
        public static final String TABLE_NAME = "bikes";
        public static final String BIKE_ID = "_id";
        public static final String BIKE_NAME = "bike_name";
        public static final String BIKE_DESCRIPTION = "bike_description";
        public static final String BIKE_COLOR = "bike_color";
        public static final String BIKE_HEIGHT = "bike_height";
        public static final String BIKE_MATERIAL = "bike_material";
        public static final String BIKE_TYPE = "bike_type";
        public static final String BIKE_MANUFACTURER = "bike_manufacturer";
        public static final String BIKE_SERIAL_NUMBER = "bike_serial_number";
    }

    public static final String CREATE_BIKE_INVENTORY_TABLE = "CREATE TABLE " +
            BikeContract.TABLE_NAME + "(" +
            BikeContract.BIKE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            BikeContract.BIKE_NAME + " TEXT," +
            BikeContract.BIKE_DESCRIPTION + " TEXT," +
            BikeContract.BIKE_COLOR + " TEXT," +
            BikeContract.BIKE_HEIGHT + " TEXT," +
            BikeContract.BIKE_MATERIAL + " TEXT," +
            BikeContract.BIKE_TYPE + " TEXT," +
            BikeContract.BIKE_MANUFACTURER + " TEXT," +
            BikeContract.BIKE_SERIAL_NUMBER + " TEXT)";

    public static final String DROP_PROJECT_TABLE = "DROP TABLE IF EXISTS "
            + BikeContract.TABLE_NAME;

}

