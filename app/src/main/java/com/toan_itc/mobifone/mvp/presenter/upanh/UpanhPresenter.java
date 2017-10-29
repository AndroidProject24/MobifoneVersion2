package com.toan_itc.mobifone.mvp.presenter.upanh;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.toan_itc.mobifone.data.local.PreferencesHelper;
import com.toan_itc.mobifone.data.rxjava.DefaultObserver;
import com.toan_itc.mobifone.data.service.RestData;
import com.toan_itc.mobifone.libs.logger.Logger;
import com.toan_itc.mobifone.mvp.model.upanh.Upanh;
import com.toan_itc.mobifone.mvp.presenter.base.BasePresenter;
import com.toan_itc.mobifone.mvp.view.upanh.UpanhView;
import dagger.internal.Preconditions;
import java.io.File;
import javax.inject.Inject;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Toan.IT
 * Date: 06/06/2016
 */
public class UpanhPresenter extends BasePresenter<UpanhView> {
  private RestData mRestData;
  private PreferencesHelper mPreferencesHelper;
  @Inject
  UpanhPresenter(RestData restData, PreferencesHelper preferencesHelper){
    this.mRestData=restData;
    this.mPreferencesHelper=preferencesHelper;
  }

  public void uploadTratruoc(String sdt, String theloai, File cmnd_mt,File cmnd_ms,File phieu){
    AndroidNetworking.upload("http://n3t.top/test/api/upanh/tratruoc")
            .addMultipartFile("cmnd_mt",cmnd_mt)
            .addMultipartFile("cmnd_ms",cmnd_ms)
            .addMultipartFile("phieu",phieu)
            .addMultipartParameter("sdt",sdt)
            .addMultipartParameter("idloai",theloai)
            .addMultipartParameter("auth_code", Preconditions.checkNotNull(mPreferencesHelper.getJsonLogin().get_$0().getAuth_code()))
            .addMultipartParameter("iduser",Preconditions.checkNotNull(mPreferencesHelper.getJsonLogin().get_$0().getId()))
            .setTag("uploadTraTruoc")
            .setPriority(Priority.HIGH)
            .build()
            .setUploadProgressListener((bytesUploaded, totalBytes) -> {
              float m = (bytesUploaded / (float) totalBytes);
              int pro = (int) (m * 100);
              getMvpView().uploadProgress(pro);
            })
            .getAsJSONObject(new JSONObjectRequestListener() {
              @Override
              public void onResponse(JSONObject response) {
                String error="0";
                try {
                  error=response.getString("error");
                } catch (JSONException e) {
                  e.printStackTrace();
                }
                if(error.equalsIgnoreCase("2"))
                  getMvpView().requestLogin();
                else if(error.equalsIgnoreCase("0"))
                  getMvpView().uploadOK();
                else
                  getMvpView().uploadFail();
              }
              @Override
              public void onError(ANError error) {
                  Logger.e("uploadFail"+error.getMessage());
                getMvpView().uploadFail();
              }
            });
  }
  public void uploadTratruocTest(RequestBody sdt, RequestBody idDV, MultipartBody.Part cmnd_mt, MultipartBody.Part cmnd_ms, MultipartBody.Part hd){
    addSubscribe(mRestData.uploadTraTruoc(sdt,idDV,cmnd_mt,cmnd_ms,hd)
            .doOnSubscribe(() -> getMvpView().showLoading())
            .doOnCompleted(() -> getMvpView().hideLoading())
            .subscribe(new DefaultObserver<Upanh>() {
              @Override
              public void onError(Throwable e) {
                e.printStackTrace();
                getMvpView().showError("1");
              }

              @Override
              public void onNext(Upanh upanh) {
                try {
                  if(upanh.getError()==0) {
                    getMvpView().uploadOK();
                  }else
                    getMvpView().uploadFail();
                }catch (Exception e){
                  e.printStackTrace();
                }
              }
            }));
  }

  public void uploadTraSauCanhan(String sdt, String theloai, File cmnd_mt,File cmnd_ms,File hd_mt,File hd_ms,File hd){
    Logger.e(sdt+"theloai="+theloai);
    AndroidNetworking.upload("http://n3t.top/test/api/upanh/trasaucanhan")
            .addMultipartFile("cmnd_mt",cmnd_mt)
            .addMultipartFile("cmnd_ms",cmnd_ms)
            .addMultipartFile("hopdong_mt",hd_mt)
            .addMultipartFile("hopdong_ms",hd_ms)
            .addMultipartFile("phuluc4",hd)
            .addMultipartParameter("sdt",sdt)
            .addMultipartParameter("idloai",theloai)
            .addMultipartParameter("auth_code", Preconditions.checkNotNull(mPreferencesHelper.getJsonLogin().get_$0().getAuth_code()))
            .addMultipartParameter("iduser",Preconditions.checkNotNull(mPreferencesHelper.getJsonLogin().get_$0().getId()))
            .setTag("uploadTraSauCanhan")
            .setPriority(Priority.HIGH)
            .build()
            .setUploadProgressListener((bytesUploaded, totalBytes) -> {
              float m = (bytesUploaded / (float) totalBytes);
              int pro = (int) (m * 100);
              getMvpView().uploadProgress(pro);
            })
            .getAsJSONObject(new JSONObjectRequestListener() {
              @Override
              public void onResponse(JSONObject response) {
                String error="0";
                try {
                  error=response.getString("error");
                } catch (JSONException e) {
                  e.printStackTrace();
                }
                Logger.e(response.toString());
                if(error.equalsIgnoreCase("2"))
                  getMvpView().requestLogin();
                else if(error.equalsIgnoreCase("0"))
                  getMvpView().uploadOK();
                else
                  getMvpView().uploadFail();
              }
              @Override
              public void onError(ANError error) {
                getMvpView().uploadFail();
              }
            });
  }
  public void uploadTraSauCanhanTest(String sdt, String idDV, RequestBody cmnd_mt,RequestBody cmnd_ms,RequestBody hd_mt,RequestBody hd_ms,RequestBody hd){
    addSubscribe(mRestData.uploadTraSau(sdt,idDV,cmnd_mt,cmnd_ms,hd_mt,hd_ms,hd)
            .doOnSubscribe(() -> getMvpView().showLoading())
            .doOnCompleted(() -> getMvpView().hideLoading())
            .subscribe(new DefaultObserver<Upanh>() {
              @Override
              public void onError(Throwable e) {
                e.printStackTrace();
                getMvpView().showError(e.getMessage());
              }

              @Override
              public void onNext(Upanh upanh) {
                try {
                  if(upanh.getError()==0) {
                    getMvpView().uploadOK();
                  }else
                    getMvpView().uploadFail();
                }catch (Exception e){
                  e.printStackTrace();
                }
              }
            }));
  }
  public void uploadDoanhnghiep(String sdt, String theloai, File cmnd_mt,File cmnd_ms,File hd_mt,File hd_ms,File hd,File gpkd){
    AndroidNetworking.upload("http://n3t.top/test/api/upanh/trasaudoanhnghiep")
            .addMultipartFile("cmnd_mt",cmnd_mt)
            .addMultipartFile("cmnd_ms",cmnd_ms)
            .addMultipartFile("hopdong_mt",hd_mt)
            .addMultipartFile("hopdong_ms",hd_ms)
            .addMultipartFile("phuluc4",hd)
            .addMultipartFile("gpkd",gpkd)
            .addMultipartParameter("sdt",sdt)
            .addMultipartParameter("idloai",theloai)
            .addMultipartParameter("auth_code", Preconditions.checkNotNull(mPreferencesHelper.getJsonLogin().get_$0().getAuth_code()))
            .addMultipartParameter("iduser",Preconditions.checkNotNull(mPreferencesHelper.getJsonLogin().get_$0().getId()))
            .setTag("uploadDoanhnghiep")
            .setPriority(Priority.HIGH)
            .build()
            .setUploadProgressListener((bytesUploaded, totalBytes) -> {
              float m = (bytesUploaded / (float) totalBytes);
              int pro = (int) (m * 100);
              getMvpView().uploadProgress(pro);
            })
            .getAsJSONObject(new JSONObjectRequestListener() {
              @Override
              public void onResponse(JSONObject response) {
                String error="0";
                try {
                   error=response.getString("error");
                } catch (JSONException e) {
                  e.printStackTrace();
                }
                if(error.equalsIgnoreCase("2"))
                  getMvpView().requestLogin();
                else if(error.equalsIgnoreCase("0"))
                  getMvpView().uploadOK();
                else
                  getMvpView().uploadFail();
              }
              @Override
              public void onError(ANError error) {
                getMvpView().uploadFail();
              }
            });
  }
  public void uploadDoanhnghiepTest(String sdt, String idDV, RequestBody cmnd_mt,RequestBody cmnd_ms,RequestBody hd_mt,RequestBody hd_ms,RequestBody hd,RequestBody gpkd){
    addSubscribe(mRestData.uploadDoanhnghiep(sdt,idDV,cmnd_mt,cmnd_ms,hd_mt,hd_ms,hd,gpkd)
            .doOnSubscribe(() -> getMvpView().showLoading())
            .doOnCompleted(() -> getMvpView().hideLoading())
            .subscribe(new DefaultObserver<Upanh>() {
              @Override
              public void onError(Throwable e) {
                e.printStackTrace();
                getMvpView().showError(e.getMessage());
              }

              @Override
              public void onNext(Upanh upanh) {
                try {
                  if(upanh.getError()==0) {
                    getMvpView().uploadOK();
                  }else
                    getMvpView().uploadFail();
                }catch (Exception e){
                  e.printStackTrace();
                }
              }
            }));
  }
  public PreferencesHelper getmPreferencesHelper(){
    return mPreferencesHelper;
  }
}
