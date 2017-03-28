package com.toan_itc.mobifone.mvp.presenter.login;

import com.toan_itc.mobifone.data.local.PreferencesHelper;
import com.toan_itc.mobifone.data.service.RestData;
import com.toan_itc.mobifone.mvp.model.register.Register;
import com.toan_itc.mobifone.mvp.presenter.base.BasePresenter;
import com.toan_itc.mobifone.mvp.view.login.RegisterView;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by Toan.IT
 * Date: 06/06/2016
 */
public class RegisterPresenter extends BasePresenter<RegisterView> {
    private RestData mRestData;
    private PreferencesHelper mPreferencesHelper;
    @Inject
    RegisterPresenter(RestData restData, PreferencesHelper preferencesHelper){
        this.mRestData=restData;
        this.mPreferencesHelper=preferencesHelper;
    }

    public void register(String email,String user,String password){
        getMvpView().showLoading();
        mRestData.getRegister(email, user, password)
                .subscribe(new Subscriber<Register>() {
                    @Override
                    public void onCompleted() {
                        getMvpView().hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        getMvpView().hideLoading();
                        getMvpView().showError("Đăng ký lỗi!");
                    }

                    @Override
                    public void onNext(Register register) {
                        try {
                          if(register.getError()==0) {
                            getMvpView().register(register);
                          }else
                            getMvpView().registerError(register.getReason());
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
    }

    public PreferencesHelper getPreferencesHelper(){
        return mPreferencesHelper;
    }
}
