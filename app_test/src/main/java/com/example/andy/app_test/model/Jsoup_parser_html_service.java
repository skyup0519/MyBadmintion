package com.example.andy.app_test.model;


import com.example.andy.app_test.TestLog;
import com.example.andy.app_test.myapp.MyApp;

import java.io.IOException;
import java.util.List;

import badminton.BadmintonUtil;

/**
 * Created by user on 2016/9/15.
 */

//用Jsoup來抓取 Html資料
public class Jsoup_parser_html_service extends Thread {

    private  final String JSOUP_SERVICE = "Jsoup_parser_html_service";
    private List<String> list;

    @Override
    public void run() {

        try {

            //使用 自創lib抓取資料 存到list中
            list = BadmintonUtil.get_Badminyon(true);

            //Log 開關
            TestLog.setBoolean_log_msg(true);

            //Log list大小()
            TestLog.myLog_d(JSOUP_SERVICE,"list SIZE",list.size()+"");

            //Log出訊息
            for(int i =0 ; i<list.size() ; i++){

                TestLog.myLog_d(JSOUP_SERVICE,"URL= "+i,list.get(i));
            }

            Main_badminton_servicer myServicer =  MyApp.getMain_badminton_servicer();
            myServicer.setViewpageUrl(list);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
