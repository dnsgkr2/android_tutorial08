package com.example.c.t12_xml;

import android.os.AsyncTask;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;

/**
 * Created by c on 2015-08-01.
 */
public class MyPullParser extends AsyncTask<String, Void, Void>{
    MyPullParser(TextView textview){
        this.textview = textview;
    }

    private TextView textview;

    private String str = "";

    public String getStr(){
        return str;
    }


    @Override
    protected Void doInBackground(String... params) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();

            URL url = new URL(params[0]);
            xpp.setInput(url.openStream(), "UTF-8");

            //hour, day, temp, wfKor

            String[] keyword = {"hour","day","temp","wfKor"};
            boolean bRead = false;
            int eventType = xpp.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT){
                switch(eventType){
                    case XmlPullParser.START_TAG:
                        String tagName = xpp.getName();
                        for(String s : keyword){
                            if(tagName.equals(s)) {
                                bRead = true;
                                str+= tagName;
                            }
                        }
                        break;
                    case XmlPullParser.TEXT:
                        if(bRead == true){
                            str+= xpp.getText();
                            bRead = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        //xpp.getName();
                        break;


                }
                eventType = xpp.next();
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        textview.setText(str);
    }
}
