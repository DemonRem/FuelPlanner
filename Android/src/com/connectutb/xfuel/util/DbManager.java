package com.connectutb.xfuel.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbManager extends SQLiteOpenHelper{
	
	/* Our database variables */
	private static final String DATABASE_NAME = "xFuelDB";
	private static final int DATABASE_VERSION = 1;
	/* Our tables and fields */
	private static final String TABLE_HISTORY= "list";
	private static final String HISTORY_ID = "id";
	private static final String HISTORY_AIRCRAFT = "aircraft";
	private static final String HISTORY_ORIG = "origin";
	private static final String HISTORY_DEST = "destination";
	private static final String HISTORY_UNIT = "unit";
	private static final String HISTORY_METAR = "metar";
	private static final String HISTORY_RULES = "rules";
	private Context context;
	
	//constructor
	public DbManager(Context context){
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	    this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//Create database and tables
		String CREATE_HISTORY_TABLE = "CREATE TABLE " + TABLE_HISTORY + "("
				+ HISTORY_ID + " INTEGER PRIMARY KEY," + HISTORY_AIRCRAFT + " TEXT,"
				+ HISTORY_ORIG + " TEXT," + HISTORY_DEST + " TEXT," + HISTORY_UNIT + " TEXT,"
				+ HISTORY_METAR + " TEXT," + HISTORY_RULES + " TEXT)";
		//Execute db query
		db.execSQL(CREATE_HISTORY_TABLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop table and create a new one
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
		
		//The create a new one
		onCreate(db);
		
	}
    
	public void addToHistory(String aircraft, String orig, String dest, boolean metar, String rules, String units){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(HISTORY_AIRCRAFT, aircraft);
		values.put(HISTORY_ORIG, orig);
		values.put(HISTORY_DEST, dest);
		values.put(HISTORY_UNIT, units);
		if (metar){
			values.put(HISTORY_METAR, "YES");
		}else{
			values.put(HISTORY_METAR, "NO");
		}
		values.put(HISTORY_RULES, rules);
		
		//Inserting the record
		db.insert(TABLE_HISTORY, null, values);
		db.close();
	}


}
