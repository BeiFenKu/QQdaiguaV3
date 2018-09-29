package com.king.YH_Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.king.qqdaigua.MainActivity;
import com.king.qqdaigua.R;
import com.king.util.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 57677 on 2018/9/15.
 */

public class vurl_dialog extends DialogFragment {

    private View view;
    private EditText et_kami;
    private Button bt_submit;
    private Button bt_cancel;
    private SharedPreferences preferences;
    private ProgressDialog xufeiDialog;
    private String user, pwd;
    private TextView textView28;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_vurl, null);
        getDialog().setTitle("微视链接上传");

        bindViews();
        return view;
    }

    private void bindViews() {
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        textView28 = (TextView) view.findViewById(R.id.textView28);
        textView28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openURL("http://url.cn/58E6zbF/");
            }
        });
        user = preferences.getString("account", "");
        pwd = preferences.getString("pwd", "");
        et_kami = (EditText) view.findViewById(R.id.et_kami);
        bt_submit = (Button) view.findViewById(R.id.bt_submit);
        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xufeiDialog = new ProgressDialog(getContext());
                xufeiDialog.setTitle("请稍等");
                xufeiDialog.setMessage("提交中，请稍等...");
                xufeiDialog.show();
                xufeiDialog.setCancelable(false);
                String kami = et_kami.getText().toString();
                if (kami.length() == 0) {
                    Toast.makeText(getContext(), "链接不可为空", Toast.LENGTH_SHORT).show();
                    getDialog().cancel();
                    xufeiDialog.cancel();
                } else {
                    String post_url = MainActivity
                            .app_url + MainActivity.app_url_1+"&info=wsvurl";
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("type", "vurl");
                        jsonObject.put("qq", user);
                        jsonObject.put("pwd", pwd);
                        jsonObject.put("vurl", kami);
                        HttpRequest http = new HttpRequest(post_url, jsonObject.toString(), handler1);
                        http.start();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
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

    Handler handler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 15) {
                try {
                    xufeiDialog.cancel();
                    JSONObject json = new JSONObject((String) msg.obj);
                    String code = json.getString("code");
                    String error = json.getString("error");
                    if (code.equals("0")) {
                        Toast.makeText(getContext(), "上传成功，已自动开启该功能", Toast.LENGTH_SHORT).show();
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id
                                .content_main, new YH_Fragment()).commit();
                        getDialog().cancel();
                    } else {
                        if (error.length() > 6) {
                            Toast.makeText(getContext(), "上传失败。链接地址格式有误，请跟着帮助说明复制链接。", Toast.LENGTH_LONG).show();
                            getDialog().cancel();
                        } else {
                            Toast.makeText(getContext(), "上传失败，与上次上传的地址一致。", Toast.LENGTH_SHORT).show();
                            getDialog().cancel();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getContext(), "网络请求错误", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void openURL(String s) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(s);
        intent.setData(content_url);
        startActivity(intent);
    }
}