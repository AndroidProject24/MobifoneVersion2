package com.toan_itc.mobifone.ui.fragment.khoso;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.bumptech.glide.Glide;
import com.toan_itc.mobifone.R;
import com.toan_itc.mobifone.intdef.KhoSimIndexDef;
import com.toan_itc.mobifone.intdef.KhosimDef;
import com.toan_itc.mobifone.intdef.StringDef;
import com.toan_itc.mobifone.libs.view.StateLayout;
import com.toan_itc.mobifone.ui.adapter.khoso.MyPagerAdapter;
import com.toan_itc.mobifone.ui.fragment.BaseFragment;
import com.toan_itc.mobifone.utils.Constant;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.hugeterry.coordinatortablayout.CoordinatorTabLayout;


public class KhosoFragment extends BaseFragment {
  @BindView(R.id.viewPager)
  ViewPager mViewPager;
  @BindView(R.id.coordinatortablayout)
  CoordinatorTabLayout mCoordinatortablayout;
  private ArrayList<Fragment> mFragments;
  private String[] mTitles=new String[4];
  private Context mContext;

  public static KhosoFragment newInstance(String type) {
    KhosoFragment fragment=new KhosoFragment();
    Bundle bundle=new Bundle();
    bundle.putString(StringDef.BUNDLE_DATA, type);
    fragment.setArguments(bundle);
    return fragment;
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    mContext = context;
  }

  @Override
  protected String getTAG() {
    return this.getClass().getSimpleName();
  }

  @Override
  protected void initViews() {
    initFragments();
    initViewPager();
    toolbarTitleListener.hideToolBar(true);
  }

  @Override
  protected int setLayoutResourceID() {
    return R.layout.khoso_fragment;
  }

  @Override
  protected void initData() {
    int[] colorArray = new int[]{
            android.R.color.holo_blue_light,
            android.R.color.holo_red_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_green_light};
    mCoordinatortablayout
            .setTitle(getString(R.string.kho_so))
            .setBackEnable(true)
            .setContentScrimColorArray(colorArray)
            .setLoadHeaderImagesListener((imageView, tab) -> Glide.with(mContext).load(Constant.URL_IMAGE).into(imageView))
            .setupWithViewPager(mViewPager);
  }

  @Override
  protected StateLayout getLoadingTargetView() {
    return ButterKnife.findById(getActivity(),R.id.stateLayout);
  }

  private void initFragments() {
    mFragments = new ArrayList<>();
    mTitles= new String[]{getString(KhosimDef.SIM_TRA_TRUOC), getString(KhosimDef.SIM_TRA_SAU), getString(KhosimDef.SIM_TRA_TRUOC_DEP), getString(KhosimDef.SIM_DEP)};
    mFragments.add(DataFragment.newInstance(KhosimDef.SIM_TRA_TRUOC));
    mFragments.add(DataFragment.newInstance(KhosimDef.SIM_TRA_SAU));
    mFragments.add(DataFragment.newInstance(KhosimDef.SIM_TRA_TRUOC_DEP));
    mFragments.add(DataFragment.newInstance(KhosimDef.SIM_DEP));
  }

  private void initViewPager() {
    mViewPager.setOffscreenPageLimit(4);
    mViewPager.setAdapter(new MyPagerAdapter(getActivity().getSupportFragmentManager(), mFragments, mTitles));
    showScreen();
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    toolbarTitleListener.hideToolBar(false);
    removePages();
  }
  public void removePages() {
    mFragments.clear();
  }

  private void showScreen(){
    String type=getArguments().getString(StringDef.BUNDLE_DATA);
    if(type!=null) {
      if (type.equalsIgnoreCase(KhoSimIndexDef.SIM_TRA_TRUOC_DEP))
          mViewPager.setCurrentItem(1);
      else if(type.equalsIgnoreCase(KhoSimIndexDef.SIM_TRA_SAU))
        mViewPager.setCurrentItem(2);
      else if(type.equalsIgnoreCase(KhoSimIndexDef.SIM_DEP))
        mViewPager.setCurrentItem(3);
      else
        mViewPager.setCurrentItem(0);
    }
  }
}

