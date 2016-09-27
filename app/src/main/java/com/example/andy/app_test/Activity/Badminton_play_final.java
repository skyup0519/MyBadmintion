package com.example.andy.app_test.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andy.app_test.DrawersGod;
import com.example.andy.app_test.R;
import com.example.andy.app_test.TestLog;
import com.example.andy.app_test.model.Game_badminton_servicer;
import com.example.andy.app_test.model.Main_badminton_servicer;
import com.example.andy.app_test.model.Main_badminton_servicer.SaveData;
import com.example.andy.app_test.myapp.MyApp;
import com.facebook.shimmer.ShimmerFrameLayout;


public class Badminton_play_final extends DrawersGod {
    private Context context;
    private Resources res;
    private Button m_btn_back;

    private int current_point = 0;
    private int index = 1;

    private Handler loser_handler = new Handler();
    private Handler setmessage_handler = new Handler();

    private TextView m_et_name_up;
    private TextView m_et_name_down;

    private TextView m_tv_message;

    private ImageView m_iv_photo_up;
    private ImageView m_iv_photo_down;

    private ImageView m_loser1;
    private ImageView m_loser2;

    private Game_badminton_servicer[] badminton_list;
    private Main_badminton_servicer badminton_Servicer;

    private StringBuilder mseeage = new StringBuilder();
    private final String GAMEEND = "Badminton gane end";
    private String current_fraction_up="";
    private String current_fraction_down="";

    private ShimmerFrameLayout mShimmerViewContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TestLog.myLog_d(GAMEEND, "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.badminton_play_final);

        findId();
        initView();
        checkData();

        run_msg_working();
        anim_playEnding();

    }

    @Override
    public LinearLayout tagLayout() {
        LinearLayout layout = (LinearLayout)findViewById(R.id.badmintion_play_final_container);
        return layout;
    }

    @Override
    protected void onStart() {
        TestLog.myLog_d(GAMEEND, "onStart()");
        super.onStart();
        shimmer();
    }

    private void findId() {
        TestLog.myLog_d(GAMEEND, "findId()");

        context = this;
        res = getResources();

        m_btn_back=(Button)findViewById(R.id.badminton_play_final_btn_Back_Min);

        m_et_name_up   = (TextView) findViewById(R.id.badminton_play_final_tv_user_up);
        m_et_name_down = (TextView) findViewById(R.id.badminton_play_final_tv_user_down);
        m_tv_message   = (TextView) findViewById(R.id.badminton_play_final_tv_msg);

        m_iv_photo_up   = (ImageView) findViewById(R.id.badminton_play_final_iv_photo_up);
        m_iv_photo_down = (ImageView) findViewById(R.id.badminton_play_final_iv_photo_down);

        m_loser1 = (ImageView) findViewById(R.id.badminton_play_final_iv_loser_up);
        m_loser2 = (ImageView) findViewById(R.id.badminton_play_final_iv_loser_down);

        mShimmerViewContainer = (ShimmerFrameLayout)findViewById(R.id.badminton_play_final_shimmer_view_container);

    }

    private void initView() {
        TestLog.myLog_d(GAMEEND, "initView()");

        badminton_Servicer = MyApp.getMain_badminton_servicer();

        badminton_list = badminton_Servicer.getList_service();

        //取得最大局數
         current_point = badminton_Servicer.getMax_point();

        //(設定) ID
        m_et_name_up.setText(badminton_list[SaveData.MODE_UP].getName().toString());
        m_et_name_down.setText(badminton_list[SaveData.MODE_DOWN].getName().toString());

        //(設定) 大頭照
        m_iv_photo_up.setImageBitmap(badminton_list[SaveData.MODE_UP].getPhoto());
        m_iv_photo_down.setImageBitmap(badminton_list[SaveData.MODE_DOWN].getPhoto());

        m_tv_message.setText(res.getText(R.string.msg_game_result));
    }

    private void checkData(){
        TestLog.myLog_d(GAMEEND, "checkData()");

        //Log
        TestLog.myLog_w(GAMEEND, "onPause()","get CurrentPoint= " + badminton_Servicer.getCurrent_typ_point());
        TestLog.myLog_w(GAMEEND, "onPause()","getPoint_1 up fraction= " + badminton_list[SaveData.MODE_UP].getPoint_1_fraction());
        TestLog.myLog_w(GAMEEND, "onPause()","getPoint_2 up fraction= " + badminton_list[SaveData.MODE_UP].getPoint_2_fraction());
        TestLog.myLog_w(GAMEEND, "onPause()","getPoint_3 up fraction= " + badminton_list[SaveData.MODE_UP].getPoint_3_fraction());
        TestLog.myLog_w(GAMEEND, "onPause()","getName up = " + badminton_list[SaveData.MODE_UP].getName());
        TestLog.myLog_w(GAMEEND, "onPause()","getBitmap up = " + badminton_list[SaveData.MODE_UP].getPhoto());

        TestLog.myLog_w(GAMEEND, "onPause()","getPoint_1 down fraction= " + badminton_list[SaveData.MODE_DOWN].getPoint_1_fraction());
        TestLog.myLog_w(GAMEEND, "onPause()","getPoint_2 down fraction= " + badminton_list[SaveData.MODE_DOWN].getPoint_2_fraction());
        TestLog.myLog_w(GAMEEND, "onPause()","getPoint_3 down fraction= " + badminton_list[SaveData.MODE_DOWN].getPoint_3_fraction());
        TestLog.myLog_w(GAMEEND, "onPause()","getName down = " + badminton_list[SaveData.MODE_DOWN].getName());
        TestLog.myLog_w(GAMEEND, "onPause()","getBitmap down = " + badminton_list[SaveData.MODE_DOWN].getPhoto());
    }

    private void run_msg_working() {
        TestLog.myLog_d(GAMEEND, "run_msg_working()");

        // (Runnable) 創造出一個Runnable，　創造一個繼承Runnable　的內部類別
        Runnable runable_msg = new Runnable_Message();

        //　(handler)　1秒後叫　Runnable　做事情
        setmessage_handler.postDelayed(runable_msg, 1000);
    }


    //　(Runnable) 內部類別
    private class Runnable_Message implements Runnable {

        @Override
        public void run() {
            TestLog.myLog_w(GAMEEND, "run");

            TestLog.myLog_d(GAMEEND, "執行第 " ,index+"次");
            TestLog.myLog_d(GAMEEND, "current_point" ,current_point);
            TestLog.myLog_d(GAMEEND, "=============================");

            pick_fraction_function(index);


            //StringBuilder
            mseeage.append("第『" + String.valueOf(index) + "』局的比數 ")
                    .append(current_fraction_up + " 比 " +
                            current_fraction_down).append("\n");

            m_tv_message.setText(mseeage);
            index++;

            TestLog.myLog_d(GAMEEND, "mseeage" ,mseeage);
            TestLog.myLog_d(GAMEEND, "index++ " ,index);

            //index < 比賽局數 才在執行一次
            if(index > current_point){
                index=1;
                TestLog.myLog_e(GAMEEND, "return");
//                setmessage_handler.removeCallbacks(this);
                return;
            }

            if(index <= current_point){
                setmessage_handler.postDelayed(this, 1200);
                TestLog.myLog_d(GAMEEND, "postDelayed");
                TestLog.myLog_d(GAMEEND, "=============================");
            }
        }
    }


    //輸贏 結果動態展示
    private void anim_playEnding() {
        TestLog.myLog_d(GAMEEND, "anim_playEnding()");

        loser_handler.postDelayed(
                new Runnable() {
                    @Override
                    public void run() {

                        int a1 = 0;
                        int a2 = 0;

                        for (int i = 1; i <= current_point; i++) {
                            TestLog.myLog_d(GAMEEND, "i" ,i);

                            pick_fraction_function(i);

                            a1 += Integer.parseInt(current_fraction_up);
                            a2 += Integer.parseInt(current_fraction_down);
                        }


                        if (a1 < a2) {
                            m_loser1.setVisibility(View.VISIBLE);
                            m_iv_photo_up.setAlpha(50);


                        } else {
                            m_loser2.setVisibility(View.VISIBLE);
                            m_iv_photo_down.setAlpha(50);

                        }
                        m_btn_back.setVisibility(View.VISIBLE);
                        loser_handler.removeCallbacks(this);

                    }
                }, 5000);

    }

    private void pick_fraction_function(int typ){
        TestLog.myLog_d(GAMEEND, "pick_fraction_function()");

        switch (typ){
            case 1:
                current_fraction_up = badminton_list[SaveData.MODE_UP].getPoint_1_fraction().toString();
                current_fraction_down = badminton_list[SaveData.MODE_DOWN].getPoint_1_fraction().toString();
                break;

            case 2:
                current_fraction_up = badminton_list[SaveData.MODE_UP].getPoint_2_fraction().toString();
                current_fraction_down = badminton_list[SaveData.MODE_DOWN].getPoint_2_fraction().toString();
                break;

            case  3:
                current_fraction_up = badminton_list[SaveData.MODE_UP].getPoint_3_fraction().toString();
                current_fraction_down = badminton_list[SaveData.MODE_DOWN].getPoint_3_fraction().toString();
                break;

            default:
                return;
        }
        TestLog.myLog_w(GAMEEND, "pick_fraction_function()  typ"+typ ,current_fraction_up);
        TestLog.myLog_w(GAMEEND, "pick_fraction_function()  typ"+typ ,current_fraction_down);

    }

    //(按鈕)
    public void onclick(View view) {
        TestLog.myLog_d(GAMEEND, "onclick()");
        //成功到結算頁面：當前局數 = 最小局數
        badminton_Servicer.setCurrent_typ_point(1);

        Intent intent= new Intent(context, Badminton_main_menu.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        startActivity(intent);
        finish();
    }

    public void  shimmer(){
        TestLog.myLog_d(GAMEEND, "shimmer()");

//        mShimmerViewContainer.setAngle(ShimmerFrameLayout.MaskAngle.CW_0);
//        mShimmerViewContainer.setDuration(100);
//        mShimmerViewContainer.setBaseAlpha(0.4f);
//        mShimmerViewContainer.setDropoff(0.3f);
//        mShimmerViewContainer.setIntensity(0.6f);
//        mShimmerViewContainer.startShimmerAnimation();

        mShimmerViewContainer.setAngle(ShimmerFrameLayout.MaskAngle.CW_90);
        mShimmerViewContainer.setDuration(3000);
        mShimmerViewContainer.startShimmerAnimation();

    }



    //(禁止) 按返回鍵
    @Override
    public void onBackPressed() {
        Toast.makeText(context,res.getText(R.string.dialog_unback),Toast.LENGTH_LONG).show();
        return;
    }

    @Override
    protected void onPause() {
        super.onPause();
        TestLog.myLog_d(GAMEEND, "onPause()");
//        loser_handler.removeCallbacks();
//        setmessage_handler.removeCallbacks();
        mShimmerViewContainer.stopShimmerAnimation();
    }

    @Override
    public void onResume() {
        super.onResume();
        TestLog.myLog_d(GAMEEND, "onResume()");
        mShimmerViewContainer.startShimmerAnimation();
    }
}
