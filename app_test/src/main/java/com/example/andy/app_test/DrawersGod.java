package com.example.andy.app_test;

import android.annotation.TargetApi;
import android.content.Context;
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

public abstract class DrawersGod extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener

{

    private Context context;
    private LinearLayout layout_mainMenu;
    private final String DRAWER_GOD = "drawersGod";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TestLog.myLog_d(DRAWER_GOD,"onCreate()");
        context=this;

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

        menu.add(11,2,Menu.NONE,"結束");
        menu.add(11,3,Menu.NONE,"確定結束");

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




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Toast.makeText(context,"onNavigationItemSelected()",Toast.LENGTH_SHORT).show();

        switch (item.getItemId()){

            case R.id.nav_camera:
                Toast.makeText(context,item.getItemId(),Toast.LENGTH_SHORT).show();
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.SCREEN_ORIENTATION_CHANGED);

                break;

            case R.id.nav_gallery:
                Toast.makeText(context,item.getItemId(),Toast.LENGTH_SHORT).show();
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.SCREEN_BRIGHTNESS_CHANGED);

                break;

            case R.id.nav_slideshow:
                Toast.makeText(context,item.getItemId(),Toast.LENGTH_SHORT).show();
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON);

                break;

            case R.id.nav_manage:
                Toast.makeText(context,item.getItemId(),Toast.LENGTH_SHORT).show();
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);

                break;

            case R.id.nav_share:
                Toast.makeText(context,item.getItemId(),Toast.LENGTH_SHORT).show();
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

                break;

            case R.id.nav_send:
                Toast.makeText(context,item.getItemId(),Toast.LENGTH_SHORT).show();
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

                break;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

}
