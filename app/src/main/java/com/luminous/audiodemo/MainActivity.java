package com.luminous.audiodemo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    private final String PLAY_TEXT = "PLAY";
    private final String PAUSE_TEXT = "PAUSE";

    MediaPlayer mediaPlayer;
    AudioManager audioManager;
    Button playPauseButton;
    SeekBar playTimeSeekBar;
    SeekBar volumeSeekBar;
    int maxVolume;
    int minVolume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        mediaPlayer = MediaPlayer.create(this, R.raw.sheyal_sharosh);
        playPauseButton = (Button) findViewById(R.id.buttonPlayPause);
        playTimeSeekBar = (SeekBar) findViewById(R.id.seekBarPlayStatus);
        volumeSeekBar = (SeekBar) findViewById(R.id.seekVolume);
        volumeSeekBar.setMax(maxVolume);

        playPauseButton.setText(PLAY_TEXT);

        volumeSeekBar.setProgress(maxVolume);
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
    }
}
