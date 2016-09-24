package com.example.andy.app_test.json;


import com.example.andy.app_test.bean.Bean;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by user on 2016/5/24.
 */
public interface InterfaceRetrofit {
    //利用第三方程式庫 先做一個Call的方法
    @GET("/opendata/datalist/apiAccess?scope=resourceAquire&rid=ed83db24-f206-4ff0-9135-a0a8d65ace31") Call<Bean> getData();

    //建構retrofit
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://data.taipei/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    //apiservice = 建構完成後 我們將retrofit打造出來,
    public static final InterfaceRetrofit apiservice =retrofit.create(InterfaceRetrofit.class);



}
