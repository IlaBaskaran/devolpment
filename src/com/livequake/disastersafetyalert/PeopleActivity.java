package com.livequake.disastersafetyalert;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class PeopleActivity extends Activity {
	
	private ListView listContacts;
	public static ArrayList<String> NotifyContacts = new ArrayList<String>();

	static final int PICK_CONTACT_REQUEST = 1;
	private ArrayList<String> Contacts = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_people);
		
//		//ListView
//		listContacts = (ListView) findViewById(R.id.viewContacts);
//		loadList();

	}
	
//	private void loadList() {
//		//New Database Helper
//		DBHelper dbHelper = new DBHelper(getApplicationContext());//getContext());
//		//SQLiteDatabase db = dbHelper.getReadableDatabase();
//		
//		List<String> ac = new ArrayList<String>();
//		ac = dbHelper.getAllContacts();
//			
//		ArrayAdapter<String> contactAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ac);
//		listContacts.setAdapter(contactAdapter);
//	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.people, menu);
		return true;
	}
	
//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//	    // Check which request it is that we're responding to
//	    if (requestCode == PICK_CONTACT_REQUEST) {
//	        // Make sure the request was successful
//	        if (resultCode == RESULT_OK) {
//	            // Get the URI that points to the selected contact
//	            Uri contactUri = data.getData();
//	            // We only need the NUMBER column, because there will be only one row in the result
//	            String[] projection = {Phone.NUMBER};
//
//	            // Perform the query on the contact to get the NUMBER column
//	            Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);
//	            cursor.moveToFirst();
//
//	            // Retrieve the phone number from the NUMBER column
//	            int column = cursor.getColumnIndex(Phone.NUMBER);
//	            String number = cursor.getString(column);
//	            
//	            //int col = cursor.getColumnIndex(Phone.DISPLAY_NAME_PRIMARY);
//	           // String name = cursor.getString(col);
//
//	            // Save number
//	            //Contacts.add(number);
//	            
//	            //New Database Helper
//	    		DBHelper dbHelper = new DBHelper(getApplicationContext());//getContext());
//	    		SQLiteDatabase db = dbHelper.getWritableDatabase();
//
//	            // Create insert entries
//	            ContentValues values = new ContentValues();
//	            values.put(DSAContract.AlertContactTable.COLUMN_NAME_PHONE, number);
//	            
//	            // Insert the new row, returning the primary key value of the new row
//	            long newRowId;
//	            newRowId = db.insert(
//	                     DSAContract.AlertContactTable.TABLE_NAME,
//	                     null,
//	                     values);
//	        }
//	    }
//	}


}
