package com.example.c.p02_bindservice;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by c on 2015-08-08.
 */
public class MyService extends Service {

    public class Mybinder extends Binder{

        public MyService getService(){
            return MyService.this;
        }
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
                int state = intent.getIntExtra("state", -1);
                switch (state) {
                    case 0:
                        if(mp != null){
                            try{
                                if(mp.isPlaying()){
                                    mp.pause();
                                    Toast.makeText(context,"Headset is unplugged",Toast.LENGTH_LONG).show();
                                }
                            }catch(Exception e){
                                e.printStackTrace();
                            }
                        }

                        Log.d("BroadcastReceiver", "Headset is unplugged");
                        break;

                    case 1:
                        Log.d("BroadcastReceiver", "Headset is plugged");
                        break;

                    default:
                        Log.d("BroadcastReceiver", "I have no idea what the headset state is");
                }
            }
        }
    };

    private IBinder binder = new Mybinder();

    private MediaPlayer mp = null;
    ProgressBar progressBar;


    @Override
    public IBinder onBind(Intent intent) {

        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        IntentFilter filter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
       // IntentFilter filter = new IntentFilter();
       // filter.addAction(Intent.ACTION_HEADSET_PLUG);
        registerReceiver(receiver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);

    }

    public void Play() {

        String path = Environment.getExternalStorageDirectory().toString();
        path += "/Music/aa.mp3";
        if (mp == null) {
            mp = new MediaPlayer();

            try {
                mp.setDataSource(path);
                mp.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mp.start();
        }
    }

    public void Pause(){
        if(mp != null){
            if(mp.isPlaying()){
                mp.pause();
            }else{
                mp.start();
            }
        }
    }

    public void Stop(){
        if(mp != null){
            mp.stop();
            mp.release();
            mp = null;
        }
    }


    public int getCurrentPostition(){
        int progress = 0;
        if(mp != null){
            progress = (int)((float)mp.getCurrentPosition()/(float)mp.getDuration()*100);
            //float progress = (float) mp.getCurrentPosition() / (float) mp.getDuration();
            //progressBar.setProgress((int) (progress * 100));
        }
        return progress;
    }


}
