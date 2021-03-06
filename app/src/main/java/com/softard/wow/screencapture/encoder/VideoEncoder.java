package com.softard.wow.screencapture.encoder;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.util.Log;
import android.view.Surface;

import com.softard.wow.screencapture.BuildConfig;
import com.softard.wow.screencapture.config.VideoEncodeConfig;

import java.util.Objects;

/**
 * Created by wOw on 2019-08-19.
 * Email: wossoneri@163.com
 * Copyright (c) 2019 Softard. All rights reserved.
 */
public class VideoEncoder extends BaseEncoder {
    private static final String TAG = "VideoEncoder";
    private VideoEncodeConfig mConfig;
    private Surface mSurface;

    public VideoEncoder(VideoEncodeConfig config) {
        super(config.mCodecName);
        this.mConfig = config;
    }

    @Override
    protected void onEncoderConfigured(MediaCodec encoder) {
        mSurface = encoder.createInputSurface();
        if (BuildConfig.DEBUG) Log.e(TAG, "VideoEncoder create input surface: " + mSurface);
    }

    @Override
    protected MediaFormat generateMediaFormat() {
        return mConfig.toMediaFormat();
    }

    /**
     * @throws NullPointerException if prepare() not call
     */
    public Surface getInputSurface() {
        return Objects.requireNonNull(mSurface, "doesn't prepare()");
    }

    @Override
    public void release() {
        if (mSurface != null) {
            mSurface.release();
            mSurface = null;
        }
        super.release();
    }

    @Override
    public void onActionError(BaseEncoderAction basktask, MediaCodec codec, Exception e) {

    }
}
