package com.example.andy.app_test.Activity;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.dialog.android.phone.HelperBasePhoneDialog;
import com.dialog.android.phone.app.PhoneDialog;
import com.example.andy.app_test.Adaptor.MyFragmentPagerAdapter;
import com.example.andy.app_test.Adaptor.RecyclerViewAdapter_mainMenu;
import com.example.andy.app_test.DrawersGod;
import com.example.andy.app_test.Fragment.Fragment_viewpage1;
import com.example.andy.app_test.Fragment.Fragment_viewpage2;
import com.example.andy.app_test.Fragment.Fragment_viewpage3;
import com.example.andy.app_test.Fragment.Fragment_viewpage4;
import com.example.andy.app_test.R;
import com.example.andy.app_test.TestLog;
import com.example.andy.app_test.util.Jsoup_parser_html_uitl;
import com.example.andy.app_test.observer.Observer;
import com.example.andy.app_test.util.SportOpenDateUtil;

import java.util.ArrayList;
import java.util.List;

public class Badminton_main_menu extends DrawersGod
                                 implements ViewPager.OnPageChangeListener, Observer,
                                            RecyclerViewAdapter_mainMenu.OnRecyclerViewItemClickListener,
                                            AlertDialog.OnClickListener{
    private final String MAINMENU = "Badminton_main_menu";
    private Context context;
    private Resources res;

    //View Page專用
    private Handler handler_viewPage;
    private Runnable run_viewPage;

    //延遲錯誤訊息的 Runnable
    private Handler handler_onFailure;
    private Runnable run_failure;

    //RecyclerView for MainMenu
    private List<Bitmap> list_recycker;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter_mainMenu Adapter_recycler;

    //適用於　Fragment_PageAdapter 在ViewPager 所變換的頁面(Fragment)，
    private Fragment_viewpage1 viewpage1;
    private Fragment_viewpage2 viewpage2;
    private Fragment_viewpage3 viewpage3;
    private Fragment_viewpage4 viewpage4;

    //存放在ViewPager轉動的 頁面
    private List<Fragment> list_fragment;
    private Fragment[] collection＿fragment;

    // ViewPager
    private ViewPager viewPager;
    private MyFragmentPagerAdapter adapter;

    // RadioGroup
    private View view;
    private RadioGroup radioGroup;

    //按鈕點擊狀態
    private int current_mode_typ;
    private final int MODE_INITIAL = 0;
    private final int MODE_BACK_MENU = 11;
    private final int MODE_UTIL_ERROR = 22;

    //進度條
    private PhoneDialog dialog_process;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.badminton_main_minu);
        TestLog.myLog_d(MAINMENU, "onCreate()");

        findId();
        init();


        //開始抓取json資料
        SportOpenDateUtil.loadDate(this);

        //開始抓取html資料
        PageAdapter_working_start();

        //進度條 (抓取資料)
        dialog_process = HelperBasePhoneDialog.createProgressDialog(context,res.getString(R.string.process_msg));
        dialog_process.show();

    }

    //(抽象與虛擬方法的調用)　繼承Drawer(抽屜) 並實作抽象方法
    @Override
    public LinearLayout tagLayout() {
        LinearLayout layout = (LinearLayout)findViewById(R.id.badminton_main_minu_container);
        return layout;
    }

    @Override
    protected void onStart() {
        super.onStart();
        TestLog.myLog_d(MAINMENU, "onStart()");

        // Viewpage 自動輪播 start
        switch_viewPage_function();
    }

    @Override
    protected void onPause() {
        super.onPause();
        TestLog.myLog_d(MAINMENU, "onPause()");

        handler_viewPage.removeCallbacks(run_viewPage);
    }

    @Override
    protected void onStop() {
        super.onStop();
        TestLog.myLog_d(MAINMENU, "onPause()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        TestLog.myLog_d(MAINMENU, "onResume()");
    }


    private void findId() {
        TestLog.myLog_d(MAINMENU, "findId()");

        viewPager  = (ViewPager) findViewById(R.id.badmintion_main_minu_viewpager);
        view       = findViewById(R.id.badmintion_main_minu_radioGroup);
        radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        recyclerView = (RecyclerView)findViewById(R.id.badmintion_main_minu_recyclerView);
    }

    private void init(){
        TestLog.myLog_d(MAINMENU, "init()");

        context = this;
        res = getResources();

        list_recycker = new ArrayList<>();

        Resources res=getResources();
        Bitmap bmp_icon_history = BitmapFactory.decodeResource(res, R.drawable.main_icon_history);
        Bitmap bmp_icon_play = BitmapFactory.decodeResource(res, R.drawable.main_icon_play);
        Bitmap bmp_icon_postion2 = BitmapFactory.decodeResource(res, R.drawable.main_icon_postion2);
        Bitmap bmp_icon_video = BitmapFactory.decodeResource(res, R.drawable.main_icon_video);

        list_recycker.add(bmp_icon_history);
        list_recycker.add(bmp_icon_play);
        list_recycker.add(bmp_icon_postion2);
        list_recycker.add(bmp_icon_video);

        recyclerView.setHasFixedSize(true);

        //Linear 創造出一個線性布局的管理者
        LinearLayoutManager llm = new LinearLayoutManager(this);

        //設定每一個item的 Orientation(方向) 為垂直(VERTICAL)
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);

        //GrideView
        GridLayoutManager grid = new GridLayoutManager(this,2);

        //設定到佈局管理者中
        recyclerView.setLayoutManager(grid);

        // 建立 & 設定 資料轉接器
        Adapter_recycler = new RecyclerViewAdapter_mainMenu(list_recycker);
        Adapter_recycler.setOnItemClickListener(this);



        recyclerView.setAdapter(Adapter_recycler);

    }

    //返回鍵
    @Override
    public void onBackPressed() {
        TestLog.myLog_d(MAINMENU, "onBackPressed()");

        //記錄按下後 MODE狀態
        current_mode_typ = MODE_BACK_MENU ;

        String msg = res.getString(R.string.dialog_msg_finish);
        String title = res.getString(R.string.dialog_title_finish);


        PhoneDialog dialog;
        // Context、標題、按鈕(右)、監聽事件、按鈕(左)、監聽事件
        dialog = HelperBasePhoneDialog.createDialog(context,
                title,
                msg,
                res.getString(R.string.dialog_btn_enter),
                this,
                res.getString(R.string.dialog_btn_cancel),
                this);
        dialog.show();
        return;

    }


//      Observer start
    @Override  //下載成功
    public void OnCompleted(String msg) {

        TestLog.myLog_w(MAINMENU, "Observer() down scuess");
        Adapter_recycler.notifyDataSetChanged();
        dialog_process.dismiss();
    }


    @Override  //下載失敗
    public void OnError(String msg) {

        TestLog.myLog_e(MAINMENU, "Observer() down error");
        dialog_process.dismiss();

        String title = res.getString(R.string.dialog_msg_error);
        msg = res.getString(R.string.dialog_title_error);

        // Context、標題、按鈕(中)、監聽事件
        PhoneDialog dialog = HelperBasePhoneDialog.createDialog(context,
                title,
                msg,
                res.getString(R.string.dialog_btn_enter),
                this);
        dialog.show();

    }


    @Override  //連線失敗
    public void onFailure(String msg) {

        TestLog.myLog_e(MAINMENU, "Observer() onFailure ");

               run_failure = new Runnable() {
            @Override
            public void run() {
                onFailure_function(run_failure);
            }
        };

        handler_onFailure = new Handler();
        handler_onFailure.postDelayed(run_failure,3300);
    }


    private void onFailure_function(Runnable r){
        //關閉 Dialog
        dialog_process.dismiss();

        //回收
        handler_onFailure.removeCallbacks(r);

        String title  = res.getString(R.string.dialog_title_noUtil);
        String msg = res.getString(R.string.dialog_msg_noUtil);

        // Context、標題、按鈕(右)、監聽事件、按鈕(左)、監聽事件
        PhoneDialog dialog = HelperBasePhoneDialog.createDialog(context,
                title,
                msg,
                res.getString(R.string.dialog_btn_enter),
                this);
        dialog.show();
    }
//      Observer end


    //ViewPage function
    private void PageAdapter_working_start() {
        TestLog.myLog_d(MAINMENU, "PageAdapter_working_start()");

        //創建Fragment
        viewpage1 = new Fragment_viewpage1();
        viewpage2 = new Fragment_viewpage2();
        viewpage3 = new Fragment_viewpage3();
        viewpage4 = new Fragment_viewpage4();

        //Fragment 放入　陣列中
        collection＿fragment = new Fragment[]{viewpage1, viewpage2, viewpage3 , viewpage4};


        //創建FragmentAdapter
        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), collection＿fragment);


        //viewPager 裝一個監聽器（產生一個接口）
        viewPager.setOnPageChangeListener(this);

        new Jsoup_parser_html_uitl().start();
        //裝入Adapter
        viewPager.setAdapter(adapter);
//        viewPager.setPageTransformer(true,new ZoomOutPageTransformer());


    }

 //     ViewPage start
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        //Log ViewPage位置
        TestLog.myLog_d(MAINMENU, "onPageSelected()",position);
        switch (position) {
            case 0:

                radioGroup.check(R.id.radioButton1);
                break;

            case 1:
                radioGroup.check(R.id.radioButton2);
                break;

            case 2:
                radioGroup.check(R.id.radioButton3);
                break;

            case 3:
                radioGroup.check(R.id.radioButton4);
                break;
        }
    }
    boolean isAutoPlay = false;

    @Override
    public void onPageScrollStateChanged(int state) {

        maxScrollChanged_function(state);
    }
//      ViewPage end


    // Viewpage 自動輪播
    private void switch_viewPage_function() {

        handler_viewPage = new Handler();
        run_viewPage = new Runnable() {

            //page=0 page=1 page=2
            public void run() {


                int pageCount = adapter.getCount();
                int pageNext = viewPager.getCurrentItem()+1;
                TestLog.myLog_d(MAINMENU, "switch_viewPage_function() "+"pageCount",pageCount);

                if (viewPager.getCurrentItem() == pageCount - 1){
                    pageNext = 0;
                    TestLog.myLog_d(MAINMENU, "switch_viewPage_function() resert",pageNext);
                }


                viewPager.setCurrentItem(pageNext, false);
                TestLog.myLog_d(MAINMENU, "switch_viewPage_function"+"  setCurrentItem",pageNext);

                handler_viewPage.postDelayed(run_viewPage, 2000);
            }



        };

        handler_viewPage.postDelayed(run_viewPage, 5000);

    }

    // Viewpage 滑動 無邊界效果
    private void maxScrollChanged_function(int state){
                switch (state) {

            case 1:// 手势滑动，空闲中

                isAutoPlay = false;

                break;

            case 2:// 界面切换中

                isAutoPlay = true;

                break;

            case 0:// 滑动结束，即切换完毕或者加载完毕

// 当前为最后一张，此时从右向左滑，则切换到第一张

                if (viewPager.getCurrentItem() == viewPager.getAdapter().getCount() - 1 && !isAutoPlay) {

                    viewPager.setCurrentItem(0);
                }

// 当前为第一张，此时从左向右滑，则切换到最后一张

                else if (viewPager.getCurrentItem() == 0 && !isAutoPlay) {
                viewPager.setCurrentItem(viewPager.getAdapter().getCount() - 1);
            }

                break;
        }
    }


    //RecyclerView onItemClick() (管理)按下後引發的事件
    @Override
    public void onItemClick(View view, int postion) {
        //Log RecyclerView onItemClick() 位置
        TestLog.myLog_d(MAINMENU, "RecyclerView onItemClick()","postion= "+postion);

        Intent intent = new Intent();
        switch (postion){

            case 0:
                intent.setClass(context,Badminton_history.class);
                break;
            case 1:
                intent.setClass(context,Badminton_play_start.class);
                break;
            case 2:
                intent.setClass(context,Badminton_sportCenter.class);
                break;
            case 3:
                intent.setClass(context,Badminton_vaideo.class);
                break;

        }
        startActivity(intent);
    }

    //Dialog onClick() (管理)按下後引發的事件
    @Override
    public void onClick(DialogInterface dialog, int which) {
        TestLog.myLog_d(MAINMENU, "Dialog onClick() ");
        //Log　Dialog onClick　點擊的狀態
        TestLog.myLog_w(MAINMENU, "Dialog onClick() MODE_BACK_MENU",MODE_BACK_MENU);
        TestLog.myLog_w(MAINMENU, "Dialog onClick() current_mode_typ",current_mode_typ);

        switch (which){

            case DialogInterface.BUTTON_POSITIVE:
                if(MODE_BACK_MENU == current_mode_typ){
                    TestLog.myLog_d(MAINMENU, "Dialog onClick()" ,"BUTTON_POSITIVE");
                    finish();
                    dialog.dismiss();
                    return;
                }

                if(MODE_UTIL_ERROR == current_mode_typ){
                    TestLog.myLog_d(MAINMENU, "Dialog onClick()" ,"BUTTON_POSITIVE");
                    return;
                }


                TestLog.myLog_w(MAINMENU, "AlertDialog onClick","BUTTON_POSITIVE other");
                dialog.dismiss();
                break;

            case DialogInterface.BUTTON_NEGATIVE:
                if(MODE_BACK_MENU == current_mode_typ){
                    TestLog.myLog_d(MAINMENU, "Dialog onClick()" ,"BUTTON_NEGATIVE");
                    dialog.dismiss();
                    return;
                }

                if(MODE_UTIL_ERROR == current_mode_typ){
                    TestLog.myLog_d(MAINMENU, "Dialog onClick()" ,"BUTTON_NEGATIVE");
                    return;
                }


                TestLog.myLog_w(MAINMENU, "AlertDialog onClick","BUTTON_NEGATIVE other");
                dialog.dismiss();
                break;


        }
        //狀態歸0
        current_mode_typ = MODE_INITIAL;

    }


    // Viewpage 動畫效果
    private class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }
}
