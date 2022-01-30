package com.example.media;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button play;
    Button pause;
    SeekBar seekBar;
    MediaPlayer mediaPlayer;
    Runnable runnable;
    Handler handler = new Handler();
    int delay=1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play=findViewById(R.id.play);
        pause=findViewById(R.id.pause);
        seekBar = findViewById(R.id.seekBar);
        mediaPlayer = MediaPlayer.create(this,R.raw.on);

//        mediaPlayer = new MediaPlayer();
//        try {
//            mediaPlayer.setDataSource("http://penguinradio.dominican.edu/Sound%20FX%20Collection/20th%20Century%20Fox.mp3");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                Toast.makeText(MainActivity.this, "Ready to play", Toast.LENGTH_SHORT).show();
//                mp.start();

        seekBar.setMax(mediaPlayer.getDuration());
        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(runnable, delay);
            }
        }, delay);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                if(b){
                    mediaPlayer.seekTo(progress);
                    seekBar.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
//            }
//        });

        //mediaPlayer.prepareAsync();


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
            }
        });
    }
}