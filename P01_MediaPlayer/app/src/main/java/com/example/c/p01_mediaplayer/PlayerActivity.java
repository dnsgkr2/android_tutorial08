package com.example.c.p01_mediaplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.io.IOException;


public class PlayerActivity extends ActionBarActivity {
    ProgressBar progressBar;
    MediaPlayer mp = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        Button btnPlay = (Button) findViewById(R.id.btnPlay);
        Button btnStop = (Button) findViewById(R.id.btnStop);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    if(mp !=null){
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                       // if(mp !=null) {
                       // if(mp.isPlaying()) {
                            float progress = (float) mp.getCurrentPosition() / (float) mp.getDuration();
                            progressBar.setProgress((int) (progress * 100));
                       // }
                       // }
                    }
                }
            }
        }).start();

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = Environment.getExternalStorageDirectory().toString();
                //path+="/Music/aa.mp3";
                Intent intent = getIntent();
                String fileName = intent.getExtras().getString("name");

                path+="/Music"+"/"+fileName;

                mp = new MediaPlayer();

                try {
                    mp.setDataSource(path);
                    mp.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mp.start();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp != null) {
                    mp.stop();
                    mp.release();
                    mp = null;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
