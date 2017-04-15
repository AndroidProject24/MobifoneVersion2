package com.toan_itc.mobifone.ui.adapter.khoso;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.toan_itc.mobifone.R;
import com.toan_itc.mobifone.mvp.model.khoso.Khoso;

import java.util.List;

public class KhosoAdapter extends BaseQuickAdapter<Khoso.DataBean, BaseViewHolder> {
    public KhosoAdapter(List<Khoso.DataBean> datas) {
        super(R.layout.khoso_item,datas);
    }

    @Override
    protected void convert(BaseViewHolder helper, Khoso.DataBean khoso) {
      helper.setText(R.id.txt_vnd,khoso.getGia())
          .setText(R.id.txt_name, khoso.getSdtview())
          .setText(R.id.btn_goicuoc, khoso.getGoicuoc())
          .addOnClickListener(R.id.btn_goicuoc)
          .addOnClickListener(R.id.img_shop);
    }
}