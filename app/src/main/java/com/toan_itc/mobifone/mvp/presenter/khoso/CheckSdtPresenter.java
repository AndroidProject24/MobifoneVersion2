package com.toan_itc.mobifone.mvp.presenter.khoso;

import com.toan_itc.mobifone.data.local.PreferencesHelper;
import com.toan_itc.mobifone.data.rxjava.DefaultObserver;
import com.toan_itc.mobifone.data.service.RestData;
import com.toan_itc.mobifone.mvp.model.khoso.CheckSdt;
import com.toan_itc.mobifone.mvp.presenter.base.BasePresenter;
import com.toan_itc.mobifone.mvp.view.khoso.CheckSdtView;
import dagger.internal.Preconditions;
import javax.inject.Inject;

/**
 * Created by Toan.IT
 * Date: 06/06/2016
 */
public class CheckSdtPresenter extends BasePresenter<CheckSdtView> {
    private RestData mRestData;
    private PreferencesHelper mPreferencesHelper;
    @Inject CheckSdtPresenter(RestData restData, PreferencesHelper preferencesHelper){
        this.mRestData=restData;
        this.mPreferencesHelper=preferencesHelper;
    }

  public void checkSDT(String sdt){
    Preconditions.checkNotNull(mPreferencesHelper.getJsonLogin().get_$0().getAuth_code());
    Preconditions.checkNotNull(mPreferencesHelper.getJsonLogin().get_$0().getId());
    mRestData.checkSDT(mPreferencesHelper.getJsonLogin().get_$0().getAuth_code(),mPreferencesHelper.getJsonLogin().get_$0().getId(),sdt)
        .subscribe(new DefaultObserver<CheckSdt>() {
          @Override
          public void onNext(CheckSdt checkSdt) {
            try{
              if(checkSdt!=null)
                getMvpView().checkSdt(checkSdt);
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
