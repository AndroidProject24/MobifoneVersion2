package com.toan_itc.mobifone.mvp.view.theloai;

import com.toan_itc.mobifone.mvp.model.theloai.Theloai;
import com.toan_itc.mobifone.mvp.view.base.BaseView;

import java.util.List;

/**
 * Created by Toan.IT
 * Date: 28/05/2016
 */
public interface TheloaiView extends BaseView {

    void showData(List<Theloai> list);

    void DataEmty();
}
