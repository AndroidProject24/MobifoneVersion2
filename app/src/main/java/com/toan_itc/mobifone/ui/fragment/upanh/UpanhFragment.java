package com.toan_itc.mobifone.ui.fragment.upanh;

import android.Manifest;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.toan_itc.mobifone.R;
import com.toan_itc.mobifone.data.rxjava.DefaultObserver;
import com.toan_itc.mobifone.intdef.IntDef;
import com.toan_itc.mobifone.libs.logger.Logger;
import com.toan_itc.mobifone.libs.rxpermissions.RxPermissions;
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
    setPermissions();
  }

  @Override
  protected StateLayout getLoadingTargetView() {
    return ButterKnife.findById(getActivity(),R.id.stateLayout);
  }

  private void setPermissions(){
    try{
      RxPermissions rxPermissions = new RxPermissions(getActivity());
      rxPermissions.setLogging(true);
      rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE,
              Manifest.permission.WRITE_EXTERNAL_STORAGE,
              Manifest.permission.INTERNET,
              Manifest.permission.RECORD_AUDIO,
              Manifest.permission.ACCESS_WIFI_STATE,
              Manifest.permission.CHANGE_WIFI_STATE,
              Manifest.permission.SET_WALLPAPER,
              Manifest.permission.CAMERA)
              .subscribe(new DefaultObserver<Boolean>() {
                @Override
                public void onNext(Boolean aBoolean) {
                  if(aBoolean)
                    Logger.d("RxPermissions:OK");
                  else
                    Logger.d("RxPermissions:Fail");
                }
              });
    }catch (Exception e){
      e.printStackTrace();
    }
  }
}

