package com.toan_itc.mobifone.mvp.presenter.vas;

import com.toan_itc.mobifone.data.local.PreferencesHelper;
import com.toan_itc.mobifone.data.rxjava.DefaultObserver;
import com.toan_itc.mobifone.data.service.RestData;
import com.toan_itc.mobifone.mvp.model.vas.Goicuoc;
import com.toan_itc.mobifone.mvp.model.vas.Vas;
import com.toan_itc.mobifone.mvp.presenter.base.BasePresenter;
import com.toan_itc.mobifone.mvp.view.vas.VasView;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by Toan.IT
 * Date: 06/06/2016
 */
public class VasPresenter extends BasePresenter<VasView> {
    private RestData mRestData;
    private PreferencesHelper mPreferencesHelper;
    @Inject VasPresenter(RestData restData, PreferencesHelper preferencesHelper){
        this.mRestData=restData;
        this.mPreferencesHelper=preferencesHelper;
    }

    public void getGoiCuoc(){
      addSubscribe(mRestData.getGoicuoc()
                .subscribe(new DefaultObserver<List<Goicuoc>>() {
                    @Override
                    public void onNext(List<Goicuoc> goicuocList) {
                        try {
                          getMvpView().getGoiCuoc(goicuocList);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                  @Override
                  public void onError(Throwable e) {
                    e.printStackTrace();
                  }

                }));
    }

    public String imgCaptcha(){
      return "http://n3t.top/test/api/captcha?auth_code="+mPreferencesHelper.getJsonLogin().get_$0().getAuth_code()+"&iduser="+
          mPreferencesHelper.getJsonLogin().get_$0().getId();//88c7696e0a1c143ff8a03bf654d4e0be&iduser=2";
    }
  public void registerGoiCuoc(String sdt,String captcha,String magoi){
    getMvpView().showLoading();
    addSubscribe(mRestData.registerVas(mPreferencesHelper.getJsonLogin().get_$0().getAuth_code(),mPreferencesHelper.getJsonLogin().get_$0().getId(),
                sdt,captcha,magoi)
        .subscribe(new DefaultObserver<Vas>() {
          @Override
          public void onNext(Vas vas) {
            try {
              getMvpView().hideLoading();
              getMvpView().registerVas(vas);
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
