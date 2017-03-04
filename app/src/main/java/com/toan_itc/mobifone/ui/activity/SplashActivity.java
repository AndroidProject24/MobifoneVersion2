package com.toan_itc.mobifone.ui.activity;

import com.toan_itc.mobifone.R;
import com.toan_itc.mobifone.data.rxjava.DefaultObserver;
import com.toan_itc.mobifone.libs.particleview.ParticleView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by vantoan on 2/24/17.
 * Email: huynhvantoan.itc@gmail.com
 */

public class SplashActivity extends BaseActivity {
  @BindView(R.id.splash)
  ParticleView mParticleView;
  @Override
  protected String getTAG() {
    return this.getClass().getSimpleName();
  }

  @Override
  protected void initViews() {
    addSubscription(Observable.timer(200, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new DefaultObserver<Long>(){
              @Override
              public void onCompleted() {
                mParticleView.startAnim();
              }
            }));
  }

  @Override
  protected int setLayoutResourceID() {
    return R.layout.splash_fragment;
  }

  @Override
  protected void initData() {
    addSubscription(Observable.timer(3, TimeUnit.SECONDS)
            .subscribe(new DefaultObserver<Long>(){
              @Override
              public void onCompleted() {
                startActivityWithoutExtras(MainActivity.class);
                finish();
              }
            }));
  }

  @Override
  protected void injectDependencies() {

  }
}
