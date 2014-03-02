package com.livequake.disastersafetyalert;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;

public class MainFragmentActivity extends FragmentActivity implements ActionBar.TabListener {
	
	// Declare Tab Variable
	Tab tab;
	MFragPagerAdapter mMFragPagerAdapter;
	ViewPager mViewPager;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_fragment);
		
		final ActionBar actionBar = getActionBar();
		
		/* Specify that tabs should be displayed in the action bar */
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	    // Specify that the Home/Up button should not be enabled, since there is no hierarchical parent.
	 	//actionBar.setHomeButtonEnabled(false);	//must set to API 14
	    
        mMFragPagerAdapter = new MFragPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pagerMain);
        mViewPager.setAdapter(mMFragPagerAdapter);

	    /* Add tab for each defined in adapter */
	    for (int i = 0; i < mMFragPagerAdapter.getCount(); i++) {
	        actionBar.addTab(
	                actionBar.newTab()
	                        .setText(mMFragPagerAdapter.getPageTitle(i))
	                        .setTabListener(this));
	    }
	    
	    /* Change highlight on tabs to reflect which is chosen */
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {}
	
	@Override
	public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {}

}
