package com.maple.recordwav.utils;

import android.content.Context;
import android.media.AudioFormat;
import android.media.MediaRecorder;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.maple.recorder.recording.AudioRecordConfig;
import com.maple.recordwav.R;
import com.maple.recordwav.databinding.ViewRecordConfigBinding;

/**
 * 录音配置View
 *
 */
public class RecordConfigView extends FrameLayout {
    private ViewRecordConfigBinding binding;
    private AudioRecordConfig recordConfig = new AudioRecordConfig(); // 参数配置

    public RecordConfigView(Context context) {
        super(context);
        init(context);
    }

    public RecordConfigView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RecordConfigView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        binding = ViewRecordConfigBinding.inflate(LayoutInflater.from(context), this, true);
        updateConfig(recordConfig);

        binding.rgAudioSource.setOnCheckedChangeListener((group, checkedId) -> {
            recordConfig.setAudioSource(checkedId == R.id.rb_default ? MediaRecorder.AudioSource.DEFAULT : MediaRecorder.AudioSource.MIC);
            if (onConfigChangeListener != null) {
                onConfigChangeListener.onConfigChange(recordConfig);
            }
        });

        binding.rgChannel.setOnCheckedChangeListener((group, checkedId) -> {
            recordConfig.setChannelConfig(checkedId == R.id.rb_channel_mono ? AudioFormat.CHANNEL_IN_MONO : AudioFormat.CHANNEL_IN_STEREO);
            if (onConfigChangeListener != null) {
                onConfigChangeListener.onConfigChange(recordConfig);
            }
        });

        binding.rgSampleRate.setOnCheckedChangeListener((group, checkedId) -> {
            recordConfig.setSampleRateInHz(checkedId == R.id.rb_44100hz ? 44100 :
                    checkedId == R.id.rb_22050hz ? 22050 :
                            checkedId == R.id.rb_16000hz ? 16000 : 11025);
            if (onConfigChangeListener != null) {
                onConfigChangeListener.onConfigChange(recordConfig);
            }
        });

        binding.rgAudioFormat.setOnCheckedChangeListener((group, checkedId) -> {
            recordConfig.setAudioFormat(checkedId == R.id.rb_8bit ? AudioFormat.ENCODING_PCM_8BIT :
                    checkedId == R.id.rb_16it ? AudioFormat.ENCODING_PCM_16BIT :
                            AudioFormat.ENCODING_PCM_FLOAT);
            if (onConfigChangeListener != null) {
                onConfigChangeListener.onConfigChange(recordConfig);
            }
        });
    }

    public void updateConfig(AudioRecordConfig newConfig) {
        recordConfig = newConfig;
        if (recordConfig.getAudioSource() == MediaRecorder.AudioSource.DEFAULT) {
            binding.rgAudioSource.check(R.id.rb_default);
        } else if (recordConfig.getAudioSource() == MediaRecorder.AudioSource.MIC) {
            binding.rgAudioSource.check(R.id.rb_source_mic);
        } else {
            binding.rgAudioSource.clearCheck();
        }

        if (recordConfig.getChannelConfig() == AudioFormat.CHANNEL_IN_MONO) {
            binding.rgChannel.check(R.id.rb_channel_mono);
        } else if (recordConfig.getChannelConfig() == AudioFormat.CHANNEL_IN_STEREO) {
            binding.rgChannel.check(R.id.rb_channel_stereo);
        } else {
            binding.rgChannel.clearCheck();
        }

        if (recordConfig.getSampleRateInHz() == 44100) {
            binding.rgSampleRate.check(R.id.rb_44100hz);
        } else if (recordConfig.getSampleRateInHz() == 22050) {
            binding.rgSampleRate.check(R.id.rb_22050hz);
        } else if (recordConfig.getSampleRateInHz() == 16000) {
            binding.rgSampleRate.check(R.id.rb_16000hz);
        } else if (recordConfig.getSampleRateInHz() == 11025) {
            binding.rgSampleRate.check(R.id.rb_11025hz);
        } else {
            binding.rgSampleRate.clearCheck();
        }

        if (recordConfig.getAudioFormat() == AudioFormat.ENCODING_PCM_8BIT) {
            binding.rgAudioFormat.check(R.id.rb_8bit);
        } else if (recordConfig.getAudioFormat() == AudioFormat.ENCODING_PCM_16BIT) {
            binding.rgAudioFormat.check(R.id.rb_16it);
        } else if (recordConfig.getAudioFormat() == AudioFormat.ENCODING_PCM_FLOAT) {
            binding.rgAudioFormat.check(R.id.rb_float);
        } else {
            binding.rgAudioFormat.clearCheck();
        }
    }

    public void setOnConfigChangeListener(OnRecordConfigChangeListener listener) {
        onConfigChangeListener = listener;
    }

    public OnRecordConfigChangeListener onConfigChangeListener = null;

    public interface OnRecordConfigChangeListener {
        void onConfigChange(AudioRecordConfig config);
    }
}
