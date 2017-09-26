package com.toan_itc.mobifone.ui.fragment.login;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.toan_itc.mobifone.R;
import com.toan_itc.mobifone.libs.view.StateLayout;
import com.toan_itc.mobifone.mvp.model.register.Register;
import com.toan_itc.mobifone.mvp.presenter.login.RegisterPresenter;
import com.toan_itc.mobifone.mvp.view.login.RegisterView;
import com.toan_itc.mobifone.ui.activity.BaseActivity;
import com.toan_itc.mobifone.ui.fragment.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.toan_itc.mobifone.R.id.etName;
import static com.toan_itc.mobifone.R.string.error;
import static com.toan_itc.mobifone.utils.Utils.isEmailValid;
import static com.toan_itc.mobifone.utils.Utils.isPasswordValid;

/**
 * Created by Toan.IT
 * Date: 11/06/2016
 */

public class RegisterFragment extends BaseFragment implements RegisterView {
  @Inject
  RegisterPresenter
  mRegisterPresenter;
  @BindView(R.id.etEmail)
  TextInputEditText mEtEmail;
  @BindView(etName)
  TextInputEditText mEtName;
  @BindView(R.id.etPassword)
  TextInputEditText mEtPassword;
  @BindView(R.id.etCPassword)
  TextInputEditText mEtCPassword;
  @BindView(R.id.btnRegister)
  Button mBtnRegister;
  @BindView(R.id.layout_pass)
  TextInputLayout mLayoutPass;
  @BindView(R.id.layout_cpass)
  TextInputLayout mLayoutRePass;
  @BindView(R.id.layout_name)
  TextInputLayout mLayoutName;
  @BindView(R.id.stateLayout)
  ViewGroup stateLayout;
  private Context mContext;
  public static RegisterFragment newInstance() {
    return new RegisterFragment();
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
  protected int setLayoutResourceID() {
    return R.layout.register_fragment;
  }

  @Override
  protected void initData() {

  }

  @Override
  protected StateLayout getLoadingTargetView() {
    return ButterKnife.findById(getActivity(),R.id.stateLayout);
  }

  @Override
  protected void initViews() {
    ((BaseActivity) getActivity()).getActivityComponent().inject(this);
    mRegisterPresenter.attachView(this);
    toolbarTitleListener.hideToolBar(true);
  }

  @Override
  public void register(Register register) {
    Snackbar.make(stateLayout,"Đăng ký thành công vui lòng chờ tài khoản kích hoạt!",Snackbar.LENGTH_LONG).show();
  }

  @Override
  public void registerError(String error) {
    Snackbar.make(stateLayout,error,Snackbar.LENGTH_LONG).show();
  }

  @OnClick(R.id.btnRegister)
  void user_signup_button(){
    check_Register();
  }

  private void check_Register() {
    mLayoutRePass.setError(null);
    mLayoutPass.setError(null);
    String email = mEtEmail.getText().toString();
    String rePassword = mEtCPassword.getText().toString();
    String user = mEtName.getText().toString();
    String password = mEtPassword.getText().toString();

    boolean cancel = false;
    View focusView = null;
    if (TextUtils.isEmpty(password)) {
      mLayoutPass.setErrorEnabled(true);
      mLayoutPass.setError(getString(R.string.error_invalid_password));
      focusView = mLayoutPass;
      cancel = true;
    }else if(!isPasswordValid(password)){
      mLayoutPass.setErrorEnabled(true);
      mLayoutPass.setError(getString(R.string.error_incorrect_password));
      focusView = mLayoutPass;
      cancel = true;
    }
    if (TextUtils.isEmpty(user)) {
      mLayoutName.setErrorEnabled(true);
      mLayoutName.setError(getString(R.string.error_field_required));
      focusView = mLayoutName;
      cancel = true;
    }
    if (TextUtils.isEmpty(rePassword)) {
        mLayoutRePass.setErrorEnabled(true);
        mLayoutRePass.setError(getString(R.string.error_field_required));
      focusView = mLayoutRePass;
      cancel = true;
    } else if (!rePassword.equalsIgnoreCase(password)) {
        mLayoutRePass.setErrorEnabled(true);
        mLayoutRePass.setError(getString(R.string.error_invalid_rePass));
      focusView = mLayoutRePass;
      cancel = true;
    }

    if (cancel) {
      focusView.requestFocus();
    } else {
      mLayoutRePass.setErrorEnabled(false);
      mLayoutPass.setErrorEnabled(false);
      mLayoutName.setErrorEnabled(false);
      mRegisterPresenter.register(email, user, password);
    }
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    try{
      toolbarTitleListener.hideToolBar(false);
    }catch (Exception e){
      e.printStackTrace();
    }
  }
}
