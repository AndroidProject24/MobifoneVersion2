package com.toan_itc.mobifone.mvp.presenter.vas;

import android.view.View;
import com.toan_itc.mobifone.data.local.PreferencesHelper;
import com.toan_itc.mobifone.data.rxjava.DefaultObserver;
import com.toan_itc.mobifone.data.service.RestData;
import com.toan_itc.mobifone.mvp.model.vas.Goicuoc;
import com.toan_itc.mobifone.mvp.model.vas.Vas;
import com.toan_itc.mobifone.mvp.presenter.base.BasePresenter;
import com.toan_itc.mobifone.mvp.view.vas.VasView;
import com.toan_itc.mobifone.utils.Constant;
import java.util.List;
import javax.inject.Inject;
import okhttp3.ResponseBody;
import retrofit2.Response;

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
      return Constant.LINK_CAPTCHA+mPreferencesHelper.getJsonLogin().get_$0().getAuth_code()+"&iduser="+
          mPreferencesHelper.getJsonLogin().get_$0().getId();
    }
  public void registerGoiCuoc(String sdt,String captcha,String magoi,View.OnClickListener clickListener){
    getMvpView().showLoading();
    addSubscribe(mRestData.registerVas(sdt,captcha,magoi,mPreferencesHelper.getJsonLogin().get_$0().getAuth_code(),
                                      mPreferencesHelper.getJsonLogin().get_$0().getId())
        .subscribe(new DefaultObserver<Vas>() {
          @Override
          public void onNext(Vas vas) {
            try {
              getMvpView().hideLoading();
              if(vas.getError()==2)
                getMvpView().requestLogin();
              else if(vas.getError()==0)
                getMvpView().registerVas(vas);
              else
                getMvpView().showEmptyViewAction(vas.getReason(),clickListener);
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

  public void checkVas(String sdt,String dateStart,String dateEnd){
    getMvpView().showLoading();
    addSubscribe(mRestData.checkVas(mPreferencesHelper.getJsonLogin().get_$0().getAuth_code(),
        mPreferencesHelper.getJsonLogin().get_$0().getId(),sdt,dateStart,dateEnd)
        .subscribe(new DefaultObserver<Response<ResponseBody>>() {
          @Override
          public void onNext(Response<ResponseBody> responseBody) {
            try {
              getMvpView().hideLoading();
              getMvpView().showHtml(responseBody.body().string());
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
