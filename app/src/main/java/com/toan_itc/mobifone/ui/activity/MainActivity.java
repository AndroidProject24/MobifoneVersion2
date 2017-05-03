package com.toan_itc.mobifone.ui.activity;

import android.Manifest;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import butterknife.BindView;
import com.google.firebase.crash.FirebaseCrash;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.toan_itc.mobifone.R;
import com.toan_itc.mobifone.data.local.PreferencesHelper;
import com.toan_itc.mobifone.data.rxjava.DefaultObserver;
import com.toan_itc.mobifone.interfaces.KeyListener;
import com.toan_itc.mobifone.interfaces.OnBackListener;
import com.toan_itc.mobifone.interfaces.ToolbarTitleListener;
import com.toan_itc.mobifone.libs.logger.Logger;
import com.toan_itc.mobifone.mvp.presenter.main.MainPresenter;
import com.toan_itc.mobifone.mvp.view.main.MainView;
import com.toan_itc.mobifone.ui.fragment.MainFragment;
import com.toan_itc.mobifone.ui.fragment.congno.CongnoFragment;
import com.toan_itc.mobifone.ui.fragment.contact.LienHeFragment;
import com.toan_itc.mobifone.ui.fragment.khoso.UIKhosoFragment;
import com.toan_itc.mobifone.ui.fragment.km.KhuyenmaiFragment;
import com.toan_itc.mobifone.ui.fragment.login.LoginFragment;
import com.toan_itc.mobifone.ui.fragment.thutuc.ThutucFragment;
import com.toan_itc.mobifone.ui.fragment.upanh.UpanhFragment;
import javax.inject.Inject;

import static com.toan_itc.mobifone.R.id.toolbar;

public class MainActivity extends BaseActivity implements ToolbarTitleListener,MainView {
  @BindView(toolbar)
  Toolbar mToolbar;
  private AccountHeader headerResult = null;
  private Drawer result = null;
  private boolean doubleBackToExitPressedOnce;
  @Inject PreferencesHelper mPreferencesHelper;
  @Inject MainPresenter mainPresenter;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    FirebaseCrash.report(new Exception("My first Android non-fatal error"));
    setSupportActionBar(mToolbar);
    initDrawer(savedInstanceState);
    setPermissions();
  }

  private void initDrawer(Bundle savedInstanceState){
    IProfile profile=null;
    if(mPreferencesHelper.getJsonLogin()!=null)
      profile = new ProfileDrawerItem().withName(mPreferencesHelper.getJsonLogin().get_$0().getUsername()).withEmail(mPreferencesHelper.getJsonLogin().get_$0().getEmail()).withIcon("").withIdentifier(100);
    else
      profile = new ProfileDrawerItem().withName("").withEmail("").withIcon("").withIdentifier(100);
    headerResult = new AccountHeaderBuilder()
            .withActivity(this)
            .withTranslucentStatusBar(true)
            .withHeaderBackground(R.drawable.material_bg_account)
            .addProfiles(profile)
            .withSavedInstance(savedInstanceState)
            .build();
    result = new DrawerBuilder(this)
            .withActivity(this)
            .withToolbar(mToolbar)
            .withHasStableIds(true)
            .withAccountHeader(headerResult)
            .withRootView(R.id.drawer_container)
            .withDisplayBelowStatusBar(false)
            .withActionBarDrawerToggleAnimated(true)
            .addDrawerItems(
                    new PrimaryDrawerItem().withName("Trang chủ").withIcon(R.drawable.ic_profile).withIdentifier(1).withSetSelected(true),
                    new PrimaryDrawerItem().withName("Kho số").withIcon(R.drawable.ic_sim).withIdentifier(2).withSetSelected(true),
                    new PrimaryDrawerItem().withName("khuyến mãi").withIcon(R.drawable.ic_khuyenmai).withIdentifier(3).withSetSelected(true),
                    new PrimaryDrawerItem().withName("Công nợ").withIcon(R.drawable.ic_congno).withIdentifier(4).withSetSelected(true),
                    new PrimaryDrawerItem().withName("Thủ tục").withIcon(R.drawable.ic_thutuc).withIdentifier(5).withSetSelected(true).withEnabled(true),
                    new PrimaryDrawerItem().withName("Úp ảnh").withIcon(R.drawable.ic_upload).withIdentifier(6).withSetSelected(true),
                    new PrimaryDrawerItem().withName("Profile").withIcon(R.drawable.ic_profile).withIdentifier(7).withSetSelected(true),
                    new SectionDrawerItem().withName("Help"),
                    new PrimaryDrawerItem().withName("Liên hệ").withIcon(R.drawable.ic_call).withIdentifier(8).withSetSelected(true),
                    new PrimaryDrawerItem().withName("Thoát").withIcon(R.drawable.ic_exit_to_app_black_24dp).withIdentifier(9).withSetSelected(true)
            )
            .withOnDrawerItemClickListener((view, position, drawerItem) -> {
              if (drawerItem != null) {
                if (mPreferencesHelper.getJsonLogin()!=null) {
                  if (drawerItem.getIdentifier() == 1) {
                    replaceFagment(getSupportFragmentManager(), R.id.fragment, MainFragment.newInstance());
                  } else if (drawerItem.getIdentifier() == 2) {
                    replaceFagment(getSupportFragmentManager(), R.id.fragment, UIKhosoFragment.newInstance());
                  } else if (drawerItem.getIdentifier() == 3) {
                    replaceFagment(getSupportFragmentManager(), R.id.fragment, KhuyenmaiFragment.newInstance());
                  } else if (drawerItem.getIdentifier() == 4) {
                    replaceFagment(getSupportFragmentManager(), R.id.fragment, CongnoFragment.newInstance());
                  } else if (drawerItem.getIdentifier() == 5) {
                    replaceFagment(getSupportFragmentManager(), R.id.fragment, ThutucFragment.newInstance());
                  } else if (drawerItem.getIdentifier() == 6) {
                    replaceFagment(getSupportFragmentManager(), R.id.fragment, UpanhFragment.newInstance());
                  } else if (drawerItem.getIdentifier() == 7) {
                    replaceFagment(getSupportFragmentManager(), R.id.fragment, UpanhFragment.newInstance());
                  } else if (drawerItem.getIdentifier() == 8) {
                    replaceFagment(getSupportFragmentManager(), R.id.fragment, LienHeFragment.newInstance());
                  } else if (drawerItem.getIdentifier() == 9) {
                    mainPresenter.exit();
                  }else {
                    replaceFagment(getSupportFragmentManager(), R.id.fragment, MainFragment.newInstance());
                  }
                }else {
                  replaceFagment(getSupportFragmentManager(), R.id.fragment, LoginFragment.newInstance());
                  Snackbar.make(mToolbar, "Xin mời bạn đăng nhập!", Snackbar.LENGTH_LONG).show();
                }
              }
              return false;
            })
            .withSavedInstance(savedInstanceState)
            .build();
  }
  @Override
  protected String getTAG() {
    return this.getClass().getSimpleName();
  }

  @Override
  protected void initViews() {
    mainPresenter.attachView(this);
    if((mPreferencesHelper.getJsonLogin()!=null&& mPreferencesHelper.getLogin()) || !mPreferencesHelper.getLogin()) {
      addFagment(getSupportFragmentManager(), R.id.fragment, MainFragment.newInstance());
    }else{
      addFagment(getSupportFragmentManager(), R.id.fragment, LoginFragment.newInstance());
    }
  }

  @Override
  protected int setLayoutResourceID() {
    return R.layout.activity_main;
  }

  @Override
  protected void initData() {
    //TODO: AUTO OFF
   /* addSubscription(Observable.timer(1, TimeUnit.SECONDS)
            .subscribe(new DefaultObserver<Long>(){
              @Override
              public void onCompleted() {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                if(mPreferencesHelper.getfinishApp() == null) {
                  Date now = new Date();
                  String dateString = formatter.format(now);
                  mPreferencesHelper.putfinshApp(dateString);
                }
                else {
                  Date before = null;
                  try {
                    before = (Date) formatter.parse(mPreferencesHelper.getfinishApp());
                    Date now = new Date();
                    long diff = now.getTime() - before.getTime();
                    long days = diff / (24 * 60 * 60 * 1000);
                    if (days > 10) {
                      finish();
                    }
                  } catch (ParseException e) {
                    e.printStackTrace();
                  }
                }
              }
            }));*/
  }

  @Override
  protected void injectDependencies() {
    getActivityComponent().inject(this);
  }
  @Override
  protected void onSaveInstanceState(Bundle outState) {
    outState = result.saveInstanceState(outState);
    outState = headerResult.saveInstanceState(outState);
    super.onSaveInstanceState(outState);
  }

  @Override
  public void onBackPressed() {
    //handle the back press :D close the drawer first and if the drawer is closed close the activity
    if (result != null && result.isDrawerOpen()) {
      result.closeDrawer();
    } else {
      String currentTag = getSupportFragmentManager().findFragmentById(R.id.fragment).getTag();
      if (currentTag.equals(MainFragment.class.getName()) || currentTag.equals(LoginFragment.class.getName())) {
        if (doubleBackToExitPressedOnce) {
          finish();
        }
        this.doubleBackToExitPressedOnce = true;
        Snackbar.make(mToolbar,R.string.msg_exit,Snackbar.LENGTH_SHORT).show();
        new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
      } else {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment);
        if (fragment instanceof OnBackListener) {
          ((OnBackListener) fragment).onBackPress();
        }
      }
    }
  }
  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if(event.getKeyCode()==KeyEvent.KEYCODE_BACK ) {
      onBackPressed();
    }
    else if(getSupportFragmentManager().findFragmentById(R.id.fragment) instanceof KeyListener) {
      KeyListener keyListener = (KeyListener) getSupportFragmentManager().findFragmentById(R.id.fragment);
      keyListener.onKeyDown(keyCode, event);
    }
    return false;
  }

  @Override
  public void changeTitle(String name) {
    mToolbar.setTitle(name);
  }

  @Override
  public void hideToolBar(boolean isHide) {
    if(mToolbar != null) {
      if (isHide)
       mToolbar.setVisibility(View.GONE);
      else
        mToolbar.setVisibility(View.VISIBLE);
    }
  }
  private void setPermissions(){
    try{
      RxPermissions rxPermissions = new RxPermissions(this);
      rxPermissions.setLogging(true);
      rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE,
              Manifest.permission.WRITE_EXTERNAL_STORAGE,
              Manifest.permission.INTERNET,
              Manifest.permission.CAMERA)
              .subscribe(new DefaultObserver<Boolean>() {
                @Override
                public void onNext(Boolean aBoolean) {
                  if(aBoolean)
                    Logger.d("RxPermissions:OK");
                  else
                    Logger.d("RxPermissions:Fail");
                }
              });
    }catch (Exception e){
      e.printStackTrace();
    }
  }

  @Override public void exit() {
    finish();
  }

  @Override public void showLoading() {

  }

  @Override public void hideLoading() {

  }

  @Override public void showError(String message) {

  }

  @Override public void showEmptyView(String message) {

  }

  @Override public void showEmptyViewAction(String message, View.OnClickListener onClickListener) {

  }

}
