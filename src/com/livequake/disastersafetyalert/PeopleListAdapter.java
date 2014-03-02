package com.livequake.disastersafetyalert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PeopleListAdapter extends BaseAdapter {
    
    private FragmentActivity activity;
    public static ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    public Map<String, Integer> safetyMap = new HashMap<String, Integer>();
    
    /* accepts list of alert contact info */
    public PeopleListAdapter(FragmentActivity a, ArrayList<HashMap<String, String>> d) {
        /* Set Map for Safety State Images */
        safetyMap.put("Safe", R.drawable.green_dot);
        safetyMap.put("Help", R.drawable.red_dot);
        safetyMap.put("Waiting", R.drawable.grey_dot);
        
        /* Get Info*/
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
        if(convertView==null) {
            vi = inflater.inflate(R.layout.list_row, null);
        }
        
        TextView entry = (TextView)vi.findViewById(R.id.date); // entry
        TextView title = (TextView)vi.findViewById(R.id.title); //title
        TextView location = (TextView)vi.findViewById(R.id.location); // location
        ImageView image = (ImageView)vi.findViewById(R.id.list_image);

        HashMap<String, String> d_list = new HashMap<String, String>();
        d_list = data.get(position);
        
        // Setting all values in listview
        //entry.setText(d_list.get(""));
        title.setText(d_list.get("name"));
        //location.setText(d_list.get(PlacesFragment.KEY_LOCATION));
        int state = Integer.parseInt(d_list.get("safety"));
        Log.i("state", d_list.get("safety"));
        switch (state) {
        	case 0:
        		image.setImageResource(safetyMap.get("Safe"));
        		break;
        	case 1:
        		image.setImageResource(safetyMap.get("Help"));
        		break;
        	case 2:
        		image.setImageResource(safetyMap.get("Waiting"));
        		break;
        } 
        //imageLoader.DisplayImage(song.get(CustomizedListViewPlaces.KEY_THUMB_URL), thumb_image);
        return vi;
    }
}