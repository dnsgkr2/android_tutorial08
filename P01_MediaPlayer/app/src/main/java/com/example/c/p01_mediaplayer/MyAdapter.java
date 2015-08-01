package com.example.c.p01_mediaplayer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by c on 2015-08-01.
 */
public class MyAdapter extends BaseAdapter{
    Context context;
    ArrayList<MainActivity> list;

    public MyAdapter(Context context, ArrayList<MainActivity> list) {
        this.context = context;
        this.list = list;
       }



    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
