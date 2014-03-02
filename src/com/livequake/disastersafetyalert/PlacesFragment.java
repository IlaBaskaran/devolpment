package com.livequake.disastersafetyalert;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PlacesFragment extends Fragment {
	
	  @Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container,
	      Bundle savedInstanceState) {
	    View view = inflater.inflate(R.layout.activity_places,
	        container, false);
	    return view;
	  }
	  
	  public String getName() {
		  return "Places";
	  }

}
