package com.livequake.disastersafetyalert;


import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {
    
    private FragmentActivity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
   // public ImageLoader imageLoader; 
    
    
    /*
     * accepts a list of full disaster list
     * 
     * 
     * */
    public ListAdapter(FragmentActivity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       // imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.list_row, null);

        TextView entry = (TextView)vi.findViewById(R.id.entry); // entry
        TextView title = (TextView)vi.findViewById(R.id.title); //title
        TextView location = (TextView)vi.findViewById(R.id.location); // location
       // ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image
        
        HashMap<String, String> d_list = new HashMap<String, String>();
        d_list = data.get(position);
        
        // Setting all values in listview
        

    	
        entry.setText(d_list.get(PlacesFragment.KEY_ENTRY));
        title.setText(d_list.get(PlacesFragment.KEY_TITLE));
        location.setText(d_list.get(PlacesFragment.KEY_LOCATION));
        //imageLoader.DisplayImage(song.get(CustomizedListViewPlaces.KEY_THUMB_URL), thumb_image);
        return vi;
    }
}