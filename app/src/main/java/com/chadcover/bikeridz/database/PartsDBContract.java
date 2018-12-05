package com.chadcover.bikeridz.database;

public class PartsDBContract {
    public static final String DB_NAME = "parts.db";
    public static final int DB_VERSION = 1;

    public static final class PartsContract {
        public static final String TABLE_NAME = "parts.db";
        public static final String PART_ID = "_id";
        public static final String BIKE_ID = "bike_id";
        public static final String PART_NAME = "part_name";
        public static final String PART_DESCRIPTION = "part_description";
        public static final String PART_MANUFACTURER = "part_manufacturer";
        public static final String PART_TYPE = "part_type";
    }

    public static final String CREATE_PARTS_TABLE = "CREATE TABLE " +
            PartsContract.TABLE_NAME + "(" +
            PartsContract.PART_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            PartsContract.BIKE_ID + " INTEGER," +
            PartsContract.PART_NAME + " TEXT," +
            PartsContract.PART_MANUFACTURER + " TEXT," +
            PartsContract.PART_TYPE + " TEXT," +
            "FOREIGN KEY(" + PartsContract.PART_ID + ") REFERENCES " +
            BikesDBContract.BikeContract.TABLE_NAME + "(" + BikesDBContract.BikeContract.BIKE_ID + "))";

    public static final String DROP_PARTS_TABLE = "DROP TABLE IF EXISTS "
            + PartsContract.TABLE_NAME;


}
