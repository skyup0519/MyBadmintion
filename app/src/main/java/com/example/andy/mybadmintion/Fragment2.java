package com.example.andy.mybadmintion;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends FatherFragment {

    //(預設)無參數的建構子是必需的
    public Fragment2() {}


    //Fragment2 展開 layout.fragment_2
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_2, container, false);
    }

    @Override
    public Class setNextClass() {
        return Activity3.class;
    }

    @Override
    public int setPointNumber() {
        return 2;
    }

}
