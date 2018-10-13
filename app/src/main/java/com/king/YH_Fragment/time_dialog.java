package com.king.YH_Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.king.qqdaigua.R;

/**
 * Created by 57677 on 2018/10/13.
 */

public class time_dialog extends DialogFragment {

    private View view;
    private ImageView imageView;
    private ImageView imageView6;
    private ImageView imageView13;
    private ImageView imageView14;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_time_border, null);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        bindViews();
        return view;
    }

    private void bindViews() {

        Time t = new Time();
        t.setToNow();
        int hour = t.hour;
        imageView6 = (ImageView) view.findViewById(R.id.imageView6);
        imageView13 = (ImageView) view.findViewById(R.id.imageView13);
        imageView14 = (ImageView) view.findViewById(R.id.imageView14);
        if (hour >= 0 && hour < 10) {
            imageView6.setVisibility(View.VISIBLE);
        } else if (hour >= 10 && hour < 16) {
            imageView13.setVisibility(View.VISIBLE);
        } else if (hour >= 16 && hour <= 23) {
            imageView14.setVisibility(View.VISIBLE);
        }
        imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().cancel();
            }
        });
    }
}
