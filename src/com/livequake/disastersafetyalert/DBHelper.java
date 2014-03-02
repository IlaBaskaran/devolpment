package com.livequake.disastersafetyalert;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	public static final  int    DATABASE_VERSION   = 1;
    public static final  String DATABASE_NAME      = "DSAdb.db";
    
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    // Method is called during creation of the database
    @Override
	public void onCreate(SQLiteDatabase db) {
    	db.execSQL(DSAContract.AlertContactTable.SQL_CREATE_ACDB);		
	}
    
    // Method is called during upgrade of the database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DSAContract.AlertContactTable.SQL_DELETE_ACDB);
        onCreate(db);
	}
	
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public List<String> getAllContacts() {
		List<String> contactList = new ArrayList<String>();
	    
	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(DSAContract.AlertContactTable.SQL_GET_ALL, null);
	 
	    // looping through all rows and adding to list
	    if (cursor.moveToFirst()) {
	        do {
	            contactList.add(cursor.getString(1));
	        } while (cursor.moveToNext());
	    }
	 
	    cursor.close();
	    db.close();
	    // return contact list
	    return contactList;
	}
}
