package com.example.c.t11_mediaplayer;

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


public class MainActivity extends ActionBarActivity {
    MediaPlayer mp = null;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button btnPlay =(Button)findViewById(R.id.btnPlay);
        Button btnStop =(Button)findViewById(R.id.btnStop);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

       // mp.getCurrentPosition();
       // mp.getDuration();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    if(mp != null){
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        float progress = (float)mp.getCurrentPosition() / (float)mp.getDuration();
                        progressBar.setProgress((int)(progress *100));
                      //  progressBar.setProgress(mp.getCurrentPosition());
                    }
                }
            }
        }).start();

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = Environment.getExternalStorageDirectory().toString();
                path +="/media/audio/notifications/aa.mp3";
                mp = new MediaPlayer();

                try {
                    mp.setDataSource(path);
                    mp.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mp.start();
               // progressBar.setMax(mp.getDuration());
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp != null){
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
