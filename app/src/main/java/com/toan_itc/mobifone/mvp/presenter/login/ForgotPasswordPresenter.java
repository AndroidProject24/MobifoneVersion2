package com.toan_itc.mobifone.mvp.presenter.login;

import com.toan_itc.mobifone.data.local.PreferencesHelper;
import com.toan_itc.mobifone.data.service.RestData;
import com.toan_itc.mobifone.mvp.presenter.base.BasePresenter;
import com.toan_itc.mobifone.mvp.view.login.ForgotPassView;

import javax.inject.Inject;

/**
 * Created by Toan.IT
 * Date: 06/06/2016
 */
public class ForgotPasswordPresenter extends BasePresenter<ForgotPassView> {
    private RestData mRestData;
    private PreferencesHelper mPreferencesHelper;
    @Inject
    ForgotPasswordPresenter(RestData restData, PreferencesHelper preferencesHelper){
        this.mRestData=restData;
        this.mPreferencesHelper=preferencesHelper;
    }
    public void forgotPass(String email){
        getMvpView().showLoading();
        /*mRestData.changePassword(email, Constant.SHOP_ID)
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {
                        getMvpView().hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        getMvpView().hideLoading();
                        getMvpView().showError(e.getMessage());
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            if(responseBody!=null)
                                getMvpView().forgotPass();
                            else
                                getMvpView().showError("Không đổi mật khẩu được!");
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });*/
    }
    public PreferencesHelper getPreferencesHelper(){
        return mPreferencesHelper;
    }
}
