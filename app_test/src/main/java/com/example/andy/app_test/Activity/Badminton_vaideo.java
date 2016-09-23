package com.example.andy.app_test.Activity;

import android.animation.ObjectAnimator;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import com.dialog.android.phone.HelperBasePhoneDialog;
import com.dialog.android.phone.app.PhoneDialog;
import com.example.andy.app_test.DrawersGod;
import com.example.andy.app_test.R;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

public class Badminton_vaideo extends DrawersGod {
    //場景變數
    private Context context;

    //資源
    private Resources res;

    //下拉式選單
    private Spinner spinner;
    private ArrayAdapter<String> adapter_spinner;
    private List<String> list_spinner;

    //Dialog
    private PhoneDialog phoneDialog;

    //神龍 (出現/隱藏Layout)
    private LinearLayout layout_god;

    //Fb閃亮亮程式碼
    private ShimmerFrameLayout mShimmerViewContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.badminton_vaideo);
        findId();

    }

    @Override
    protected void onStart() {
        super.onStart();
        spinner_work();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mShimmerViewContainer.stopShimmerAnimation();
    }

    //Drawer(抽屜) 抽象方法實做
    @Override
    public LinearLayout tagLayout() {
        LinearLayout view = (LinearLayout) findViewById(R.id.badminton_vaideo_container);
        return view;
    }

    private void findId() {
        context = this;
        res = getResources();
        spinner = (Spinner) findViewById(R.id.badminton_vaideo_spinner);
        layout_god = (LinearLayout)findViewById(R.id.badminton_vaideo_god);
    }

    private void spinner_work() {

        mShimmerViewContainer = (ShimmerFrameLayout) findViewById(R.id.badminton_vaideo_shimmer_view_container);
        mShimmerViewContainer.setDuration(5000);
        mShimmerViewContainer.setRepeatMode(ObjectAnimator.REVERSE);
        mShimmerViewContainer.startShimmerAnimation();

        //Spinner 先創造出一個陣列
        list_spinner = new ArrayList<>();
        list_spinner.add("請選擇要觀賞的影片");
        list_spinner.add("1.羽球特定球路訓練：殺球");
        list_spinner.add("2.羽球特定球路訓練：挑球");
        list_spinner.add("3.羽球特定球路訓練：小球");
        list_spinner.add("4.羽球特定球路訓練：切球");
        list_spinner.add("5.羽球特定球路訓練：平球");
        list_spinner.add("6.羽球特定球路訓練：勾球");
        list_spinner.add("7.羽球特定球路訓練：跳殺");
        list_spinner.add("8.羽球特定球路訓練：搓球");
        list_spinner.add("9.羽球特定球路訓練：封網");
        list_spinner.add("10.羽球特定球路訓練：高遠球");


        //創造一個 Adapter
        adapter_spinner = new ArrayAdapter<String>(this,R.layout.test, list_spinner);
        adapter_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Spinner連結
        spinner.setAdapter(adapter_spinner);

        //Spinner監聽器
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        Toast.makeText(context, position + "", Toast.LENGTH_LONG).show();
                        Uri uri = Uri.parse("https://youtu.be/LkieIDm06-c");
                        Intent it = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(it);
                        break;
                    case 2:
                        Toast.makeText(context, position + "", Toast.LENGTH_LONG).show();
                        Uri uri2 = Uri.parse("https://youtu.be/Is15v_DBELM");
                        Intent it2 = new Intent(Intent.ACTION_VIEW, uri2);
                        startActivity(it2);
                        break;
                    case 3:
                        Toast.makeText(context, position + "", Toast.LENGTH_LONG).show();
                        Uri uri3 = Uri.parse("https://youtu.be/LkieIDm06-c");
                        Intent it3 = new Intent(Intent.ACTION_VIEW, uri3);
                        startActivity(it3);
                        break;
                    case 4:


                        Uri uri4 = Uri.parse("https://youtu.be/mvE_2rk0KXk?list_spinner=UU0rKqImcam2LEwH4qRS8OPg");
                        Intent it4 = new Intent(Intent.ACTION_VIEW, uri4);
                        startActivity(it4);
                        break;
                    case 5:

                        Uri uri5 = Uri.parse("https://youtu.be/gh0G_WUiWBM?list_spinner=UU0rKqImcam2LEwH4qRS8OPg");
                        Intent it5 = new Intent(Intent.ACTION_VIEW, uri5);
                        startActivity(it5);

                        break;
                    case 6:

                        Uri uri6 = Uri.parse("https://youtu.be/gh0G_WUiWBM?list_spinner=UU0rKqImcam2LEwH4qRS8OPg");
                        Intent it6 = new Intent(Intent.ACTION_VIEW, uri6);
                        startActivity(it6);

                        break;
                    case 7:

                        Uri uri7 = Uri.parse("https://youtu.be/Okt21KcbJNY?list_spinner=UU0rKqImcam2LEwH4qRS8OPg");
                        Intent it7 = new Intent(Intent.ACTION_VIEW, uri7);
                        startActivity(it7);

                        break;
                    case 8:

                        Uri uri8 = Uri.parse("https://youtu.be/eT5uSgwnzu0?list_spinner=UU0rKqImcam2LEwH4qRS8OPg");
                        Intent it8 = new Intent(Intent.ACTION_VIEW, uri8);
                        startActivity(it8);

                        break;
                    case 9:

                        Uri uri9 = Uri.parse("https://youtu.be/vf_LQxLi0wY");
                        Intent it9 = new Intent(Intent.ACTION_VIEW, uri9);
                        startActivity(it9);

                        break;
                    case 10:

                        Uri uri10 = Uri.parse("https://youtu.be/yZrkGqll0gM");
                        Intent it10 = new Intent(Intent.ACTION_VIEW, uri10);
                        startActivity(it10);

                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void onclick(View view) {
        switch (view.getId()){
//            case R.id.btn_back:
//                finish();
//                break;

            case R.id.btn_ie:

                //招喚神龍
                 layout_god.setVisibility(View.VISIBLE);
                 phoneDialog = HelperBasePhoneDialog.createDialog(context,
                                                                  res.getString(R.string.dialog_tittle_god),
                                                                  res.getString(R.string.dialog_msg_god),
                                                                  res.getString(R.string.dialog_yes),
                                                                  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent2 = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                        startActivityForResult(intent2, 201);
                        phoneDialog.dismiss();
                    }
                });
                phoneDialog.show();
                break;
        }
    }

    //語音功能 回乎涵式
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //語音功能
        if(requestCode == 201 && resultCode == RESULT_OK) {
            ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            StringBuilder IE_message =new StringBuilder();
            IE_message.append("羽球 ")
                    .append(text.get(0));

            //Google功能
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY,IE_message.toString());
            startActivity(intent);
            IE_message.delete(3,IE_message.length());

        }
        //隱藏神龍
        layout_god.setVisibility(View.GONE);
    }

}
