package com.toan_itc.mobifone.ui.fragment;

import com.toan_itc.mobifone.R;
import com.toan_itc.mobifone.data.local.PreferencesHelper;
import com.toan_itc.mobifone.libs.view.StateLayout;
import com.toan_itc.mobifone.ui.activity.MainActivity;
import com.toan_itc.mobifone.ui.fragment.congno.CongnoFragment;
import com.toan_itc.mobifone.ui.fragment.khoso.UIKhosoFragment;
import com.toan_itc.mobifone.ui.fragment.km.KhuyenmaiFragment;
import com.toan_itc.mobifone.ui.fragment.thutuc.ThutucFragment;
import com.toan_itc.mobifone.ui.fragment.upanh.UpanhFragment;

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
        replaceFagment(getFragmentManager(),R.id.fragment, CongnoFragment.newInstance());
    }

    @OnClick(R.id.card_khoso)
    public void click_khoso(){
        replaceFagment(getFragmentManager(),R.id.fragment, UIKhosoFragment.newInstance());
    }

    @OnClick(R.id.card_thutuc)
    public void click_thutuc(){
        replaceFagment(getFragmentManager(),R.id.fragment, ThutucFragment.newInstance());
    }

    @OnClick(R.id.card_km)
    public void click_ctkm(){
        replaceFagment(getFragmentManager(),R.id.fragment, KhuyenmaiFragment.newInstance());
    }

    @OnClick(R.id.card_upanh)
    public void click_upanh(){
        replaceFagment(getFragmentManager(),R.id.fragment, UpanhFragment.newInstance());
    }

}
