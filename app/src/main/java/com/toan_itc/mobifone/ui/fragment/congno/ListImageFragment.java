package com.toan_itc.mobifone.ui.fragment.congno;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.toan_itc.mobifone.R;
import com.toan_itc.mobifone.intdef.StringDef;
import com.toan_itc.mobifone.libs.view.StateLayout;
import com.toan_itc.mobifone.ui.adapter.congno.ListImageAdapter;
import com.toan_itc.mobifone.ui.fragment.BaseFragment;
import dagger.internal.Preconditions;
import java.util.ArrayList;

public class ListImageFragment extends BaseFragment{
  @BindView(R.id.recyclerview) RecyclerView mRecyclerview;
  private Context mContext;
  public static ListImageFragment newInstance(ArrayList<String> list) {
    ListImageFragment fragment = new ListImageFragment();
    Bundle bundle = new Bundle();
    bundle.putStringArrayList(StringDef.BUNDLE_DATA, list);
    fragment.setArguments(bundle);
    return fragment;
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    mContext = context;
  }

  @Override protected String getTAG() {
    return this.getClass().getSimpleName();
  }

  @Override protected void initViews() {
    mRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
    mRecyclerview.setHasFixedSize(true);
  }

  @Override protected int setLayoutResourceID() {
    return R.layout.list_image_fragment;
  }

  @Override protected void initData() {
    ListImageAdapter listImageAdapter =new ListImageAdapter(Preconditions.checkNotNull(getArguments()).getStringArrayList(StringDef.BUNDLE_DATA));
    listImageAdapter.openLoadAnimation();
    mRecyclerview.setAdapter(listImageAdapter);
  }

  @Override protected StateLayout getLoadingTargetView() {
    return ButterKnife.findById(getActivity(), R.id.stateLayout);
  }
}

