package com.example.andy.app_test.util;

import android.content.Context;
import android.util.Log;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016/9/28.
 */
public class FireBase_Util {

    private static Firebase firebase;

    private static List<String> mylist;

    private static String URL = "https://fir-d11d6.firebaseio.com/table_msg";

    private static FireBase_OnchangeListener fireBase_onchangeListener;

    public static void  fireasefunction(Context context){



        //先設定context
        firebase.setAndroidContext(context);

        //設定網址
        firebase = new Firebase(URL);

        //確定有連線
//        Toast.makeText(context , firebase.getKey().toString(),Toast.LENGTH_SHORT).show();

//        adapter = new ArrayAdapter(context , android.R.layout.simple_list_item_1,android.R.id.text1);

        // 創造出一個陣列
        mylist = new ArrayList<>();



        //firebase 安裝監聽器
        firebase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                try{
                    mylist.add(dataSnapshot.child("data").getValue().toString());
                    Log.w("tag"," ChildAdded=  "+mylist.toString()+"");
                    fireBase_onchangeListener.onChange();

                }catch (Exception e){

                    Log.e("tag","Error ChildAdded");
                }

            }


            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                String post_str = dataSnapshot.getKey();
                int post_int = Integer.valueOf(post_str);
                Log.w("tag"," 取得的位置=  "+post_str);
                Log.w("tag"," 更換的資訊是=  "+dataSnapshot.child("data").getValue().toString());
                mylist.set(post_int-1,dataSnapshot.child("data").getValue().toString());

                try{
                    mylist.set(post_int-1,dataSnapshot.child("data").getValue().toString());
                    Log.w("tag"," ChildChanged=  "+mylist.toString()+"");


                }catch (Exception e){

                    Log.e("tag","Error Changed");
                }

                fireBase_onchangeListener.onChange();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                try{
                    mylist.remove(dataSnapshot.child("data").getValue().toString());
                    fireBase_onchangeListener.onChange();
                }catch (Exception e){
                    Log.e("tag","Error Removed");
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    public static List<String> getMylist() {
        return mylist;
    }

    public static void setMylist(List<String> mylist) {
        FireBase_Util.mylist = mylist;
    }



    public interface FireBase_OnchangeListener{

       void onChange();

    }


    public static void setFireBase_onchangeListener(FireBase_OnchangeListener myfireBase_onchangeListener) {
       fireBase_onchangeListener = myfireBase_onchangeListener;
    }

}
