package com.example.andy.mybadmintion;

import android.os.Bundle;
import android.widget.Toast;

public class Activity1 extends FatherActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //預設 setContentView(R.layout.activity_father);
        setContentView(R.layout.activity_1);

    }


    @Override
    public int set_saveData_number() {
        Toast.makeText(this,"到了Activity1",Toast.LENGTH_LONG).show();
        return 1;
    }
}
