package com.livequake.disastersafetyalert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class PlacesFragment extends Fragment{

	// All static variables
	static final String URL = "http://earthquake.usgs.gov/eqcenter/catalogs/1day-M2.5.xml";
	// XML node keys
	static final String KEY_ENTRY = "entry";
	static final String KEY_TITLE = "title"; // parent node
	static final String KEY_LOCATION = "georss:point";
	//static final String KEY_THUMB_URL = "thumb_url";
	
	
	private ListView list_view;
	private ListAdapter adapter;
    
	  @Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container,
	      Bundle savedInstanceState) {
	    View view = inflater.inflate(R.layout.activity_places,
	        container, false);
   
	    
		ArrayList<HashMap<String, String>> DEventList = new ArrayList<HashMap<String, String>>();

		XMLParser parser = new XMLParser();
		String xml = parser.getXmlFromUrl(URL); // getting XML from URL
		Document doc = parser.getDomElement(xml); // getting DOM element
		
		NodeList nl = doc.getElementsByTagName(KEY_ENTRY);
		// looping through all song nodes <song>
		for (int i = 0; i < nl.getLength(); i++) {
			// creating new HashMap
			HashMap<String, String> map = new HashMap<String, String>();
			Element e = (Element) nl.item(i);
			// adding each child node to HashMap key => value
			map.put(KEY_TITLE, parser.getValue(e, KEY_TITLE));
			map.put(KEY_LOCATION, parser.getValue(e, KEY_LOCATION));

			// adding HashList to ArrayList
			DEventList.add(map);
		}
		
		
		//list
		list_view=(ListView) view.findViewById(R.id.place_d_list);
		
		// Getting adapter by passing xml data ArrayList
        adapter=new ListAdapter(getActivity(), DEventList);        
        list_view.setAdapter(adapter);
        
        // Click event for single list row
        list_view.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
							

			}
		});	
        
        
        
	    return view;
	  }
	  
	  
	  public String getName() {
		  return "Places";
	  }





}
