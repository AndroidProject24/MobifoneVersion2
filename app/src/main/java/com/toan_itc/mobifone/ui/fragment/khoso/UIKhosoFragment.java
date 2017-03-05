package com.toan_itc.mobifone.ui.fragment.khoso;

import com.toan_itc.mobifone.R;
import com.toan_itc.mobifone.intdef.KhoSimIndexDef;
import com.toan_itc.mobifone.libs.view.StateLayout;
import com.toan_itc.mobifone.ui.fragment.BaseFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Toan.IT
 * Created by vantoan on 3/5/17.
 * Email: Huynhvantoan.itc@gmail.com
 */

public class UIKhosoFragment extends BaseFragment{


  public static UIKhosoFragment newInstance() {
    return new UIKhosoFragment();
  }


  @Override
  protected String getTAG() {
    return this.getClass().getName();
  }

  @Override
  protected void initViews() {

  }

  @Override
  protected int setLayoutResourceID() {
    return R.layout.ui_khoso_fragment;
  }

  @Override
  protected void initData() {

  }

  @Override
  protected StateLayout getLoadingTargetView() {
    return ButterKnife.findById(getActivity(),R.id.stateLayout);
  }
  @OnClick(R.id.card_tratruoc)
  public void click_tratruoc(){
    replaceFagment(getFragmentManager(),R.id.fragment, KhosoFragment.newInstance(KhoSimIndexDef.SIM_TRA_TRUOC));
  }

  @OnClick(R.id.card_tratruoc_dep)
  public void click_tratruoc_dep(){
    replaceFagment(getFragmentManager(),R.id.fragment, KhosoFragment.newInstance(KhoSimIndexDef.SIM_TRA_TRUOC_DEP));
  }

  @OnClick(R.id.card_trasau)
  public void click_trasau(){
    replaceFagment(getFragmentManager(),R.id.fragment, KhosoFragment.newInstance(KhoSimIndexDef.SIM_TRA_SAU));
  }

  @OnClick(R.id.card_sodep)
  public void click_sodep(){
    replaceFagment(getFragmentManager(),R.id.fragment, KhosoFragment.newInstance(KhoSimIndexDef.SIM_DEP));
  }
}
