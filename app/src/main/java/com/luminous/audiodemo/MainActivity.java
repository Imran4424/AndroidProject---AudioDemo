package com.luminous.audiodemo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private final String PLAY_TEXT = "PLAY";
    private final String PAUSE_TEXT = "PAUSE";

    MediaPlayer mediaPlayer;
    AudioManager audioManager;
    Button playPauseButton;
    SeekBar playTimeSeekBar;
    SeekBar volumeSeekBar;
    int maxVolume;
    int currentVolume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        mediaPlayer = MediaPlayer.create(this, R.raw.sheyal_sharosh);
        playPauseButton = (Button) findViewById(R.id.buttonPlayPause);

        playTimeSeekBar = (SeekBar) findViewById(R.id.seekBarPlayStatus);
        playTimeSeekBar.setMax(mediaPlayer.getDuration());

        volumeSeekBar = (SeekBar) findViewById(R.id.seekVolume);
        volumeSeekBar.setMax(maxVolume);

        playPauseButton.setText(PLAY_TEXT);

        playTimeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mediaPlayer.seekTo(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        volumeSeekBar.setProgress(currentVolume);
        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                playTimeSeekBar.setProgress(mediaPlayer.getCurrentPosition());
            }
        }, 0, 500);
    }

    public void playPause(View v) {
        if(playPauseButton.getText().toString().equals(PLAY_TEXT)) {
            mediaPlayer.start();
            playPauseButton.setText(PAUSE_TEXT);
        } else {
            mediaPlayer.pause();
            playPauseButton.setText(PLAY_TEXT);
        }


    }
    
    public void stop(View v) {
        mediaPlayer.stop();

        mediaPlayer = MediaPlayer.create(this, R.raw.sheyal_sharosh);
        playPauseButton.setText(PLAY_TEXT);
        playTimeSeekBar.setProgress(0);
    }
}
