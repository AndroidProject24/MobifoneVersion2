package com.toan_itc.mobifone.ui.fragment.login;

import android.content.Context;
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
import com.toan_itc.mobifone.ui.fragment.MainFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.toan_itc.mobifone.R.id.etName;
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
    @BindView(R.id.btnRegister)
    Button mBtnRegister;
    @BindView(R.id.layout_email)
    TextInputLayout mLayoutEmail;
    @BindView(R.id.layout_pass)
    TextInputLayout mLayoutPass;
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
    }

    @Override
    public void register(Register register) {
        replaceFagment(getFragmentManager(),R.id.fragment, MainFragment.newInstance());
    }
    @OnClick(R.id.btnRegister)
    void user_signup_button(){
        check_Register();
    }

    private void check_Register() {
        mLayoutEmail.setError(null);
        mLayoutPass.setError(null);
        String email = mEtEmail.getText().toString();
        String user = mEtName.getText().toString();
        String password = mEtPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mLayoutPass.setErrorEnabled(true);
            mLayoutPass.setError(getString(R.string.error_invalid_password));
            focusView = mLayoutPass;
            cancel = true;
        }
        if (!TextUtils.isEmpty(user) && !isPasswordValid(user)) {
            mLayoutPass.setErrorEnabled(true);
            mLayoutPass.setError(getString(R.string.error_field_required));
            focusView = mLayoutPass;
            cancel = true;
        }
        if (TextUtils.isEmpty(email)) {
            mLayoutName.setErrorEnabled(true);
            mLayoutName.setError(getString(R.string.error_field_required));
            focusView = mLayoutName;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mLayoutEmail.setErrorEnabled(true);
            mLayoutEmail.setError(getString(R.string.error_invalid_email));
            focusView = mLayoutEmail;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            mLayoutEmail.setErrorEnabled(false);
            mLayoutPass.setErrorEnabled(false);
            mLayoutName.setErrorEnabled(false);
            mRegisterPresenter.register(email, user, password);
        }
    }
}
