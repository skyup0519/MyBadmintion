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


    private int fraction1;
    private int fraction2;
    private int fraction3;


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

        switch (gamePoint) {
            case 1:
                onClickPoint_1();
                break;
            case 2:
                onClickPoint_2();
                break;

            case 3:
                onClickPoint_3();
                break;
        }
    }


    private void findId() {

        m_btn_加 = (Button) getView().findViewById(R.id.btn_加);
        m_btn_減 = (Button) getView().findViewById(R.id.btn_減);
        m_tv_fraction1 = (TextView) getView().findViewById(R.id.tv_fraction1);
        m_tv_fraction2 = (TextView) getView().findViewById(R.id.tv_fraction2);
        m_tv_fraction3 = (TextView) getView().findViewById(R.id.tv_fraction3);

    }
    //點擊 第一局
    private void onClickPoint_1() {
        Toast.makeText(getActivity(),"Fragment1 開始設定第一局分數",Toast.LENGTH_LONG).show();

        m_btn_加.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlerDialogListener listener = new AlerDialogListener();

                //測試
                // m_tv_fraction1
                fraction1 = Integer.parseInt(m_tv_fraction1.getText().toString());

                if (fraction1 == 5) {
//                    LayoutInflater inflater = getActivity().getLayoutInflater();
//                    View view = inflater.inflate(R.layout.activity_2,  null );
                    new AlertDialog.Builder(getActivity())
//                          .setView(view)
                            .setMessage("請問是否結束這局?")
                            .setPositiveButton("確定", listener)
                            .setNegativeButton("取消", listener)
                            .show();
                } else if (fraction1 < 5) {
                    //測試
                    // m_tv_fraction1
                    m_tv_fraction1.setText(String.valueOf(++fraction1));
                    if (fraction1 == 5) {
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
                fraction1 = Integer.parseInt(m_tv_fraction1.getText().toString());
                if (fraction1 > 0 && fraction1<=5) {
                    //測試m_tv_fraction1
                    m_tv_fraction1.setText(String.valueOf(fraction1 - 1));
                }
            }
        });

    }
    //點擊 第二局
    private void onClickPoint_2() {
        Toast.makeText(getActivity(),"Fragment2 開始設定第二局分數",Toast.LENGTH_LONG).show();
        m_btn_加.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlerDialogListener listener = new AlerDialogListener();
                fraction2 = Integer.parseInt(m_tv_fraction2.getText().toString());

                if (fraction2 == 5) {
//                    LayoutInflater inflater = getActivity().getLayoutInflater();
//                    View view = inflater.inflate(R.layout.activity_2,  null );
                    new AlertDialog.Builder(getActivity())
//                          .setView(view)
                            .setMessage("請問是否結束這局?")
                            .setPositiveButton("確定", listener)
                            .setNegativeButton("取消", listener)
                            .show();
                } else if (fraction2 < 5) {
                    //測試
                    // m_tv_fraction1
                    m_tv_fraction2.setText(String.valueOf(++fraction2));
                    if (fraction2 == 5) {
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
                //測試m_tv_fraction1
                fraction2 = Integer.parseInt(m_tv_fraction2.getText().toString());
                if (fraction2 > 0) {
                    //測試m_tv_fraction1
                    m_tv_fraction2.setText(String.valueOf(fraction2 - 1));
                }
            }
        });

    }
    //點擊 第三局
    private void onClickPoint_3() {
        Toast.makeText(getActivity(),"Fragment1 開始設定第三局分數",Toast.LENGTH_LONG).show();
        m_btn_加.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlerDialogListener listener = new AlerDialogListener();
                fraction3 = Integer.parseInt(m_tv_fraction3.getText().toString());

                if (fraction3 == 5) {
//                    LayoutInflater inflater = getActivity().getLayoutInflater();
//                    View view = inflater.inflate(R.layout.activity_2,  null );
                    new AlertDialog.Builder(getActivity())
//                          .setView(view)
                            .setMessage("請問是否結束這局?")
                            .setPositiveButton("確定", listener)
                            .setNegativeButton("取消", listener)
                            .show();
                } else if (fraction3 < 5) {
                    //測試
                    // m_tv_fraction3
                    m_tv_fraction3.setText(String.valueOf(++fraction3));
                    if (fraction3 == 5) {
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
                //測試m_tv_fraction3
                fraction3 = Integer.parseInt(m_tv_fraction3.getText().toString());
                if (fraction3 > 0) {
                    //測試m_tv_fraction3
                    m_tv_fraction3.setText(String.valueOf(fraction3 - 1));
                }
            }
        });}

    //內部類別：實現Dailog所響應的事件
    private class AlerDialogListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    Intent intent = new Intent(getActivity(), setNextClass());
//                     gamePoint =0;


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
