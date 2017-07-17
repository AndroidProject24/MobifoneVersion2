package com.toan_itc.mobifone.ui.fragment;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.ConnectionStateChange;
import com.toan_itc.mobifone.R;
import com.toan_itc.mobifone.data.local.PreferencesHelper;
import com.toan_itc.mobifone.data.rxjava.RxBus;
import com.toan_itc.mobifone.data.service.RestApi;
import com.toan_itc.mobifone.intdef.StringDef;
import com.toan_itc.mobifone.libs.logger.Logger;
import com.toan_itc.mobifone.libs.view.StateLayout;
import com.toan_itc.mobifone.mvp.model.khoso.CheckSdt;
import com.toan_itc.mobifone.ui.activity.MainActivity;
import com.toan_itc.mobifone.ui.activity.WebviewActivity;
import com.toan_itc.mobifone.ui.fragment.congno.CongnoFragment;
import com.toan_itc.mobifone.ui.fragment.khoso.UIKhosoFragment;
import com.toan_itc.mobifone.ui.fragment.km.KhuyenmaiFragment;
import com.toan_itc.mobifone.ui.fragment.login.LoginFragment;
import com.toan_itc.mobifone.ui.fragment.thutuc.ThutucFragment;
import com.toan_itc.mobifone.ui.fragment.upanh.UpanhFragment;
import com.toan_itc.mobifone.ui.fragment.vas.VasFragment;
import dagger.internal.Preconditions;
import javax.inject.Inject;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Toan.IT
 * Date: 11/06/2016
 */

public class MainFragment extends BaseFragment {
  @Inject
  PreferencesHelper mPreferencesHelper;
  @BindView(R.id.card_congno) CardView card_congno;
  @BindView(R.id.card_vas) CardView card_vas;
  @BindView(R.id.card_upanh) CardView card_upanh;
  @BindView(R.id.card_cuoc) CardView card_cuoc;
  private Pusher pusher;
  public static MainFragment newInstance() {
    return new MainFragment();
  }
  @Override
  protected String getTAG() {
    return this.getClass().getSimpleName();
  }

  @Override
  protected int setLayoutResourceID() {
    return R.layout.main_fragment;
  }

  @Override
  protected void initData() {
    try {
      if(mPreferencesHelper.getLogin()) {
        PusherOptions options = new PusherOptions();
        options.setCluster("ap1");
        pusher = new Pusher("8a52acf03f615e76dafa", options);
        pusher.connect(new ConnectionEventListener() {
          @Override public void onConnectionStateChange(ConnectionStateChange change) {
            if (change.getCurrentState().equals(ConnectionState.DISCONNECTED)) {
              if (pusher != null) {
                pusher.connect();
              }
            }
           /* Logger.e("State changed to="+change.getCurrentState() +
                " from " + change.getPreviousState());*/
          }

          @Override public void onError(String message, String code, Exception e) {
            Logger.e("There was a problem connecting!");
          }
        });
        Channel channel = pusher.subscribe("my-channel-auth_code".replace("auth_code",
            Preconditions.checkNotNull(mPreferencesHelper.getJsonLogin().get_$0().getAuth_code())));
        channel.bind("my-event", (channelName, eventName, data) -> {
          //Logger.e("my-event="+data);
          CheckSdt checkSdt = new CheckSdt();
          String dataParse = "";
          try {
            JSONObject jsonObj = new JSONObject(data);
            dataParse = jsonObj.getString("message");
          } catch (JSONException e) {
            e.printStackTrace();
          }
          checkSdt.setReason(dataParse);
          RxBus.getDefault().send(checkSdt);
        });
        pusher.connect();
      }
    }catch (Exception e){
      e.printStackTrace();
    }
  }

  @Override
  protected StateLayout getLoadingTargetView() {
    return ButterKnife.findById(getActivity(),R.id.stateLayout);
  }

  @Override
  protected void initViews() {
    ((MainActivity)getActivity()).getActivityComponent().inject(this);
    if(!mPreferencesHelper.getLogin()){
      card_vas.setVisibility(View.INVISIBLE);
      card_congno.setVisibility(View.INVISIBLE);
      card_upanh.setVisibility(View.GONE);
    }
  }

  @OnClick(R.id.card_congno)
  public void click_congno(){
    checkLogin(CongnoFragment.newInstance());
  }

  @OnClick(R.id.card_khoso)
  public void click_khoso(){
    checkLogin(UIKhosoFragment.newInstance());
  }

  @OnClick(R.id.card_thutuc)
  public void click_thutuc(){
    checkLogin(ThutucFragment.newInstance());
  }

  @OnClick(R.id.card_km)
  public void click_ctkm(){
    checkLogin(KhuyenmaiFragment.newInstance());
  }

  @OnClick(R.id.card_vas)
  public void click_vas(){
    checkLogin(VasFragment.newInstance());
  }

  @OnClick(R.id.card_cuoc)
  public void click_cuoc(){
      Intent intent = new Intent(getActivity(), WebviewActivity.class);
      intent.putExtra(StringDef.BUNDLE_DATA, RestApi.CUOC);
      startActivity(intent);
  }

  @OnClick(R.id.card_upanh)
  public void click_upanh(){
    checkLogin(UpanhFragment.newInstance());
  }
  private void checkLogin(Fragment fragment){
    if ((mPreferencesHelper.getJsonLogin()!=null&& mPreferencesHelper.getLogin()) || !mPreferencesHelper.getLogin())
      replaceFagment(getFragmentManager(),R.id.fragment, fragment);
    else {
      replaceFagment(getFragmentManager(),R.id.fragment, LoginFragment.newInstance());
      Snackbar.make(getLoadingTargetView(), "Xin mời bạn đăng nhập!", Snackbar.LENGTH_LONG).show();
    }
  }

  @Override public void onDestroy() {
    super.onDestroy();
    try {
      if (pusher != null) pusher.disconnect();
    }catch (Exception e){
      e.printStackTrace();
    }
  }
}
