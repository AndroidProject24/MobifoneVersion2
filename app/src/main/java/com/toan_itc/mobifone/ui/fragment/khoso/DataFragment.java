package com.toan_itc.mobifone.ui.fragment.khoso;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import butterknife.BindView;
import butterknife.OnClick;
import com.jakewharton.rxbinding.internal.Preconditions;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.toan_itc.mobifone.R;
import com.toan_itc.mobifone.intdef.IntDef;
import com.toan_itc.mobifone.intdef.KhoSimIndexDef;
import com.toan_itc.mobifone.intdef.KhosimDef;
import com.toan_itc.mobifone.intdef.StringDef;
import com.toan_itc.mobifone.libs.logger.Logger;
import com.toan_itc.mobifone.libs.view.StateLayout;
import com.toan_itc.mobifone.mvp.model.khoso.Dangsim;
import com.toan_itc.mobifone.mvp.model.khoso.Dauso;
import com.toan_itc.mobifone.mvp.model.khoso.Info;
import com.toan_itc.mobifone.mvp.model.khoso.Khoso;
import com.toan_itc.mobifone.mvp.presenter.khoso.KhosoPresenter;
import com.toan_itc.mobifone.mvp.view.khoso.KhosoView;
import com.toan_itc.mobifone.ui.activity.BaseActivity;
import com.toan_itc.mobifone.ui.activity.WebviewActivity;
import com.toan_itc.mobifone.ui.adapter.khoso.DangsimAdapter;
import com.toan_itc.mobifone.ui.adapter.khoso.DausoAdapter;
import com.toan_itc.mobifone.ui.adapter.khoso.KhosoAdapter;
import com.toan_itc.mobifone.ui.fragment.BaseFragment;
import com.toan_itc.mobifone.ui.fragment.upanh.UpanhTraSauFragment;
import com.toan_itc.mobifone.ui.fragment.upanh.UpanhTratruocFragment;
import com.toan_itc.mobifone.utils.DialogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by hugeterry(http://hugeterry.cn)
 * Date: 17/1/28 17:36
 */
public class DataFragment extends BaseFragment implements KhosoView,Spinner.OnItemSelectedListener {
  @Inject
  KhosoPresenter
  mKhosoPresenter;
  @BindView(R.id.recyclerview)
  RecyclerView mRecyclerview;
  @BindView(R.id.txt_search) AppCompatEditText txt_search;
  @BindView(R.id.spinner) AppCompatSpinner mSpinner;
  @BindView(R.id.spinner_dauso)
  AppCompatSpinner mSpinner_dauso;
  @BindView(R.id.stateLayout)
  StateLayout mViewGroup;
  private KhosoAdapter mKhosoAdapter;
  private Context mContext;
  private int mCurrentCounter=0;
  private Khoso mKhoso;
  private boolean isFist=true;
  private String nextLink="";
  private TextInputEditText etName=null;
  private TextInputEditText etEmail=null;
  private TextInputEditText etPhone=null;
  private AppCompatSpinner spinner=null ;
  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    mContext = context;
  }

  public static DataFragment newInstance(int title) {
    DataFragment fra = new DataFragment();
    Bundle bundle = new Bundle();
    bundle.putInt(StringDef.BUNDLE_TITLE, title);
    fra.setArguments(bundle);
    return fra;
  }

  @Override
  protected String getTAG() {
    return DataFragment.class.getSimpleName();
  }

  @Override
  protected void initViews() {
    ((BaseActivity) getActivity()).getActivityComponent().inject(this);
    mKhosoPresenter.attachView(this);
    mRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
    List<Dauso> dausoList = new ArrayList<>();
    dausoList.add(0, new Dauso("Đầu số"));
    dausoList.add(1, new Dauso("090"));
    dausoList.add(2, new Dauso("093"));
    dausoList.add(3, new Dauso("089"));
    dausoList.add(4, new Dauso("0120"));
    dausoList.add(5, new Dauso("0121"));
    dausoList.add(6, new Dauso("0122"));
    dausoList.add(7, new Dauso("0126"));
    dausoList.add(8, new Dauso("0128"));
    mSpinner_dauso.setAdapter(new DausoAdapter(mContext, dausoList));
  }

  @Override
  protected int setLayoutResourceID() {
    return R.layout.data_fragment;
  }

  @Override
  protected void initData() {
    Logger.e(KhosimDef.SIM_DEP+"");
    mKhosoPresenter.dangSim("0");
    search_sim("", "", "");
  }

  @Override
  protected StateLayout getLoadingTargetView() {
    return mViewGroup;
  }

    @Override
    public void nextLink(String link) {
        this.nextLink=link;
    }

    @Override
  public void listSim(Khoso khoso) {
    try {
      isFist = false;
      mKhoso = Preconditions.checkNotNull(khoso, "khoso not null!");
      nextLink = mKhoso.getPage().getNextLink();
      mKhosoAdapter = new KhosoAdapter(khoso.getData());
      mRecyclerview.setAdapter(mKhosoAdapter);
      mKhosoAdapter.setOnLoadMoreListener(() -> {
        if (mKhoso.getData() != null) {
          mRecyclerview.postDelayed(() -> {
            if (mCurrentCounter >= Integer.parseInt(mKhoso.getTotalrows())) {
              //Data are all loaded.
              mKhosoAdapter.loadMoreEnd();
            } else {
              mKhosoAdapter.addData(mKhosoPresenter.loadMore(nextLink));
              mCurrentCounter = mKhosoAdapter.getData().size();
              mKhosoAdapter.loadMoreComplete();
            }
          }, 500);
        } else {
          Snackbar.make(mRecyclerview, getString(R.string.retry), Snackbar.LENGTH_LONG).show();
          mKhosoAdapter.loadMoreFail();
        }
      }, mRecyclerview);
      mKhosoAdapter.setOnItemChildClickListener((baseQuickAdapter, view, i) -> {
        if(!TextUtils.isEmpty(khoso.getIdkm())) {
          switch (view.getId()) {
            case R.id.btn_goicuoc:
              if(Preconditions.checkNotNull(getArguments().getInt(StringDef.BUNDLE_TITLE), "data not null!")==KhosimDef.SIM_TRA_SAU){
                String[] listID=khoso.getIdkm().split(",");
                DialogUtil.dialogBuilder(mContext, "Khuyến mãi", "Bạn có muốn xem chương trình khuyến mãi")
                    .setNegativeButton("Cá nhân",
                        (dialog, which) -> {
                          dialog.dismiss();
                          Intent intent = new Intent(mContext, WebviewActivity.class);
                          intent.putExtra(StringDef.BUNDLE_DATA, "http://n3t.top/test/fire/viewbaiviet/"+listID[1]);
                          startActivity(intent);
                        }).setNeutralButton("Doanh nghiệp", (dialog, which) -> {
                  dialog.dismiss();
                  Intent intent = new Intent(mContext, WebviewActivity.class);
                  intent.putExtra(StringDef.BUNDLE_DATA, "http://n3t.top/test/fire/viewbaiviet/"+listID[0]);
                  startActivity(intent);
                }).setPositiveButton("Không", (dialog, which) -> dialog.dismiss()).create().show();
              }else {
                DialogUtil.dialogBuilder(mContext, "Khuyến mãi",
                    "Bạn có muốn xem chương trình khuyến mãi").setNegativeButton("Có", new DialogInterface.OnClickListener() {
                  @Override public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    Intent intent = new Intent(mContext, WebviewActivity.class);
                    intent.putExtra(StringDef.BUNDLE_DATA, "http://n3t.top/test/fire/viewbaiviet/"+khoso.getIdkm());
                    startActivity(intent);
                  }
                }).setPositiveButton("Không", (dialog, which) -> dialog.dismiss()).create().show();
              }
              break;
            case R.id.img_shop:
              Dialog dialog=DialogUtil.dialogBuilder(mContext,"Nhập thông tin",R.layout.profile_layout).setNegativeButton("Có", new DialogInterface.OnClickListener() {
                @Override public void onClick(DialogInterface dialog, int which) {
                  try {
                    if (!TextUtils.isEmpty(etName.getText().toString()) && !TextUtils.isEmpty(
                        etEmail.getText().toString()) && !TextUtils.isEmpty(
                        spinner.getSelectedItem().toString())) {
                      Info info = new Info();
                      info.setPhone(khoso.getData().get(i).getSdtview());
                      info.setCmnd(etEmail.getText().toString());
                      info.setDichvu(((com.toan_itc.mobifone.mvp.model.khoso.Theloai) spinner.getSelectedItem()).getIdloai());
                      info.setHokhau(etPhone.getText().toString());
                      mKhosoPresenter.getPreferencesHelper().putJsonInfo(info);
                      dialog.dismiss();
                      try {
                        if (Preconditions.checkNotNull(getArguments().getInt(StringDef.BUNDLE_TITLE), "data not null!") == KhosimDef.SIM_TRA_SAU)
                          replaceFagment(getFragmentManager(), R.id.fragment, UpanhTraSauFragment.newInstance(IntDef.TWO));
                        else
                          replaceFagment(getFragmentManager(), R.id.fragment, UpanhTratruocFragment.newInstance(IntDef.ONE));
                      }catch (Exception e){
                        e.printStackTrace();
                        replaceFagment(getFragmentManager(), R.id.fragment, UpanhTratruocFragment.newInstance(IntDef.ONE));
                      }
                    } else {
                      Snackbar.make(mRecyclerview, "Vui lòng điền đầy đủ thông tin!", Snackbar.LENGTH_LONG).show();
                    }
                  }catch (Exception e){
                    e.printStackTrace();
                  }
                }
              }).setPositiveButton("Không", new DialogInterface.OnClickListener() {
                @Override public void onClick(DialogInterface dialog, int which) {
                  dialog.dismiss();
                }
              }).create();
              dialog.show();
              etName=(TextInputEditText) dialog.findViewById(R.id.etName);
              etEmail=(TextInputEditText) dialog.findViewById(R.id.etEmail);
              etPhone=(TextInputEditText) dialog.findViewById(R.id.etPhone);
              spinner = (AppCompatSpinner) dialog.findViewById(R.id.spinner);
              mKhosoPresenter.getTheloai(mContext,spinner);
              break;
          }
        }
      });
    }catch (Exception e){
      e.printStackTrace();
    }
  }

  @Override
  public void listDangSim(List<Dangsim> dangsimList) {
    mSpinner.setAdapter(new DangsimAdapter(mContext,dangsimList));
    mSpinner.setOnItemSelectedListener(this);
    mSpinner_dauso.setOnItemSelectedListener(this);
  }

  @Override
  public void emty(String message) {
    if(mKhosoAdapter==null)
      mKhosoAdapter=new KhosoAdapter(null);
    mKhosoAdapter.setNewData(null);
    mKhosoAdapter.setEmptyView(R.layout.view_empty, (ViewGroup) mRecyclerview.getParent());
   // Snackbar.make(mRecyclerview,message,Snackbar.LENGTH_LONG).show();
  }

  @OnClick(R.id.btn_search)
  void clickSearch(){
    try {
      if (txt_search.getText().length() > 0) {
        search_sim(txt_search.getText().toString(), returnDauso(), returnDangSo());
      }
    }catch (Exception e){
      e.printStackTrace();
    }
  }
  private void subscribeToEditText() {
    addSubscription(RxTextView.textChangeEvents(txt_search)
            .skip(1)
            .debounce(1500, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getSearchObserver()));
  }

  private Observer<TextViewTextChangeEvent> getSearchObserver() {
    return new Observer<TextViewTextChangeEvent>() {
      @Override
      public void onCompleted() {
      }

      @Override
      public void onError(Throwable e) {
        e.printStackTrace();
      }

      @Override
      public void onNext(TextViewTextChangeEvent textViewTextChangeEvent) {
        if (textViewTextChangeEvent.text().length() > 0) {
          search_sim(textViewTextChangeEvent.text().toString(),returnDauso(),returnDangSo());
        }
      }
    };
  }

  private void search_sim(String textSearch, String dauso,String dang) {
    Preconditions.checkNotNull(textSearch,"textSearch not null!");
    Preconditions.checkNotNull(dauso,"dauso not null!");
    Preconditions.checkNotNull(dang,"dang not null!");
    switch (Preconditions.checkNotNull(getArguments().getInt(StringDef.BUNDLE_TITLE), "data not null!")) {
      case KhosimDef.SIM_TRA_TRUOC:
        mKhosoPresenter.searchSim(textSearch, KhoSimIndexDef.SIM_TRA_TRUOC, dauso,dang);
        break;
      case KhosimDef.SIM_TRA_SAU:
        mKhosoPresenter.searchSim(textSearch, KhoSimIndexDef.SIM_TRA_SAU, dauso,dang);
        break;
      case KhosimDef.SIM_TRA_TRUOC_DEP:
        mKhosoPresenter.searchSim(textSearch, KhoSimIndexDef.SIM_TRA_TRUOC_DEP, dauso,dang);
        break;
      case KhosimDef.SIM_DEP:
        mKhosoPresenter.searchSim(textSearch, KhoSimIndexDef.SIM_DEP, dauso,dang);
        break;
      default:
        break;
    }
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    mKhosoPresenter.detachView();
  }

  private String returnSearch(){
    if(!TextUtils.isEmpty(txt_search.getText()))
      return txt_search.getText().toString();
    else
      return "";
  }

  private String returnDauso(){
    String dauso = "";
    try {
      if (mSpinner_dauso.getSelectedItem().toString().trim().equalsIgnoreCase("Đầu số"))
        dauso = "";
      else
        try {
          if(((Dauso) mSpinner_dauso.getSelectedItem()).getTen()!=null)
            dauso = ((Dauso) mSpinner_dauso.getSelectedItem()).getTen();
        }catch (Exception e){
          e.printStackTrace();
        }
      return dauso;
    }catch (Exception e){
      e.printStackTrace();
    }
    return dauso;
  }

  @Override
  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    try {
      switch (parent.getId()) {
        case R.id.spinner:
          if (!isFist)
            search_sim(txt_search.getText().toString(), returnDauso(), returnDangSo());
          break;
        case R.id.spinner_dauso:
          if (!isFist)
            search_sim(txt_search.getText().toString(), returnDauso(), returnDangSo());
          break;
      }
    }catch (Exception e){
      e.printStackTrace();
    }
  }

  @Override
  public void onNothingSelected(AdapterView<?> parent) {

  }
  private String returnDangSo(){
    try {
      if(((Dangsim) mSpinner.getSelectedItem()).getTenkey()!=null)
        return ((Dangsim) mSpinner.getSelectedItem()).getTenkey();
    }catch (Exception e){
      e.printStackTrace();
    }
    return "";
  }
}