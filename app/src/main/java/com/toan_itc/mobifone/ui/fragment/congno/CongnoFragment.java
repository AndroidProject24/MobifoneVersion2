package com.toan_itc.mobifone.ui.fragment.congno;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.toan_itc.mobifone.R;
import com.toan_itc.mobifone.libs.view.StateLayout;
import com.toan_itc.mobifone.mvp.model.congno.Congno;
import com.toan_itc.mobifone.mvp.presenter.congno.CongnoPresenter;
import com.toan_itc.mobifone.mvp.view.congno.CongnoView;
import com.toan_itc.mobifone.ui.activity.BaseActivity;
import com.toan_itc.mobifone.ui.adapter.congno.CongnoAdapter;
import com.toan_itc.mobifone.ui.adapter.congno.ListImageAdapter;
import com.toan_itc.mobifone.ui.fragment.BaseFragment;
import com.toan_itc.mobifone.ui.fragment.login.LoginFragment;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;

public class CongnoFragment extends BaseFragment implements CongnoView{
  @BindView(R.id.recyclerview) RecyclerView mRecyclerview;
  private Context mContext;
  @Inject CongnoPresenter congnoPresenter;
  public static CongnoFragment newInstance() {
    return new CongnoFragment();
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    mContext = context;
  }

  @Override protected String getTAG() {
    return this.getClass().getSimpleName();
  }

  @Override protected void initViews() {
    ((BaseActivity) getActivity()).getActivityComponent().inject(this);
    congnoPresenter.attachView(this);
    mRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
    mRecyclerview.setHasFixedSize(true);
  }

  @Override protected int setLayoutResourceID() {
    return R.layout.congno_fragment;
  }

  @Override protected void initData() {
    congnoPresenter.getCongno();
  }

  @Override protected StateLayout getLoadingTargetView() {
    return ButterKnife.findById(getActivity(), R.id.stateLayout);
  }

  @Override public void showData(List<Congno> dataBeanList) {
    CongnoAdapter congnoAdapter=new CongnoAdapter(dataBeanList);
    congnoAdapter.openLoadAnimation();
    mRecyclerview.setAdapter(congnoAdapter);
    congnoAdapter.setOnItemChildClickListener((baseQuickAdapter, view, i) -> {
      List<String> wordList = Arrays.asList(dataBeanList.get(i).getImages().split(","));
      ListImageAdapter congnoAdapter1 =new ListImageAdapter(wordList);
      congnoAdapter1.openLoadAnimation();
      mRecyclerview.setAdapter(congnoAdapter1);
    });
  }

  @Override public void requestLogin() {
    replaceFagment(getFragmentManager(), R.id.fragment, LoginFragment.newInstance());
  }
}

