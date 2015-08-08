package com.example.c.t20_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by c on 2015-08-08.
 */
public class MycountFragment extends Fragment {
    TextView textView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.counter_fragment, container,false);

        Button button = (Button)rootView.findViewById(R.id.btnIncrease);
        textView = (TextView)rootView.findViewById(R.id.textViewCounter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = Integer.parseInt(textView.getText().toString());
                number++;

                textView.setText(Integer.toString(number));

            }
        });

        return rootView;
    }
}
