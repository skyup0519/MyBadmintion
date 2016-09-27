package com.example.andy.app_test.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.andy.app_test.R;
import com.facebook.shimmer.ShimmerFrameLayout;

public class Badminton_welcome extends AppCompatActivity  {

    private Context context;
    private ImageView iv_welcome,iv_welcome2;
    private AnimationDrawable  anim;
    private LinearLayout layout_container_msg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badminton_welcome);
        context=this;
        findId();
        init();
        start_work();
    }

    private void findId(){
        iv_welcome2 = (ImageView) findViewById(R.id.activity_badminton_welcome_iv2);

        iv_welcome = (ImageView) findViewById(R.id.activity_badminton_welcome_iv);

        layout_container_msg = (LinearLayout)findViewById(R.id.activity_badminton_welcome_container_msg);

        mShimmerViewContainer = (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);
    }

    private void init(){

        //取消狀態欄
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        iv_welcome.setBackgroundResource(R.drawable.welcome_anim);
        anim = (AnimationDrawable) iv_welcome.getBackground();
        anim.start();
    }

    Handler handler = new Handler();
    Runnable r;
    ShimmerFrameLayout mShimmerViewContainer;
    private void start_work(){



        r= new Runnable() {
            @Override
            public void run() {
                layout_container_msg.setVisibility(View.VISIBLE);
                mShimmerViewContainer.setAngle(ShimmerFrameLayout.MaskAngle.CW_90);
                mShimmerViewContainer.setDuration(2000);
                mShimmerViewContainer.setDropoff(0.7f);
                mShimmerViewContainer.setMaskShape(ShimmerFrameLayout.MaskShape.LINEAR);
                mShimmerViewContainer.startShimmerAnimation();

            }
        };

        handler.postDelayed(r,4500);
    }


    @Override
    protected void onStart() {
        super.onStart();



        iv_welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anim.stop();
                //動畫期間 將進場動畫、開始提示(隱藏)
                iv_welcome.setVisibility(View.GONE);
                layout_container_msg.setVisibility(View.GONE);

                //imageView 設定動畫元件(透明度調整)

                Animation animation = AnimationUtils.loadAnimation(context, R.anim.welcome_final);
                animation.setFillEnabled(true);
                animation.setFillAfter(true);

                //監聽器
                animation.setAnimationListener(new Animation.AnimationListener() {

                    @Override  //動畫開始要做的事情
                    public void onAnimationStart(Animation animation) {


                    }

                    @Override  //動畫結束後要做的事情
                    public void onAnimationEnd(Animation animation) {
                        startActivity(new Intent(context,Badminton_main_menu.class));
                        finish();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                //轉場動畫
                iv_welcome2.setAnimation(animation);

            }
        });
    }


}
