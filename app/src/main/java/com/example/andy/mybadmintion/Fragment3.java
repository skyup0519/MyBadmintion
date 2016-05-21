package com.example.andy.mybadmintion;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment3 extends FatherFragment {

    //(預設)無參數的建構子是必需的
    public Fragment3() {}


    //Fragment3 展開 layout.fragment_3
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_3, container, false);
    }

    @Override
    public Class setNextClass() {
        return Activity1.class;
    }

    @Override
    public int setPointNumber() {
        return 3;
    }

}
