package com.toan_itc.mobifone.ui.adapter.theloai;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.toan_itc.mobifone.R;
import com.toan_itc.mobifone.mvp.model.theloai.Theloai;

import java.util.List;

public class TheloaiAdapter extends BaseQuickAdapter<Theloai, BaseViewHolder> {
    public TheloaiAdapter(List<Theloai> datas) {
        super(R.layout.thutuc_item,datas);
    }

    @Override
    protected void convert(BaseViewHolder helper, Theloai theloai) {
        helper.setText(R.id.txt_thutuc, theloai.getTenloai())
                .addOnClickListener(R.id.layout_item);
        Glide.with(mContext).load(theloai.getImage()).crossFade().into((ImageView) helper.getView(R.id.img_theloai));
    }
}