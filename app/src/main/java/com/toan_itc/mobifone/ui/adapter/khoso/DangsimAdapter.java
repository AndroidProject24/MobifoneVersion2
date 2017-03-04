package com.toan_itc.mobifone.ui.adapter.khoso;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.toan_itc.mobifone.mvp.model.khoso.Dangsim;

import java.util.List;

public class DangsimAdapter extends BaseAdapter implements SpinnerAdapter {

    private final Context mContext;
    private List<Dangsim> asr;

    public DangsimAdapter(Context context,List<Dangsim> asr) {
        this.asr=asr;
        this.mContext = context;
    }



    public int getCount()
    {
        return asr.size();
    }

    public Object getItem(int i)
    {
        return asr.get(i);
    }

    public long getItemId(int i)
    {
        return (long)i;
    }



    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView txt = new TextView(mContext);
        txt.setTextSize(18);
        txt.setGravity(Gravity.CENTER_VERTICAL);
        txt.setText(asr.get(position).getTends());
        txt.setTextColor(Color.parseColor("#000000"));
        return  txt;
    }

    public View getView(int i, View view, ViewGroup viewgroup) {
        TextView txt = new TextView(mContext);
        txt.setGravity(Gravity.CENTER);
        txt.setTextSize(16);
        txt.setText(asr.get(i).getTends());
        txt.setTextColor(Color.parseColor("#000000"));
        return  txt;
    }
}