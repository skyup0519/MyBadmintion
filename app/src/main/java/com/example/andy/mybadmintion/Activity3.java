package com.example.andy.mybadmintion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Activity3 extends FatherActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
    }

    @Override
    public int set_saveData_number() {
        return 3;
    }
}
