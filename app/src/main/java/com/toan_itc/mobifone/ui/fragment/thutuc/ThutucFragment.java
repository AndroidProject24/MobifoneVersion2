package com.toan_itc.mobifone.ui.fragment.thutuc;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.toan_itc.mobifone.R;
import com.toan_itc.mobifone.intdef.StringDef;
import com.toan_itc.mobifone.intdef.TheloaiDef;
import com.toan_itc.mobifone.libs.logger.Logger;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import me.majiajie.pagerbottomtabstrip.MaterialMode;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageBottomTabLayout;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;


public class ThutucFragment extends BaseFragment implements TheloaiView {
  @Inject
  TheloaiPresenter
  mTheloaiPresenter;
  private Context mContext;
  @BindView(R.id.tab)
  PageBottomTabLayout layoutTab;
  @BindView(R.id.recyclerview)
  RecyclerView mRecyclerview;
  private List<Theloai> mTheloaiList;
  public static ThutucFragment newInstance() {
    return new ThutucFragment();
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
    initBottomTab();
    initRecyclerview();
  }

  @Override
  protected int setLayoutResourceID() {
    return R.layout.thutuc_fragment;
  }

  @Override
  protected void initData() {
    mTheloaiPresenter.getData(TheloaiDef.HOA_MANG_TRA_TRUOC);
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
  }

  @Override
  public void DataEmty() {

  }

  private void initRecyclerview(){
    mRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
    mRecyclerview.setHasFixedSize(true);
    mRecyclerview.addOnItemTouchListener(new OnItemChildClickListener( ) {
      @Override
      public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        Logger.e("onSimpleItemChildClick"+mTheloaiList.get(position).getUrl());
        Intent intent = new Intent(mContext, WebviewActivity.class);
        intent.putExtra(StringDef.BUNDLE_DATA, mTheloaiList.get(position).getUrl());
        startActivity(intent);
      }
    });
  }
  private void initBottomTab() {
    NavigationController navigationController = layoutTab.material()
            .addItem(R.drawable.ic_home, getString(R.string.upload_tratruoc), getResources().getColor(R.color.md_teal_A700))
            .addItem(R.drawable.ic_business, getString(R.string.upload_trasau), getResources().getColor(R.color.md_red_500))
            .setMode(MaterialMode.CHANGE_BACKGROUND_COLOR)
            .build();
    navigationController.addTabItemSelectedListener(listener);
  }

  OnTabItemSelectedListener listener = new OnTabItemSelectedListener() {
    @Override
    public void onSelected(int index, int old) {
      Logger.e( "onSelected:" + index+"old:"+old);
      switch (index){
        case 0:
          mTheloaiPresenter.getData(TheloaiDef.HOA_MANG_TRA_TRUOC);
          break;
        case 1:
          mTheloaiPresenter.getData(TheloaiDef.HOA_MANG_TRA_SAU);
          break;
        default:
          break;
      }
    }

    @Override
    public void onRepeat(int index) {
      Log.i("asd", "onRepeatClick:" + index);
    }
  };

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    mTheloaiPresenter.detachView();
  }
}

