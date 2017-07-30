package com.sumit.dell_pc.cfg.inventory.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by DELL_PC on 7/29/2017.
 */

public class ViewPageAdaptor extends FragmentPagerAdapter {
    ArrayList<Fragment> fragments = new ArrayList<>();
    ArrayList<String> tabTitle = new ArrayList<String>();

    public ViewPageAdaptor(FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<String> tabTitle) {
        super(fm);
        this.fragments = fragments;
        this.tabTitle = tabTitle;
    }

    public void addFragments(Fragment fragments, String titles) {
        this.fragments.add(fragments);
        this.tabTitle.add(titles);
    }

    public ViewPageAdaptor(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
