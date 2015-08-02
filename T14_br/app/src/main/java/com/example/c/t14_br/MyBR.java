package com.example.c.t14_br;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by c on 2015-08-02.
 */
public class MyBR extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if(bundle !=null){
            Object[] pdus = (Object[])bundle.get("pdus");

            String str = "";
            for(int i=0; i<pdus.length; i++){
                SmsMessage msg = SmsMessage.createFromPdu((byte[]) pdus[i]);
                str += "SMS from "+msg.getOriginatingAddress();
                str += " : ";
                str += msg.getMessageBody() + "\n";
            }
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
            Log.d("MyBR", str);
        }
    }
}
