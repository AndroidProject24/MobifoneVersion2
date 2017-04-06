package com.toan_itc.mobifone.mvp.presenter.main;

import com.toan_itc.mobifone.data.local.PreferencesHelper;
import com.toan_itc.mobifone.data.rxjava.DefaultObserver;
import com.toan_itc.mobifone.data.service.RestData;
import com.toan_itc.mobifone.mvp.model.login.Exit;
import com.toan_itc.mobifone.mvp.model.login.Login;
import com.toan_itc.mobifone.mvp.presenter.base.BasePresenter;
import com.toan_itc.mobifone.mvp.view.main.MainView;
import javax.inject.Inject;

/**
 * Created by Toan.IT
 * Date: 06/06/2016
 */
public class MainPresenter extends BasePresenter<MainView> {
    private RestData mRestData;
    private PreferencesHelper mPreferencesHelper;
    @Inject MainPresenter(RestData restData, PreferencesHelper preferencesHelper){
        this.mRestData=restData;
        this.mPreferencesHelper=preferencesHelper;
    }
    public void exit(){
      addSubscribe(mRestData.exitApp(mPreferencesHelper.getJsonLogin().get_$0().getAuth_code(),mPreferencesHelper.getJsonLogin().get_$0().getId())
                .subscribe(new DefaultObserver<Exit>() {
                    @Override
                    public void onNext(Exit exit) {
                        try {
                          getMvpView().exit();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                  @Override
                  public void onError(Throwable exception) {
                    getMvpView().exit();
                  }
                }));
    }
  public void checkLogin(){
    mRestData.getLogin(mPreferencesHelper.getUserId(),mPreferencesHelper.getUserPass())
        .subscribe(new DefaultObserver<Login>() {
          @Override
          public void onNext(Login login) {
            try {
              if(login.getError()==0) {
                mPreferencesHelper.putJsonLogin(login);
              }
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
