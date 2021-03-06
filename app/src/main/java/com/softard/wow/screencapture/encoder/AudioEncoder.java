package com.softard.wow.screencapture.encoder;

import android.media.MediaCodec;
import android.media.MediaFormat;

import com.softard.wow.screencapture.config.AudioEncodeConfig;

/**
 * Created by wOw on 2019-08-19.
 * Email: wossoneri@163.com
 * Copyright (c) 2019 Softard. All rights reserved.
 */
public class AudioEncoder extends BaseEncoder {
    private static final String TAG = "AudioEncoder";
    private final AudioEncodeConfig mConfig;

    public AudioEncoder(AudioEncodeConfig config) {
        super(config.mCodecName);
        this.mConfig = config;
    }

    @Override
    protected MediaFormat generateMediaFormat() {
        return mConfig.toMediaFormat();
    }

    @Override
    public void onActionError(BaseEncoderAction basktask, MediaCodec codec, Exception e) {

    }
}

