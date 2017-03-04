package com.toan_itc.mobifone.ui.fragment.upanh;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.toan_itc.mobifone.R;
import com.toan_itc.mobifone.intdef.StringDef;
import com.toan_itc.mobifone.libs.logger.Logger;
import com.toan_itc.mobifone.libs.view.StateLayout;
import com.toan_itc.mobifone.ui.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.majiajie.pagerbottomtabstrip.MaterialMode;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageBottomTabLayout;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;

public class UpanhTraSauFragment extends BaseFragment{
  private Context mContext;
  @BindView(R.id.tab)
  PageBottomTabLayout layoutTab;
  NavigationController mNavigationController;
  List<Fragment> mFragments;
  public static UpanhTraSauFragment newInstance(int type) {
    UpanhTraSauFragment fra = new UpanhTraSauFragment();
    Bundle bundle = new Bundle();
    bundle.putInt(StringDef.BUNDLE_DATA, type);
    fra.setArguments(bundle);
    return fra;
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    mContext=context;
  }

  @Override
  protected String getTAG() {
    return this.getClass().getSimpleName();
  }

  @Override
  protected void initViews() {
    initFragment();
    initBottomTab();
  }

  @Override
  protected int setLayoutResourceID() {
    return R.layout.upanh_trasau;
  }

  @Override
  protected void initData() {

  }

  @Override
  protected StateLayout getLoadingTargetView() {
     return ButterKnife.findById(getActivity(),R.id.stateLayout);
  }

  private void initFragment() {
    mFragments = new ArrayList<>();
    mFragments.add(TraSauCanhanFragment.newInstance(1));
    mFragments.add(TraSauDoanhnghiepFragment.newInstance(1));
    replaceFagment(getFragmentManager(),R.id.frameLayout,mFragments.get(0));
  }

  private void initBottomTab() {
    mNavigationController = layoutTab.material()
            .addItem(R.drawable.ic_person_black_24dp, getString(R.string.upload_ca_nhan), getResources().getColor(R.color.md_teal_A700))
            .addItem(R.drawable.ic_location_city_black_24dp, getString(R.string.upload_doanhnghiep), getResources().getColor(R.color.md_red_500))
            .setMode(MaterialMode.CHANGE_BACKGROUND_COLOR)
            .build();
    mNavigationController.addTabItemSelectedListener(listener);
  }

  OnTabItemSelectedListener listener = new OnTabItemSelectedListener() {
    @Override
    public void onSelected(int index, int old) {
      Logger.e( "onSelected:" + index+"old:"+old);
      replaceFagment(getFragmentManager(), R.id.frameLayout, mFragments.get(index));
    }

    @Override
    public void onRepeat(int index) {
      Log.i("asd", "onRepeatClick:" + index);
    }
  };
}

