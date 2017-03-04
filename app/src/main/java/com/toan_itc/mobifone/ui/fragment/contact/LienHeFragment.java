package com.toan_itc.mobifone.ui.fragment.contact;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;

import com.toan_itc.mobifone.R;
import com.toan_itc.mobifone.libs.view.StateLayout;
import com.toan_itc.mobifone.ui.fragment.BaseFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Toan.IT
 * Created by toan.it on 12/30/15.
 * Email: Huynhvantoan.itc@gmail.com
 */
@SuppressLint("ValidFragment")
public class LienHeFragment extends BaseFragment {

    public static LienHeFragment newInstance() {
        return new LienHeFragment();
    }
    @Override
    protected String getTAG() {
        return this.getClass().getName();
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_lienhe;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected StateLayout getLoadingTargetView() {
        return ButterKnife.findById(getActivity(),R.id.stateLayout);
    }

    @OnClick(R.id.txt_email) public void send_email(){
        Intent i = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"vienthongthanhnhan@gmail.com"});
        startActivity(Intent.createChooser(i, "Gửi mail..."));
    }
    @OnClick(R.id.txt_phone) public void call_phone(){
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:0901067979"));
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(callIntent);
    }
    @OnClick(R.id.txt_hotline) public void call_hotline(){
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:0903669889"));
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(callIntent);
    }
    @OnClick(R.id.txt_website) public void open_website(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://vienthongthanhnhan.com/"));
        startActivity(browserIntent);
    }
}
