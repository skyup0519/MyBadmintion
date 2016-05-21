package com.example.andy.mybadmintion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public abstract class FatherActivity extends AppCompatActivity {
    private String scores_上 = "0";
    private String scores_下 = "0";
    private FatherFragment fatherFragment;
    private int gamePoint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //預設 setContentView(R.layout.activity_father);

    }


    @Override
    protected void onStart() {
        gamePoint = set_saveData_number();
        super.onStart();
        initialOptions(gamePoint);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //一定是Activity快要結束時，才要做資料的存放(一開始是沒有資料的)
        saveData(gamePoint);
    }

    private void saveData(int gamePoint) {

        //先找到Activity的片段(分別找到片段1、2)


        switch (gamePoint) {

            case 1:
                //在第一局的時候，我先找到Fragment1 的片段
                Fragment1 Fragment1_上 = (Fragment1) getSupportFragmentManager().findFragmentById(R.id.fragment1_1);
                Fragment1 Fragment1_下 = (Fragment1) getSupportFragmentManager().findFragmentById(R.id.fragment1_2);

                //並分別找到 片段『第一局』TextView 存在欄位的分數
                scores_上 = Fragment1_上.get_m_tv_fraction1().getText().toString();
                scores_下 = Fragment1_下.get_m_tv_fraction1().getText().toString();

                //把分數存在myapp中(生命週期較長)
                myapp.setPoint_1_Number上(scores_上);
                myapp.setPoint_1_Number下(scores_下);
                Toast.makeText(this, "Activity1 save資料" + "scores_上=" + scores_上 + ", scores_下= " + scores_下, Toast.LENGTH_LONG).show();

                break;

            case 2:
                //在第二局的時候，我先找到Fragment1 的片段
                Fragment2 Fragment2_上 = (Fragment2) getSupportFragmentManager().findFragmentById(R.id.fragment2_1);
                Fragment2 Fragment2_下 = (Fragment2) getSupportFragmentManager().findFragmentById(R.id.fragment2_2);

                //並分別找到 片段1、2『第二局』TextView 存在欄位的分數
                scores_上 = Fragment2_上.get_m_tv_fraction2().getText().toString();
                scores_下 = Fragment2_下.get_m_tv_fraction2().getText().toString();

                //把分數存在myapp中(生命週期較長)
                myapp.setPoint_2_Number上(scores_上);
                myapp.setPoint_2_Number下(scores_下);
                Toast.makeText(this, "Activity2 save資料" + "scores_上=" + scores_上 + ", scores_下= " + scores_下, Toast.LENGTH_LONG).show();

                break;

            case 3:
                //在第三局的時候，我先找到Fragment1 的片段
                Fragment3 Fragment3_上 = (Fragment3) getSupportFragmentManager().findFragmentById(R.id.fragment3_1);
                Fragment3 Fragment3_下 = (Fragment3) getSupportFragmentManager().findFragmentById(R.id.fragment3_2);

                //並分別找到 片段1、2『第二局』TextView 存在欄位的分數
                scores_上 = Fragment3_上.get_m_tv_fraction3().getText().toString();
                scores_下 = Fragment3_下.get_m_tv_fraction3().getText().toString();

                //把分數存在myapp中(生命週期較長)
                myapp.setPoint_3_Number上(scores_上);
                myapp.setPoint_3_Number下(scores_下);
                Toast.makeText(this, "Activity3 save資料" + "scores_上=" + scores_上 + ", scores_下= " + scores_下, Toast.LENGTH_LONG).show();

                break;

            //如果往後還需要增加比賽局數　我只要在這邊修改程式就可以囉


        }


    }

    private void initialOptions(int gamePoint) {
        if (gamePoint == 3) {
          initialOption3();
        }
        else if(gamePoint==2){
          initialOption2();
        }


    }


    private void initialOption2() {

        Toast.makeText(this, "Activity2　得到初值" + myapp.getPoint_1_Number上() + "，" + myapp.getPoint_1_Number下(), Toast.LENGTH_LONG).show();
        Fragment2 Fragment2_上 = (Fragment2) getSupportFragmentManager().findFragmentById(R.id.fragment2_1);
        Fragment2 Fragment2_下 = (Fragment2) getSupportFragmentManager().findFragmentById(R.id.fragment2_2);
        Fragment2_上.setInit_Point1(myapp.getPoint_1_Number上());
        Fragment2_下.setInit_Point1(myapp.getPoint_1_Number下());
        Toast.makeText(this, " Activity2→onStart() 初值　成功", Toast.LENGTH_LONG).show();
    }

    private void initialOption3() {

        Toast.makeText(this, "Activity3　得到初值" + myapp.getPoint_2_Number上() + "，" + myapp.getPoint_2_Number下(), Toast.LENGTH_LONG).show();
        Fragment3 Fragment2_上 = (Fragment3) getSupportFragmentManager().findFragmentById(R.id.fragment3_1);
        Fragment3 Fragment2_下 = (Fragment3) getSupportFragmentManager().findFragmentById(R.id.fragment3_2);
        Fragment2_上.setInit_Point1(myapp.getPoint_1_Number上());
        Fragment2_下.setInit_Point1(myapp.getPoint_1_Number下());
        Fragment3 Fragment3_上 = (Fragment3) getSupportFragmentManager().findFragmentById(R.id.fragment3_1);
        Fragment3 Fragment3_下 = (Fragment3) getSupportFragmentManager().findFragmentById(R.id.fragment3_2);
        Fragment3_上.setInit_Point2(myapp.getPoint_2_Number上());
        Fragment3_下.setInit_Point2(myapp.getPoint_2_Number下());
        Toast.makeText(this, " Activity3→onStart() 初值　成功", Toast.LENGTH_LONG).show();
    }


    //做一個初值設定用
    public abstract int set_saveData_number();

    public void onclick(View view) {

    }
}

