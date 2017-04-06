package com.toan_itc.mobifone.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.toan_itc.mobifone.mvp.model.login.Login;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PreferencesHelper {
  private static SharedPreferences mPref;
  private static final String PREF_FILE_NAME = "Salon_pref_file";
  private static final String PREF_FINISH_APP = "PREF_FINISH_APP";
  private static final String PREF_KEY_USER_ID = "PREF_KEY_USER_ID";
  private static final String PREF_KEY_USER_PASS = "PREF_KEY_USER_PASS";
  private static final String PREF_USER= "PREF_USER";
  @Inject
  public PreferencesHelper(Context context) {
    mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
  }
  public void clear() {
    mPref.edit().clear().apply();
  }

  public void putUserId(@NonNull String userId) {
    mPref.edit().putString(PREF_KEY_USER_ID, userId).apply();
  }
  @NonNull
  public String getUserId() {
    return mPref.getString(PREF_KEY_USER_ID,"");
  }

  public void putUserPass(@NonNull String userPass) {
    mPref.edit().putString(PREF_KEY_USER_PASS, userPass).apply();
  }
  @NonNull
  public String getUserPass() {
    return mPref.getString(PREF_KEY_USER_PASS,"");
  }

  public void putfinshApp(@NonNull String day) {
    mPref.edit().putString(PREF_FINISH_APP, day).apply();
  }
  public String getfinishApp() {
    return mPref.getString(PREF_FINISH_APP,null);
  }

  public void putJsonLogin(Login login) {
    mPref.edit().putString(PREF_USER, new Gson().toJson(login)).apply();
  }

  @Nullable
  public Login getJsonLogin() {
    String data = mPref.getString(PREF_USER, null);
    if (data != null) {
      return new Gson().fromJson(data, Login.class);
    }
    return null;
  }
}
