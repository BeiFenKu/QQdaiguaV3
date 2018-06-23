package com.king.qqdaigua;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by KingLee on 2018/4/17.
 */

public class about_dialog extends DialogFragment {

    private View view;
    private RelativeLayout r1_1;
    private TextView textView9;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_about, null);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        bindViews();
        return view;
    }

    private void bindViews() {
        r1_1 = (RelativeLayout) view.findViewById(R.id.rl_1);
        r1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().cancel();
            }
        });
        textView9 = (TextView) view.findViewById(R.id.textView9);
        textView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openURL("https://github.com/qq576777915/QQdaiguaV3");
            }
        });
    }

    private void openURL(String s) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(s);
        intent.setData(content_url);
        startActivity(intent);
    }
}
