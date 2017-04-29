package com.toan_itc.mobifone.ui.fragment.login;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.toan_itc.mobifone.R;
import com.toan_itc.mobifone.libs.view.StateLayout;
import com.toan_itc.mobifone.mvp.model.login.Login;
import com.toan_itc.mobifone.mvp.model.register.Register;
import com.toan_itc.mobifone.mvp.presenter.login.LoginPresenter;
import com.toan_itc.mobifone.mvp.view.login.LoginView;
import com.toan_itc.mobifone.ui.activity.BaseActivity;
import com.toan_itc.mobifone.ui.fragment.BaseFragment;
import dagger.internal.Preconditions;
import javax.inject.Inject;

import static com.toan_itc.mobifone.utils.Utils.isPasswordValid;

/**
 * Created by Toan.IT
 * Date: 11/06/2016
 */

public class ChangePasswordFragment extends BaseFragment implements LoginView {
  @Inject
  LoginPresenter
  mLoginPresenter;

  @BindView(R.id.stateLayout)
  ViewGroup stateLayout;
  @BindView(R.id.etPassOld)
  TextInputEditText mEtPassOld;
  @BindView(R.id.layout_pass_old)
  TextInputLayout mLayoutPassOld;
  @BindView(R.id.etPasswordNew)
  TextInputEditText mEtPasswordNew;
  @BindView(R.id.layout_pass_new)
  TextInputLayout mLayoutPassNew;
  @BindView(R.id.btnChangePass)
  Button mBtnChangePass;
  private Context mContext;

  public static ChangePasswordFragment newInstance() {
    return new ChangePasswordFragment();
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    mContext = context;
  }

  @Override
  protected String getTAG() {
    return this.getClass().getSimpleName();
  }

  @Override
  protected int setLayoutResourceID() {
    return R.layout.change_pass_fragment;
  }

  @Override
  protected void initData() {

  }

  @Override
  protected StateLayout getLoadingTargetView() {
    return ButterKnife.findById(getActivity(), R.id.stateLayout);
  }

  @Override
  protected void initViews() {
    ((BaseActivity) getActivity()).getActivityComponent().inject(this);
    mLoginPresenter.attachView(this);
  }

  @Override
  public void login(Login login) {

  }

  @Override
  public void register(Register register) {

  }

  @Override
  public void login_error(String error) {

  }

  @Override
  public void changePass(String data) {

  }

  @Override
  public void updateProfile(boolean OK) {

  }

  @Override public void requestLogin() {
    replaceFagment(getFragmentManager(), R.id.fragment, LoginFragment.newInstance());
  }

  @OnClick(R.id.btnChangePass)
  void btnChangePass() {
    checkChangePass();
  }

  @SuppressWarnings("ConstantConditions")
  private void checkChangePass() {
    try {
      mEtPasswordNew.setError(null);
      mEtPassOld.setError(null);
      String passnew = mEtPasswordNew.getText().toString();
      String passold = mEtPassOld.getText().toString();

      boolean cancel = false;
      View focusView = null;
      if (TextUtils.isEmpty(passnew)) {
        mLayoutPassNew.setErrorEnabled(true);
        mLayoutPassNew.setError(getString(R.string.error_invalid_password));
        focusView = mLayoutPassNew;
        cancel = true;
      }else if(!isPasswordValid(passnew)){
        mLayoutPassNew.setErrorEnabled(true);
        mLayoutPassNew.setError(getString(R.string.error_incorrect_password));
        focusView = mLayoutPassNew;
        cancel = true;
      }
      if (TextUtils.isEmpty(passold)) {
        mLayoutPassOld.setErrorEnabled(true);
        mLayoutPassOld.setError(getString(R.string.error_invalid_password));
        focusView = mLayoutPassOld;
        cancel = true;
      }else if(!isPasswordValid(passold)){
        mLayoutPassOld.setErrorEnabled(true);
        mLayoutPassOld.setError(getString(R.string.error_incorrect_password));
        focusView = mLayoutPassOld;
        cancel = true;
      }
      if (cancel) {
        focusView.requestFocus();
      } else {
        mLayoutPassOld.setErrorEnabled(false);
        mLayoutPassNew.setErrorEnabled(false);
        Login login= Preconditions.checkNotNull(mLoginPresenter.getPreferencesHelper().getJsonLogin());
        mLoginPresenter.changePassword(Preconditions.checkNotNull(login.get_$0().getAuth_code()),
                Preconditions.checkNotNull(login.get_$0().getUsername()),
                Preconditions.checkNotNull(login.get_$0().getId()),passold, passnew);
      }
    }catch (Exception e){
      e.printStackTrace();
    }
  }
}
