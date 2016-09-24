package com.example.andy.app_test.util;


import com.example.andy.app_test.TestLog;
import com.example.andy.app_test.bean.Bean;
import com.example.andy.app_test.json.InterfaceRetrofit;
import com.example.andy.app_test.model.Getter_SportBean_service;
import com.example.andy.app_test.myapp.MyApp;
import com.example.andy.app_test.observer.Observer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user on 2016/5/25.
 */
public class SportOpenDateUtil {
public static final String OPENDATAUTIL = "SportOpenDateUtil";

    //觀察者模式：載入資料 只要跟我訂閱報紙 就可以取得資料
    public static void loadDate( final Observer observer){



        //apiservice=我們打造出的retrofit
        //apiService.getTaipeiAttractionBean() 可以想做是讀取資料
        Call<Bean> call = InterfaceRetrofit.apiservice.getData();

        //由call建立一個執行緒(並響應一個Callback的事件，@Override出兩個方法)
        //建立一個新的執行緒，確保下載資料的時候app不會卡死
        call.enqueue(new Callback<Bean>() {
            @Override
            public void onResponse(Call<Bean> call, Response<Bean> response) {
                TestLog.myLog_d(OPENDATAUTIL,"onResponse()","網路目前連線成功");
                  //如果我成功抓取資料
                if(response.isSuccessful()){

                    //將下載的資料 由sport_center_bean接起來
                    Bean sport_center_bean =response.body();

                    //下載完成馬上把資料紀錄在myapp中
                    MyApp.setGetterSportBeanservice(new Getter_SportBean_service(sport_center_bean));

                    //(日誌)確認有幾個
                    TestLog.myLog_d(OPENDATAUTIL,"count",sport_center_bean.getResult().getCount());

                    //做一個 讀取日誌的方法
                    testLog_sport_centerData(sport_center_bean);

                    //(日誌) 下載成功
                    TestLog.myLog_d(OPENDATAUTIL,"onResponse()","網路目前連線成功 + 下載成功");
                    observer.OnCompleted("");//◎告訴訂閱者下載完成

                }
                else {
                    //(日誌) 下載失敗
                    TestLog.myLog_e(OPENDATAUTIL,"onResponse()","網路目前連線成功 + 但下載失敗");
                    observer.OnError("下載失敗");//◎告訴訂閱者失敗(message)

                    return;
                }
            }

            @Override
            public void onFailure(Call<Bean> call, Throwable t) {
                TestLog.myLog_e(OPENDATAUTIL,"目前沒有網路歐" );
                observer.onFailure("");
            }
        });}


        //輸入"陣列" 我就執行內容中的程式
    public static void testLog_sport_centerData(Bean bean) {


        for (int i = 0; i < bean.getResult().getCount(); i++) {
            //先取得Bean裡面的count(共有10個 由count可得知)
            Bean.ResultBean.ResultsBean Sport_Center = bean.getResult().getSport_Centers().get(i);


            //Log 很重要的是 你要加一個 "" 讓輸出的資訊轉換成為文字

            TestLog.myLog_d(OPENDATAUTIL,"----------------------" + i+1 + "----------------------" );
            TestLog.myLog_d(OPENDATAUTIL,Sport_Center.get_id() );
            TestLog.myLog_d(OPENDATAUTIL,  Sport_Center.getO_tlc_agency_name());
            TestLog.myLog_d(OPENDATAUTIL, Sport_Center.getO_tlc_agency_opentime());
            TestLog.myLog_d(OPENDATAUTIL, Sport_Center.getO_tlc_agency_address());
            TestLog.myLog_d(OPENDATAUTIL, Sport_Center.getO_tlc_agency_phone());
            TestLog.myLog_d(OPENDATAUTIL, Sport_Center.getO_tlc_agency_fax());
            TestLog.myLog_d(OPENDATAUTIL, Sport_Center.getO_tlc_agency_email());


        }
    }


}
