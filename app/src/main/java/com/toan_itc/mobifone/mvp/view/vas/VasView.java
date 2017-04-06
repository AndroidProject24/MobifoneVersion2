package com.toan_itc.mobifone.mvp.view.vas;


import com.toan_itc.mobifone.mvp.model.vas.Goicuoc;
import com.toan_itc.mobifone.mvp.model.vas.Vas;
import com.toan_itc.mobifone.mvp.view.base.BaseView;
import java.util.List;

/**
 * Created by Toan.IT
 * Date: 28/05/2016
 */
public interface VasView extends BaseView {
    void getGoiCuoc(List<Goicuoc> goicuocList);

    void registerVas(Vas vas);
}
