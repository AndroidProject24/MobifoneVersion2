package com.toan_itc.mobifone.ui.fragment.vas;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.toan_itc.mobifone.R;
import com.toan_itc.mobifone.libs.logger.Logger;
import com.toan_itc.mobifone.libs.view.StateLayout;
import com.toan_itc.mobifone.mvp.model.vas.Goicuoc;
import com.toan_itc.mobifone.mvp.model.vas.Vas;
import com.toan_itc.mobifone.mvp.presenter.vas.VasPresenter;
import com.toan_itc.mobifone.mvp.view.vas.VasView;
import com.toan_itc.mobifone.ui.activity.BaseActivity;
import com.toan_itc.mobifone.ui.adapter.vas.GoiCuocAdapter;
import com.toan_itc.mobifone.ui.fragment.BaseFragment;
import com.toan_itc.mobifone.ui.fragment.login.LoginFragment;
import java.util.List;
import javax.inject.Inject;

import static com.toan_itc.mobifone.utils.ImageUtils.loadImageView;

public class VasFragment extends BaseFragment implements VasView {
  @BindView(R.id.edt_sdt) TextInputEditText edtSdt;
  @BindView(R.id.spinner) AppCompatSpinner spinner;
  @BindView(R.id.img_captcha) AppCompatImageView imgCaptcha;
  @BindView(R.id.edt_captcha) TextInputEditText edtCaptcha;
  private Context mContext;
  @Inject VasPresenter vasPresenter;

  public static VasFragment newInstance() {
    return new VasFragment();
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
    return R.layout.vas_fragment;
  }

  @Override protected void initData() {
    vasPresenter.getGoiCuoc();
    Logger.e(vasPresenter.imgCaptcha());
    loadImageView(mContext,vasPresenter.imgCaptcha(),imgCaptcha);
  }

  @Override protected StateLayout getLoadingTargetView() {
    return ButterKnife.findById(getActivity(), R.id.stateLayout);
  }

  @Override public void getGoiCuoc(List<Goicuoc> goicuocList) {
    spinner.setAdapter(new GoiCuocAdapter(mContext,goicuocList));
  }

  @Override
  public void registerVas(Vas vas) {
    Snackbar.make(getLoadingTargetView(),vas.getReason(),Snackbar.LENGTH_LONG).show();
  }

  @Override public void requestLogin() {
    replaceFagment(getFragmentManager(), R.id.fragment, LoginFragment.newInstance());
  }

  @OnClick(R.id.btnRegister)
  public void btnRegister(){
    if (!TextUtils.isEmpty(edtSdt.getText().toString()) && !TextUtils.isEmpty(edtCaptcha.getText().toString())) {
      vasPresenter.registerGoiCuoc(edtSdt.getText().toString(),edtCaptcha.getText().toString(),((Goicuoc) spinner.getSelectedItem()).getMagoi());
    }else{
      Snackbar.make(getLoadingTargetView(),"vui lòng nhập đầy đủ thông tin!",Snackbar.LENGTH_LONG).show();;
    }
  }
}

