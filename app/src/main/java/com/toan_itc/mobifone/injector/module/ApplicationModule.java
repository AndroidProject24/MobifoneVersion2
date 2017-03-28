package com.toan_itc.mobifone.injector.module;

import com.toan_itc.mobifone.BaseApplication;
import com.toan_itc.mobifone.data.local.PreferencesHelper;
import com.toan_itc.mobifone.data.rxjava.RxBus;
import com.toan_itc.mobifone.data.service.RestApi;
import com.toan_itc.mobifone.data.service.RestClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

  protected final BaseApplication application;

  public ApplicationModule(BaseApplication application) {
    this.application = application;
  }
  @Provides
  @Singleton
  BaseApplication provideApplication() {
    return application;
  }

  @Provides
  @Singleton
  RestApi mRestApi() {
    return RestClient.sRestClient(application);
  }

  @Provides
  @Singleton
  RxBus mRxBus() {
    return new RxBus();
  }

  @Provides
  @Singleton
  PreferencesHelper mPreferencesHelper() {
    return new PreferencesHelper(application);
  }
}