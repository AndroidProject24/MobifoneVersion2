package com.toan_itc.mobifone.ui.activity;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;

import com.toan_itc.mobifone.R;
import com.toan_itc.mobifone.intdef.StringDef;
import com.toan_itc.mobifone.libs.customtabclient.WebviewFallback;
import com.toan_itc.mobifone.libs.customtabclient.broadcast.ShareBroadcastReceiver;
import com.toan_itc.mobifone.libs.customtabclient.helepr.CustomTabActivityHelper;

/**
 * Toan.IT
 * Created by vantoan on 2/26/17.
 * Email: huynhvantoan.itc@gmail.com
 */

public class WebviewActivity extends AppCompatActivity {
  private CustomTabActivityHelper mCustomTabActivityHelper;
  protected String Url="https://www.google.com.vn";
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Intent intent=getIntent();
    Url=intent.getStringExtra(StringDef.BUNDLE_DATA);
    mCustomTabActivityHelper = new CustomTabActivityHelper();
    CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();
    intentBuilder.setToolbarColor(getResources().getColor(R.color.colorPrimary));
    String shareLabel = getString(R.string.action_share);
    Bitmap icon = BitmapFactory.decodeResource(getResources(),
            android.R.drawable.ic_menu_share);
    PendingIntent pendingIntent = createPendingIntent();
    intentBuilder.setActionButton(icon, shareLabel, pendingIntent);
    String menuItemTitle = getString(R.string.app_name);
    PendingIntent menuItemPendingIntent = createPendingIntent();
    intentBuilder.addMenuItem(menuItemTitle, menuItemPendingIntent);
    intentBuilder.setShowTitle(true);
    intentBuilder.enableUrlBarHiding();
    intentBuilder.setCloseButtonIcon(
            BitmapFactory.decodeResource(getResources(), R.drawable.ic_arrow_back));
    intentBuilder.setStartAnimations(this, R.anim.right_in, R.anim.left_out);
    intentBuilder.setExitAnimations(this, android.R.anim.slide_in_left,
            android.R.anim.slide_out_right);
    CustomTabActivityHelper.openCustomTab(
            this, intentBuilder.build(), Uri.parse(Url), new WebviewFallback());
  }
  private PendingIntent createPendingIntent() {
    Intent actionIntent = new Intent(this.getApplicationContext(), ShareBroadcastReceiver.class);
    return PendingIntent.getBroadcast(getApplicationContext(), 0, actionIntent, 0);
  }
  @Override
  protected void onStart() {
    super.onStart();
    mCustomTabActivityHelper.bindCustomTabsService(this);
  }
  @Override
  protected void onStop() {
    super.onStop();
    mCustomTabActivityHelper.unbindCustomTabsService(this);
    this.finish();
  }
}
