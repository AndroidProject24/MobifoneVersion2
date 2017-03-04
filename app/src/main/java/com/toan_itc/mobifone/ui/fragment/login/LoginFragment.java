package com.toan_itc.mobifone.ui.fragment.login;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.toan_itc.mobifone.R;
import com.toan_itc.mobifone.libs.view.StateLayout;
import com.toan_itc.mobifone.mvp.model.login.Login;
import com.toan_itc.mobifone.mvp.presenter.login.LoginPresenter;
import com.toan_itc.mobifone.mvp.view.login.LoginView;
import com.toan_itc.mobifone.ui.activity.BaseActivity;
import com.toan_itc.mobifone.ui.fragment.BaseFragment;
import com.toan_itc.mobifone.ui.fragment.MainFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.toan_itc.mobifone.R.id.layout_email;
import static com.toan_itc.mobifone.R.id.layout_pass;
import static com.toan_itc.mobifone.utils.Utils.isEmailValid;
import static com.toan_itc.mobifone.utils.Utils.isPasswordValid;


public class LoginFragment extends BaseFragment implements LoginView {
  @Inject
  LoginPresenter
  mLoginPresenter;
  @BindView(R.id.etEmail)
  TextInputEditText mTxtEmail;
  @BindView(R.id.etPassword)
  TextInputEditText mPassword;
  @BindView(R.id.checkboxRemember)
  CheckBox checkboxRemember;
  @BindView(R.id.btnLogin)
  Button btnLogin;
  @BindView(R.id.tvRestore)
  TextView tvRestore;
  @BindView(R.id.ll1)
  LinearLayout ll1;
  @BindView(R.id.tvRegister)
  TextView tvRegister;
  @BindView(layout_email)
  TextInputLayout layoutEmail;
  @BindView(layout_pass)
  TextInputLayout layoutPass;
  @BindView(R.id.login_layout)
  ViewGroup mLoginLayout;
  private Context mContext;
  public static LoginFragment newInstance() {
    return new LoginFragment();
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
    mLoginPresenter.attachView(this);
    mTxtEmail.addTextChangedListener(textWatcher);
    mPassword.addTextChangedListener(textWatcher);
  }

  @Override
  protected int setLayoutResourceID() {
    return R.layout.login_fragment;
  }

  @Override
  protected void initData() {

  }

  @Override
  protected StateLayout getLoadingTargetView() {
    return ButterKnife.findById(getActivity(),R.id.login_layout);
  }

  private TextWatcher textWatcher = new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
      enableLoginBtn();
    }
  };

  private void enableLoginBtn() {
    btnLogin.setEnabled(mTxtEmail.length() != 0 && mPassword.getText().length() != 0);
  }

  @OnClick(R.id.btnLogin)
  void sign_in() {
    checkLogin();
  }

  private void checkLogin() {
    mTxtEmail.setError(null);
    mPassword.setError(null);
    String email = mTxtEmail.getText().toString();
    String password = mPassword.getText().toString();

    boolean cancel = false;
    View focusView = null;
    if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
      layoutPass.setErrorEnabled(true);
      layoutPass.setError(getString(R.string.error_invalid_password));
      focusView = layoutPass;
      cancel = true;
    }
    if (TextUtils.isEmpty(email)) {
      layoutEmail.setError(getString(R.string.error_field_required));
      focusView = layoutEmail;
      cancel = true;
    } else if (!isEmailValid(email)) {
      layoutEmail.setErrorEnabled(true);
      layoutEmail.setError(getString(R.string.error_invalid_email));
      focusView = layoutEmail;
      cancel = true;
    }
    if (cancel) {
      focusView.requestFocus();
    } else {
      layoutEmail.setErrorEnabled(false);
      layoutPass.setErrorEnabled(false);
      // mLoginPresenter.login(mTxtEmail.getText().toString(), mPassword.getText().toString(), Constant.SHOP_ID);
    }
  }

  @Override
  public void login(Login login) {
    //mLoginPresenter.send_token(Utils.getDeviceIMEI(mContext),"",Constant.ANDROID_OS,Utils.getDeviceName(),Utils.getDeviceVersion(),"0","0");
    if(login.getFirst_name()!=null&&login.getLast_name()!=null){
      Snackbar.make(mLoginLayout,"Welcome "+login.getFirst_name()+" "+login.getLast_name(),Snackbar.LENGTH_LONG).show();
    }
    replaceFagment(getFragmentManager(),R.id.fragment, MainFragment.newInstance());
  }

  @Override
  public void login_error() {
    Snackbar.make(mLoginLayout,"Login Fail!",Snackbar.LENGTH_LONG).show();
  }

  @OnClick(R.id.tvRegister)
  void signUp(){
    replaceFagment(getFragmentManager(),R.id.fragment,RegisterFragment.newInstance());
  }
}

