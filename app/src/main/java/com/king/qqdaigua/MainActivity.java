package com.king.qqdaigua;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.baidu.mobstat.SendStrategyEnum;
import com.baidu.mobstat.StatService;
import com.king.Caculer_Fragment.caculer_Fragment;
import com.king.Login_Fragment.BlankFragment1;
import com.king.YH_Fragment.update_dialog;
import com.king.level_Fragment.level_Fragment;
import com.king.util.HttpRequest;
import com.king.util.OperatingSharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //版本号控制更新提示
    public static String app_ver = "3.85";
    //过期否检测值
    public static String guoqi_button = "0";

    protected boolean useThemestatusBarColor = true;//是否使用特殊的标题栏背景颜色，android5
    // .0以上可以设置状态栏背景色，如果不使用则使用透明色值
    protected boolean useStatusBarColor = false;//是否使用状态栏文字和图标为暗色，如果状态栏采用了白色系，则需要使状态栏和图标为暗色，android6
    // .0以上可以设置

    public static String admin_pwd = "123456";
    public static String app_qq = "1776885812";
    public static String app_name = "帝王代挂";
    public static String app_subName = " For Android";
    public static String app_url = "https://www.dkingdg.com/";
    public static String app_buy = "https://www.dkingdg.com/buy/";
    public static String web_jiekou = "http://api.52dg.gg/";
    public static String web_jiekou1 = "http://kkkking.daigua.org/";
    public static String buy_url = "https://www.dkingdg.com/buy/";
    public static String check_url = "http://api.52dg.gg/lgcx?qq=";
    //临时存放 QQ扫码登录用的Cookie
    public static String cookie_1 = "";


    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private int qi_sign = 0;
    private String update_sign = "0";

    private MenuItem menuItem;
    private String skin_value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        updateCheck();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatService.setSendLogStrategy(this, SendStrategyEnum.APP_START, 1, false);

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        skin_value = preferences.getString("skin_value", "1");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.rgb(24, 180, 237));
        if (skin_value.equals("1")) {
            toolbar.setBackgroundColor(Color.rgb(24, 180, 237));
        } else if (skin_value.equals("2")) {
            toolbar.setBackgroundColor(Color.rgb(72, 243, 251));
        } else if (skin_value.equals("3")) {
            toolbar.setBackgroundColor(Color.rgb(147, 29, 3));
        } else if (skin_value.equals("4")) {
            toolbar.setBackgroundColor(Color.rgb(124, 51, 154));
        } else if (skin_value.equals("5")) {
            toolbar.setBackgroundColor(Color.rgb(0, 133, 251));
        }
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
//                Log.e("距离", "" + slideOffset);
                if (slideOffset > 0) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
                        View decorView = getWindow().getDecorView();
                        int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                        decorView.setSystemUiVisibility(option);
                        //根据上面设置是否对状态栏单独设置颜色
                        if (useThemestatusBarColor) {
                            getWindow().setStatusBarColor(getResources().getColor(R.color.huangse));
                        } else {
                            getWindow().setStatusBarColor(Color.TRANSPARENT);
                        }
                    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
                        WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
                        localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !useStatusBarColor) {//android6.0以后可以对状态栏文字颜色和图标进行修改
                        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                    }
                    super.onDrawerOpened(drawerView);
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
                        View decorView = getWindow().getDecorView();
                        int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                        decorView.setSystemUiVisibility(option);
                        //根据上面设置是否对状态栏单独设置颜色
                        if (useThemestatusBarColor) {
                            if (skin_value.equals("1")) {
                                getWindow().setStatusBarColor(getResources().getColor(R.color.blue_title));
                            } else if (skin_value.equals("2")) {
                                getWindow().setStatusBarColor(getResources().getColor(R.color.skin_green));
                            } else if (skin_value.equals("3")) {
                                getWindow().setStatusBarColor(getResources().getColor(R.color.skin_orange));
                            } else if (skin_value.equals("4")) {
                                getWindow().setStatusBarColor(getResources().getColor(R.color.skin_pou));
                            } else if (skin_value.equals("5")) {
                                getWindow().setStatusBarColor(getResources().getColor(R.color.skin_colorful));
                            }
                        } else {
                            getWindow().setStatusBarColor(Color.TRANSPARENT);
                        }
                    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
                        WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
                        localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !useStatusBarColor) {//android6.0以后可以对状态栏文字颜色和图标进行修改
                        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                    }
                }
                super.onDrawerSlide(drawerView, slideOffset);
            }

        };
        drawer.addDrawerListener(toggle);
//        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        initv();
        new Handler().postDelayed(new LoadMainTabTask(), 0);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new BlankFragment1())
                .commit();
    }


//    private void initEvent() {
//        drawerbar = new ActionBarDrawerToggle(this, drawerLayout, R.mipmap.ic_launcher, R.string.open, R.string.close) {
//            //菜单打开
//            @Override
//            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
//            }
//
//            // 菜单关闭
//            @Override
//            public void onDrawerClosed(View drawerView) {
//                super.onDrawerClosed(drawerView);
//            }
//        };
//
//        drawerLayout.setDrawerListener(drawerbar);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            Log.e("点击了", "");
        }

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.dengjizhongxin) {
            // Handle the camera action
            String serverday = preferences.getString("serverday", "");
            String dgtime = preferences.getString("dgtime", "");
            String score = preferences.getString("score", "");
            if (serverday.length() == 0 || dgtime.length() == 0 || score.length() == 0) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "请先登录再使用此功能", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                getSupportFragmentManager().beginTransaction().replace(R.id
                        .content_main, new level_Fragment()).commit();
            }
        } else if (id == R.id.qq_sport) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "QQ运动一键加速功能即将上线！软件端一个按钮本地完成运动加速任务，敬请期待！", Toast.LENGTH_LONG)
                            .show();
                }
            });
        } else if (id == R.id.skin) {
            getSupportFragmentManager().beginTransaction().replace(R.id
                    .content_main, new skin_change()).commit();
        } else if (id == R.id.qq_carculer) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new
                    caculer_Fragment()).commit();
        } else if (id == R.id.update) {
            updateCheck();
            if (update_sign.equals("0")) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "您安装的是最新版本", Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }
        } else if (id == R.id.back) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new
                    BlankFragment1()).commit();
        } else if (id == R.id.fankui) {
            openURL("https://wj.qq.com/s/2588190/1bb8/");
        } else if (id == R.id.nav_manage) {
            new about_dialog().show(getSupportFragmentManager(), "");
        } else if (id == R.id.board) {
            new board_dialog().show(getSupportFragmentManager(), "");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 基本功能：加载介绍界面或主页面
     * 编写：Jason
     */
    private class LoadMainTabTask implements Runnable {
        @Override
        public void run() {
            qi_sign++;
            boolean opentimes = OperatingSharedPreferences
                    .getSharedPreferences(getApplicationContext());
            // 若为新安装的应用，进入介绍界面,并保存启动次数到SharedPreferences。若不是新安装，则直接进入首页
            if (opentimes) {
                OperatingSharedPreferences
                        .setSharedPreferences(getApplicationContext());
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),
                        PowerSplashActivity.class);
                startActivity(intent);//打开引导页
            } else {
                Intent intent = new Intent(getApplicationContext(),
                        QiDongActivity.class);
                startActivity(intent);//直接打开首页
            }
        }
    }

    private void initv() {


        editor = preferences.edit();
        editor.putString("score", "");
//                    editor.putString("score", double_finis+"");
        editor.apply();


//        fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
//        fab.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                openURL(app_url);
//                Snackbar.make(v, "你已经长按", Snackbar.LENGTH_LONG)
//                        .show();
//                return true;
//            }
//        });
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "长按我可进入网页版哦~", Snackbar.LENGTH_SHORT)
//                        .show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();
        if (id == R.id.action_settings3) {
//            fab.setVisibility(View.GONE);
            openURL("http://www.dkingdg.com/");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void updateCheck() {
        String post_url = MainActivity.app_url + "ajax/dg" +
                ".php?ajax=true&star=update_ver";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("type", "update");
            HttpRequest http = new HttpRequest(post_url, jsonObject.toString(), handler);
            http.start();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void openURL(String s) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(s);
        intent.setData(content_url);
        startActivity(intent);
    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 10) {
                try {
                    JSONObject json = new JSONObject((String) msg.obj);
                    String code = json.getString("code");
                    if (Double.parseDouble(code) > Double.parseDouble(MainActivity.app_ver)) {
                        update_sign = "1";
                        new update_dialog().show(getSupportFragmentManager(), "");
                    } else {
                        update_sign = "0";
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };
}
