package com.toan_itc.mobifone.mvp.presenter.login;

import com.toan_itc.mobifone.data.local.PreferencesHelper;
import com.toan_itc.mobifone.data.service.RestData;
import com.toan_itc.mobifone.mvp.model.login.Login;
import com.toan_itc.mobifone.mvp.presenter.base.BasePresenter;
import com.toan_itc.mobifone.mvp.view.login.LoginView;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by Toan.IT
 * Date: 06/06/2016
 */
public class LoginPresenter extends BasePresenter<LoginView> {
    private RestData mRestData;
    private PreferencesHelper mPreferencesHelper;
    @Inject
    LoginPresenter(RestData restData, PreferencesHelper preferencesHelper){
        this.mRestData=restData;
        this.mPreferencesHelper=preferencesHelper;
    }
    public void login(String email,String password){
        getMvpView().showLoading();
        mRestData.getLogin(email,password)
                .subscribe(new Subscriber<Login>() {
                    @Override
                    public void onCompleted() {
                        getMvpView().hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        getMvpView().hideLoading();
                        getMvpView().showError("error!");
                    }

                    @Override
                    public void onNext(Login login) {
                        try {
                            if(login.getId()!=null&&!login.getId().equalsIgnoreCase("")) {
                                getMvpView().login(login);
                                mPreferencesHelper.putUserId(login.getId());
                                mPreferencesHelper.putEmail(login.getEmail());
                            }else
                                getMvpView().login_error();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
    }

    public void changePassword(String passOld,String passNew){
        getMvpView().showLoading();
        mRestData.changePassword(passOld,passNew)
                .subscribe(new Subscriber<Login>() {
                    @Override
                    public void onCompleted() {
                        getMvpView().hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        getMvpView().hideLoading();
                        getMvpView().showError("");
                    }

                    @Override
                    public void onNext(Login shopses) {
                        try {
                            getMvpView().login(null);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
    }

  public void updateProfile(String auth_code,String username, String id,String email,
                            String phone, String first_name,String last_name){
    getMvpView().showLoading();
    mRestData.updateProfile(auth_code,username,id,email,phone,first_name,last_name)
            .subscribe(new Subscriber<Login>() {
              @Override
              public void onCompleted() {
                getMvpView().hideLoading();
              }

              @Override
              public void onError(Throwable e) {
                e.printStackTrace();
                getMvpView().hideLoading();
                getMvpView().showError("");
              }

              @Override
              public void onNext(Login shopses) {
                try {
                  getMvpView().login(null);
                }catch (Exception e){
                  e.printStackTrace();
                }
              }
            });
  }

}
