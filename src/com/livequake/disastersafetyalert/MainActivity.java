package com.livequake.disastersafetyalert;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private String[] bNames = {"History", "Notify", "WatchArea", "What", "CheckFriends", "Settings"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button bHist = (Button) findViewById(R.id.History);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels;
        int buttonWidth = width/2;
        
        Button bNotify = (Button) findViewById(R.id.Notify);
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        width = displaymetrics.widthPixels;
        buttonWidth = width/2;
        
        Button bWatch = (Button) findViewById(R.id.WatchArea);
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        width = displaymetrics.widthPixels;
        buttonWidth = width/2;
        
        Button bWhat = (Button) findViewById(R.id.What);
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        width = displaymetrics.widthPixels;
        buttonWidth = width/2;
        
        Button bCheck = (Button) findViewById(R.id.CheckFriends);
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        width = displaymetrics.widthPixels;
        buttonWidth = width/2;
        
        Button bSettings = (Button) findViewById(R.id.Settings);
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        width = displaymetrics.widthPixels;
        buttonWidth = width/2;
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
