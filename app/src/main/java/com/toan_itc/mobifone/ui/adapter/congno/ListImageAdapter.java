package com.toan_itc.mobifone.ui.adapter.congno;

import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.toan_itc.mobifone.R;
import java.util.List;

public class ListImageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public ListImageAdapter(List<String> datas) {
        super(R.layout.congno_item,datas);
    }

    @Override
    protected void convert(BaseViewHolder helper, String image) {
        Glide.with(mContext).load(image).crossFade().into((ImageView) helper.getView(R.id.img_congno));
    }
}