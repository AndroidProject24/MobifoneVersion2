package com.toan_itc.mobifone.ui.fragment.vas;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.webkit.WebView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.toan_itc.mobifone.R;
import com.toan_itc.mobifone.intdef.StringDef;
import com.toan_itc.mobifone.libs.view.StateLayout;
import com.toan_itc.mobifone.mvp.model.vas.Goicuoc;
import com.toan_itc.mobifone.mvp.model.vas.Vas;
import com.toan_itc.mobifone.mvp.presenter.vas.VasPresenter;
import com.toan_itc.mobifone.mvp.view.vas.VasView;
import com.toan_itc.mobifone.ui.activity.BaseActivity;
import com.toan_itc.mobifone.ui.fragment.BaseFragment;
import com.toan_itc.mobifone.ui.fragment.login.LoginFragment;
import dagger.internal.Preconditions;
import java.util.List;
import javax.inject.Inject;

public class CheckVasFragment extends BaseFragment implements VasView {
  @BindView(R.id.edt_phone) TextInputEditText edtPhone;
  @BindView(R.id.edt_date_start) TextInputEditText edtDateStart;
  @BindView(R.id.edt_date_end) TextInputEditText edtDateEnd;
  @BindView(R.id.show_html) WebView showHtml;
  private Context mContext;
  @Inject VasPresenter vasPresenter;

  public static CheckVasFragment newInstance(String sdt) {
    CheckVasFragment fra = new CheckVasFragment();
    Bundle bundle = new Bundle();
    bundle.putString(StringDef.BUNDLE_DATA, sdt);
    fra.setArguments(bundle);
    return fra;
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
    vasPresenter.attachView(this);
  }

  @Override protected int setLayoutResourceID() {
    return R.layout.check_vas_fragment;
  }

  @Override protected void initData() {
    edtPhone.setText(Preconditions.checkNotNull(getArguments()).getString(StringDef.BUNDLE_DATA,"BUNDLE_DATA not null"));
  }

  @Override protected StateLayout getLoadingTargetView() {
    return ButterKnife.findById(getActivity(), R.id.stateLayout);
  }

  @Override public void getGoiCuoc(List<Goicuoc> goicuocList) {

  }

  @Override public void registerVas(Vas vas) {

  }

  @Override public void requestLogin() {
    replaceFagment(getFragmentManager(), R.id.fragment, LoginFragment.newInstance());
  }

  @Override public void showHtml(String html) {
    showHtml.loadDataWithBaseURL("", html, "text/html", "UTF-8", "");
  }

  @OnClick(R.id.btncheckVas) void checkVAS() {
      vasPresenter.checkVas(edtPhone.getText().toString(), edtDateStart.getText().toString(),edtDateEnd.getText().toString());
  }

}

