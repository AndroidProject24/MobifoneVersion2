package com.toan_itc.mobifone.ui.adapter.khoso;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.toan_itc.mobifone.R;
import com.toan_itc.mobifone.mvp.model.khoso.Khoso;

import java.util.List;

public class KhosoAdapter extends BaseQuickAdapter<Khoso.Data, BaseViewHolder> {
    public KhosoAdapter(List<Khoso.Data> datas) {
        super(R.layout.khoso_item,datas);
    }

    @Override
    protected void convert(BaseViewHolder helper, Khoso.Data khoso) {
        helper.setText(R.id.txt_name, khoso.getSdtview());
        helper.setText(R.id.txt_vnd,khoso.getGia());
    }
}