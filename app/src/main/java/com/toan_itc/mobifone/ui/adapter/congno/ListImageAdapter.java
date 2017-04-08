package com.toan_itc.mobifone.ui.adapter.congno;

import android.text.TextUtils;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.toan_itc.mobifone.R;
import com.toan_itc.mobifone.utils.Constant;
import java.util.List;

public class ListImageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public ListImageAdapter(List<String> datas) {
        super(R.layout.image_item,datas);
    }

    @Override
    protected void convert(BaseViewHolder helper, String image) {
        if(!TextUtils.isEmpty(image)) {
            Glide.with(mContext).load(Constant.LINK_IMAGE + image).crossFade().into((ImageView) helper.getView(R.id.img_congno));
        }
    }
}