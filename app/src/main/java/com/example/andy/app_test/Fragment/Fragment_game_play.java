package com.example.andy.app_test.Fragment;


import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dialog.android.phone.HelperBasePhoneDialog;
import com.dialog.android.phone.app.PhoneDialog;
import com.example.andy.app_test.Activity.Badminton_play_final;
import com.example.andy.app_test.R;
import com.example.andy.app_test.TestLog;
import com.example.andy.app_test.model.Game_badminton_servicer;
import com.example.andy.app_test.model.Main_badminton_servicer;
import com.example.andy.app_test.myapp.MyApp;


public class Fragment_game_play extends Fragment implements View.OnClickListener, AlertDialog.OnClickListener {

    private final String FRAGMENT = "fragment";

    private Resources res;



    private View view;

    private Button btn_add_fraction, btn_delete_fraction;

    private TextView tv_point1, tv_point2, tv_point3;

    private int current_fraction = 0;

    private int current_point = 1;

    private Main_badminton_servicer badminton_Servicer = MyApp.getMain_badminton_servicer();

    private final int MAX_FRACTION = badminton_Servicer.getMax_fraction();

    private final int MIN_FRACTION = badminton_Servicer.getMin_fraction();

    private final int MAX_POINT = badminton_Servicer.getMax_point();

    private final int MIN_POINT = badminton_Servicer.getMin_point();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        TestLog.myLog_d(FRAGMENT, "onCreateView()");

        view = inflater.inflate(R.layout.play_game_fragment, container, false);
        return view;
    }


    //畫面開始執行
    @Override
    public void onStart() {
        super.onStart();
        TestLog.myLog_d(FRAGMENT, "onStart()");

        init();
    }

    private void init() {


        TestLog.myLog_w(FRAGMENT, "init() MAX_FRACTION", MAX_FRACTION);
        TestLog.myLog_w(FRAGMENT, "init() MIN_FRACTION", MIN_FRACTION);
        TestLog.myLog_w(FRAGMENT, "init() MAX_POINT", MAX_POINT);
        TestLog.myLog_w(FRAGMENT, "init() MIN_POINT", MIN_POINT);

        res = getResources();

        btn_add_fraction = (Button) view.findViewById(R.id.play_game_fragment_btn_add);
        btn_delete_fraction = (Button) view.findViewById(R.id.play_game_fragment_btn_delete);

        tv_point1 = (TextView) view.findViewById(R.id.play_game_fragment_tv_point1);
        tv_point2 = (TextView) view.findViewById(R.id.play_game_fragment_tv_point2);
        tv_point3 = (TextView) view.findViewById(R.id.play_game_fragment_tv_point3);

        btn_add_fraction.setOnClickListener(this);
        btn_delete_fraction.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        TestLog.myLog_d(FRAGMENT, "onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        TestLog.myLog_d(FRAGMENT, "onPause()");
    }


    //《方法》增加分數
    private void add_fraction_function(int current_typ_point) {
        TestLog.myLog_d(FRAGMENT, "add_fraction_function()");

        //當前的分數 >= 最大分數 就return
        if (MAX_FRACTION <= current_fraction) {
            return;
        }


        switch (current_typ_point) {

            case 1:
                TestLog.myLog_d(FRAGMENT, "Add point1's fraction");

                current_fraction++;
                tv_point1.setText(String.valueOf(current_fraction));
                break;

            case 2:
                TestLog.myLog_d(FRAGMENT, "Add point2's fraction");

                current_fraction++;
                tv_point2.setText(String.valueOf(current_fraction));
                break;

            case 3:
                TestLog.myLog_d(FRAGMENT, "Add point3's fraction");

                current_fraction++;
                tv_point3.setText(String.valueOf(current_fraction));
                break;
        }
        TestLog.myLog_d(FRAGMENT,res.getString(R.string.msg_check_current_Fraction), current_fraction);




        //結束比賽→ (當前局數 = 最大局數) 且 (當前分數 => 最大分數) 執行  並return
        if(MAX_FRACTION <= current_fraction && badminton_Servicer.getCurrent_typ_point() == MAX_POINT){

            TestLog.myLog_d(FRAGMENT, "ready action intent", current_fraction);
            intent_function();
            return;
        }

        //結束本局→ (當前的分數 >= 最大分數) 執行 並return
        if (MAX_FRACTION <= current_fraction) {
            TestLog.myLog_d(FRAGMENT, "ready next point", current_fraction);

            String title = res.getString(R.string.dialog_title_nextPoint);
            String msg =res.getString(R.string.dialog_msg_nextPoint);

            // Context、標題、按鈕(右)、監聽事件、按鈕(左)、監聽事件
            PhoneDialog dialog = HelperBasePhoneDialog.createDialog(getActivity(),
                    title,
                    msg,
                    res.getString(R.string.dialog_btn_enter),
                    this,
                    res.getString(R.string.dialog_btn_cancel),
                    this);
            dialog.show();
            return;
        }

    }

    //《方法》Intent
    private void intent_function(){

        String title = res.getString(R.string.dialog_title_nextClass);
        String msg =res.getString(R.string.dialog_msg_nextClass);

        // Context、標題、按鈕(右)、監聽事件、按鈕(左)、監聽事件
        PhoneDialog dialog = HelperBasePhoneDialog.createDialog(getActivity(),
                title,
                msg,
                res.getString(R.string.dialog_btn_enter),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //跳轉
                        Intent intent = new Intent(getActivity(),Badminton_play_final.class);
                        startActivity(intent);
                        dialog.dismiss();
                        TestLog.myLog_w(FRAGMENT,"intent_function()"+res.getString(R.string.msg_check_current_Point), current_point);
                        TestLog.myLog_w(FRAGMENT,"intent_function(),getServicer"+res.getString(R.string.msg_check_current_Point),badminton_Servicer.getCurrent_typ_point());
                    }
                },
                res.getString(R.string.dialog_btn_cancel),
                this);
        dialog.show();
    }

    //《方法》減少分數
    private void delete_fraction_function(int current_typ_point) {
        TestLog.myLog_d(FRAGMENT, "delete_fraction_function()");

        //當前的分數　<= 最小分數　就 return
        if (MIN_FRACTION >= current_fraction) {
            TestLog.myLog_d(FRAGMENT, "delete return", current_fraction);
            return;
        }
        switch (current_typ_point) {

            case 1:
                TestLog.myLog_d(FRAGMENT, "減少 第一局 分數");

                current_fraction--;
                tv_point1.setText(String.valueOf(current_fraction));
                break;

            case 2:
                TestLog.myLog_d(FRAGMENT, "減少 第二局 分數");

                current_fraction--;
                tv_point2.setText(String.valueOf(current_fraction));

                break;

            case 3:
                TestLog.myLog_d(FRAGMENT, "減少 第三局 分數");

                current_fraction--;
                tv_point3.setText(String.valueOf(current_fraction));
                break;
        }
        TestLog.myLog_d(FRAGMENT, "目前的分數", current_fraction);
    }

    //《方法》交換分數
    public void change_fraction_function(int typ){
        TestLog.myLog_d(FRAGMENT, "change_fraction_function()");

        Game_badminton_servicer[] myList = badminton_Servicer.getList_service();

        String point1_fraction = myList[typ].getPoint_1_fraction();
        String point2_fraction = myList[typ].getPoint_2_fraction();
        String point3_fraction = myList[typ].getPoint_3_fraction();

        tv_point1.setText(point1_fraction);
        tv_point2.setText(point2_fraction);
        tv_point3.setText(point3_fraction);
    }

    //Button onClick
    @Override
    public void onClick(View v) {
        TestLog.myLog_d(FRAGMENT, "onClick");

        current_point = badminton_Servicer.getCurrent_typ_point();
        TestLog.myLog_w(FRAGMENT,"onClick()"+res.getString(R.string.msg_check_current_Point),current_point);

//      如果當前的局數 < 改變後的局數  就分數歸0  Start
//        int typ_check = current_point;
//        current_point = badminton_Servicer.getCurrent_typ_point();
//
//        TestLog.myLog_d(FRAGMENT, "getCurrent_typ_check", typ_check);
//        TestLog.myLog_d(FRAGMENT, "getCurrent_typ_point", current_point);
//
//        if(current_point > typ_check){
//            current_fraction = 0 ;
//        }
//      如果當前的局數 < 改變後的局數  就分數歸0   end



//      交換分數後 所做的檢查function Start  (交換分數後 先取得當前Layout的分數 再進行加減分)
        check_current_point_function(current_point);
        TestLog.myLog_d(FRAGMENT, "getCurrent_typ_point", current_point);
//      交換分數後 所做的檢查function End


        switch (v.getId()) {

            case R.id.play_game_fragment_btn_add:
                TestLog.myLog_d(FRAGMENT, "btn_add onClick()");

                //新增分數function
                add_fraction_function(current_point);
                break;

            case R.id.play_game_fragment_btn_delete:
                TestLog.myLog_d(FRAGMENT, "btn_delete  onClick()");

                //刪除分數function
                delete_fraction_function(current_point);
                break;
        }
    }

    //Dialog onClick
    @Override
    public void onClick(DialogInterface dialog, int which) {

        switch (which) {

            case DialogInterface.BUTTON_POSITIVE:
                TestLog.myLog_d(FRAGMENT, "dialog BUTTON_POSITIVE");

                //如果 當前局數 >= max局數 就return
                if (MAX_POINT <= current_point) {
                    Toast.makeText(getActivity(), "Final", Toast.LENGTH_SHORT).show();
                    return;
                }

                //當前局數++
                current_point++;

                //成功換到下局： 當前分數歸0
                current_fraction = 0;

                //成功換到下局： 記錄當前局數
                badminton_Servicer.setCurrent_typ_point(current_point);
                TestLog.myLog_d(FRAGMENT, "成功換到下局： 記錄當前局數", current_point);

                dialog.dismiss();
                break;

            case DialogInterface.BUTTON_NEGATIVE:
                TestLog.myLog_d(FRAGMENT, "dialog BUTTON_NEGATIVE");

                dialog.dismiss();

                //如果取消　就減少當局一分
                delete_fraction_function(current_point);
                break;
        }
    }

    //Fragment 執行 clean_fraction
    public void fragment_clean_current_point_fraction() {
        TestLog.myLog_d(FRAGMENT, "clean_current_point1_fraction()");

        //先取得當前的局數
        int current_typ = badminton_Servicer.getCurrent_typ_point();

        switch (current_typ) {

            case 1:
                //設定當前分數=0
                current_fraction = 0;
                tv_point1.setText(String.valueOf(current_fraction));
                TestLog.myLog_d(FRAGMENT,"Clean= " + current_fraction+ "局", "， Current_point= " + current_typ);
                break;

            case 2:
                //設定當前分數=0
                current_fraction = 0;
                tv_point2.setText(String.valueOf(current_fraction));
                TestLog.myLog_d(FRAGMENT,"Clean= " + current_fraction+ "局", "， Current_point= " + current_typ);
                break;

            case 3:
                //設定當前分數=0
                current_fraction = 0;
                tv_point3.setText(String.valueOf(current_fraction));
                TestLog.myLog_d(FRAGMENT,"Clean= " + current_fraction+ "局", "， Current_point= " + current_typ);
                break;
        }
    }


    public Game_badminton_servicer fragment_savaData_function(String name , Bitmap bit){

        String point1_str = tv_point1.getText().toString();
        String point2_str = tv_point2.getText().toString();
        String point3_str = tv_point3.getText().toString();


        Game_badminton_servicer servicer;
        servicer = new Game_badminton_servicer(point1_str,point2_str,point3_str,name,bit);

        return servicer;
    }

    /**
     * 交換分數後 所做的檢查function
     * 因為交換前的分數 可能會跟 交換後的不一樣
     * 所以先取得當前Layout的分數 再進行加減分
     */
    private void check_current_point_function(int typ_point){
        TestLog.myLog_d(FRAGMENT, "check_current_point_function()");



        //current_point = 當前的局數
        switch (typ_point){

            case 1:
                current_fraction = Integer.valueOf(tv_point1.getText().toString());
                break;
            case 2:
                current_fraction = Integer.valueOf(tv_point2.getText().toString());
                break;
            case 3:
                current_fraction = Integer.valueOf(tv_point3.getText().toString());
                break;

        }

        TestLog.myLog_w(FRAGMENT,"check_current_point_function()"+res.getString(R.string.msg_check_current_Point),current_point);
        TestLog.myLog_w(FRAGMENT,"check_current_point_function()"+res.getString(R.string.msg_check_current_Fraction),current_fraction);
    }



}
