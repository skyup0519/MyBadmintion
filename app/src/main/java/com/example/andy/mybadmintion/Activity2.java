package com.example.andy.mybadmintion;

import android.os.Bundle;
import android.widget.Toast;

public class Activity2 extends FatherActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
    }

    @Override
    public int set_saveData_number() {
        Toast.makeText(this, "到了Activity2", Toast.LENGTH_LONG).show();
        return 2;
    }
}
