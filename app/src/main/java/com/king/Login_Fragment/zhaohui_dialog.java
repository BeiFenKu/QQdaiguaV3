package com.king.Login_Fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.king.qqdaigua.MainActivity;
import com.king.qqdaigua.R;

/**
 * Created by 57677 on 2018/6/22.
 */

public class zhaohui_dialog extends DialogFragment{
    private View view;
    private TextView tv_update;
    private Button bt_submit;
    private Button bt_submit1;
    private Button bt_submit2;
    private Button bt_cancel;
    private TextView tv_update_title;
    private String lock;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_zhaohui, null);
        BindViews();
        if (getDialog() != null) {
            getDialog().setTitle("找回密码");
        }
        return view;
    }

    private void BindViews() {
        tv_update_title = (TextView) view.findViewById(R.id.tv_update_title);
        tv_update = (TextView) view.findViewById(R.id.tv_update);
        bt_submit = (Button) view.findViewById(R.id.bt_submit);
        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openURL("http://kkkking.daigua.org/index/find.html");
            }
        });
        bt_submit1 = (Button) view.findViewById(R.id.bt_submit1);
        bt_submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openURL("http://kkkking.daigua.org/qrlogin/");
            }
        });
        bt_submit2 = (Button) view.findViewById(R.id.bt_submit2);
        bt_submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager cm = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                // 创建普通字符型ClipData
                ClipData mClipData = ClipData.newPlainText("Label", "3539699522");
                // 将ClipData内容放到系统剪贴板里。
                cm.setPrimaryClip(mClipData);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(),"机器人的QQ已复制到粘贴板",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        bt_cancel = (Button) view.findViewById(R.id.bt_cancel);
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().cancel();
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
