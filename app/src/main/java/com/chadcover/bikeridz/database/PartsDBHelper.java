package com.chadcover.bikeridz.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.chadcover.bikeridz.bike.Part;
import java.util.List;
import java.util.ListIterator;

public class PartsDBHelper extends SQLiteOpenHelper {
    private Context context;
    protected List<Part> parts;

    public PartsDBHelper(Context context) {
        super(context, PartsDBContract.DB_NAME, null, PartsDBContract.DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PartsDBContract.CREATE_PARTS_TABLE);
        setParts(this.context);
        updateDatabase(db, 0, PartsDBContract.DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(PartsDBContract.DROP_PARTS_TABLE);
        onCreate(db);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    // helper methods
    ///////////////////////////////////////////////////////////////////////////////////////////

    public static void insertPart(SQLiteDatabase db, String partName, String partDescription,
                  String partManufacturer, String typeOfPart, int bikeId ) {
        ContentValues parts = new ContentValues();
        parts.put(PartsDBContract.PartsContract.PART_NAME, partName);
        parts.put(PartsDBContract.PartsContract.PART_DESCRIPTION, partDescription);
        parts.put(PartsDBContract.PartsContract.PART_MANUFACTURER, partManufacturer);
        parts.put(PartsDBContract.PartsContract.PART_TYPE, typeOfPart);
        parts.put(PartsDBContract.PartsContract.BIKE_ID, bikeId);
    }


    public void updateDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            List<Part> mParts = getParts();
            ListIterator<Part> mPartsIter = mParts.listIterator();
            while (mPartsIter.hasNext()) {
                Part part = mPartsIter.next();
                insertPart(db, part.getName(), part.getDescription(), part.getManufacturer(),
                    part.getTypeOfPartString(), part.getBikeId());
            }
        }
    }

    private void setParts(Context context) {
        this.parts = new Part().createSampleParts();
    }

    private List<Part> getParts() {
        return this.parts;
    }
}
