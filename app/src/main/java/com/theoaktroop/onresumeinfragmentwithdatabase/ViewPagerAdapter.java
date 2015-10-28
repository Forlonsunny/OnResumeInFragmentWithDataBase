package com.theoaktroop.onresumeinfragmentwithdatabase;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yeahi on 10/26/2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    //Added
    private Map<Integer,String> fragmentTag;
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    //Added
    private FragmentManager fragmentManager;

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
        fragmentManager = manager;
        fragmentTag = new HashMap<Integer,String>();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    //Added

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object obj =  super.instantiateItem(container, position);
        if(obj instanceof Fragment){
            Fragment fragment = (Fragment) obj;
            String tag = fragment.getTag();
            fragmentTag.put(position,tag);
        }
        return obj;
    }
    //Added
    public Fragment getFragment(int position){
        String tag = fragmentTag.get(position);
        if(tag == null){
            return null;
        }
        return fragmentManager.findFragmentByTag(tag);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}