package com.toan_itc.mobifone.ui.fragment.km;

import android.content.Context;
import android.support.v4.view.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.hugeterry.coordinatortablayout.CoordinatorTabLayout;
import com.bumptech.glide.Glide;
import com.toan_itc.mobifone.R;
import com.toan_itc.mobifone.intdef.KhuyenmaiDef;
import com.toan_itc.mobifone.libs.view.StateLayout;
import com.toan_itc.mobifone.ui.adapter.theloai.KMPagerAdapter;
import com.toan_itc.mobifone.ui.fragment.BaseFragment;
import com.toan_itc.mobifone.utils.Constant;

public class KhuyenmaiFragment extends BaseFragment{
  private Context mContext;
  @BindView(R.id.viewPager)
  ViewPager mViewPager;
  @BindView(R.id.coordinatortablayout)
  CoordinatorTabLayout mCoordinatortablayout;
  private String[] mTitles=new String[4];
  public static KhuyenmaiFragment newInstance() {
    return new KhuyenmaiFragment();
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
    initFragments();
    initViewPager();
    toolbarTitleListener.hideToolBar(true);
  }

  @Override
  protected int setLayoutResourceID() {
    return R.layout.khuyenmai_fragment;
  }

  @Override
  protected void initData() {
    int[] colorArray = new int[]{
        android.R.color.holo_blue_light,
        android.R.color.holo_green_dark,
        android.R.color.holo_red_light,
        android.R.color.holo_orange_light};
    mCoordinatortablayout
        .setContentScrimColorArray(colorArray)
        .setLoadHeaderImagesListener((imageView, tab) -> Glide.with(mContext).load(Constant.URL_IMAGE).into(imageView))
        .setupWithViewPager(mViewPager);
  }

  @Override
  protected StateLayout getLoadingTargetView() {
     return ButterKnife.findById(getActivity(),R.id.stateLayout);
  }


  private void initFragments() {
    mTitles= new String[]{getString(KhuyenmaiDef.KHUYEN_MAI_TRA_SAU_CA_NHAN),getString(KhuyenmaiDef.KHUYEN_MAI_TRA_SAU_DOANH_NGHIEP), getString(KhuyenmaiDef.KHUYEN_MAI_SO_DEP), getString(KhuyenmaiDef.KHUYEN_MAI_TRONG_NGAY)};
  }

  private void initViewPager() {
    mViewPager.setAdapter(new KMPagerAdapter(getActivity().getSupportFragmentManager(), mTitles));
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    toolbarTitleListener.hideToolBar(false);
  }
}

