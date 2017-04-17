package com.toan_itc.mobifone.mvp.view.upanh;

import com.toan_itc.mobifone.mvp.view.base.BaseView;

/**
 * Created by Toan.IT
 * Date: 28/05/2016
 */
public interface UpanhView extends BaseView {

    void uploadProgress(int progress);

    void uploadOK();

    void uploadFail();

    void requestLogin();
}
