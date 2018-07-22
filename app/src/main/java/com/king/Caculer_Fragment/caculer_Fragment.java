package com.king.Caculer_Fragment;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.king.ewrite.CircleImageView;
import com.king.qqdaigua.MainActivity;
import com.king.qqdaigua.R;
import com.king.util.HttpRequest;
import com.king.util.SetImageViewUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.king.qqdaigua.R.id.web_view;

/**
 * Created by KingLee on 2018/5/8.
 */

public class caculer_Fragment extends Fragment {

    private String level_qq = "576777915";
    private String level_url = "https://vip.qq.com/pk/index?param=";


    private ProgressDialog dialog_wait;
    private ProgressDialog waitDialog;
    private CircleImageView image_touxiang;
    private View view;
    private WebView webView;
    private CookieManager cookieManager;
    private String cookie;
    protected boolean useThemestatusBarColor = true;//是否使用特殊的标题栏背景颜色，android5
    // .0以上可以设置状态栏背景色，如果不使用则使用透明色值
    protected boolean useStatusBarColor = false;//是否使用状态栏文字和图标为暗色，如果状态栏采用了白色系，则需要使状态栏和图标为暗色，android6
    private EditText editText;
    private Button button;
    private RelativeLayout relativeLayout;
    private TextView fanhui_cancel;
    private TextView tv_qname;
    private TextView tv_qq;
    private TextView tv_level;
    private RelativeLayout cacluer_2;
    private LinearLayout ll1;
    private RelativeLayout input_qq_rel;
    private TextView speed_1;
    private TextView day_1;
    private TextView speed_2;
    private TextView day_2;
    private TextView day_3;
    private TextView tv_caculer_1;
    private TextView tv_caculer_2;
    // .0以上可以设置


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_caculer, container, false);
        setTitle();
        bindViews();

        return view;
    }

    private void ajax_login1(String account) {
        JSONObject json = new JSONObject();
        String post_url = MainActivity.check_url + account;
        try {
            json.put("type", "logincheck");
            HttpRequest http = new HttpRequest(post_url, json.toString(), handler);
            http.start();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 12) {

                String text = msg.obj.toString();
                String pattern = "(.+?)<p>所属代理ID:(.*?)</p>(.+?)";
                Pattern r = Pattern.compile(pattern);
                Matcher m = r.matcher(text);
                if (m.find()) {
                    Log.e("帝王检查：", m.group(2));
                    if (m.group(2).equals("65416")) {
                        dialog_wait.cancel();
                        relativeLayout.setVisibility(View.GONE);
                        webView.setVisibility(View.VISIBLE);
                    } else {
                        dialog_wait.cancel();
                        if (getActivity() != null) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getContext(), "无法使用，原因：此账号非帝王代挂官方用户", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
                } else {
                    dialog_wait.cancel();
                    relativeLayout.setVisibility(View.GONE);
                    webView.setVisibility(View.VISIBLE);
                }
            }
        }
    };

    private void bindViews() {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        waitDialog = new ProgressDialog(getContext());
                        waitDialog.setTitle("请稍等");
                        waitDialog.setMessage("资源计算中，请稍等...");
                        waitDialog.setCancelable(false);
                    } catch (Exception e) {

                    }
                }
            });
        }

        tv_caculer_1 = (TextView) view.findViewById(R.id.tv_caculer_1);
        tv_caculer_2 = (TextView) view.findViewById(R.id.tv_caculer_2);
        day_3 = (TextView) view.findViewById(R.id.day_3);
        day_2 = (TextView) view.findViewById(R.id.day_2);
        speed_2 = (TextView) view.findViewById(R.id.speed_2);
        day_1 = (TextView) view.findViewById(R.id.day_1);
        speed_1 = (TextView) view.findViewById(R.id.speed_1);
        input_qq_rel = (RelativeLayout) view.findViewById(R.id.input_qq_rel);
        ll1 = (LinearLayout) view.findViewById(R.id.ll1);
        cacluer_2 = (RelativeLayout) view.findViewById(R.id.cacluer_2);
        tv_level = (TextView) view.findViewById(R.id.tv_level);
        tv_qq = (TextView) view.findViewById(R.id.tv_qq);
        tv_qname = (TextView) view.findViewById(R.id.tv_qname);
        image_touxiang = (CircleImageView) view.findViewById(R.id.image_touxiang);
        SetImageViewUtil.setImageToImageView(image_touxiang, "http://q2.qlogo" +
                ".cn/headimg_dl?dst_uin=1278991552&spec=100");
        webView = (WebView) view.findViewById(web_view);
        CookieSyncManager.createInstance(getContext());
        cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        CookieSyncManager.getInstance().sync();
        relativeLayout = (RelativeLayout) view.findViewById(R.id.input_qq_rel);
        editText = (EditText) view.findViewById(R.id.editText);
        button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().length() < 5) {
                    Toast.makeText(getContext(), "QQ号输入不规范", Toast.LENGTH_SHORT).show();
                    return;
                } else {

                    dialog_wait = new ProgressDialog(getContext());
                    dialog_wait.setTitle("检测中");
                    dialog_wait.setMessage("检测中，请稍等...");
                    dialog_wait.setCancelable(false);
                    dialog_wait.show();
                    level_qq = editText.getText().toString();
                    ajax_login1(level_qq);
                }
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUserAgentString("Mozilla/5.0 (Linux; Android 8.0; MI 6 Build/OPR1.170623.027; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044006 Mobile Safari/537.36 V1_AND_SQ_7.5.5_806_YYB_D QQ/7.5.5.3460 NetType/4G WebP/0.3.0 Pixel/1080");
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    if (webView.getUrl().equals("https://h5.qzone.qq.com/mqzone/index") ||
                            webView.getUrl().equals("https://h5.qzone.qq.com/mqzone/profile")) {
                        waitDialog.show();
                        cookieManager = CookieManager.getInstance();
                        cookie = cookieManager.getCookie("https://h5.qzone.qq.com/mqzone/index");
                        Toast.makeText(getContext(),
                                "登陆成功，如未查询成功，请重新登录查询",
                                Toast
                                        .LENGTH_LONG).show();
                        webView.loadUrl(level_url + level_qq);
                    }
                }
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onLoadResource(WebView view, final String paramAnonymousString) {
//                Log.e("拿到地址::::", "" + paramAnonymousString);
                if (paramAnonymousString.indexOf("vip.qq.com/pk/index?param") != -1) {
                    cookieManager = CookieManager.getInstance();
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                int sign = 0;
                                String text = "";
                                URL url = new URL(paramAnonymousString);
                                HttpURLConnection urlConnection = (HttpURLConnection) url
                                        .openConnection();
                                urlConnection.setConnectTimeout(5000);
                                urlConnection.setReadTimeout(5000);
                                urlConnection.setRequestProperty("cookie", cookie);
                                int responsecode = urlConnection.getResponseCode();
                                if (responsecode == 200) {
                                    BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));

                                    String str = "";
                                    while ((str = reader.readLine()) != null) {
                                        str = reader.readLine();
                                        if (str != null) {
                                            if (str.indexOf("URL_PARAM") != -1) {
                                                sign = 1;
                                            } else if (str.indexOf("<script>") != -1) {
                                                sign = 0;
                                            }
//                                            Log.e("输出" + sign + "：", str);
                                            if (sign == 1) {
                                                text += "\n";
                                                text += str;
                                            }
                                        }
//                                        Log.e(paramAnonymousString + "拿到" + sign + "源码：", str);
//                                        resource[sign++] = str;
                                    }
                                    System.out.println("核心内容输出：" + text);
                                    coreSuanfa(text);
                                    if (getActivity() != null) {
                                        getActivity().runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                webView.setVisibility(View.GONE);
                                                ll1.setVisibility(View.GONE);
                                                input_qq_rel.setVisibility(View.GONE);
                                                cacluer_2.setVisibility(View.VISIBLE);
                                            }
                                        });
                                    }
                                    waitDialog.cancel();
                                } else {
                                    Log.e("获取不到网页的源码，服务器响应代码为：", "" + responsecode);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                }
                super.onLoadResource(view, paramAnonymousString);
            }


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //此方法为检测是否在加载【弹起本地浏览器JS】的方法，以免QQ空间被本地浏览器打开
                try {
                    boolean bool = url.startsWith("jsbridge://");
                    if (bool) {
                        return false;
                    }
                } catch (Exception e) {
                    return false;
                }
                webView.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl("https://qzone.qq.com/");
    }

    public void coreSuanfa(String text) {
        double fw_speed1 = 2.2; // 第一个圈代挂可达到的最高上限
        double fw_speed2 = 1.6; // 第二个圈代挂可达到的最高上限
        double fw_speed = 1.0; //第一个圈速度
        String dg_speed = ""; //代挂速度

        String now_level = regular(text, "iQQLevel"); //现在等级
        String now_onlineDay = regular(text, "iTotalActiveDay");  //总在线日
        String next_level_day = "";                       //下个等级总日数
        String next_level_uday = "";                      //下个等级所需日数
        String next_hg_level = "";                        //下个皇冠等级
        String next_hg_level_day = "";                    //下个皇冠等级总日数
        final String qq = regular(text, "param");         //帐号QQ
        String is_superclub = regular(text, "is_superclub"); //是否超级
        String is_year_club = regular(text, "is_year_club"); //是否年费
        String is_club = regular(text, "is_club"); // 是否会员
        String level = regular(text, "level");   //会员等级
        String next_hg_level_sday1 = "";                  //下个皇冠等级 速度1 所需日
        String next_hg_level_sday2 = "";                  //下个皇冠等级 速度2(代挂) 所需日
        if (Integer.parseInt(now_level) < 64) {
            next_hg_level = "64";
        } else if (Integer.parseInt(now_level) >= 64 && Integer.parseInt(now_level) < 128) {
            next_hg_level = "128";
        } else {
            next_hg_level = "192";
        }
        next_level_day = String.valueOf((Integer.parseInt(now_level) + 1) * (Integer.parseInt
                (now_level) + 1)
                + 4 * (Integer.parseInt(now_level) + 1));
        next_hg_level_day = String.valueOf((Integer.parseInt(next_hg_level)) * (Integer.parseInt(next_hg_level))
                + 4 * (Integer.parseInt(next_hg_level)));
        if (is_superclub.equals("1")) {
            // 是SVIP
            if (is_year_club.equals("1")) {
                // 是年费SVIP
                switch (level.charAt(0)) {
                    case '1':
                        fw_speed = 1.7;
                        break;
                    case '2':
                        fw_speed = 1.9;
                        break;
                    case '3':
                        fw_speed = 2.0;
                        break;
                    case '4':
                        fw_speed = 2.1;
                        break;
                    case '5':
                        fw_speed = 2.2;
                        break;
                    case '6':
                        fw_speed = 2.4;
                        break;
                    case '7':
                        fw_speed = 2.7;
                        break;
                    case '8':
                        fw_speed = 3.0;
                        break;
                }
            } else {
                // 是普通SVIP
                switch (level.charAt(0)) {
                    case '1':
                        fw_speed = 1.4;
                        break;
                    case '2':
                        fw_speed = 1.6;
                        break;
                    case '3':
                        fw_speed = 1.7;
                        break;
                    case '4':
                        fw_speed = 1.8;
                        break;
                    case '5':
                        fw_speed = 1.8;
                        break;
                    case '6':
                        fw_speed = 1.9;
                        break;
                    case '7':
                        fw_speed = 2.1;
                        break;
                    case '8':
                        fw_speed = 2.2;
                        break;
                }
            }
        } else {
            // 是VIP
            if (is_year_club.equals("1")) {
                // 是年费VIP
                switch (level.charAt(0)) {
                    case '1':
                        fw_speed = 1.5;
                        break;
                    case '2':
                        fw_speed = 1.7;
                        break;
                    case '3':
                        fw_speed = 1.8;
                        break;
                    case '4':
                        fw_speed = 1.9;
                        break;
                    case '5':
                        fw_speed = 1.9;
                        break;
                    case '6':
                        fw_speed = 2.0;
                        break;
                    case '7':
                        fw_speed = 2.2;
                        break;
                }
            } else {
                // 是普通VIP
                switch (level.charAt(0)) {
                    case '1':
                        fw_speed = 1.2;
                        break;
                    case '2':
                        fw_speed = 1.4;
                        break;
                    case '3':
                        fw_speed = 1.5;
                        break;
                    case '4':
                        fw_speed = 1.6;
                        break;
                    case '5':
                        fw_speed = 1.6;
                        break;
                    case '6':
                        fw_speed = 1.7;
                        break;
                    case '7':
                        fw_speed = 1.9;
                        break;
                }
            }
        }
        next_hg_level_sday1 = String.valueOf(Math.round((Double.parseDouble(next_hg_level_day) - Double
                .parseDouble
                        (now_onlineDay)) / fw_speed));

        DecimalFormat df = new DecimalFormat("#.0");
        dg_speed = String.valueOf(df.format(fw_speed * fw_speed1 + fw_speed2));
        next_hg_level_sday2 = String.valueOf(Math.round((Double.parseDouble(next_hg_level_day) - Double
                .parseDouble
                        (now_onlineDay)) / Double.parseDouble(dg_speed)));
        next_level_uday = String.valueOf(Math.round((Double.parseDouble(next_level_day) - Double
                .parseDouble
                        (now_onlineDay)) / Double.parseDouble(dg_speed)));
        tv_qname.setText(regular(text, "sNickName"));
        tv_qq.setText(qq);
        tv_level.setText(now_level);
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    SetImageViewUtil.setImageToImageView(image_touxiang, "http://q2.qlogo" +
                            ".cn/headimg_dl?dst_uin=" + qq + "&spec=100");
                }
            });
        }
        day_3.setText(next_level_uday);
        day_2.setText(next_hg_level_sday2);
        speed_2.setText(dg_speed);
        speed_1.setText(String.valueOf(fw_speed));
        day_1.setText(next_hg_level_sday1);
        tv_caculer_1.setText(String.valueOf(fw_speed) + "倍以上");
        tv_caculer_2.setText(dg_speed + "倍以上");
    }

    public String regular(String text, String target) {
        if (text != null) {
            String pattern = "(.+?)\"" + target + "\":\"(.*?)\"(.+?)";
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(text);
            if (m.find()) {
//            System.out.println("Found value: " + m.group(0) );
//            System.out.println("Found value: " + m.group(1) );
                System.out.println("Found value: " + m.group(2));
                return m.group(2).toString();
            } else {
                String p = "(.+?)\"" + target + "\":(.*?),\"(.+?)";
                Pattern r1 = Pattern.compile(p);
                Matcher m1 = r1.matcher(text);
                if (m1.find()) {
                    System.out.println("Found value: " + m1.group(2));
                    return m1.group(2).toString();
                } else {
                    System.out.println("NO MATCH");
                }
            }
        }
        return "";
    }

    public void setTitle() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getActivity().getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //根据上面设置是否对状态栏单独设置颜色
            if (useThemestatusBarColor) {
                getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.blue_title));
            } else {
                getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getActivity().getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !useStatusBarColor) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
}