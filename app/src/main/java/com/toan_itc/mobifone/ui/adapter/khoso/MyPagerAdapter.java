package com.toan_itc.mobifone.ui.adapter.khoso;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.toan_itc.mobifone.intdef.KhosimDef;
import com.toan_itc.mobifone.ui.fragment.khoso.DataFragment;

/**
 * Created by hugeterry(http://hugeterry.cn)
 * Date: 16/1/28 17:24
 */
public class MyPagerAdapter extends FragmentStatePagerAdapter {
    private String[] mTitles;

    public MyPagerAdapter(FragmentManager fm, String[] mTitles) {
        super(fm);
        this.mTitles = mTitles;
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        DataFragment mFragments=null;
        switch (position){
            case 0:
              mFragments=DataFragment.newInstance(KhosimDef.SIM_TRA_TRUOC);
                break;
            case 1:
              mFragments=DataFragment.newInstance(KhosimDef.SIM_TRA_SAU);
                break;
            case 2:
              mFragments=DataFragment.newInstance(KhosimDef.SIM_TRA_TRUOC_DEP);
                break;
            case 3:
              mFragments=DataFragment.newInstance(KhosimDef.SIM_DEP);
                break;
        }
        return mFragments;
    }

}
