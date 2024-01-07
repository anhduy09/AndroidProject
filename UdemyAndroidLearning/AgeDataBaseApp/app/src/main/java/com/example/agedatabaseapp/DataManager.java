package com.example.agedatabaseapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataManager {

    private final String TAG = this.getClass().getSimpleName();

    private SQLiteDatabase db;

    // Next we have a pblic staic final string for
    // each row/table that we need to refer botg
    // inside and outside this class

    public static final String TABLE_ROW_ID = "_id";
    public static final String TABLE_ROW_NAME = "name";
    public static final String TABLE_ROW_AGE = "age";

    // Next we have a private static final strings for
    // each row/table that we need to refer to just
    // inside this class

    private static final String DB_NAME = "name_age_db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_N_AND_A = "name_and_age";

    public DataManager(Context context) {
        // Create an instance of our internal
        //CustomSQLiteOpenHelper
        CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(context);

        //get a writable database
        db = helper.getWritableDatabase();
    }

    public void insert(String name, String age) {
        // add all details to the tabe
        String query = "INSERT INTO " + TABLE_N_AND_A + " (" +
                TABLE_ROW_NAME + ", " +
                TABLE_ROW_AGE +
                ") " +
                "VALUES (" +
                "'" + name + "'" + ", " +
                "'" + age + "'" +
                ");";
        Log.i(TAG, "insert() = " + query);
        db.execSQL(query);
    }

    public void delete(String name) {
        // delete the details from the table if already exist
        String query = "DELETE FROM " + TABLE_N_AND_A +
                " wHERE " + TABLE_ROW_NAME +
                " = '" + name + "';";
        Log.i(TAG, "delete() = "+query);
        db.execSQL(query);
    }

    //get all the records
    public Cursor selectAll() {
        Cursor c = db.rawQuery("SELECT *" + " from "+
                TABLE_N_AND_A, null);
        return c;
    }

    //Find a specific record
    public Cursor searchName(String name) {
        String query = "SELECT "+
                TABLE_ROW_ID + ", " +
                TABLE_ROW_NAME +
                ", " + TABLE_ROW_AGE +
                " from " +
                TABLE_N_AND_A + " WHERE " +
                TABLE_ROW_NAME + " = '" +name + ",;";
        Log.i(TAG, "SearchName() "+ query);
        Cursor c = db.rawQuery(query, null);
        return c;
    }

    private class CustomSQLiteOpenHelper extends SQLiteOpenHelper {
        public CustomSQLiteOpenHelper (Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }
        //this runs the first time the database is created


        @Override
        public void onCreate(SQLiteDatabase db) {
            //create a table for photos and all their details
            String newTablequeryString = "create table "
                    + TABLE_N_AND_A + " ("
                    + TABLE_ROW_ID
                    + " integer primary key autoincrement not null,"
                    + TABLE_ROW_NAME
                    + " text not null,"
                    + TABLE_ROW_AGE
                    + " text not null);";
            db.execSQL(newTablequeryString);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //not need in this app, but we must still override it
        }
    }
}
