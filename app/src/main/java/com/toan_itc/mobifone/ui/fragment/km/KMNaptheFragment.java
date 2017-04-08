package com.toan_itc.mobifone.ui.fragment.km;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.toan_itc.mobifone.R;
import com.toan_itc.mobifone.intdef.StringDef;
import com.toan_itc.mobifone.intdef.TheloaiDef;
import com.toan_itc.mobifone.libs.view.StateLayout;
import com.toan_itc.mobifone.mvp.model.theloai.Theloai;
import com.toan_itc.mobifone.mvp.presenter.theloai.TheloaiPresenter;
import com.toan_itc.mobifone.mvp.view.theloai.TheloaiView;
import com.toan_itc.mobifone.ui.activity.BaseActivity;
import com.toan_itc.mobifone.ui.activity.WebviewActivity;
import com.toan_itc.mobifone.ui.adapter.theloai.TheloaiAdapter;
import com.toan_itc.mobifone.ui.fragment.BaseFragment;
import java.util.List;
import javax.inject.Inject;


public class KMNaptheFragment extends BaseFragment implements TheloaiView{
  @Inject
  TheloaiPresenter
  mTheloaiPresenter;
  @BindView(R.id.recyclerview)
  RecyclerView mRecyclerview;
  private List<Theloai> mTheloaiList;
  private Context mContext;
  public static KMNaptheFragment newInstance() {
    return new KMNaptheFragment();
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
    ((BaseActivity) getActivity()).getActivityComponent().inject(this);
    mTheloaiPresenter.attachView(this);
    initRecyclerview();
  }

  @Override
  protected int setLayoutResourceID() {
    return R.layout.khuyenmai_list;
  }

  @Override
  protected void initData() {
    mTheloaiPresenter.getData(TheloaiDef.KHUYEN_MAI_NAP_THE_NGAY);
  }

  @Override
  protected StateLayout getLoadingTargetView() {
    return ButterKnife.findById(getActivity(),R.id.stateLayout);
  }

  @Override
  public void showData(List<Theloai> theloaiList) {
    mTheloaiList=theloaiList;
    TheloaiAdapter theloaiAdapter=new TheloaiAdapter(mTheloaiList);
    theloaiAdapter.openLoadAnimation();
    mRecyclerview.setAdapter(theloaiAdapter);
    theloaiAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
      @Override public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intent intent = new Intent(mContext, WebviewActivity.class);
        intent.putExtra(StringDef.BUNDLE_DATA, mTheloaiList.get(i).getUrl());
        startActivity(intent);
      }
    });
  }

  @Override
  public void DataEmty() {

  }

  private void initRecyclerview(){
    mRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
    mRecyclerview.setHasFixedSize(true);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    mTheloaiPresenter.detachView();
  }
}
