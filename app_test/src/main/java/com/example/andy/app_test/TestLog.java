package com.example.andy.app_test;

import android.util.Log;

/**
 * Created by user on 2016/9/13.
 */
public class TestLog {

    private static  boolean boolean_log_msg = true;

    private final static String TAG = "tag";

    private static int current_typ_point = 1;


    public static boolean getBoolean_log_msg() {
        return boolean_log_msg;
    }

    /**
     *
     * @param boolean_log_msg true=開啟Log訊息 ，false=關閉Log訊息
     */
    public static void setBoolean_log_msg(boolean boolean_log_msg) {
        TestLog.boolean_log_msg = boolean_log_msg;
    }


    public static void myLog_d(String Tittle , Object msg){
        if(boolean_log_msg ==false){
            return;
        }
        Log.d(TAG,Tittle + "→ " + msg.toString());
    }

    public static void myLog_d(String Tittle , Object msg ,Object data){
        if(boolean_log_msg ==false){
            return;
        }
        Log.d(TAG,Tittle + "→ " + msg.toString() + " = " + data.toString());
    }

    public static void myLog_i(String Tittle , Object msg){
        if(boolean_log_msg ==false){
            return;
        }
        Log.i(TAG,Tittle + "→ " + msg.toString());
    }

    public static void myLog_i(String Tittle , Object msg ,Object data){
        if(boolean_log_msg ==false){
            return;
        }
        Log.i(TAG,Tittle + "→ " + msg.toString() + " = " + data.toString());
    }



    public static void myLog_e(String Tittle , Object msg){
        if(boolean_log_msg ==false){
            return;
        }

        Log.e(TAG,Tittle + "→ " +msg.toString());

    }

    public static void myLog_e(String Tittle , Object msg , Object data){
        if(boolean_log_msg ==false){
            return;
        }

        Log.e(TAG,Tittle + "→ " + msg.toString() + " = " + data.toString());

    }

    public static void myLog_w(String Tittle , Object msg){
        if(boolean_log_msg ==false){
            return;
        }

        Log.w(TAG,Tittle + "→ " +msg.toString());

    }

    public static void myLog_w(String Tittle , Object msg , Object data){
        if(boolean_log_msg ==false){
            return;
        }

        Log.w(TAG,Tittle + "→ " + msg.toString() + " = " + data.toString());

    }






}
