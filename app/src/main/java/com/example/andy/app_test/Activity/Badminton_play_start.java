package com.example.andy.app_test.Activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.FragmentTransaction;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dialog.android.phone.HelperBasePhoneDialog;
import com.dialog.android.phone.app.PhoneDialog;
import com.example.andy.app_test.DrawersGod;
import com.example.andy.app_test.Fragment.Fragment_game_play;
import com.example.andy.app_test.R;
import com.example.andy.app_test.TestLog;
import com.example.andy.app_test.model.Game_badminton_servicer;
import com.example.andy.app_test.model.Main_badminton_servicer;
import com.example.andy.app_test.model.Main_badminton_servicer.SaveData;
import com.example.andy.app_test.myapp.MyApp;
import com.example.andy.app_test.util.FireBase_Util;

import java.util.List;


public class Badminton_play_start extends DrawersGod implements AlertDialog.OnClickListener , FireBase_Util.FireBase_OnchangeListener {

    private Context context;
    private Resources res;

    //標籤
    private final String BADMINTON = "Badminton gane start";

    //集合第0、1項
    private Game_badminton_servicer[] badminton_list;


    //按鈕點擊狀態
    private int current_mode_typ;
    private final int MODE_BACK_MENU = 00;
    private final int MODE_CHANGR = 11;
    private final int MODE_RESTAR = 22;

    //servicer
    private Main_badminton_servicer badminton_Servicer;

    //Fragment
    private Fragment_game_play fragment_up;
    private Fragment_game_play fragment_down;

    //Layout contauner
    private LinearLayout layout_container_up;
    private LinearLayout layout_container_down;

    //team msg
    private LinearLayout layout_team_up;
    private LinearLayout layout_team_down;


    //EditText
    private EditText m_et_name_up;
    private EditText m_et_name_down;

    //ImageView
    private ImageView m_iv_photo_up;
    private ImageView m_iv_photo_down;

    //Bitmap
    private Bitmap bitmap_up;
    private Bitmap bitmap_down;

    private   TextView tv_murquee;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.badminton_play_main);
        TestLog.myLog_d(BADMINTON, "onCreate()");

        init();

        start_setGame_rule();


    }

    @Override
    protected void onStart() {
        super.onStart();
        TestLog.myLog_d(BADMINTON, "onStart()");


    }


    @Override
    protected void onPause() {
        super.onPause();
        TestLog.myLog_d(BADMINTON, "onPause()");
        save_data_function();
    }


    @Override
    public LinearLayout tagLayout() {
        LinearLayout layout = (LinearLayout)findViewById(R.id.badmintion_play_main_container);
        return layout;
    }

    private void init() {
        TestLog.myLog_d(BADMINTON, "init()");

        context = this;
        res=getResources();
        badminton_Servicer = MyApp.getMain_badminton_servicer();
        badminton_list = badminton_Servicer.getList_service();

        //跑馬燈
        tv_murquee = (TextView) findViewById(R.id.marquee_msg);
        tv_murquee.setSelected(true);

        //fragment container
        layout_container_up = (LinearLayout)findViewById(R.id.badmintion_play_main_linear_fragment_container_up) ;
        layout_container_down = (LinearLayout)findViewById(R.id.badmintion_play_main_linear_fragment_container_down) ;

        //team 全部資訊
        layout_team_up= (LinearLayout)findViewById(R.id.badmintion_play_main_linear_container_team_up) ;
        layout_team_down= (LinearLayout)findViewById(R.id.badmintion_play_main_linear_container_team_down) ;


        m_et_name_up = (EditText) findViewById(R.id.badmintion_play_main_et_name_up);
        m_et_name_down = (EditText) findViewById(R.id.badmintion_play_main_et_name_down);
        m_iv_photo_up = (ImageView) findViewById(R.id.badmintion_play_main_iv_photo_up);
        m_iv_photo_down = (ImageView) findViewById(R.id.badmintion_play_main_iv_photo_down);

        fragment_up = new Fragment_game_play();
        fragment_down = new Fragment_game_play();

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.badmintion_play_main_linear_fragment_container_up, fragment_up);
        ft.replace(R.id.badmintion_play_main_linear_fragment_container_down, fragment_down);
        ft.commit();

        //設定Log的開關
//        TestLog.setBoolean_log_msg(false);
    }

    private void start_setGame_rule(){
        PhoneDialog dialog = HelperBasePhoneDialog.createDialog(context,
                "選擇 『比賽制度』",
                "舊制15分，新制21分",
                "新制",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        badminton_Servicer.setMax_fraction(21);

                        dialog.dismiss();
                        dialog.dismiss();


                    }
                },
                "舊制",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        badminton_Servicer.setMax_fraction(15);
                        dialog.dismiss();
                        dialog.dismiss();

                    }
                });


        dialog.show();

        //Firebase start
        FireBase_Util.fireasefunction(context);
        FireBase_Util.setFireBase_onchangeListener(this);
    }



    //Button onclick
    public void onclick(View view) {
        String msg = "" ;
        String title ="";

        switch (view.getId()) {

            case R.id.badmintion_play_main_btn_Back:
                TestLog.myLog_d(BADMINTON, "btn_Back()","onclick");

                //記錄按下後 MODE狀態
                current_mode_typ = MODE_BACK_MENU;

                title = res.getString(R.string.dialog_title_back);
                msg = res.getString(R.string.dialog_msg_back);
                break;

            case R.id.badmintion_play_main_btn_Change:
                TestLog.myLog_d(BADMINTON, "btn_Change()","onclick");

                //記錄按下後 MODE狀態
                current_mode_typ = MODE_CHANGR;

                title = res.getString(R.string.dialog_title_change);
                msg = res.getString(R.string.dialog_msg_change);
                break;

            case R.id.badmintion_play_main_btn_Restar:
                TestLog.myLog_d(BADMINTON, "btn_Restar()","onclick");

                //記錄按下後 MODE狀態
                current_mode_typ = MODE_RESTAR;

                title = res.getString(R.string.dialog_title_restar);
                msg = res.getString(R.string.dialog_msg_restar);
                break;
        }

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

    }


    //Dialog onclick
    @Override
    public void onClick(DialogInterface dialog, int which) {

        switch (which) {

            case DialogInterface.BUTTON_POSITIVE:

                if (MODE_BACK_MENU == current_mode_typ) {
                    TestLog.myLog_d(BADMINTON, "Dialog.onClick()","MODE_BACK_MENU，MODE_RESTAR ");

                    back_function();
                    dialog.dismiss();
                    return;
                }

                if (MODE_CHANGR == current_mode_typ) {
                    TestLog.myLog_d(BADMINTON, "Dialog.onClick()","MODE_CHANGR，MODE_RESTAR ");

                    change_function();
                    dialog.dismiss();
                    return;
                }

                if (MODE_RESTAR == current_mode_typ) {
                    TestLog.myLog_d(BADMINTON, "Dialog.onClick()","BUTTON_POSITIVE，MODE_RESTAR ");

                    restar_function();
                    dialog.dismiss();
                    return;
                }

                break;

            //取消
            case DialogInterface.BUTTON_NEGATIVE:

                TestLog.myLog_d(BADMINTON, "Dialog.onClick()","BUTTON_NEGATIVE");
                dialog.dismiss();
        }
    }

    //Firebase onclick
    @Override
    public void onChange() {
        TestLog.myLog_d(BADMINTON, "play_start onChange() Listener");


        List<String> mylist = FireBase_Util.getMylist();
        TestLog.myLog_d(BADMINTON, "onChange() mylist",mylist);

        if(mylist == null){
            TestLog.myLog_e(BADMINTON, "mylist return");
            tv_murquee.setSelected(true);
            return;
        }



        tv_murquee.setText("                     "+mylist.get(0).toString());
        tv_murquee.setSelected(true);
        //跑馬燈 end

    }

    //《方法》本局刷新
    private void restar_function() {
        TestLog.myLog_d(BADMINTON, "restar_function");

        fragment_up.fragment_clean_current_point_fraction();
        fragment_down.fragment_clean_current_point_fraction();
    }

    //《方法》回主畫面
    private void back_function() {
        TestLog.myLog_d(BADMINTON, "back_function");

        //反回主畫面 比賽局數歸0
        badminton_Servicer.setCurrent_typ_point(1);
        finish();
    }

    //《方法》換邊
    private void change_function() {
        TestLog.myLog_d(BADMINTON, "change_function()");
        //紀錄當局的分數
        save_data_function();

        //(方向、角度、完成時間、view、cxt)
        play_start(1,360,2500,layout_container_up,context);
        play_start(1,-360,2500,layout_container_down,context);

    }

    //(禁止) 別人按返回鍵
    @Override
    public void onBackPressed() {
        TestLog.myLog_d(BADMINTON, "onBackPressed()");


        //記錄按下後 MODE狀態
        current_mode_typ = MODE_BACK_MENU;
        String title = res.getString(R.string.dialog_title_back);
        String msg = res.getString(R.string.dialog_msg_back);

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

    //考慮到生命週期 及 交換局數 = 在此建立一個function方便使用
    private void save_data_function(){
        TestLog.myLog_d(BADMINTON, "save_data_function()");

        //Name
        String name_up = m_et_name_up.getText().toString();
        String name_down = m_et_name_down.getText().toString();

        //Bitmap
        Bitmap bitmap_up = ((BitmapDrawable) m_iv_photo_up.getDrawable()).getBitmap();
        Bitmap bitmap_down = ((BitmapDrawable) m_iv_photo_down.getDrawable()).getBitmap();

        // badminton_list[SaveData.MODE_DOWN] 此集合myList[0] 記錄上面物件狀態
        // badminton_list[SaveData.MODE_UP]   此集合myList[1] 記錄下面物件狀態
        badminton_list[SaveData.MODE_UP]=fragment_up.fragment_savaData_function(name_up , bitmap_up);
        badminton_list[SaveData.MODE_DOWN]=fragment_down.fragment_savaData_function(name_down , bitmap_down);

        //Log
        TestLog.myLog_w(BADMINTON, "onPause()","get CurrentPoint= " + badminton_Servicer.getCurrent_typ_point());
        TestLog.myLog_w(BADMINTON, "onPause()","getPoint_1 up fraction= " + badminton_list[SaveData.MODE_UP].getPoint_1_fraction());
        TestLog.myLog_w(BADMINTON, "onPause()","getPoint_2 up fraction= " + badminton_list[SaveData.MODE_UP].getPoint_2_fraction());
        TestLog.myLog_w(BADMINTON, "onPause()","getPoint_3 up fraction= " + badminton_list[SaveData.MODE_UP].getPoint_3_fraction());
        TestLog.myLog_w(BADMINTON, "onPause()","getName up = " + badminton_list[SaveData.MODE_UP].getName());
        TestLog.myLog_w(BADMINTON, "onPause()","getBitmap up = " + badminton_list[SaveData.MODE_UP].getPhoto());

        TestLog.myLog_w(BADMINTON, "onPause()","getPoint_1 down fraction= " + badminton_list[SaveData.MODE_DOWN].getPoint_1_fraction());
        TestLog.myLog_w(BADMINTON, "onPause()","getPoint_2 down fraction= " + badminton_list[SaveData.MODE_DOWN].getPoint_2_fraction());
        TestLog.myLog_w(BADMINTON, "onPause()","getPoint_3 down fraction= " + badminton_list[SaveData.MODE_DOWN].getPoint_3_fraction());
        TestLog.myLog_w(BADMINTON, "onPause()","getName down = " + badminton_list[SaveData.MODE_DOWN].getName());
        TestLog.myLog_w(BADMINTON, "onPause()","getBitmap down = " + badminton_list[SaveData.MODE_DOWN].getPhoto());
    }

    // 動畫加速效果 AccelerateInterpolator();
    private Interpolator accelerator = new OvershootInterpolator();

    // 動畫減速效果
    private Interpolator decelerator = new DecelerateInterpolator();


    // 參數 direction : 1:right to left, -1:left to right
    // 參數 speed : 翻動速度
    public void play_start(int direction, int angle, int speed, View view, final Context context) {
        // 顯示到隱藏翻轉設定
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotationY", 0f, angle * direction);

        // 顯示到隱藏完成時間
        animator.setDuration(speed);

        // 加入動畫加速效果
        animator.setInterpolator(accelerator);

        // 動畫監聽器
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator anim) {

                play_end(1,720,200,layout_container_up,context);
                play_end(1,720,200,layout_container_down,context);
            }
        });

        // 動畫啓動
        animator.start();
    }

    public void play_end(int direction, int angle, int speed, View view, final Context context) {
//        Toast.makeText(context, "play_end", Toast.LENGTH_SHORT).show();

        // 顯示到隱藏翻轉設定
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotationX", 0f, angle * direction);

        // 顯示到隱藏完成時間
        animator.setDuration(speed);

        // 加入動畫加速效果
        animator.setInterpolator(accelerator);

        // 動畫監聽器
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator anim) {


                //(重制) 分數
                fragment_up.change_fraction_function(SaveData.MODE_DOWN);
                fragment_down.change_fraction_function(SaveData.MODE_UP);

                //(重制) name
                m_et_name_up.setText(badminton_list[SaveData.MODE_DOWN].getName().toString());
                m_et_name_down.setText(badminton_list[SaveData.MODE_UP].getName().toString());

                //(重制) 大頭照
                m_iv_photo_up.setImageBitmap(badminton_list[SaveData.MODE_DOWN].getPhoto());
                m_iv_photo_down.setImageBitmap(badminton_list[SaveData.MODE_UP].getPhoto());

                //震動功能
                Vibrator mVibrator;
                mVibrator = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
                mVibrator.vibrate(30);
            }
        });

        // 動畫啓動
        animator.start();
    }
}

