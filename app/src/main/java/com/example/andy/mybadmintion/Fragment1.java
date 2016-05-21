package com.example.andy.mybadmintion;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class Fragment1 extends FatherFragment {
private int number;

    //(預設)無參數的建構子是必需的
    public Fragment1() {}

    //Fragment1 展開 layout.fragment_1
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_1, container, false);
    }

    @Override
    public Class setNextClass() {
        return Activity2.class;
    }

    @Override
    public int setPointNumber() {

        return 1;
    }

}
