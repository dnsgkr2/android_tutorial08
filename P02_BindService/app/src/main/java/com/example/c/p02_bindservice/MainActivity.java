package com.example.c.p02_bindservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;


public class MainActivity extends ActionBarActivity {
    ProgressBar progressBar;
    MediaPlayer mp = null;
    MyService myService;
    boolean isBound;

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.Mybinder binder = (MyService.Mybinder)service;
            myService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };


    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = new Intent(MainActivity.this, MyService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }


    @Override
    protected void onStop() {
        super.onStop();
        if(isBound){
            unbindService(connection);
        }
    }

//    BroadcastReceiver receiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
//                int state = intent.getIntExtra("state", -1);
//                switch (state) {
//                    case 0:
//                        Log.d("BroadcastReceiver", "Headset is unplugged");
//                        break;
//                    case 1:
//                        Log.d("BroadcastReceiver", "Headset is plugged");
//                        break;
//                    default:
//                        Log.d("BroadcastReceiver", "I have no idea what the headset state is");
//                }
//            }
//        }
//    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
//                        float progress = (float) mp.getCurrentPosition() / (float) mp.getDuration();
//                        progressBar.setProgress((int) (progress * 100));

                        if(isBound) {
                            progressBar.setProgress(myService.getCurrentPostition());
                        }
                    }
                }

        }).start();



        Button btnPlay = (Button)findViewById(R.id.btnPlay);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String path = Environment.getExternalStorageDirectory().toString();
//
//                Intent intent = getIntent();
//               // String fileName = intent.getExtras().getString("name");
//                //path+="/Music"+"/"+fileName;
//
//                path+="/Music/aa.mp3";
//            mp = new MediaPlayer();
//
//            try {
//                mp.setDataSource(path);
//                mp.prepare();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            mp.start();

                myService.Play();
        }
        });


        Button btnStop = (Button)findViewById(R.id.btnStop);

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (mp != null) {
//                    mp.stop();
//                    mp.release();
//                    mp = null;
//                }
                myService.Stop();
            }
        });

        Button btnPause = (Button)findViewById(R.id.btnPause);
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mp.pause();

                myService.Pause();
            }
        });


    }


//    @Override
//    protected void onResume() {
//
//        IntentFilter filter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
//        registerReceiver(receiver, filter);
//        super.onResume();
//    }
//
//
//    @Override
//    protected void onPause() {
//        unregisterReceiver(receiver);
//        super.onPause();
//    }


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

    private class BroadCastReceiver {
    }
}
