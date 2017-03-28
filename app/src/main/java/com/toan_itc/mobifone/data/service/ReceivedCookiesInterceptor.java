package com.toan_itc.mobifone.data.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Toan.IT
 * Created by vantoan on 3/24/17.
 * Email: Huynhvantoan.itc@gmail.com
 */

public class ReceivedCookiesInterceptor implements Interceptor {
  private Context context;
  public ReceivedCookiesInterceptor(Context context) {
    this.context = context;
  } // AddCookiesInterceptor()
  //a%3A10%3A%7Bs%3A10%3A%22session_id%22%3Bs%3A32%3A%227d93651051e8b3a93d3eb791b2d49b02%22%3Bs%3A10%3A%22ip_address%22%3Bs%3A12%3A%2227.78.25.222%22%3Bs%3A10%3A%22user_agent%22%3Bs%3A120%3A%22Mozilla%2F5.0+%28Macintosh%3B+Intel+Mac+OS+X+10_12_3%29+AppleWebKit%2F537.36+%28KHTML%2C+like+Gecko%29+Chrome%2F57.0.2987.110+Safari%2F537.3%22%3Bs%3A13%3A%22last_activity%22%3Bi%3A1490367127%3Bs%3A9%3A%22user_data%22%3Bs%3A0%3A%22%22%3Bs%3A8%3A%22identity%22%3Bs%3A4%3A%22test%22%3Bs%3A8%3A%22username%22%3Bs%3A4%3A%22test%22%3Bs%3A5%3A%22email%22%3Bs%3A18%3A%22test%40localhost.com%22%3Bs%3A7%3A%22user_id%22%3Bs%3A1%3A%222%22%3Bs%3A14%3A%22old_last_login%22%3Bs%3A10%3A%221490367363%22%3B%7Db50acf51664086db34e07b03e19667b09a490244
  @Override
  public Response intercept(Chain chain) throws IOException {
    Response originalResponse = chain.proceed(chain.request());

    if (!originalResponse.headers("Set-Cookie").isEmpty()) {
      HashSet<String> cookies = (HashSet<String>) PreferenceManager.getDefaultSharedPreferences(context).getStringSet("PREF_COOKIES", new HashSet<String>());

      for (String header : originalResponse.headers("Set-Cookie")) {
        cookies.add(header);
      }

      SharedPreferences.Editor memes = PreferenceManager.getDefaultSharedPreferences(context).edit();
      memes.putStringSet("PREF_COOKIES", cookies).apply();
      memes.commit();
    }

    return originalResponse;
  }
}