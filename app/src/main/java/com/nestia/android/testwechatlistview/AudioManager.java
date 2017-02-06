package com.nestia.android.testwechatlistview;

import android.media.MediaRecorder;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by chenxinying on 17/2/4
 */

public class AudioManager {
    private MediaRecorder mediaRecorder;
    private String mDir;//录制的视频保存的文件夹路径
    private String mCurrentFilePath;//保存的路径回传给button，button再传给Activity
    private static AudioManager mInstance;
    private boolean isPrepared = false;

    private AudioManager(String dir) {
        mDir = dir;
    }

    public interface AudioStateListener {
        void wellPrepared();
    }

    public AudioStateListener mListener;

    public void setOnAudioStateListener(AudioStateListener listener) {
        mListener = listener;
    }

    public static AudioManager getInstance(String dir) {
        if (mInstance == null) {
            synchronized (AudioManager.class) {
                if (mInstance == null) {
                    mInstance = new AudioManager(dir);
                }
                return mInstance;
            }
        }
        return null;
    }

    public void prepareAudio() {
//创建文件夹
        try {
            isPrepared = false;
            File dir = new File(mDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String fileName = generateFileName();
            File file = new File(dir, fileName);
            mCurrentFilePath = file.getAbsolutePath();
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setOutputFile(file.getAbsolutePath());
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);//设置音频源，为麦克风
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);//设置音频源的输出格式
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB); //设置音频的编码
            mediaRecorder.prepare();
            mediaRecorder.start();
            //准备结束
            isPrepared = true;
            if (mListener != null)
                mListener.wellPrepared();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 随机生成文件的名称
     *
     * @return
     */
    private String generateFileName() {
        //音屏后缀名 .amr
        return UUID.randomUUID().toString() + ".amr";
    }

    public int getVoiceLevel(int maxLevel) {
        if (isPrepared) {
            //mediaRecorder.getMaxAmplitude() mediaRecorder最大振幅  1-32767
            try {
                return maxLevel * mediaRecorder.getMaxAmplitude() / 32768 + 1;
            } catch (Exception e) {

            }
        }
        return 1;
    }

    public void release() {
        if (mediaRecorder != null) {
//            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
        }
    }

    public void cancel() {
        release();
        if (mCurrentFilePath != null) {
            File file = new File(mCurrentFilePath);
            file.delete();
            mCurrentFilePath = null;
        }
    }

    public String getCurrentFilePath() {
        return mCurrentFilePath;
    }
}

















