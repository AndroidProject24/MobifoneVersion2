package com.toan_itc.mobifone.ui.fragment.khoso;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.webkit.WebView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.toan_itc.mobifone.R;
import com.toan_itc.mobifone.data.rxjava.RxBus;
import com.toan_itc.mobifone.libs.view.StateLayout;
import com.toan_itc.mobifone.mvp.model.khoso.CheckSdt;
import com.toan_itc.mobifone.mvp.presenter.khoso.CheckSdtPresenter;
import com.toan_itc.mobifone.mvp.view.khoso.CheckSdtView;
import com.toan_itc.mobifone.ui.activity.BaseActivity;
import com.toan_itc.mobifone.ui.fragment.BaseFragment;
import dagger.internal.Preconditions;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;

public class CheckSdtFragment extends BaseFragment implements CheckSdtView {
  @Inject CheckSdtPresenter checkSdtPresenter;
  @BindView(R.id.edt_phone) TextInputEditText edtPhone;
  @BindView(R.id.txt_state) AppCompatTextView txtState;
  @BindView(R.id.show_html) WebView show_html;
  private Context mContext;

  public static CheckSdtFragment newInstance() {
    return new CheckSdtFragment();
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
    checkSdtPresenter.attachView(this);
  }

  @Override protected int setLayoutResourceID() {
    return R.layout.check_sdt_fragment;
  }

  @Override protected void initData() {
    getCompositeSubscription().add(RxBus.getDefault().toObserverable()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(o -> {
              try {
                if (o instanceof CheckSdt) {
                  if (((CheckSdt) o).getReason()!=null) {
                    txtState.setVisibility(View.GONE);
                    show_html.setVisibility(View.VISIBLE);
                    show_html.loadDataWithBaseURL("", ((CheckSdt) o).getReason(), "text/html", "UTF-8", "");
                  }
                }
              }catch (Exception e){
                e.printStackTrace();
              }
            }
        ));
  }

  @Override protected StateLayout getLoadingTargetView() {
    return ButterKnife.findById(getActivity(), R.id.stateLayout);
  }

  @Override public void checkSdt(CheckSdt checkSdt) {
    txtState.setVisibility(View.VISIBLE);
    show_html.setVisibility(View.GONE);
    txtState.setText(Preconditions.checkNotNull(checkSdt.getReason()));
  }

  @OnClick(R.id.btn_checkSdt)
  public void btn_checkSdt(){
    checkSdtPresenter.checkSDT(Preconditions.checkNotNull(edtPhone.getText().toString()));
  }

  @Override public void onDestroy() {
    super.onDestroy();
    checkSdtPresenter.detachView();
  }

}

