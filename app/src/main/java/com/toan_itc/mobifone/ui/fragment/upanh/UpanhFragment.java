package com.toan_itc.mobifone.ui.fragment.upanh;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.toan_itc.mobifone.R;
import com.toan_itc.mobifone.intdef.IntDef;
import com.toan_itc.mobifone.libs.view.StateLayout;
import com.toan_itc.mobifone.ui.adapter.TabPagerAdapter;
import com.toan_itc.mobifone.ui.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UpanhFragment extends BaseFragment {
  @BindView(R.id.tabLayout)
  TabLayout mTabLayout;
  @BindView(R.id.view_pager)
  ViewPager mViewPager;
  private Context mContext;

  public static UpanhFragment newInstance() {
    return new UpanhFragment();
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
    TabPagerAdapter tabPagerAdapter = new TabPagerAdapter(getActivity().getSupportFragmentManager());
    tabPagerAdapter.addFragment(UpanhTratruocFragment.newInstance(IntDef.ONE), getResources().getString(R.string.upload_tratruoc));
    tabPagerAdapter.addFragment(UpanhTraSauFragment.newInstance(IntDef.TWO), getResources().getString(R.string.upload_trasau));
    mViewPager.setAdapter(tabPagerAdapter);
    mTabLayout.setupWithViewPager(mViewPager);
  }

  @Override
  protected int setLayoutResourceID() {
    return R.layout.upanh_fragment;
  }

  @Override
  protected void initData() {

  }

  @Override
  protected StateLayout getLoadingTargetView() {
    return ButterKnife.findById(getActivity(),R.id.stateLayout);
  }


}

