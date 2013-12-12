package com.example.meeting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class PersonDatabaseHelper {
	 private static final String TAG = PersonDatabaseHelper.class.getSimpleName();
	 private static final int DATABASE_VERSION = 1;
	    private static final String DATABASE_NAME = "mydatabaseDB";

	    // table configuration
	    private static final String TABLE_NAME = "meeting";         // Table name
	    private static final String PERSON_TABLE_COLUMN_ID = "_id";     // a column named "_id" is required for cursor
	    private static final String PERSON_TABLE_COLUMN_NAME = "person_name";
	    private static final String PERSON_TABLE_COLUMN_PERSON = "person_pin";
	    private static final String PERSON_TABLE_COLUMN_PLACE = "person_place";
	    private static final String PERSON_TABLE_COLUMN_DATE = "person_date";
	    private static final String PERSON_TABLE_COLUMN_NO = "person_no";
	    private DatabaseOpenHelper openHelper;
	    private SQLiteDatabase database;
	    public PersonDatabaseHelper(Context aContext) {
			
	        openHelper = new DatabaseOpenHelper(aContext);
	        database = openHelper.getWritableDatabase();
	    }
	    public void insertData (String aPersonName, String aPersonPin, String aPersonPlace, String aPersonDate, String aPersonno ) {

	        // we are using ContentValues to avoid sql format errors

	        ContentValues contentValues = new ContentValues();

	        contentValues.put(PERSON_TABLE_COLUMN_NAME, aPersonName);
	        contentValues.put(PERSON_TABLE_COLUMN_PERSON, aPersonPin);
	        contentValues.put(PERSON_TABLE_COLUMN_PLACE, aPersonPlace);
	        contentValues.put(PERSON_TABLE_COLUMN_DATE, aPersonDate);
	        contentValues.put(PERSON_TABLE_COLUMN_NO, aPersonno);

	        database.insert(TABLE_NAME, null, contentValues);
	    }

	    public Cursor getAllData () {

	        String buildSQL = "SELECT * FROM " + TABLE_NAME;

	        Log.d(TAG, "getAllData SQL: " + buildSQL);

	        return database.rawQuery(buildSQL, null);
	    }

	    private class DatabaseOpenHelper extends SQLiteOpenHelper {

	        public DatabaseOpenHelper(Context aContext) {
	            super(aContext, DATABASE_NAME, null, DATABASE_VERSION);
	        }

	        @Override
	        public void onCreate(SQLiteDatabase sqLiteDatabase) {
	            // Create your tables here

	            String buildSQL = "CREATE TABLE " + TABLE_NAME + "( " + PERSON_TABLE_COLUMN_ID + " INTEGER PRIMARY KEY, " +
	                    PERSON_TABLE_COLUMN_NAME + " TEXT, " + PERSON_TABLE_COLUMN_PERSON + " TEXT, " + PERSON_TABLE_COLUMN_PLACE + " TEXT, " + PERSON_TABLE_COLUMN_DATE + " TEXT, " + PERSON_TABLE_COLUMN_NO + " TEXT )";

	            Log.d(TAG, "onCreate SQL: " + buildSQL);

	            sqLiteDatabase.execSQL(buildSQL);
	        }

	        @Override
	        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
	            // Database schema upgrade code goes here

	            String buildSQL = "DROP TABLE IF EXISTS " + TABLE_NAME;

	            Log.d(TAG, "onUpgrade SQL: " + buildSQL);

	            sqLiteDatabase.execSQL(buildSQL);       // drop previous table

	            onCreate(sqLiteDatabase);               // create the table from the beginning
	        }
	    }


}
