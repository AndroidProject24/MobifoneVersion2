package com.toan_itc.mobifone.ui.fragment.congno;

import android.content.Context;

import com.toan_itc.mobifone.R;
import com.toan_itc.mobifone.libs.view.StateLayout;
import com.toan_itc.mobifone.ui.fragment.BaseFragment;

import butterknife.ButterKnife;


public class CongnoFragment extends BaseFragment  {
  private Context mContext;
  public static CongnoFragment newInstance() {
    return new CongnoFragment();
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

  }

  @Override
  protected int setLayoutResourceID() {
    return R.layout.congno_fragment;
  }

  @Override
  protected void initData() {

  }

  @Override
  protected StateLayout getLoadingTargetView() {
    return ButterKnife.findById(getActivity(),R.id.stateLayout);
  }
}

