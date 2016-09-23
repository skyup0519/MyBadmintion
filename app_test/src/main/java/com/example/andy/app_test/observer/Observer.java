package com.example.andy.app_test.observer;

/**
 * Created by user on 2016/5/25.
 */


    public interface Observer {

        public  abstract void OnCompleted(String msg);
        public abstract void OnError(String msg);
        public abstract void onFailure(String msg);
    }


