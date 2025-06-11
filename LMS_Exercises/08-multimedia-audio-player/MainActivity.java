package com.yourpackage.name; // Replace with your actual package name
import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private Button playButton, pauseButton, stopButton;
    private SeekBar seekBarAudio;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playButton = findViewById(R.id.buttonPlay);
        pauseButton = findViewById(R.id.buttonPause);
        stopButton = findViewById(R.id.buttonStop);
        seekBarAudio = findViewById(R.id.seekBarAudio);

        mediaPlayer = MediaPlayer.create(this, R.raw.your_audio_file); // Replace 'your_audio_file' with your actual audio file name

        playButton.setOnClickListener(v - > {
            mediaPlayer.start();
            updateSeekBar();
        });
        pauseButton.setOnClickListener(v - >
            mediaPlayer.pause());
        stopButton.setOnClickListener(v - > {
            mediaPlayer.stop();
            mediaPlayer =
            MediaPlayer.create(getApplicationContext(),
                R.raw.your_audio_file); // Reinitialize MediaPlayer
        });
        seekBarAudio.setMax(mediaPlayer.getDuration());
        seekBarAudio.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Implementation not needed for this example
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Implementation not needed for this example
            }
        });
    }
    
    private void updateSeekBar() {
        seekBarAudio.setProgress(mediaPlayer.getCurrentPosition());
        if (mediaPlayer.isPlaying()) {
            Runnable updater = this::updateSeekBar;
            handler.postDelayed(updater, 1000);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
        }
    }
}