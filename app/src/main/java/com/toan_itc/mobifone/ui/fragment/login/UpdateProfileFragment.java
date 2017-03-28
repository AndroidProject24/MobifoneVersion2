package com.toan_itc.mobifone.ui.fragment.login;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fernandocejas.frodo.core.checks.Preconditions;
import com.toan_itc.mobifone.R;
import com.toan_itc.mobifone.libs.view.StateLayout;
import com.toan_itc.mobifone.mvp.model.login.Login;
import com.toan_itc.mobifone.mvp.model.register.Register;
import com.toan_itc.mobifone.mvp.presenter.login.LoginPresenter;
import com.toan_itc.mobifone.mvp.view.login.LoginView;
import com.toan_itc.mobifone.ui.activity.BaseActivity;
import com.toan_itc.mobifone.ui.fragment.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.toan_itc.mobifone.utils.Utils.isEmailValid;

/**
 * Created by Toan.IT
 * Date: 11/06/2016
 */

public class UpdateProfileFragment extends BaseFragment implements LoginView {
  @Inject
  LoginPresenter
          mLoginPresenter;
  @BindView(R.id.stateLayout)
  ViewGroup stateLayout;
  @BindView(R.id.etName)
  TextInputEditText mEtName;
  @BindView(R.id.layout_name)
  TextInputLayout mLayoutName;
  @BindView(R.id.etEmail)
  TextInputEditText mEtEmail;
  @BindView(R.id.layout_email)
  TextInputLayout mLayoutEmail;
  @BindView(R.id.etPhone)
  TextInputEditText mEtPhone;
  @BindView(R.id.layout_phone)
  TextInputLayout mLayoutPhone;
  @BindView(R.id.etNameFist)
  TextInputEditText mEtNameFist;
  @BindView(R.id.layout_nameFist)
  TextInputLayout mLayoutNameFist;
  @BindView(R.id.etNameLast)
  TextInputEditText mEtNameLast;
  @BindView(R.id.btnUpdateProfile)
  Button mBtnUpdateProfile;
  Unbinder unbinder;
  private Context mContext;

  public static UpdateProfileFragment newInstance() {
    return new UpdateProfileFragment();
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
    return R.layout.update_profile_fragment;
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
  public void updateProfile(boolean ok) {

  }

    /*@Override
    public void register(Register register) {
        mRegisterPresenter.getPreferencesHelper().putUserId(register.getId());
    }

    @Override
    public void checkEmail() {
        Snackbar.make(mEtEmail,"Email emty or exist!",Snackbar.LENGTH_LONG).show();
    }*/

  @OnClick(R.id.btnUpdateProfile)
  void btnUpdateProfile() {
    check_Update();
  }

  @SuppressWarnings("ConstantConditions")
  private void check_Update() {
    try {
      mLayoutEmail.setError(null);
      mLayoutName.setError(null);
      String email = mEtEmail.getText().toString();
      String name = mEtName.getText().toString();

      boolean cancel = false;
      View focusView = null;
      if (TextUtils.isEmpty(email)) {
        mLayoutEmail.setErrorEnabled(true);
        mLayoutEmail.setError(getString(R.string.error_field_required));
        focusView = mLayoutEmail;
        cancel = true;
      } else if (!isEmailValid(email)) {
        mLayoutEmail.setErrorEnabled(true);
        mLayoutEmail.setError(getString(R.string.error_invalid_email));
        focusView = mLayoutEmail;
        cancel = true;
      }
      if (TextUtils.isEmpty(name)) {
        mLayoutName.setErrorEnabled(true);
        mLayoutName.setError(getString(R.string.error_field_required));
        focusView = mLayoutName;
        cancel = true;
      }
      if (cancel) {
        focusView.requestFocus();
      } else {
        mLayoutName.setErrorEnabled(false);
        mLayoutEmail.setErrorEnabled(false);
        mLoginPresenter.updateProfile(Preconditions.checkNotNull(mLoginPresenter.getPreferencesHelper().getJsonLogin()).get_$0().getAuth_code(),name, Preconditions.checkNotNull(mLoginPresenter.getPreferencesHelper().getJsonLogin()).get_$0().getId(), email,mEtPhone.getText().toString(),mEtNameFist.getText().toString(),mEtNameLast.getText().toString());
      }
    }catch (Exception e){
      e.printStackTrace();
    }
  }

}
