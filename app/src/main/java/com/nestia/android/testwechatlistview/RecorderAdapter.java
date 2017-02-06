package com.nestia.android.testwechatlistview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by chenxinying on 17/2/5
 */

public class RecorderAdapter extends ArrayAdapter<MainActivity.Recorder> {

    private List<MainActivity.Recorder> mDatas;
    private Context mContext;
    private int mMinItemWidth;
    private int mMaxItemWidth;
    private LayoutInflater mInflater;

    public RecorderAdapter(Context context, List<MainActivity.Recorder> datas) {
        super(context, -1, datas);//第二个参数是 布局id 指定为－1，表明使用自己建立的布局
        mContext = context;
        mDatas = datas;

        mInflater = LayoutInflater.from(context);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        mMaxItemWidth = (int) (outMetrics.widthPixels * 0.7f);
        mMinItemWidth = (int) (outMetrics.widthPixels * 0.15f);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_recorder, parent, false);
            holder = new ViewHolder();
            holder.seconds = (TextView) convertView.findViewById(R.id.id_recorder_time);
            holder.lenght = convertView.findViewById(R.id.id_recorder_length);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.seconds.setText(Math.round(getItem(position).time) + "\"");
        ViewGroup.LayoutParams lp = holder.lenght.getLayoutParams();
        lp.width= (int) (mMinItemWidth+(mMaxItemWidth/60f)*getItem(position).time);
        return convertView;
    }

    private class ViewHolder {
        TextView seconds;
        View lenght;
    }
}
