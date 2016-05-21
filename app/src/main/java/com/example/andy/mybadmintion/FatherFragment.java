package com.example.andy.mybadmintion;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public  abstract class FatherFragment extends Fragment {
    //按鈕 響應的事件，我們用JAVA程式碼完成
    private Button m_btn_加;
    private Button m_btn_減;

    //增加、減少、初值設定分數 都需要改變 TextView
    private TextView m_tv_fraction1;
    private TextView m_tv_fraction2;
    private TextView m_tv_fraction3;


    //比賽的局數(正在第幾局)
    private int gamePoint;


    private int fraction;




    public FatherFragment() {
        // Required empty public constructor
    }


    @Override
    public abstract View  onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState);
//    {
//        // 原本：return inflater.inflate(R.layout.fragment_father, container, false);
//        return inflater.inflate(R.layout.fragment_father, container, false);
//    }


    //畫面開始執行
    @Override
    public void onStart() {
        super.onStart();
        gamePoint =setPointNumber();
        Toast.makeText(getActivity(),"Fragment→onStart，設定分數的case="+ gamePoint,Toast.LENGTH_LONG).show();
        findId();
        onClickPoint(gamePoint);
//        switch (gamePoint) {
//            case 1:
//                onClickPoint_1();
//                break;
//            case 2:
//                onClickPoint_2();
//                break;
//
//            case 3:
//                onClickPoint_3();
//                break;
//        }
    }


    private void findId() {

        m_btn_加 = (Button) getView().findViewById(R.id.btn_加);
        m_btn_減 = (Button) getView().findViewById(R.id.btn_減);
        m_tv_fraction1 = (TextView) getView().findViewById(R.id.tv_fraction1);
        m_tv_fraction2 = (TextView) getView().findViewById(R.id.tv_fraction2);
        m_tv_fraction3 = (TextView) getView().findViewById(R.id.tv_fraction3);

    }
    //點擊 第局
    private void onClickPoint(int gamePoint) {

        TextView test =null;
        switch (gamePoint) {
            case 1:
             test  = m_tv_fraction1;
                break;

            case 2:
                test  = m_tv_fraction2;
                break;

            case 3:
                test  = m_tv_fraction3;
                break;
        }


        Toast.makeText(getActivity(),"Fragment1 開始設定第一局分數",Toast.LENGTH_LONG).show();

        final TextView finalTest = test;
        m_btn_加.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlerDialogListener listener = new AlerDialogListener();


                fraction = Integer.parseInt(finalTest.getText().toString());

                if (fraction == 5) {

                    new AlertDialog.Builder(getActivity())
                            .setMessage("請問是否結束這局?")
                            .setPositiveButton("確定", listener)
                            .setNegativeButton("取消", listener)
                            .show();
                } else if (fraction < 5) {
                    //測試
                    // m_tv_fraction1
                    finalTest.setText(String.valueOf(++fraction));
                    if (fraction == 5) {
                        new AlertDialog.Builder(getActivity())
                                .setMessage("請問是否結束這局?")
                                .setPositiveButton("確定", listener)
                                .setNegativeButton("取消", listener)
                                .show();
                    }
                }
            }
        });
        m_btn_減.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fraction = Integer.parseInt(finalTest.getText().toString());
                if (fraction > 0 && fraction <=5) {
                    //測試m_tv_fraction1
                    finalTest.setText(String.valueOf(fraction - 1));
                }
            }
        });

    }

    //內部類別：實現Dailog所響應的事件
    private class AlerDialogListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    Intent intent = new Intent(getActivity(), setNextClass());
                     fraction =0;


                    startActivity(intent);
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    break;
            }
        }
    }

    //封裝的效應
    //取得第一局的TextView
    public TextView get_m_tv_fraction1() {
        return m_tv_fraction1;
    }

    //取得第二局的TextView
    public TextView get_m_tv_fraction2() {
        return m_tv_fraction2;
    }

    //取得第三局的TextView
    public TextView get_m_tv_fraction3() {
        return m_tv_fraction3;
    }

    //解除耦合，Fragment工作內容 由Fragment自己決定
    //
    public void setInit_Point1(String s) {
        m_tv_fraction1.setText(s);
    }

    public void setInit_Point2(String s) {
        m_tv_fraction2.setText(s);
    }

    public Class getclass() {
        return setNextClass();
    }

    //抽象：讓子類別 設定 跳轉的Class
    public abstract Class setNextClass();

    //抽象：讓子類別 設定 局數
    public  abstract int setPointNumber();

}
