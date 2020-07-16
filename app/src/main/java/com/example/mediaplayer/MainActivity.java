package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMediaPlayer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button play = (Button) findViewById(R.id.play);
        Button pause = (Button) findViewById(R.id.pause);
        Button middle = (Button) findViewById(R.id.middle);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp = MediaPlayer.create(MainActivity.this, R.raw.demo);
                mp.start();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.pause();
            }
        });

        middle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int duration = mp.getDuration();
                mp.seekTo(duration/2);
                mp.start();
            }
        });
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mp != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mp.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mp = null;
        }
    }
}
