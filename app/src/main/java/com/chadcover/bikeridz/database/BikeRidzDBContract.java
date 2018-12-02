package com.chadcover.bikeridz.database;

public class BikeRidzDBContract {
    public static final String DB_NAME = "bikeridz.db";
    public static final int DB_VERSION = 1;

    public static final class BikeRidzContract {
        public static final String TABLE_NAME = "bikeshops";
        public static final String SHOP_ID = "_id";
        public static final String SHOP_NAME = "shop_name";
        public static final String STREET_NUMBER = "street_number";
        public static final String STREET_NAME = "street_name";
        public static final String CITY = "city";
        public static final String STATE = "state";
        public static final String ZIP = "zip";
        public static final String FULL_ADDRESS = "full_address";
        public static final String PHONE_NUMBER = "phone_number";
        public static final String LATITUDE = "latitude";
        public static final String LONGITUDE = "longitude";
        public static final String HOURS_MON = "hours_mon";
        public static final String HOURS_TUES = "hours_tues";
        public static final String HOURS_WED = "hours_wed";
        public static final String HOURS_THURS = "hours_thurs";
        public static final String HOURS_FRI = "hours_fri";
        public static final String HOURS_SAT = "hours_sat";
        public static final String HOURS_SUN = "hours_sun";
    }

    public static final String CREATE_BIKERIDZ_TABLE = "CREATE TABLE " +
            BikeRidzContract.TABLE_NAME + "(" +
            BikeRidzContract.SHOP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            BikeRidzContract.SHOP_NAME + " TEXT," +
            BikeRidzContract.STREET_NUMBER + " TEXT," +
            BikeRidzContract.STREET_NAME + " TEXT," +
            BikeRidzContract.CITY + " TEXT," +
            BikeRidzContract.STATE + " TEXT," +
            BikeRidzContract.ZIP + " TEXT," +
            BikeRidzContract.LATITUDE + " REAL," +
            BikeRidzContract.LONGITUDE + " REAL," +
            BikeRidzContract.FULL_ADDRESS + " TEXT," +
            BikeRidzContract.PHONE_NUMBER + " TEXT," +
            BikeRidzContract.HOURS_MON + " TEXT," +
            BikeRidzContract.HOURS_TUES + " TEXT," +
            BikeRidzContract.HOURS_WED + " TEXT," +
            BikeRidzContract.HOURS_THURS + " TEXT," +
            BikeRidzContract.HOURS_FRI + " TEXT," +
            BikeRidzContract.HOURS_SAT + " TEXT," +
            BikeRidzContract.HOURS_SUN + " TEXT)";

    public static final String DROP_PROJECT_TABLE = "DROP TABLE IF EXISTS "
            + BikeRidzContract.TABLE_NAME;
}

