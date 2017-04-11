package com.toan_itc.mobifone.ui.adapter.theloai;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.toan_itc.mobifone.ui.fragment.km.CTKMSodepFragment;
import com.toan_itc.mobifone.ui.fragment.km.CTKMTrasauFragment;
import com.toan_itc.mobifone.ui.fragment.km.KMNaptheFragment;

/**
 * Created by hugeterry(http://hugeterry.cn)
 * Date: 16/1/28 17:24
 */
public class KMPagerAdapter extends FragmentStatePagerAdapter {
  private String[] mTitles;

  public KMPagerAdapter(FragmentManager fm, String[] mTitles) {
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
    Fragment mFragments=null;
    switch (position){
      case 0:
        mFragments=CTKMTrasauFragment.newInstance();
        break;
      case 1:
        mFragments=CTKMSodepFragment.newInstance();
        break;
      case 2:
        mFragments=KMNaptheFragment.newInstance();
        break;
    }
    return mFragments;
  }

}
