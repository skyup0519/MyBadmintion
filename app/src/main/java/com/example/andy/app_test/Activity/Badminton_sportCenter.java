package com.example.andy.app_test.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dialog.android.phone.HelperBasePhoneDialog;
import com.dialog.android.phone.app.PhoneDialog;
import com.example.andy.app_test.Adaptor.RecyclerViewAdapter_sportCenter;
import com.example.andy.app_test.DrawersGod;
import com.example.andy.app_test.R;
import com.example.andy.app_test.TestLog;
import com.example.andy.app_test.observer.Observer;
import com.example.andy.app_test.util.SportOpenDateUtil;


public class Badminton_sportCenter extends DrawersGod implements Observer,AlertDialog.OnClickListener {

    private static final String SPORT_MENU = "Badminton_sport_menu";
    private RecyclerView m_rv_attractions;
    private RecyclerViewAdapter_sportCenter Adapter_recycler;
    private Context context;
    private Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TestLog.myLog_d(SPORT_MENU,"onCreate()");
        setContentView(R.layout.badminton_sport_center);

        //SportOpenDateUtil.loadDate(我需要一個Observer)
        //Badminton_sportCenter 繼承Observer 所以本身就是Observer，所以輸入this即可
        SportOpenDateUtil.loadDate(this);

        init();

        dialog_process = HelperBasePhoneDialog.createProgressDialog(context,res.getString(R.string.process_msg));
        dialog_process.show();


    }

    @Override
    public LinearLayout tagLayout() {
        LinearLayout layout = (LinearLayout)findViewById(R.id.badminton_sport_center_container);
        return layout;
    }

    @Override
    protected void onResume() {
        super.onResume();
        TestLog.myLog_d(SPORT_MENU,"onResume()");
        //onPause back to onResume



    }

    @Override
    protected void onPause() {
        super.onPause();
        TestLog.myLog_d(SPORT_MENU,"onPause()");

    }

    //初始化
    private void init() {
        TestLog.myLog_d(SPORT_MENU,"init()");

        context = this;
        res = getResources();
//        //先找到 RecyclerView的id
        m_rv_attractions = (RecyclerView) findViewById(R.id.rv_recyclerViews);
//
//        //設定每一個 RecyclerView的結構都相同
        m_rv_attractions.setHasFixedSize(true);
//
//        //創造出一個線性布局的管理者
        LinearLayoutManager llm = new LinearLayoutManager(context);
//
//        //設定每一個item的 Orientation(方向) 為垂直(VERTICAL)
        llm.setOrientation(LinearLayoutManager.VERTICAL);

//        GridLayoutManager grid = new GridLayoutManager(this,2);

        // 設定到佈局管理者中
        m_rv_attractions.setLayoutManager(llm);


        // 建立 & 設定 資料轉接器
        Adapter_recycler = new RecyclerViewAdapter_sportCenter();

        m_rv_attractions.setAdapter(Adapter_recycler);
    }

    public void onclick(View view) {
        switch (view.getId()) {

            case R.id.item_recycler_view_tv_center_MAP:
                TextView m_tv_center_MAP = (TextView) view;
                String positon_MAP = (String) m_tv_center_MAP.getTag();
                if (positon_MAP == null){return;}
                TestLog.myLog_d(SPORT_MENU,"tag2　positon_MAP", positon_MAP+"");
                //google_MAP位置
                Uri gmmIntentUri_MAP = Uri.parse("geo:0,0?q=" + positon_MAP+"&z=5");
                Intent mapIntent_MAP = new Intent(Intent.ACTION_VIEW, gmmIntentUri_MAP);
                mapIntent_MAP.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent_MAP);

                break;
            //google_MAP_導航
            case R.id.item_recycler_view_tv_center_導航:
                TextView m_tv_center_導航 = (TextView) view;
                String positon_導航 = (String) m_tv_center_導航.getTag();
                if (positon_導航==null){return;}
                TestLog.myLog_d(SPORT_MENU,"tag　positon_導航 ", positon_導航);
                Uri gmmIntentUri_導航 = Uri.parse("google.navigation:q=" + positon_導航 + ",+Sydney+台灣");
                Intent mapIntent_導航 = new Intent(Intent.ACTION_VIEW, gmmIntentUri_導航);
                mapIntent_導航.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent_導航);
                break;

            case R.id.item_recycler_view_tv_center_phone:
                TextView m_tv_center_phone = (TextView) view;
                String number = m_tv_center_phone.getText().toString();
                TestLog.myLog_d(SPORT_MENU,"tag　number ", number+"");
                if (number.substring(4).equals("")){return;}


                Uri uri_phone = Uri.parse("tel:" + number.substring(3));
                Intent it_phone = new Intent(Intent.ACTION_DIAL, uri_phone);
                startActivity(it_phone);

                break;
            case R.id.item_recycler_view_tv_center_mail:
                TextView m_tv_center_mail = (TextView) view;
                String mail = m_tv_center_mail.getText().toString();

                if (mail.substring(8).equals("")){return;}
                TestLog.myLog_d(SPORT_MENU, "tag　mail ", mail+"");
                Uri uri_mail = Uri.parse("mailto:" + mail.substring(7));
                Intent it_mail = new Intent(Intent.ACTION_SENDTO, uri_mail);
                startActivity(it_mail);
                break;
        }

    }

    private PhoneDialog dialog_process;
    private Runnable run_failure;
    //按鈕點擊狀態
    private int current_mode_typ;
    private final int MODE_INITIAL = 0;
    private final int MODE_BACK_MENU = 111;
    private final int MODE_UTIL_ERROR = 222;
    private final int MODE_UTIL_SCUESS = 333;

    @Override  //下載成功
    public void OnCompleted(String msg) {
        TestLog.myLog_d(SPORT_MENU, "OnCompleted");
        Adapter_recycler.notifyDataSetChanged();


        TestLog.myLog_w(SPORT_MENU, "Observer() down scuess");
        dialog_process.dismiss();

        String title = res.getString(R.string.dialog_title_scuess);
        msg = res.getString(R.string.dialog_msg_scuess);

        // Context、標題、按鈕(中)、監聽事件
        PhoneDialog dialog = HelperBasePhoneDialog.createDialog(context,
                title,
                msg,
                res.getString(R.string.dialog_btn_enter),
                this);
        dialog.show();

    }

    @Override  //下載失敗
    public void OnError(String msg) {
        TestLog.myLog_d(SPORT_MENU, "OnError");

        TestLog.myLog_e(SPORT_MENU, "Observer() down error");
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

        TestLog.myLog_e(SPORT_MENU, "Observer() onFailure ");

        run_failure = new Runnable() {
            @Override
            public void run() {
                onFailure_function(run_failure);
            }
        };

        Handler handler = new Handler();
        handler.postDelayed(run_failure,3300);
    }

    private void onFailure_function(Runnable r){
        //關閉 Dialog
        dialog_process.dismiss();

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

    @Override
    public void onClick(DialogInterface dialog, int which) {

        TestLog.myLog_d(SPORT_MENU, "AlertDialog onClick");
        TestLog.myLog_w(SPORT_MENU, "current_mode_typ",current_mode_typ);

        switch (which){


            case DialogInterface.BUTTON_POSITIVE:
                if(MODE_BACK_MENU == current_mode_typ){
                    TestLog.myLog_d(SPORT_MENU, "AlertDialog onClick","Posit MODE_BACK_MENU");
                    finish();
                    dialog.dismiss();
                    return;
                }

                if(MODE_UTIL_ERROR == current_mode_typ){
                    TestLog.myLog_d(SPORT_MENU, "AlertDialog onClick","Posit MODE_UTIL_ERROR");
                    return;
                }

                if(MODE_UTIL_SCUESS == current_mode_typ){
                    TestLog.myLog_d(SPORT_MENU, "AlertDialog onClick","Posit MODE_UTIL_SCUESS");
                    dialog.dismiss();
                    return;
                }


                TestLog.myLog_d(SPORT_MENU, "AlertDialog onClick","Posit other");
                dialog.dismiss();
                break;

            case DialogInterface.BUTTON_NEGATIVE:
                if(MODE_BACK_MENU == current_mode_typ){
                    TestLog.myLog_d(SPORT_MENU, "AlertDialog onClick","nega MODE_BACK_MENU");

                    dialog.dismiss();
                    return;
                }

                if(MODE_UTIL_ERROR == current_mode_typ){
                    TestLog.myLog_d(SPORT_MENU, "AlertDialog onClick","nega MODE_BACK_MENU");
                    return;
                }

                if(MODE_UTIL_SCUESS == current_mode_typ){
                    TestLog.myLog_d(SPORT_MENU, "AlertDialog onClick","nega MODE_UTIL_SCUESS");
                    return;
                }


                TestLog.myLog_d(SPORT_MENU, "AlertDialog onClick","nega other");
                dialog.dismiss();
                break;

        }
        //狀態歸0
        current_mode_typ = MODE_INITIAL;
    }

}
