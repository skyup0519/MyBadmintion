package com.example.andy.app_test;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dialog.android.phone.HelperBasePhoneDialog;
import com.dialog.android.phone.app.PhoneDialog;

public abstract class DrawersGod extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener


{

    private final String DRAWER_GOD = "drawersGod";

    private Context context;

    private Resources res;

    private LinearLayout layout_mainMenu;

    private PhoneDialog phoneDialog;

    private Intent intent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TestLog.myLog_d(DRAWER_GOD,"onCreate()");
        context=this;
        res = getResources();

    }

    @TargetApi(Build.VERSION_CODES.KITKAT_WATCH)
    @Override
    protected void onStart() {
        super.onStart();
        TestLog.myLog_d(DRAWER_GOD,"onStart()");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //設定icon 為原圖顏色
        navigationView.setItemTextColor(null);
        navigationView.setItemIconTintList(null);

        intent = new Intent();

        layout_mainMenu = tagLayout();
        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                TestLog.myLog_d(DRAWER_GOD,"onDrawerSlide()");
                TestLog.myLog_w(DRAWER_GOD,"onDrawerSlide() slideOffset",slideOffset);


                if (slideOffset>=1){
                    layout_mainMenu.setVisibility(View.INVISIBLE);
                }else {
                    layout_mainMenu.setVisibility(View.VISIBLE);
                }
                Float f = (1f-slideOffset)+0.2f;
                TestLog.myLog_d(DRAWER_GOD,"onDrawerSlide() f",f);
                layout_mainMenu.setAlpha(f);
            }

            @Override
            public void onDrawerOpened(View drawerView) {

                TestLog.myLog_d(DRAWER_GOD,"onDrawerOpened()");

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                TestLog.myLog_d(DRAWER_GOD,"onDrawerClosed()");

            }

            @Override
            public void onDrawerStateChanged(int newState) {
                TestLog.myLog_d(DRAWER_GOD,"onDrawerStateChanged()");
            }
        });

    }


    public abstract LinearLayout tagLayout();


    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

            TestLog.myLog_d(DRAWER_GOD,"onBackPressed()","START close");

        } else {
            super.onBackPressed();

            TestLog.myLog_d(DRAWER_GOD,"onBackPressed()","START Open");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(00,0, Menu.NONE,"開啟-全屏");
        menu.add(00,1,Menu.NONE,"關閉-全屏");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
View view = findViewById(R.id.content_view_up);


        switch (item.getItemId()){

            case 0:  // 開啟-全屏
                Toast.makeText(context,"全螢幕",Toast.LENGTH_SHORT).show();
                view.setVisibility(View.GONE);
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                break;
            case 1:  //  關閉-全屏
                Toast.makeText(context,"關閉全螢幕",Toast.LENGTH_SHORT).show();
                view.setVisibility(View.VISIBLE);
                WindowManager.LayoutParams attrs = getWindow().getAttributes();
                attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
                getWindow().setAttributes(attrs);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
                break;

            case 2:
                Toast.makeText(context,"item"+item.getTitle().toString(),Toast.LENGTH_SHORT).show();
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                break;

            case 3:
                Toast.makeText(context,"item"+item.getTitle().toString(),Toast.LENGTH_SHORT).show();
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.CONTENTS_FILE_DESCRIPTOR);
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    private final String FACEBOOK = "com.facebook.katana";
    private final String YOUTUBE  = "com.google.android.youtube";
    private final String BADMINTION_ENG  = "com.giraffegames.realbadminton";
    private final String BADMINTION_CH   = "com.sandy.superbadminton";
    private final String BADMINTION_GAME = "com.badminton.free";
    private final String TBIKE     = "lin.melvin.tbike";
    private final String TAIPEI_GO = "commuter.lin.melvin.commuter";

//    //傳送訊息
//    intent = new Intent();
//    intent.setType("text/plain");
//    intent.setAction(Intent.ACTION_SEND);
//    intent.putExtra( "key" ,  " Success" );
//    intent.setPackage("com.facebook.katana");
//    startActivity(intent);

private String mode_navigationItem_seked = "";



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {



        TestLog.myLog_d(DRAWER_GOD,"onNavigationItemSelected()",item.getTitle());
        switch (item.getItemId()){


            case R.id.nav_fb:

                 //設定狀態
                 mode_navigationItem_seked = FACEBOOK;

                break;

            case R.id.nav_youTube:
                //設定狀態
                mode_navigationItem_seked = YOUTUBE;


                 break;

//            case R.id.nav_3d_eng:
//                //設定狀態
//                mode_navigationItem_seked = BADMINTION_ENG;
//
//                break;

            case R.id.nav__3d_ch:
                //設定狀態
                mode_navigationItem_seked = BADMINTION_CH;

                break;

            case R.id.nav_3d_game:
                //設定狀態
                mode_navigationItem_seked = BADMINTION_GAME;

                break;

            case R.id.nav_bike:
                //設定狀態
                mode_navigationItem_seked = TBIKE;

                break;

            case R.id.nav_taipeiGo:
                //設定狀態
                mode_navigationItem_seked = TAIPEI_GO;

                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        android.app.AlertDialog.OnClickListener dialogListener = new Drawers_dialog();
        try
        {
            TestLog.myLog_d(DRAWER_GOD,"onNavigationItemSelected()","try");
            intent = getPackageManager().getLaunchIntentForPackage(mode_navigationItem_seked);
            startActivity(intent);

        }
        catch (Exception e)
        {
            phoneDialog = HelperBasePhoneDialog.createDialog(context,
                    res.getString(R.string.dialog_tittle_drawer)+" "+item.getTitle()+"』",
                    res.getString(R.string.dialog_msg_drawer),
                    res.getString(R.string.dialog_yes),
                    dialogListener,
                            res.getString(R.string.dialog_no),
                    dialogListener);
            phoneDialog.show();

        }

        return true;
    }

    //Dialog.OnClickListener Drawer專屬
    private class Drawers_dialog implements DialogInterface.OnClickListener{

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            switch (i){
                case DialogInterface.BUTTON_POSITIVE:
                    phoneDialog.dismiss();

                    try {
                        TestLog.myLog_e(DRAWER_GOD,"Dialog onClick()","try");
                        // Open app with Google Play app
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+mode_navigationItem_seked));
                        startActivity(intent);

                    }catch (Exception e){

                        TestLog.myLog_e(DRAWER_GOD,"Dialog onClick()","catch");
                    }
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    phoneDialog.dismiss();

                    break;
            }
        }
    }


}
