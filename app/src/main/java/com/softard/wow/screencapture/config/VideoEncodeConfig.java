package com.softard.wow.screencapture.config;

/*
 * Copyright (c) 2017 Yrom Wang <http://www.yrom.net>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.media.MediaCodecInfo;
import android.media.MediaFormat;


/**
 * @author yrom
 * @version 2017/12/3
 */
/**
 * Created by wOw on 2019-08-19.
 * Email: wossoneri@163.com
 * Copyright (c) 2019 Softard. All rights reserved.
 */

public class VideoEncodeConfig extends SimpleVideoEncodeConfig {
    private final static String TAG = "VideoEncodeConfig";
    final MediaCodecInfo.CodecProfileLevel codecProfileLevel;

    /**
     * @param codecProfileLevel profile level for video encoder nullable
     */
    public VideoEncodeConfig(String codecName, String mimeType, int width, int height, int bitrate, int framerate, int iframeInterval, MediaCodecInfo.CodecProfileLevel codecProfileLevel) {
        super(codecName, mimeType, width, height, bitrate);
        this.width = width;
        this.height = height;
        this.bitrate = bitrate;
        this.framerate = framerate;
        this.iframeInterval = iframeInterval;
        this.codecProfileLevel = codecProfileLevel;
    }

    @Override
    public MediaFormat toMediaFormat() {
        final MediaFormat mediaformat = MediaFormat.createVideoFormat(mMIMEType, width, height);
        switch (mMIMEType) {
            case MediaFormat.MIMETYPE_VIDEO_AVC:
                mediaformat.setInteger(MediaFormat.KEY_COLOR_FORMAT, MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface);
                break;
            case MediaFormat.MIMETYPE_VIDEO_VP8:
                mediaformat.setInteger(MediaFormat.KEY_COLOR_FORMAT, MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV420Flexible);
                break;
        }
        mediaformat.setInteger(MediaFormat.KEY_BIT_RATE, bitrate);
        mediaformat.setInteger(MediaFormat.KEY_FRAME_RATE, framerate);
        mediaformat.setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, iframeInterval);
        mediaformat.setInteger(MediaFormat.KEY_CHANNEL_COUNT, 0);
        if (codecProfileLevel != null && codecProfileLevel.profile != 0 && codecProfileLevel.level != 0) {
            mediaformat.setInteger(MediaFormat.KEY_PROFILE, codecProfileLevel.profile);
            mediaformat.setInteger("level", codecProfileLevel.level);
        }
        // maybe useful
        // format.setInteger(MediaFormat.KEY_REPEAT_PREVIOUS_FRAME_AFTER, 10_000_000);
        return mediaformat;
    }

//    @Override
//    public String toString() {
//        return "VideoEncodeConfig{" +
//                "width=" + width +
//                ", height=" + height +
//                ", bitrate=" + bitrate +
//                ", framerate=" + framerate +
//                ", iframeInterval=" + iframeInterval +
//                ", codecName='" + mCodecName + '\'' +
//                ", mimeType='" + mMIMEType + '\'' +
//                ", codecProfileLevel=" + (codecProfileLevel == null ? "" : MediaUtils.avcProfileLevelToString(codecProfileLevel)) +
//                '}';
//    }
}
