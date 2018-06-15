package com.king.level_Fragment;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.king.YH_Fragment.YH_Fragment;
import com.king.qqdaigua.R;

/**
 * Created by KingLee on 2018/6/2.
 */

public class level_Fragment extends Fragment {

    private ProgressDialog waitDialog;
    private SharedPreferences preferences;
    private View view;
    private LinearLayout level_main;
    private TextView textView17;
    private TextView tv_final;
    private TextView tv_server;
    private TextView tv_dgtime;
    private TextView tv_level_1;
    private ImageView iv_1;
    private TextView tv_1;
    private TextView tv_level_2;
    private ImageView iv_2;
    private TextView tv_2;
    private TextView tv_level_3;
    private ImageView iv_3;
    private TextView tv_3;
    private TextView tv_level_4;
    private ImageView iv_4;
    private TextView tv_4;
    private TextView tv_level_5;
    private ImageView iv_5;
    private TextView tv_5;
    private TextView tv_level;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_viplevel, container, false);
        bindView();
        return view;
    }

    private void bindView() {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        waitDialog = new ProgressDialog(getContext());
                        waitDialog.setTitle("请稍等");
                        waitDialog.setMessage("资源计算中，请稍等...");
                        waitDialog.show();
                        waitDialog.setCancelable(false);
                    } catch (Exception e) {

                    }
                }
            });
        }
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        level_main = (LinearLayout) view.findViewById(R.id.level_main);
        textView17 = (TextView) view.findViewById(R.id.textView17);
        textView17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id
                        .content_main, new YH_Fragment()).commit();
            }
        });

        String serverday = preferences.getString("serverday", "");
        String dgtime = preferences.getString("dgtime", "");
        String score = preferences.getString("score", "");
        tv_final = (TextView) view.findViewById(R.id.textView13);
        tv_final.setText(score);
        tv_server = (TextView) view.findViewById(R.id.textView10);
        tv_server.setText(serverday);
        tv_dgtime = (TextView) view.findViewById(R.id.textView12);
        tv_dgtime.setText(dgtime);
        tv_level_1 = (TextView) view.findViewById(R.id.textView18);
        iv_1 = (ImageView) view.findViewById(R.id.iv1);
        tv_1 = (TextView) view.findViewById(R.id.tv_1);
        tv_level_2 = (TextView) view.findViewById(R.id.textView19);
        iv_2 = (ImageView) view.findViewById(R.id.iv2);
        tv_2 = (TextView) view.findViewById(R.id.tv_2);
        tv_level_3 = (TextView) view.findViewById(R.id.textView20);
        iv_3 = (ImageView) view.findViewById(R.id.iv3);
        tv_3 = (TextView) view.findViewById(R.id.tv_3);
        tv_level_4 = (TextView) view.findViewById(R.id.textView21);
        iv_4 = (ImageView) view.findViewById(R.id.iv4);
        tv_4 = (TextView) view.findViewById(R.id.tv_4);
        tv_level_5 = (TextView) view.findViewById(R.id.textView22);
        iv_5 = (ImageView) view.findViewById(R.id.iv5);
        tv_5 = (TextView) view.findViewById(R.id.tv_5);
        tv_level = (TextView) view.findViewById(R.id.textView25);
        if (Integer.parseInt(score) < 30){
            tv_level.setText("VIP 0");
        } else if (Integer.parseInt(score) < 36 && Integer.parseInt(score) >= 30){
            tv_level.setText("VIP 1");
        }else if (Integer.parseInt(score) < 126 && Integer.parseInt(score) >= 36){
            tv_level.setText("VIP 2");
        }else if (Integer.parseInt(score) < 306 && Integer.parseInt(score) >= 126){
            tv_level.setText("VIP 3");
        }else if (Integer.parseInt(score) < 720 && Integer.parseInt(score) >= 306){
            tv_level.setText("VIP 4");
        }else if ( Integer.parseInt(score) >= 720){
            tv_level.setText("VIP 5");
        }
        if (Integer.parseInt(score) < 30) {
            tv_level_1.setTextColor(Color.GRAY);
            iv_1.setImageDrawable(getResources().getDrawable(R.mipmap
                    .level_progress_off));
            tv_1.setTextColor(Color.GRAY);
        }
        if (Integer.parseInt(score) < 36) {
            tv_level_2.setTextColor(Color.GRAY);
            iv_2.setImageDrawable(getResources().getDrawable(R.mipmap
                    .level_progress_off));
            tv_2.setTextColor(Color.GRAY);
        }
        if (Integer.parseInt(score) < 126) {
            tv_level_3.setTextColor(Color.GRAY);
            iv_3.setImageDrawable(getResources().getDrawable(R.mipmap
                    .level_progress_off));
            tv_3.setTextColor(Color.GRAY);
        }
        if (Integer.parseInt(score) < 306) {
            tv_level_4.setTextColor(Color.GRAY);
            iv_4.setImageDrawable(getResources().getDrawable(R.mipmap
                    .level_progress_off));
            tv_4.setTextColor(Color.GRAY);
        }
        if (Integer.parseInt(score) < 720) {
            tv_level_5.setTextColor(Color.GRAY);
            iv_5.setImageDrawable(getResources().getDrawable(R.mipmap
                    .level_progress_off));
            tv_5.setTextColor(Color.GRAY);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                waitDialog.cancel();
            }
        }).start();
    }
}
