package com.livequake.disastersafetyalert;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class MFragPagerAdapter extends FragmentPagerAdapter {
	
	private static final int FRAGMENT_COUNT = 2;
	private List<Fragment> mFragments = new ArrayList<Fragment>();
	private FragmentManager mFM;
	public boolean disableSwipe = false;
	
	public MFragPagerAdapter(FragmentManager fm) {
		super(fm);
		mFM = fm;
		
		//add fragments
		mFragments.add(new PlacesFragment());
		mFragments.add(new PeopleFragment());

	}

	@Override
	public Fragment getItem(int arg0) {
		Fragment fragment = mFragments.get(arg0);
		disableSwipe = false;
		return fragment;
	}

	@Override
	public int getCount() {
		return FRAGMENT_COUNT;
	}
	
    public Fragment getActiveFragment(ViewPager container, int pos) {
    	String name = "android:switcher:" + container.getId() + ":" + pos;
    	return  mFM.findFragmentByTag(name);
    }

	
	@Override
	public CharSequence getPageTitle(int position) {
		switch (position) {
			case 0:
				return "Places";
			case 1:
				return "People";
		}
		return null;
    }
}
