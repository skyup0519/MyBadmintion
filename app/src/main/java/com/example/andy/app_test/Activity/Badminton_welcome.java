package com.example.andy.app_test.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.andy.app_test.R;

public class Badminton_welcome extends AppCompatActivity  {

    private ImageView iv_welcome,iv_welcome2;
    private AnimationDrawable  anim;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badminton_welcome);
        context=this;

        //取消狀態欄
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        iv_welcome = (ImageView) findViewById(R.id.activity_badminton_welcome_iv);

        iv_welcome.setBackgroundResource(R.drawable.welcome_anim);
        anim = (AnimationDrawable) iv_welcome.getBackground();
        anim.start();



    }

    @Override
    protected void onStart() {
        super.onStart();

        Toast.makeText(Badminton_welcome.this,"點擊後 開始",Toast.LENGTH_SHORT).show();
        iv_welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anim.stop();
                iv_welcome.setVisibility(View.GONE);




                //imageView 設定動畫元件(透明度調整)
                iv_welcome2 = (ImageView) findViewById(R.id.activity_badminton_welcome_iv2);
                Animation animation = AnimationUtils.loadAnimation(context, R.anim.welcome_final);
                animation.setFillEnabled(true);
                animation.setFillAfter(true);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        startActivity(new Intent(context,Badminton_main_menu.class));
                        finish();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                iv_welcome2.setAnimation(animation);

            }
        });
    }


}
