package com.toan_itc.mobifone.injector.component;

import com.toan_itc.mobifone.data.local.PreferencesHelper;
import com.toan_itc.mobifone.data.rxjava.RxBus;
import com.toan_itc.mobifone.data.service.RestData;
import com.toan_itc.mobifone.injector.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { ApplicationModule.class })
public interface ApplicationComponent {
    RestData mRestData();
    RxBus mRxBus();
    PreferencesHelper mPreferencesHelper();
}