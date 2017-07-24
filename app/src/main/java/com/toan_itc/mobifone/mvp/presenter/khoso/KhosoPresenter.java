package com.toan_itc.mobifone.mvp.presenter.khoso;

import android.content.Context;
import android.widget.Spinner;
import com.toan_itc.mobifone.data.local.PreferencesHelper;
import com.toan_itc.mobifone.data.rxjava.DefaultObserver;
import com.toan_itc.mobifone.data.service.RestData;
import com.toan_itc.mobifone.libs.logger.Logger;
import com.toan_itc.mobifone.mvp.model.khoso.Dangsim;
import com.toan_itc.mobifone.mvp.model.khoso.Khoso;
import com.toan_itc.mobifone.mvp.presenter.base.BasePresenter;
import com.toan_itc.mobifone.mvp.view.khoso.KhosoView;
import com.toan_itc.mobifone.ui.adapter.khoso.TheloaiAdapter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import retrofit2.Response;

/**
 * Created by Toan.IT
 * Date: 06/06/2016
 */
public class KhosoPresenter extends BasePresenter<KhosoView> {
  private RestData mRestData;
  private PreferencesHelper mPreferencesHelper;
  private List<Khoso.DataBean> dataList=new ArrayList<>();
  @Inject
  KhosoPresenter(RestData restData, PreferencesHelper preferencesHelper){
    this.mRestData=restData;
    this.mPreferencesHelper=preferencesHelper;
  }
  public void searchSim(String search, String kho, String dau, String dang){
    getMvpView().showLoading();
    addSubscribe(mRestData.getKhoSim(search,kho,dau,dang)
            .subscribe(new DefaultObserver<Khoso>() {
              @Override
              public void onError(Throwable e) {
                e.printStackTrace();
                getMvpView().hideLoading();
                //getMvpView().showError(e.getMessage());
              }

              @Override
              public void onNext(Khoso khoso) {
                try {
                  getMvpView().hideLoading();
                  if(khoso.getData()!=null) {
                    getMvpView().listSim(khoso);
                  }else
                    getMvpView().emty("Sim chưa có bạn có thể chọn kho số khác!");
                }catch (Exception e){
                  e.printStackTrace();
                }
              }
            }));
  }
  public List<Khoso.DataBean> loadMore(String url){
    Logger.e("loadMore="+url);
    getMvpView().showLoading();
    addSubscribe(mRestData.getLoadmore(url)
            .subscribe(new DefaultObserver<Khoso>() {
              @Override
              public void onError(Throwable e) {
                e.printStackTrace();
                getMvpView().hideLoading();
                //getMvpView().showError(e.getMessage());
              }

              @Override
              public void onNext(Khoso khoso) {
                try {
                  getMvpView().hideLoading();
                  if(khoso!=null&&!khoso.getData().isEmpty()) {
                   dataList=khoso.getData();
                   getMvpView().nextLink(khoso.getPage().getNextLink());
                  }else
                    getMvpView().emty("Hết dữ liệu!");
                }catch (Exception e){
                  e.printStackTrace();
                }
              }
            }));
    return dataList;
  }
  public void dangSim(String noibat){
    getMvpView().showLoading();
    addSubscribe(mRestData.getDangSim(noibat)
            .subscribe(new DefaultObserver<List<Dangsim>>() {
              @Override
              public void onError(Throwable e) {
                e.printStackTrace();
                getMvpView().hideLoading();
                //getMvpView().showError(e.getMessage());
              }

              @Override
              public void onNext(List<Dangsim> dangsimList) {
                try {
                  getMvpView().hideLoading();
                  if(dangsimList!=null&&!dangsimList.isEmpty()) {
                    getMvpView().listDangSim(dangsimList);
                  }
                }catch (Exception e){
                  e.printStackTrace();
                }
              }
            }));
  }
  public void getTheloai(Context mContext,Spinner spinner){
    addSubscribe(mRestData.getTheLoai(115)
        .subscribe(new DefaultObserver<List<com.toan_itc.mobifone.mvp.model.khoso.Theloai>>() {
          @Override
          public void onNext(List<com.toan_itc.mobifone.mvp.model.khoso.Theloai> theloaiList) {
            try {
              getMvpView().hideLoading();
              if(theloaiList!=null&&!theloaiList.isEmpty()) {
                TheloaiAdapter theloaiAdapter=new TheloaiAdapter(mContext, theloaiList);
                spinner.setAdapter(theloaiAdapter);
              }
            }catch (Exception e){
              e.printStackTrace();
            }
          }
        }));
  }

  public PreferencesHelper getPreferencesHelper(){
    return mPreferencesHelper;
  }
}
