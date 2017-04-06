package com.toan_itc.mobifone.ui.fragment.congno;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.toan_itc.mobifone.R;
import com.toan_itc.mobifone.libs.view.StateLayout;
import com.toan_itc.mobifone.mvp.model.congno.Congno;
import com.toan_itc.mobifone.mvp.presenter.congno.CongnoPresenter;
import com.toan_itc.mobifone.mvp.view.congno.CongnoView;
import com.toan_itc.mobifone.ui.activity.BaseActivity;
import com.toan_itc.mobifone.ui.adapter.congno.CongnoAdapter;
import com.toan_itc.mobifone.ui.fragment.BaseFragment;
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

  @Override public void showData(List<Congno.DataBean> dataBeanList) {
    CongnoAdapter congnoAdapter=new CongnoAdapter(dataBeanList);
    congnoAdapter.openLoadAnimation();
    mRecyclerview.setAdapter(congnoAdapter);
    mRecyclerview.addOnItemTouchListener(new OnItemChildClickListener( ) {
      @Override
      public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
       /* ListImageAdapter congnoAdapter=new ListImageAdapter(dataBeanList.get(0).getImages());
        congnoAdapter.openLoadAnimation();
        mRecyclerview.setAdapter(congnoAdapter);*/
      }
    });
  }
}

