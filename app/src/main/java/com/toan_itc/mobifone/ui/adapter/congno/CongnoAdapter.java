package com.toan_itc.mobifone.ui.adapter.congno;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.toan_itc.mobifone.R;
import com.toan_itc.mobifone.mvp.model.congno.Congno;
import java.util.List;

public class CongnoAdapter extends BaseQuickAdapter<Congno.DataBean, BaseViewHolder> {
    public CongnoAdapter(List<Congno.DataBean> datas) {
        super(R.layout.congno_item,datas);
    }

    @Override
    protected void convert(BaseViewHolder helper, Congno.DataBean congno) {
        helper.setText(R.id.txt_sdt, congno.getSdt())
            .setText(R.id.txt_tenloai,congno.getTenLoai())
            .setText(R.id.txt_check,congno.getThanhtoan())
            .setText(R.id.txt_date,congno.getDate())
            .addOnClickListener(R.id.layout_item);
        //Glide.with(mContext).load(congno.getImage()).crossFade().into((ImageView) helper.getView(R.id.img_theloai));
    }
}