package com.toan_itc.mobifone.mvp.view.congno;


import com.toan_itc.mobifone.mvp.model.congno.Congno;
import com.toan_itc.mobifone.mvp.view.base.BaseView;
import java.util.List;

/**
 * Created by Toan.IT
 * Date: 28/05/2016
 */
public interface CongnoView extends BaseView {
    void showData(List<Congno.DataBean> dataBeanList);
}
