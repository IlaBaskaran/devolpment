package com.livequake.disastersafetyalert;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
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
	private ListView listContacts;
	private static Context ctx;
	static final String beginText = "[LIGHTHOUSE ALERT] ";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_people, container, false);
		ctx = this.getActivity();
		
		/* Add buttons */
		Button bAC = (Button) view.findViewById(R.id.AddContacts);
		bAC.setOnClickListener(this);
		Button bSafe = (Button) view.findViewById(R.id.Safe);
		bSafe.setOnClickListener(this);
		Button bHelp = (Button) view.findViewById(R.id.Help);
		bHelp.setOnClickListener(this);
 
		//ListView
		listContacts = (ListView) view.findViewById(R.id.viewContacts);
		listContacts.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int pos, long arg) {
				String selected =(listContacts.getItemAtPosition(pos).toString());
				checkUp(selected);
			}
		});
		
		loadList();
		return view;
	}

	/* Load names from Alert Contact List into ListView */
	private void loadList() {
		/* New Database Helper */
		DBHelper dbHelper = new DBHelper(this.getActivity().getApplicationContext());

		List<String> ac = new ArrayList<String>();
		ac = dbHelper.getAllNames();
		
		listContacts.setAdapter(null);
		
		ArrayAdapter<String> contactAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, ac);
		listContacts.setAdapter(contactAdapter);
	}
	
	private void checkUp(String n) {
		CharSequence colors[] = new CharSequence[] {"Call", "Text", "Delete", "Cancel"};
		final String name = n;

		AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
		builder.setTitle("Check Up via Call or Text");
		builder.setItems(colors, new DialogInterface.OnClickListener() {
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		    	DBHelper d = new DBHelper(PeopleFragment.ctx.getApplicationContext());
		    	String num = d.getPhone(name);
        		
		        switch (which) {
		        	/* Call */
		        	case 0:
		        		//call
		        		break;
		        	/* Text */
		        	case 1:
		        		sendSMS(num, 2);
		        		break;
		        	/* Delete from Alert Contacts List and update ListView */
		        	case 2:
		        		SQLiteDatabase db = d.getWritableDatabase();
		        		db.delete(DSAContract.AlertContactTable.TABLE_NAME, "PhoneNumber" + " = \"" + num + "\";", null);
		        		loadList();
		        		break;
		        	/* Cancel Action */
		        	case 3: return;
		        }
		    }
		});
		builder.show();
	}

	private void sendSMS(String num, int state) {
		String message = "";
		switch (state) {
			/* Safe Message */
			case 0:
				message = "Just letting you know I'm safe.";
				break;
			/* Help Message */
			case 1:
				message = "Request help";
				break;
			/* CheckUp Message */
			case 2:
				message = "Hey, are you okay?";
				break;
		}
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(num, null, beginText + message, null, null);
	}
	
	@Override
	public void onClick(View v) {
		int state = 0;

		/* New Database Helper */
		DBHelper dbHelper = new DBHelper(this.getActivity().getApplicationContext());

		/* Get Numbers */
		List<String> ac = new ArrayList<String>();
		ac = dbHelper.getAllNumbers();

		/* Set Message State */
		switch (v.getId()) {
			case R.id.Safe:
				state = 0;
				break;
			case R.id.Help:
				state = 1;
				break;
			case R.id.AddContacts:
				state = 3;
				Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
				pickContactIntent.setType(Phone.CONTENT_TYPE);
				startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
				break;
		}
		loadList();
		
		/* Send to each in Alert Contacts List */
		if(ac.isEmpty()) {
			Toast q = Toast.makeText(PeopleFragment.ctx, "No contacts to alert!", Toast.LENGTH_LONG);
			q.show();
		}
		if(state != 3) {
			for(int i = 0; i < ac.size(); i++) {
				sendSMS(ac.get(i), state);
			}
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		/* Check which request it is that we're responding to */
		if (requestCode == PICK_CONTACT_REQUEST) {
			/* Make sure the request was successful */
			if (resultCode == this.getActivity().RESULT_OK) {
				/* Get the URI that points to the selected contact */
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
				
				Log.i("dbinput", name);
				Log.i("dbinput", number);
				
				/* New Database Helper */
				DBHelper dbHelper = new DBHelper(this.getActivity().getApplicationContext());//getContext());
				
				if(dbHelper.getPhone(name).isEmpty()) {
					SQLiteDatabase db = dbHelper.getWritableDatabase();

					/* Create insert entries */
					ContentValues values = new ContentValues();
					values.put(DSAContract.AlertContactTable.COLUMN_NAME_NAME_ID, name);
					values.put(DSAContract.AlertContactTable.COLUMN_NAME_PHONE, number);
			            
					/* Insert the new row, returning the primary key value of the new row */
					db.insert(DSAContract.AlertContactTable.TABLE_NAME, null, values);
				}
				else {
					Toast t = Toast.makeText(PeopleFragment.ctx, "Duplicate Contact", Toast.LENGTH_LONG);
					t.show();
				}
			}
		}
	}

	@Override
	public void onResume() {
		loadList();
		Log.i("test", "onResume called");
		super.onResume();
	}
	
	public String getName() {
		return "People";
	}
}
