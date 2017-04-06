package com.toan_itc.mobifone.ui.fragment;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

import com.toan_itc.mobifone.R;
import com.toan_itc.mobifone.data.local.PreferencesHelper;
import com.toan_itc.mobifone.libs.view.StateLayout;
import com.toan_itc.mobifone.ui.activity.MainActivity;
import com.toan_itc.mobifone.ui.fragment.congno.CongnoFragment;
import com.toan_itc.mobifone.ui.fragment.khoso.UIKhosoFragment;
import com.toan_itc.mobifone.ui.fragment.km.KhuyenmaiFragment;
import com.toan_itc.mobifone.ui.fragment.login.LoginFragment;
import com.toan_itc.mobifone.ui.fragment.thutuc.ThutucFragment;
import com.toan_itc.mobifone.ui.fragment.upanh.UpanhFragment;

import com.toan_itc.mobifone.ui.fragment.vas.VasFragment;
import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Toan.IT
 * Date: 11/06/2016
 */

public class MainFragment extends BaseFragment {
    @Inject
    PreferencesHelper mPreferencesHelper;
    public static MainFragment newInstance() {
        return new MainFragment();
    }
    @Override
    protected String getTAG() {
        return this.getClass().getSimpleName();
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.main_fragment;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected StateLayout getLoadingTargetView() {
        return ButterKnife.findById(getActivity(),R.id.stateLayout);
    }

    @Override
    protected void initViews() {
        ((MainActivity)getActivity()).getActivityComponent().inject(this);
    }

    @OnClick(R.id.card_congno)
    public void click_congno(){
      checkLogin(CongnoFragment.newInstance());
    }

    @OnClick(R.id.card_khoso)
    public void click_khoso(){
      checkLogin(UIKhosoFragment.newInstance());
    }

    @OnClick(R.id.card_thutuc)
    public void click_thutuc(){
      checkLogin(ThutucFragment.newInstance());
    }

    @OnClick(R.id.card_km)
    public void click_ctkm(){
      checkLogin(KhuyenmaiFragment.newInstance());
    }

    @OnClick(R.id.card_vas)
    public void click_vas(){
      checkLogin(VasFragment.newInstance());
    }


    @OnClick(R.id.card_upanh)
    public void click_upanh(){
      checkLogin(UpanhFragment.newInstance());
    }
    private void checkLogin(Fragment fragment){
      if (mPreferencesHelper.getJsonLogin()!=null)
        replaceFagment(getFragmentManager(),R.id.fragment, fragment);
      else {
        replaceFagment(getFragmentManager(),R.id.fragment, LoginFragment.newInstance());
        Snackbar.make(getLoadingTargetView(), "Xin mời bạn đăng nhập!", Snackbar.LENGTH_LONG).show();
      }
    }
}
