package com.king.qqdaigua;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.king.Login_Fragment.BlankFragment1;
import com.king.YH_Fragment.YH_Fragment;

/**
 * Created by 57677 on 2018/8/27.
 */

public class skin_change extends Fragment {

    private ProgressDialog waitDialog;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private View view;
    private RelativeLayout rl1;
    private RelativeLayout rl2;
    private RelativeLayout rl3;
    private RelativeLayout rl4;
    private RelativeLayout rl5;
    private Drawable btnDrawable_select;
    private Drawable btnDrawable;
    private Button back;
    private String skin_value;
    private Button submit;
    private View view1;
    private Toolbar toolbar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.user_skin, container, false);
        view1 = inflater.inflate(R.layout.activity_main, container, false);
        bindView();
        return view;
    }

    private void bindView() {
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        skin_value = preferences.getString("skin_value", "1");

        toolbar = (Toolbar) view1.findViewById(R.id.toolbar);

        Resources resources = getContext().getResources();
        btnDrawable = resources.getDrawable(R.drawable.board_quan);
        btnDrawable_select = resources.getDrawable(R.drawable.board_quan_select);

        back = (Button) view.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
        rl1 = (RelativeLayout) view.findViewById(R.id.rl1);
        rl2 = (RelativeLayout) view.findViewById(R.id.rl2);
        rl3 = (RelativeLayout) view.findViewById(R.id.rl3);
        rl4 = (RelativeLayout) view.findViewById(R.id.rl4);
        rl5 = (RelativeLayout) view.findViewById(R.id.rl5);
        rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelect("1");
            }
        });
        rl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelect("2");
            }
        });
        rl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelect("3");
            }
        });
        rl4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelect("4");
            }
        });
        rl5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelect("5");
            }
        });
        submit = (Button) view.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setColor(skin_value);
            }
        });
        setSelect(skin_value);

    }

    private void openURL(String s) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(s);
        intent.setData(content_url);
        startActivity(intent);
    }

    private void setColor(String num) {

        editor = preferences.edit();
        //1 = 默认 2 = 绿 3 = 橙 4 = 紫 5 = 彩色
        editor.putString("skin_value", num);
        editor.apply();

//        int screenWidth = getActivity().getWindowManager().getDefaultDisplay().getWidth();//真实分辨率 宽
//        int screenHeight = getActivity().getWindowManager().getDefaultDisplay().getHeight();//真实分辨率 高
//
//        DisplayMetrics dm = new DisplayMetrics();
//        dm = getResources().getDisplayMetrics();
//        int densityDPI = dm.densityDpi;     // 屏幕密度（每寸像素：120(ldpi)/160(mdpi)/213(tvdpi)/240(hdpi)/320(xhdpi)）
//        Toast.makeText(getContext(), "真实分辨率：" + screenWidth + "*" + screenHeight + "  每英寸:" + densityDPI, Toast.LENGTH_LONG).show();

        switch (num) {
            case "1":
                submit.setBackgroundResource(R.drawable.king_button);
//                toolbar.setBackgroundColor(Color.rgb(24, 180, 237));//默认
//                ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
                break;
            case "2":
                submit.setBackgroundResource(R.drawable.king_button_green);
//                toolbar.setBackgroundColor(Color.rgb(72, 243, 251));//绿色
//                ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
                break;
            case "3":
                submit.setBackgroundResource(R.drawable.king_button_orange);
//                toolbar.setBackgroundColor(Color.rgb(147, 29, 3));//橙色
//                ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
                break;
            case "4":
                submit.setBackgroundResource(R.drawable.king_button_pou);
//                toolbar.setBackgroundColor(Color.rgb(124, 51, 154));//紫色
//                ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
                break;
            case "5":
                submit.setBackgroundResource(R.drawable.king_button_colorful);
//                toolbar.setBackgroundColor(Color.rgb(0, 133, 251));//彩色
//                ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
                break;
        }
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getContext(), "主题设置成功！重启APP完全生效", Toast.LENGTH_SHORT).show();
                }
            });
        }
//        toolbar.setBackgroundColor(Color.rgb(24, 180, 237));//默认
//        toolbar.setBackgroundColor(Color.rgb(72, 243, 251));//绿色
//        toolbar.setBackgroundColor(Color.rgb(147, 29, 3));//橙色
//        toolbar.setBackgroundColor(Color.rgb(124, 51, 154));//紫色
//        toolbar.setBackgroundColor(Color.rgb(0, 133, 251));//彩色
    }

    private void setSelect(String num) {
        skin_value = num;
        if (num.equals("1")) {
            rl1.setBackgroundDrawable(btnDrawable_select);
            rl2.setBackgroundDrawable(btnDrawable);
            rl3.setBackgroundDrawable(btnDrawable);
            rl4.setBackgroundDrawable(btnDrawable);
            rl5.setBackgroundDrawable(btnDrawable);
        } else if (num.equals("2")) {
            rl1.setBackgroundDrawable(btnDrawable);
            rl2.setBackgroundDrawable(btnDrawable_select);
            rl3.setBackgroundDrawable(btnDrawable);
            rl4.setBackgroundDrawable(btnDrawable);
            rl5.setBackgroundDrawable(btnDrawable);
        } else if (num.equals("3")) {
            rl1.setBackgroundDrawable(btnDrawable);
            rl2.setBackgroundDrawable(btnDrawable);
            rl3.setBackgroundDrawable(btnDrawable_select);
            rl4.setBackgroundDrawable(btnDrawable);
            rl5.setBackgroundDrawable(btnDrawable);
        } else if (num.equals("4")) {
            rl1.setBackgroundDrawable(btnDrawable);
            rl2.setBackgroundDrawable(btnDrawable);
            rl3.setBackgroundDrawable(btnDrawable);
            rl4.setBackgroundDrawable(btnDrawable_select);
            rl5.setBackgroundDrawable(btnDrawable);
        } else if (num.equals("5")) {
            rl1.setBackgroundDrawable(btnDrawable);
            rl2.setBackgroundDrawable(btnDrawable);
            rl3.setBackgroundDrawable(btnDrawable);
            rl4.setBackgroundDrawable(btnDrawable);
            rl5.setBackgroundDrawable(btnDrawable_select);
        }
    }
}