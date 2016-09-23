package com.example.andy.app_test.myapp;

import android.app.Application;
import android.content.Context;

import com.example.andy.app_test.model.Getter_SportBean_service;
import com.example.andy.app_test.model.Main_badminton_servicer;


/**
 * Created by user on 2016/5/21.
 */
public class MyApp extends Application {
    private static Context context;


    //Static，放在這邊給需要用到的人參考
    //為什麼給myapp管理，因為不要給需要的人 new出來，類似簡單工廠方法但又不是
    //Main_badminton_servicer 負責記錄分數，然後由myapp管理
    private static Main_badminton_servicer main_badminton_servicer;

    //Getter_SportBean_service 負責管理分數，然後由myapp管理
    private static Getter_SportBean_service getterSportBeanservice;




    public MyApp() {
        context=this;
    }

    public static Context getContext() {
        return context;
    }

    public static Getter_SportBean_service getGetterSportBeanservice() {
        return getterSportBeanservice;
    }


    public static void setGetterSportBeanservice(Getter_SportBean_service getterSportBeanservice) {
        MyApp.getterSportBeanservice = getterSportBeanservice;
    }

    //現在 Main_badminton_servicer 由 myapp管理
    public static Main_badminton_servicer getMain_badminton_servicer() {
       if(main_badminton_servicer ==null){
        main_badminton_servicer = new Main_badminton_servicer();
       }
        return main_badminton_servicer;
    }

    public static void setMain_badminton_servicer(Main_badminton_servicer main_badminton_servicer) {
        MyApp.main_badminton_servicer = main_badminton_servicer;
    }



}
