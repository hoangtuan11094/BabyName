package com.hoangtuan.moduletabhostpagesviews.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.hoangtuan.moduletabhostpagesviews.popular.TabAllFragment;
import com.hoangtuan.moduletabhostpagesviews.popular.TabBoyFragment;
import com.hoangtuan.moduletabhostpagesviews.popular.TabGirlFragment;

/**
 * Created by atbic on 17/4/2018.
 */

public class PagesAdapter extends FragmentStatePagerAdapter {
    int mNoOfTabs;
    String year;
    public PagesAdapter(FragmentManager fm,int mNoOfTabs,String year) {
        super(fm);
        this.mNoOfTabs=mNoOfTabs;
        this.year=year;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                TabAllFragment tabAllFragment=TabAllFragment.newInstance(year);
                return tabAllFragment;
            case 1:
                TabBoyFragment tabBoyFragment= TabBoyFragment.newInstance(year);
                return tabBoyFragment;
            case 2:
                TabGirlFragment tabGirlFragment= TabGirlFragment.newInstance(year);
                return tabGirlFragment;
                default:
                    return null;
        }

    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
