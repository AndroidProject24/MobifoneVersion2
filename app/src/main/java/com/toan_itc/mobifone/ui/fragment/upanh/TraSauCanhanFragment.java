package com.toan_itc.mobifone.ui.fragment.upanh;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.github.clans.fab.FloatingActionButton;
import com.toan_itc.mobifone.R;
import com.toan_itc.mobifone.intdef.BitmapDef;
import com.toan_itc.mobifone.intdef.StringDef;
import com.toan_itc.mobifone.libs.logger.Logger;
import com.toan_itc.mobifone.libs.takephoto.RxPhoto;
import com.toan_itc.mobifone.libs.takephoto.shared.TypeRequest;
import com.toan_itc.mobifone.libs.view.StateLayout;
import com.toan_itc.mobifone.mvp.presenter.upanh.UpanhPresenter;
import com.toan_itc.mobifone.mvp.view.upanh.UpanhView;
import com.toan_itc.mobifone.ui.activity.BaseActivity;
import com.toan_itc.mobifone.ui.fragment.BaseFragment;
import com.toan_itc.mobifone.utils.UploadUtils;

import java.io.File;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.toan_itc.mobifone.utils.ImageUtils.loadImageView;

public class TraSauCanhanFragment extends BaseFragment implements UpanhView {
  @Inject
  UpanhPresenter
  mUpanhPresenter;
  private Context mContext;
  @BindView(R.id.coordinatorlayout)
  CoordinatorLayout mCoordinatorlayout;
  @BindView(R.id.img_cmnd_mattruoc)
  ImageView imgCmndMattruoc;
  @BindView(R.id.img_cmnd_matsau)
  ImageView imgCmndMatsau;
  @BindView(R.id.img_hd_mattruoc)
  ImageView imgHdMattruoc;
  @BindView(R.id.img_hd_matsau)
  ImageView imgHdMatsau;
  @BindView(R.id.img_phuluc)
  ImageView imgPhuluc;
  @BindView(R.id.fab)
  FloatingActionButton mFloatingActionButton;
  private File requestBody=null,requestBody1=null,requestBody2=null,requestBody3=null,requestBody4=null;
  public static TraSauCanhanFragment newInstance(int type) {
    TraSauCanhanFragment fra = new TraSauCanhanFragment();
    Bundle bundle = new Bundle();
    bundle.putInt(StringDef.BUNDLE_DATA, type);
    fra.setArguments(bundle);
    return fra;
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
    mUpanhPresenter.attachView(this);
  }

  @Override
  protected int setLayoutResourceID() {
    return R.layout.upanh_trasau_canhan_fragment;
  }

  @Override
  protected void initData() {

  }

  @Override
  protected StateLayout getLoadingTargetView() {
    return ButterKnife.findById(getActivity(),R.id.stateLayout);
  }

  @OnClick(R.id.fab)
  public void btn_upload(){
    mUpanhPresenter.uploadTraSauCanhan("01676542546","125",requestBody,requestBody1,requestBody2,requestBody3,requestBody4);
  }
  @OnClick(R.id.btn_cmnd_mattruoc)
  public void btn_cmnd_mattruoc(){
    Dialog_Update_avatar(BitmapDef.CMND1);
  }
  @OnClick(R.id.btn_cmnd_matsau)
  public void btn_cmnd_matsau(){
    Dialog_Update_avatar(BitmapDef.CMND2);
  }
  @OnClick(R.id.btn_hd_mattruoc)
  public void btn_hd_mattruoc(){
    Dialog_Update_avatar(BitmapDef.HD1);
  }
  @OnClick(R.id.btn_hd_matsau)
  public void btn_hd_matsau(){
    Dialog_Update_avatar(BitmapDef.HD2);
  }
  @OnClick(R.id.btn_phuluc)
  public void btn_phuluc(){
    Dialog_Update_avatar(BitmapDef.PL4);
  }

  private void Dialog_Update_avatar(int index) {
    final Dialog dialog = new Dialog(mContext);
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialog.setContentView(R.layout.layout_dialog_avatar);
    Window window = dialog.getWindow();
    WindowManager.LayoutParams wlp = window.getAttributes();
    wlp.gravity = Gravity.BOTTOM;
    window.setAttributes(wlp);
    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    Button camera = (Button) dialog.findViewById(R.id.camera);
    Button thuvien = (Button) dialog.findViewById(R.id.thuvien);
    Button huy = (Button) dialog.findViewById(R.id.huy);
    camera.setOnClickListener(v -> {
      dialog.dismiss();
      subscription.unsubscribe();
      subscription=RxPhoto.requestUri(mContext, TypeRequest.CAMERA)
              .doOnNext((uri) -> returnUri(index,uri))
              .subscribe();
    });
    thuvien.setOnClickListener(v -> {
      dialog.dismiss();
      subscription.unsubscribe();
      subscription=RxPhoto.requestUri(mContext, TypeRequest.GALLERY)
              .doOnNext((uri) -> returnUri(index,uri))
              .subscribe();

    });
    huy.setOnClickListener(v -> dialog.dismiss());
    dialog.show();
  }

  private void returnUri(int index, Uri uri){
    switch (index){
      case BitmapDef.CMND1:
        loadImageView(mContext,uri,imgCmndMattruoc);
        requestBody = UploadUtils.prepareFilePart(mContext,uri);
        break;
      case BitmapDef.CMND2:
        loadImageView(mContext,uri,imgCmndMatsau);
        requestBody1 = UploadUtils.prepareFilePart(mContext,uri);
        break;
      case BitmapDef.HD1:
        loadImageView(mContext,uri,imgHdMattruoc);
        requestBody2 = UploadUtils.prepareFilePart(mContext,uri);
        break;
      case BitmapDef.HD2:
        loadImageView(mContext,uri,imgHdMatsau);
        requestBody3 = UploadUtils.prepareFilePart(mContext,uri);
        break;
      case BitmapDef.PL4:
        loadImageView(mContext,uri,imgPhuluc);
        requestBody4 = UploadUtils.prepareFilePart(mContext,uri);
        break;
    }
    if(showFab())
      mFloatingActionButton.setVisibility(View.VISIBLE);
  }

  @Override
  public void uploadProgress(int progress) {
    mFloatingActionButton.setProgress(progress,true);
  }

  @Override
  public void uploadOK() {
    Logger.e("uploadOK");
    Snackbar.make(mCoordinatorlayout, "Upload thành công!", Snackbar.LENGTH_LONG).show();
    subscription.unsubscribe();
    mFloatingActionButton.setProgress(100,true);
  }

  @Override
  public void uploadFail() {
    Logger.e("uploadFail");
    Snackbar.make(mCoordinatorlayout, "Upload bị lỗi!", Snackbar.LENGTH_LONG).show();
  }
  private boolean showFab(){
    return requestBody!= null && requestBody1 != null && requestBody2 != null&& requestBody3 != null&& requestBody4 != null;
  }


  @Override
  public void onDestroyView() {
    super.onDestroyView();
    mUpanhPresenter.detachView();
  }
}

