package com.toan_itc.mobifone.mvp.view.login;

import com.toan_itc.mobifone.mvp.model.login.Login;
import com.toan_itc.mobifone.mvp.view.base.BaseView;

/**
 * Created by Toan.IT
 * Date: 28/05/2016
 */
public interface LoginView extends BaseView {

    void login(Login login);

    void login_error();
}
