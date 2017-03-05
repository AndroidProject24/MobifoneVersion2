package com.toan_itc.mobifone.mvp.view.khoso;

import com.toan_itc.mobifone.mvp.model.khoso.Dangsim;
import com.toan_itc.mobifone.mvp.model.khoso.Khoso;
import com.toan_itc.mobifone.mvp.view.base.BaseView;

import java.util.List;

/**
 * Created by Toan.IT
 * Date: 28/05/2016
 */
public interface KhosoView extends BaseView {

    void listSim(Khoso khoso);

    void listDangSim(List<Dangsim> dangsimList);
}
