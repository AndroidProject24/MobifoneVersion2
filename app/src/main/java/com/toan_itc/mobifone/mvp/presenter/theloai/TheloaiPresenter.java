package com.toan_itc.mobifone.mvp.presenter.theloai;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.toan_itc.mobifone.data.local.PreferencesHelper;
import com.toan_itc.mobifone.data.rxjava.DefaultObserver;
import com.toan_itc.mobifone.data.service.RestData;
import com.toan_itc.mobifone.mvp.model.theloai.Theloai;
import com.toan_itc.mobifone.mvp.presenter.base.BasePresenter;
import com.toan_itc.mobifone.mvp.view.theloai.TheloaiView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Toan.IT
 * Date: 06/06/2016
 */
public class TheloaiPresenter extends BasePresenter<TheloaiView> {
    private RestData mRestData;
    private PreferencesHelper mPreferencesHelper;
    @Inject
    TheloaiPresenter(RestData restData, PreferencesHelper preferencesHelper){
        this.mRestData=restData;
        this.mPreferencesHelper=preferencesHelper;
    }
    @RxLogObservable
    public void getData(int theloai){
      getMvpView().showLoading();
        addSubscribe(mRestData.getData(theloai)
               /* .doOnSubscribe(() -> getMvpView().showLoading())
                .doOnCompleted(() -> getMvpView().hideLoading())*/
                .subscribe(new DefaultObserver<List<Theloai>>() {
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        getMvpView().showError(e.getMessage());
                       getMvpView().hideLoading();
                    }

                    @Override
                    public void onNext(List<Theloai> theloaiList) {
                        try {
                          getMvpView().hideLoading();
                            if(theloaiList!=null&&!theloaiList.isEmpty()) {
                                getMvpView().showData(theloaiList);
                            }else
                                getMvpView().showEmptyView("emty");
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }));
    }
}
