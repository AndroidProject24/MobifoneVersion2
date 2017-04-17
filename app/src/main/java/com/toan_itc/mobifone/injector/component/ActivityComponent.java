package com.toan_itc.mobifone.injector.component;

import com.toan_itc.mobifone.injector.module.ActivityModule;
import com.toan_itc.mobifone.injector.qualifier.PerActivity;
import com.toan_itc.mobifone.ui.activity.MainActivity;
import com.toan_itc.mobifone.ui.fragment.MainFragment;
import com.toan_itc.mobifone.ui.fragment.congno.CongnoFragment;
import com.toan_itc.mobifone.ui.fragment.khoso.DataFragment;
import com.toan_itc.mobifone.ui.fragment.khoso.KhosoFragment;
import com.toan_itc.mobifone.ui.fragment.km.CTKMSodepFragment;
import com.toan_itc.mobifone.ui.fragment.km.CTKMTrasauFragment;
import com.toan_itc.mobifone.ui.fragment.km.KMNaptheFragment;
import com.toan_itc.mobifone.ui.fragment.login.ChangePasswordFragment;
import com.toan_itc.mobifone.ui.fragment.login.LoginFragment;
import com.toan_itc.mobifone.ui.fragment.login.RegisterFragment;
import com.toan_itc.mobifone.ui.fragment.login.UpdateProfileFragment;
import com.toan_itc.mobifone.ui.fragment.thutuc.ThutucFragment;
import com.toan_itc.mobifone.ui.fragment.upanh.TraSauCanhanFragment;
import com.toan_itc.mobifone.ui.fragment.upanh.TraSauDoanhnghiepFragment;
import com.toan_itc.mobifone.ui.fragment.upanh.UpanhTratruocFragment;

import com.toan_itc.mobifone.ui.fragment.vas.CheckVasFragment;
import com.toan_itc.mobifone.ui.fragment.vas.VasFragment;
import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

  void inject(MainActivity mainActivity);

  void inject(MainFragment mainFragment);

  /*LOGIN*/
  void inject(LoginFragment loginFragment);

  void inject(RegisterFragment registerFragment);

  void inject(ChangePasswordFragment changePasswordFragment);

  void inject(UpdateProfileFragment updateProfileFragment);

  /*KHOSO*/
  void inject(KhosoFragment khosoFragment);

  void inject(DataFragment dataFragment);

  /*THUTUC*/
  void inject(ThutucFragment thutucFragment);

  /*UPANH*/
  void inject(UpanhTratruocFragment upanhTratruocFragment);

  void inject(TraSauCanhanFragment traSauCanhanFragment);

  void inject(TraSauDoanhnghiepFragment traSauDoanhnghiepFragment);

  /*KM*/
  void inject(CTKMSodepFragment ctkmSodepFragment);

  void inject(KMNaptheFragment kmNaptheFragment);

  void inject(CTKMTrasauFragment ctkmTrasauFragment);

  /*CONGNO*/
  void inject(CongnoFragment congnoFragment);

  /*VAS*/
  void inject(VasFragment vasFragment);

  void inject(CheckVasFragment checkVasFragment);
}