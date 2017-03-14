package com.toan_itc.mobifone.ui.fragment.khoso;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jakewharton.rxbinding.internal.Preconditions;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.toan_itc.mobifone.R;
import com.toan_itc.mobifone.intdef.KhoSimIndexDef;
import com.toan_itc.mobifone.intdef.KhosimDef;
import com.toan_itc.mobifone.intdef.StringDef;
import com.toan_itc.mobifone.libs.view.StateLayout;
import com.toan_itc.mobifone.mvp.model.khoso.Dangsim;
import com.toan_itc.mobifone.mvp.model.khoso.Khoso;
import com.toan_itc.mobifone.mvp.presenter.khoso.KhosoPresenter;
import com.toan_itc.mobifone.mvp.view.khoso.KhosoView;
import com.toan_itc.mobifone.ui.activity.BaseActivity;
import com.toan_itc.mobifone.ui.adapter.khoso.DangsimAdapter;
import com.toan_itc.mobifone.ui.adapter.khoso.KhosoAdapter;
import com.toan_itc.mobifone.ui.fragment.BaseFragment;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by hugeterry(http://hugeterry.cn)
 * Date: 17/1/28 17:36
 */
public class DataFragment extends BaseFragment implements KhosoView,RadioGroup.OnCheckedChangeListener,View.OnClickListener {
  @Inject
  KhosoPresenter
  mKhosoPresenter;
  @BindView(R.id.recyclerview)
  RecyclerView mRecyclerview;
  @BindView(R.id.txt_search)
  EditText txt_search;
  @BindView(R.id.rad_090)
  RadioButton mRad090;
  @BindView(R.id.rad_093)
  RadioButton mRad093;
  @BindView(R.id.rad_089)
  RadioButton mRad089;
  @BindView(R.id.radio_group)
  RadioGroup mRadioGroup;
  @BindView(R.id.spinner)
  Spinner mSpinner;
  @BindView(R.id.spinner_dauso)
  Spinner mSpinner_dauso;
  private KhosoAdapter mKhosoAdapter;
  private Context mContext;
  private boolean isErr=false;
  private int mCurrentCounter=0;
  private Khoso mKhoso;
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
    subscribeToEditText();
    mRadioGroup.setOnCheckedChangeListener(this);
  }

  @Override
  protected int setLayoutResourceID() {
    return R.layout.data_fragment;
  }

  @Override
  protected void initData() {
    mKhosoPresenter.dangSim("0");
    search_sim("",mRad090.getText().toString(),"");
  }

  @Override
  protected StateLayout getLoadingTargetView() {
    return ButterKnife.findById(getActivity(),R.id.stateLayout);
  }

  @Override
  public void listSim(Khoso khoso) {
    mKhoso=khoso;
    mKhosoAdapter = new KhosoAdapter(khoso.getData());
    mRecyclerview.setAdapter(mKhosoAdapter);
    mKhosoAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
      @Override public void onLoadMoreRequested() {
        if (mKhoso != null && !mKhoso.getData().isEmpty()) {
          mRecyclerview.postDelayed(new Runnable() {
            @Override
            public void run() {
              if (mCurrentCounter >= Integer.parseInt(mKhoso.getTotalrows())) {
                //Data are all loaded.
                mKhosoAdapter.loadMoreEnd();
              } else {
                if (isErr) {
                  //Successfully get more data
                  mKhosoAdapter.addData(mKhosoPresenter.loadMore(DataFragment.this,mKhoso.getPage().getNextLink()));
                  mCurrentCounter = mKhosoAdapter.getData().size();
                  mKhosoAdapter.loadMoreComplete();
                } else {
                  //Get more data failed
                  isErr = true;
                  Snackbar.make(mRecyclerview, getString(R.string.retry), Snackbar.LENGTH_LONG).show();
                  mKhosoAdapter.loadMoreFail();

                }
              }
            }

          }, 500);
        }
      }
    });
  }

  @Override
  public void listDangSim(List<Dangsim> dangsimList) {

    mSpinner.setAdapter(new DangsimAdapter(mContext,dangsimList));
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
          int selectedId = mRadioGroup.getCheckedRadioButtonId();
          search_sim(textViewTextChangeEvent.text().toString(), ((RadioButton)ButterKnife.findById(getView(),selectedId)).getText().toString(),mSpinner.getSelectedItem().toString().trim());
        }
      }
    };
  }

  private void search_sim(String textSearch, String dauso,String dang) {
    switch (Preconditions.checkNotNull(getArguments().getInt(StringDef.BUNDLE_TITLE), "data not null!")) {
      case KhosimDef.SIM_TRA_TRUOC:
        mKhosoPresenter.searchSim(this,textSearch, KhoSimIndexDef.SIM_TRA_TRUOC, dauso,dang);
        break;
      case KhosimDef.SIM_TRA_SAU:
        mKhosoPresenter.searchSim(this,textSearch, KhoSimIndexDef.SIM_TRA_SAU, dauso,dang);
        break;
      case KhosimDef.SIM_TRA_TRUOC_DEP:
        mKhosoPresenter.searchSim(this,textSearch, KhoSimIndexDef.SIM_TRA_TRUOC_DEP, dauso,dang);
        break;
      case KhosimDef.SIM_DEP:
        mKhosoPresenter.searchSim(this,textSearch, KhoSimIndexDef.SIM_DEP, dauso,dang);
        break;
      default:
        break;
    }
  }

  @Override
  public void onCheckedChanged(RadioGroup group, int checkedId) {
    switch (checkedId){
      case R.id.rad_089:
        search_sim(txt_search.getText().toString(),mRad089.getText().toString(), mSpinner.getSelectedItem().toString().trim());
        break;
      case R.id.rad_090:
        search_sim(txt_search.getText().toString(),mRad090.getText().toString(),mSpinner.getSelectedItem().toString().trim());
        break;
      case R.id.rad_093:
        search_sim(txt_search.getText().toString(),mRad093.getText().toString(),mSpinner.getSelectedItem().toString().trim());
        break;
    }
  }

  @Override
  public void onClick(View v) {
    try {
      txt_search.setText("");
      int selectedId = mRadioGroup.getCheckedRadioButtonId();
      search_sim(txt_search.getText().toString(), ((RadioButton) ButterKnife.findById(getView(), selectedId)).getText().toString(), mSpinner.getSelectedItem().toString().trim());
    }catch (Exception e){
      e.printStackTrace();
      search_sim("", mRad090.getText().toString(), "");
    }
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    mKhosoPresenter.detachView();
  }
}