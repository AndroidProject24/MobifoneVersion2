package com.toan_itc.mobifone.ui.fragment.upanh;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.github.clans.fab.FloatingActionButton;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.model.TakePhotoOptions;
import com.toan_itc.mobifone.R;
import com.toan_itc.mobifone.intdef.BitmapDef;
import com.toan_itc.mobifone.intdef.StringDef;
import com.toan_itc.mobifone.libs.logger.Logger;
import com.toan_itc.mobifone.libs.view.StateLayout;
import com.toan_itc.mobifone.mvp.presenter.upanh.UpanhPresenter;
import com.toan_itc.mobifone.mvp.view.upanh.UpanhView;
import com.toan_itc.mobifone.ui.activity.BaseActivity;
import com.toan_itc.mobifone.utils.FileUtils;

import java.io.File;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.toan_itc.mobifone.utils.ImageUtils.loadImageView;

public class TraSauCanhanFragment extends TakePhotoFragment implements UpanhView {
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
  private TakePhoto mTakePhoto;
  private Uri imageUri;
  private int index=0;
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
    mTakePhoto=getTakePhoto();
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
      this.index=index;
      initTakePhoto();
      mTakePhoto.onPickFromCaptureWithCrop(imageUri,getCropOptions());
    });
    thuvien.setOnClickListener(v -> {
      dialog.dismiss();
      this.index=index;
      initTakePhoto();
      mTakePhoto.onPickFromGalleryWithCrop(imageUri,getCropOptions());

    });
    huy.setOnClickListener(v -> dialog.dismiss());
    dialog.show();
  }
  private void initTakePhoto(){
    File file=new File(Environment.getExternalStorageDirectory(), "/temp/"+System.currentTimeMillis() + ".jpg");
    if (!file.getParentFile().exists())file.getParentFile().mkdirs();
    imageUri = Uri.fromFile(file);
    configCompress(mTakePhoto);
    TakePhotoOptions.Builder builder=new TakePhotoOptions.Builder();
    builder.setWithOwnGallery(true);
    mTakePhoto.setTakePhotoOptions(builder.create());
  }
  private void configCompress(TakePhoto takePhoto){
    CompressConfig config=new CompressConfig.Builder()
            .setMaxSize(102400)
            .setMaxPixel(800)
            .enableReserveRaw(false)
            .create();
    takePhoto.onEnableCompress(config,true);
  }
  private CropOptions getCropOptions(){
    CropOptions.Builder builder=new CropOptions.Builder();
    builder.setOutputX(800).setOutputY(600);
    builder.setWithOwnCrop(true);
    return builder.create();
  }
  private void returnData(String image){
    switch (index){
      case BitmapDef.CMND1:
        loadImageView(mContext,image,imgCmndMattruoc);
        requestBody = FileUtils.getFileByPath(image);
        break;
      case BitmapDef.CMND2:
        loadImageView(mContext,image,imgCmndMatsau);
        requestBody1 = FileUtils.getFileByPath(image);
        break;
      case BitmapDef.HD1:
        loadImageView(mContext,image,imgHdMattruoc);
        requestBody2 = FileUtils.getFileByPath(image);
        break;
      case BitmapDef.HD2:
        loadImageView(mContext,image,imgHdMatsau);
        requestBody3 = FileUtils.getFileByPath(image);
        break;
      case BitmapDef.PL4:
        loadImageView(mContext,image,imgPhuluc);
        requestBody4 = FileUtils.getFileByPath(image);
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

  @Override
  public void takeSuccess(TResult result) {
    super.takeSuccess(result);
    Logger.e("takeSuccess：" + result.getImage().getCompressPath());
    returnData(result.getImage().getCompressPath());
  }
}

