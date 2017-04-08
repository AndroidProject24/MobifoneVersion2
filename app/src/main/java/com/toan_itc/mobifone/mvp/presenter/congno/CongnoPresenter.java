package com.toan_itc.mobifone.mvp.presenter.congno;

import com.toan_itc.mobifone.data.local.PreferencesHelper;
import com.toan_itc.mobifone.data.rxjava.DefaultObserver;
import com.toan_itc.mobifone.data.service.RestData;
import com.toan_itc.mobifone.mvp.model.congno.Congno;
import com.toan_itc.mobifone.mvp.presenter.base.BasePresenter;
import com.toan_itc.mobifone.mvp.view.congno.CongnoView;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by Toan.IT
 * Date: 06/06/2016
 */
public class CongnoPresenter extends BasePresenter<CongnoView> {
    private RestData mRestData;
    private PreferencesHelper mPreferencesHelper;
    @Inject CongnoPresenter(RestData restData, PreferencesHelper preferencesHelper){
        this.mRestData=restData;
        this.mPreferencesHelper=preferencesHelper;
    }
    public void getCongno(){
      getMvpView().showLoading();
      addSubscribe(mRestData.getCongno(mPreferencesHelper.getJsonLogin().get_$0().getAuth_code(),mPreferencesHelper.getJsonLogin().get_$0().getId())
                .subscribe(new DefaultObserver<List<Congno>>() {
                    @Override
                    public void onNext(List<Congno> congnoList) {
                        try {
                          getMvpView().hideLoading();
                          if(!congnoList.isEmpty()) {
                            getMvpView().showData(congnoList);
                          }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                  @Override
                  public void onError(Throwable e) {
                    e.printStackTrace();
                    getMvpView().hideLoading();
                    getMvpView().showError(e.getMessage());
                  }

                }));
    }

  public PreferencesHelper getPreferencesHelper(){
    return mPreferencesHelper;
  }
}
