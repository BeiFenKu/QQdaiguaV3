package com.king.Login_Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ImageView;

import com.king.qqdaigua.MainActivity;
import com.king.qqdaigua.R;

/**
 * Created by 57677 on 2018/11/10.
 */

public class delete_qq_dialog extends DialogFragment {
    private View view;
    private ImageView r1_1;
    private WebView web_view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bz_fragment, null);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        bindViews();
        return view;
    }

    private void bindViews() {
        r1_1 = (ImageView) view.findViewById(R.id.imageView);
        r1_1.bringToFront();
        r1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().cancel();
            }
        });
        web_view = (WebView) view.findViewById(R.id.web_view);
        web_view.getSettings().setJavaScriptEnabled(true);
        web_view.getSettings().setUserAgentString("Mozilla/5.0 (Linux; Android 8.0; MI 6 Build/OPR1.170623.027; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044006 Mobile Safari/537.36 V1_AND_SQ_7.5.5_806_YYB_D QQ/7.5.5.3460 NetType/4G WebP/0.3.0 Pixel/1080");
        System.out.println("http://kkkking.daigua.org" + MainActivity.del_skey);
        web_view.loadUrl("http://kkkking.daigua.org" + MainActivity.del_skey);
    }
}
