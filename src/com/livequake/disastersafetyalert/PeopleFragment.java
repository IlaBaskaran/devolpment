package com.livequake.disastersafetyalert;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class PeopleFragment extends Fragment implements OnClickListener {

	static final int PICK_CONTACT_REQUEST = 1;
	private ArrayList<String> Contacts = new ArrayList<String>();
	private ListView listContacts;
	private static Context ctx;
	
	  @Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		  View view = inflater.inflate(R.layout.activity_people, container, false);
		  
		  Button bAC = (Button) view.findViewById(R.id.AddContacts);
		  bAC.setOnClickListener(this);
		  Button bSafe = (Button) view.findViewById(R.id.Safe);
		  bSafe.setOnClickListener(this);
		  Button bHelp = (Button) view.findViewById(R.id.Help);
		  bHelp.setOnClickListener(this);
		  
		  ctx = this.getActivity();
		  
			//ListView
			listContacts = (ListView) view.findViewById(R.id.viewContacts);
			listContacts.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					Toast toast = Toast.makeText(PeopleFragment.ctx, "CLICKED", Toast.LENGTH_LONG);
					toast.show();
				}
				
			});
			loadList();
		  return view;
	  }
	  
		private void loadList() {
			//New Database Helper
			DBHelper dbHelper = new DBHelper(this.getActivity().getApplicationContext());
			
			List<String> ac = new ArrayList<String>();
			ac = dbHelper.getAllContacts();
			
			Log.i("fjdksl", "");
				
			ArrayAdapter<String> contactAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, ac);
			listContacts.setAdapter(contactAdapter);
		}

	  
		@Override
		public void onClick(View v) {
			String message = "";

			SmsManager sms = SmsManager.getDefault();
			
			switch (v.getId()) {
	        	case R.id.Safe:
	        		message = "I'm safe!";
	        		break;
	        	case R.id.Help:
	        		message = "Request help!";
	        		break;
	        	case R.id.AddContacts:
	        	    Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
	        	    pickContactIntent.setType(Phone.CONTENT_TYPE);
	        	    startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
	        	    break;
			}
			
			for(int i = 0; i < Contacts.size(); i++) {
				sms.sendTextMessage(Contacts.get(i), null, message, null, null);
			}
			
		}
		
		@Override
		public void onActivityResult(int requestCode, int resultCode, Intent data) {
		    // Check which request it is that we're responding to
		    if (requestCode == PICK_CONTACT_REQUEST) {
		        // Make sure the request was successful
		        if (resultCode == this.getActivity().RESULT_OK) {
		            // Get the URI that points to the selected contact
		            Uri contactUri = data.getData();
		            /* Get Display Name and Number */
		            String[] projection = {Phone.DISPLAY_NAME, Phone.NUMBER};

		            /* Perform the query on the contact to get the NUMBER column */
		            Cursor cursor = this.getActivity().getContentResolver().query(contactUri, projection, null, null, null);
		            cursor.moveToFirst();

		            /* Retrieve the name and phone number from the correct columns */
		            int nameCol = cursor.getColumnIndex(Phone.DISPLAY_NAME);
		            int numCol = cursor.getColumnIndex(Phone.NUMBER);
		            String name = cursor.getString(nameCol);
		            String number = cursor.getString(numCol);
		            
		            Log.i("stuff", name);
	            
		            //New Database Helper
		    		DBHelper dbHelper = new DBHelper(this.getActivity().getApplicationContext());//getContext());
		    		SQLiteDatabase db = dbHelper.getWritableDatabase();

		            // Create insert entries
		            ContentValues values = new ContentValues();
		            values.put(DSAContract.AlertContactTable.COLUMN_NAME_NAME_ID, name);
		            values.put(DSAContract.AlertContactTable.COLUMN_NAME_PHONE, number);
		            
		            // Insert the new row, returning the primary key value of the new row
		            long newRowId;
		            newRowId = db.insert(
		                     DSAContract.AlertContactTable.TABLE_NAME,
		                     null,
		                     values);
		        }
		    }
		}

	  
	  public String getName() {
		  return "People";
	  }
}
