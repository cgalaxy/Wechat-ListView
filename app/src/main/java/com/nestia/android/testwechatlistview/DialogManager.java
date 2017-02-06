package com.nestia.android.testwechatlistview;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by chenxinying on 17/2/3
 */

public class DialogManager {
    private Dialog dialog;
    private ImageView mIcon;
    private ImageView mVoice;
    private TextView mLabel;
    private Context mContext;

    public DialogManager(Context context) {
        this.mContext = context;
    }

    public void showRecordingDialog() {
        dialog = new Dialog(mContext, R.style.Theme_AduioDialog);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_recorder, null);
        dialog.setContentView(view);
        mIcon = (ImageView) dialog.findViewById(R.id.id_recorder_dialog_icon);
        mVoice = (ImageView) dialog.findViewById(R.id.id_recorder_dialog_voice);
        mLabel = (TextView) dialog.findViewById(R.id.id_recorder_dialog_label);
        dialog.show();
    }

    public void recording() {
        //正在录音是textview的显示
        if (dialog != null && dialog.isShowing()) {
            mIcon.setVisibility(View.VISIBLE);
            mVoice.setVisibility(View.VISIBLE);
            mLabel.setVisibility(View.VISIBLE);
            mIcon.setImageResource(R.drawable.recorder);
            mLabel.setText("手指上滑，取消发送");
        }
    }

    public void wantToCancel() {
        if (dialog != null && dialog.isShowing()) {
            mIcon.setVisibility(View.VISIBLE);
            mVoice.setVisibility(View.GONE);
            mLabel.setVisibility(View.VISIBLE);
            mIcon.setImageResource(R.drawable.cancel);
            mLabel.setText("松开手指，取消发送");
        }
    }

    public void tooShort() {
        if (dialog != null && dialog.isShowing()) {
            mIcon.setVisibility(View.VISIBLE);
            mVoice.setVisibility(View.GONE);
            mLabel.setVisibility(View.VISIBLE);
            mIcon.setImageResource(R.drawable.voice_to_short);
            mLabel.setText("录音时间过短");
        }
    }

    public void dimissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }

    public void updateVoiceLevel(int level) {
        if (dialog != null && dialog.isShowing()) {
           /* mIcon.setVisibility(View.VISIBLE);
            mVoice.setVisibility(View.VISIBLE);
            mLabel.setVisibility(View.VISIBLE);*/
            int resId=mContext.getResources().getIdentifier("v"+level,"drawable",mContext.getPackageName());
            mVoice.setImageResource(resId);
        }

    }
}






































