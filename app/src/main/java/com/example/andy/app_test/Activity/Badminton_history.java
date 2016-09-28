package com.example.andy.app_test.Activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.andy.app_test.DrawersGod;
import com.example.andy.app_test.R;
import com.example.andy.app_test.TestLog;
import com.example.andy.app_test.util.FireBase_Util;

import java.util.List;


public class Badminton_history extends DrawersGod implements FireBase_Util.FireBase_OnchangeListener  {
    private TextView tv_msg;
    private Context context;
    private final String BADMINTON_HISTORY ="Badminton gane start";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.badminton_history);
        findId();
        go();
    }

    @Override
    public LinearLayout tagLayout() {
        LinearLayout layout = (LinearLayout)findViewById(R.id.badminton_history_container);
        return layout;
    }

    private void findId() {
        context = this;
        tv_msg = (TextView) findViewById(R.id.tv_history);

    }

    private void go() {
        //Firebase start
        FireBase_Util.fireasefunction(context);
        FireBase_Util.setFireBase_onchangeListener(this);

    }


    public void onclick(View view) {
        finish();
    }

    @Override
    public void onChange() {
        TestLog.myLog_d(BADMINTON_HISTORY, "history_onChange() Listener");

        List<String> mylist = FireBase_Util.getMylist();
        TestLog.myLog_d(BADMINTON_HISTORY, "onChange() mylist",mylist);

        if(mylist == null){
            TestLog.myLog_e(BADMINTON_HISTORY, "mylist return");
            return;
        }

        tv_msg.setText(mylist.get(1).toString());

    }
}
