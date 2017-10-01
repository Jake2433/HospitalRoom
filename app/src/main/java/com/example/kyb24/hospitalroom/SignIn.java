package com.example.kyb24.hospitalroom;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Timer;
import java.util.logging.Handler;

/**
 * Created by kyb24 on 2017-09-26.
 */

public class SignIn extends Activity
    implements View.OnClickListener {

    final Context context = this;
    private Button ClickedBtnMain1;
    private Button ClickedBtnMain2;
    private Button ClickedBtnMain3;
    private Button ClickedBtnMain4;
    private Button ClickedBtnMain5;
    private Button ClickedBtnMain6;
    private Button ClickedBtnMain7;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        ClickedBtnMain1 = (Button) findViewById(R.id.Btn_Main1);
        ClickedBtnMain2 = (Button) findViewById(R.id.Btn_Main2);
        ClickedBtnMain3 = (Button) findViewById(R.id.Btn_Main3);
        ClickedBtnMain4 = (Button) findViewById(R.id.Btn_Main4);
        ClickedBtnMain5 = (Button) findViewById(R.id.Btn_Main5);
        ClickedBtnMain6 = (Button) findViewById(R.id.Btn_Main6);
        ClickedBtnMain7 = (Button) findViewById(R.id.Btn_Main6);

        // 클릭 이벤트
        ClickedBtnMain1.setOnClickListener(this);
        ClickedBtnMain2.setOnClickListener(this);
        ClickedBtnMain3.setOnClickListener(this);
        ClickedBtnMain4.setOnClickListener(this);
        ClickedBtnMain5.setOnClickListener(this);
        ClickedBtnMain6.setOnClickListener(this);
        ClickedBtnMain7.setOnClickListener(this);

    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.Btn_Main1:
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
                        SignIn.this);
             //   alertBuilder.setIcon(R.drawable.);
                alertBuilder.setTitle("병실을 선택해주세요");

                // List Adapter 생성
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        SignIn.this,
                        android.R.layout.select_dialog_singlechoice);
                adapter.add("101호");
                adapter.add("102호");
                adapter.add("103호");
                adapter.add("105호");
                adapter.add("107호");

                // 버튼 생성
                alertBuilder.setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        });

                // Adapter 셋팅
                alertBuilder.setAdapter(adapter,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {

                                // AlertDialog 안에 있는 AlertDialog
                                String field = ClickedBtnMain1.getText().toString();
                                String strName = adapter.getItem(id);
                                AlertDialog.Builder innBuilder = new AlertDialog.Builder(
                                        SignIn.this);
                                innBuilder.setMessage(strName+"를 선택하셨습니다.");
                                innBuilder.setTitle(field);
                                innBuilder
                                        .setPositiveButton(
                                                "확인",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        Intent intent = new Intent(getApplicationContext(),Room.class);
                                                        startActivity(intent);
                                                    }
                                                });
                                innBuilder.show();
                            }
                        });
                alertBuilder.show();
                break;

            case R.id.Btn_Main2:
                AlertDialog.Builder alertBuilder2 = new AlertDialog.Builder(
                        SignIn.this);
                //   alertBuilder.setIcon(R.drawable.);
                alertBuilder2.setTitle("병실을 선택해주세요");

                // List Adapter 생성
                final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                        SignIn.this,
                        android.R.layout.select_dialog_singlechoice);
                adapter2.add("201호");
                adapter2.add("202호");
                adapter2.add("203호");
                adapter2.add("205호");
                adapter2.add("207호");

                // 버튼 생성
                alertBuilder2.setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        });

                // Adapter 셋팅
                alertBuilder2.setAdapter(adapter2,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {

                                // AlertDialog 안에 있는 AlertDialog
                                String field = ClickedBtnMain2.getText().toString();
                                String strName = adapter2.getItem(id);
                                AlertDialog.Builder innBuilder = new AlertDialog.Builder(
                                        SignIn.this);
                                innBuilder.setMessage(strName+"를 선택하셨습니다.");
                                innBuilder.setTitle(field);
                                innBuilder
                                        .setPositiveButton(
                                                "확인",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {dialog.dismiss();}
                                                });
                                innBuilder.show();
                            }
                        });
                alertBuilder2.show();
                break;

            case R.id.Btn_Main3:
                AlertDialog.Builder alertBuilder3 = new AlertDialog.Builder(
                        SignIn.this);
                //   alertBuilder.setIcon(R.drawable.);
                alertBuilder3.setTitle("병실을 선택해주세요");

                // List Adapter 생성
                final ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(
                        SignIn.this,
                        android.R.layout.select_dialog_singlechoice);
                adapter3.add("301호");
                adapter3.add("302호");
                adapter3.add("303호");
                adapter3.add("305호");
                adapter3.add("307호");

                // 버튼 생성
                alertBuilder3.setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        });

                // Adapter 셋팅
                alertBuilder3.setAdapter(adapter3,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {

                                // AlertDialog 안에 있는 AlertDialog
                                String field = ClickedBtnMain3.getText().toString();
                                String strName = adapter3.getItem(id);
                                AlertDialog.Builder innBuilder = new AlertDialog.Builder(
                                        SignIn.this);
                                innBuilder.setMessage(strName+"를 선택하셨습니다.");
                                innBuilder.setTitle(field);
                                innBuilder
                                        .setPositiveButton(
                                                "확인",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {dialog.dismiss();}
                                                });
                                innBuilder.show();
                            }
                        });
                alertBuilder3.show();
                break;

            case R.id.Btn_Main4:
                AlertDialog.Builder alertBuilder4 = new AlertDialog.Builder(
                        SignIn.this);
                //   alertBuilder.setIcon(R.drawable.);
                alertBuilder4.setTitle("병실을 선택해주세요");

                // List Adapter 생성
                final ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(
                        SignIn.this,
                        android.R.layout.select_dialog_singlechoice);
                adapter4.add("401호");
                adapter4.add("402호");
                adapter4.add("403호");
                adapter4.add("405호");
                adapter4.add("407호");

                // 버튼 생성
                alertBuilder4.setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        });

                // Adapter 셋팅
                alertBuilder4.setAdapter(adapter4,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {

                                // AlertDialog 안에 있는 AlertDialog
                                String field = ClickedBtnMain4.getText().toString();
                                String strName = adapter4.getItem(id);
                                AlertDialog.Builder innBuilder = new AlertDialog.Builder(
                                        SignIn.this);
                                innBuilder.setMessage(strName+"를 선택하셨습니다.");
                                innBuilder.setTitle(field);
                                innBuilder
                                        .setPositiveButton(
                                                "확인",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {dialog.dismiss();}
                                                });
                                innBuilder.show();
                            }
                        });
                alertBuilder4.show();
                break;

            case R.id.Btn_Main5:
                AlertDialog.Builder alertBuilder5 = new AlertDialog.Builder(
                        SignIn.this);
                //   alertBuilder.setIcon(R.drawable.);
                alertBuilder5.setTitle("병실을 선택해주세요");

                // List Adapter 생성
                final ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(
                        SignIn.this,
                        android.R.layout.select_dialog_singlechoice);
                adapter5.add("501호");
                adapter5.add("502호");
                adapter5.add("503호");
                adapter5.add("505호");
                adapter5.add("507호");

                // 버튼 생성
                alertBuilder5.setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        });

                // Adapter 셋팅
                alertBuilder5.setAdapter(adapter5,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {

                                // AlertDialog 안에 있는 AlertDialog
                                String field = ClickedBtnMain5.getText().toString();
                                String strName = adapter5.getItem(id);
                                AlertDialog.Builder innBuilder = new AlertDialog.Builder(
                                        SignIn.this);
                                innBuilder.setMessage(strName+"를 선택하셨습니다.");
                                innBuilder.setTitle(field);
                                innBuilder
                                        .setPositiveButton(
                                                "확인",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {dialog.dismiss();}
                                                });
                                innBuilder.show();
                            }
                        });
                alertBuilder5.show();
                break;

            case R.id.Btn_Main6:
                AlertDialog.Builder alertBuilder6 = new AlertDialog.Builder(
                        SignIn.this);
                //   alertBuilder.setIcon(R.drawable.);
                alertBuilder6.setTitle("병실을 선택해주세요");

                // List Adapter 생성
                final ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(
                        SignIn.this,
                        android.R.layout.select_dialog_singlechoice);
                adapter6.add("601호");
                adapter6.add("602호");
                adapter6.add("603호");
                adapter6.add("605호");
                adapter6.add("607호");

                // 버튼 생성
                alertBuilder6.setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        });

                // Adapter 셋팅
                alertBuilder6.setAdapter(adapter6,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {

                                // AlertDialog 안에 있는 AlertDialog
                                String field = ClickedBtnMain6.getText().toString();
                                String strName = adapter6.getItem(id);
                                AlertDialog.Builder innBuilder = new AlertDialog.Builder(
                                        SignIn.this);
                                innBuilder.setMessage(strName+"를 선택하셨습니다.");
                                innBuilder.setTitle(field);
                                innBuilder
                                        .setPositiveButton(
                                                "확인",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {dialog.dismiss();}
                                                });
                                innBuilder.show();
                            }
                        });
                alertBuilder6.show();
                break;

            case R.id.Btn_Main7:
                AlertDialog.Builder alertBuilder7 = new AlertDialog.Builder(
                        SignIn.this);
                //   alertBuilder.setIcon(R.drawable.);
                alertBuilder7.setTitle("병실을 선택해주세요");

                // List Adapter 생성
                final ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(
                        SignIn.this,
                        android.R.layout.select_dialog_singlechoice);
                adapter7.add("701호");
                adapter7.add("702호");
                adapter7.add("703호");
                adapter7.add("705호");
                adapter7.add("707호");

                // 버튼 생성
                alertBuilder7.setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        });

                // Adapter 셋팅
                alertBuilder7.setAdapter(adapter7,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {

                                // AlertDialog 안에 있는 AlertDialog
                                String field = ClickedBtnMain7.getText().toString();
                                String strName = adapter7.getItem(id);
                                AlertDialog.Builder innBuilder = new AlertDialog.Builder(
                                        SignIn.this);
                                innBuilder.setMessage(strName+"를 선택하셨습니다.");
                                innBuilder.setTitle(field);
                                innBuilder
                                        .setPositiveButton(
                                                "확인",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {dialog.dismiss();}
                                                });
                                innBuilder.show();
                            }
                        });
                alertBuilder7.show();
                break;

            default:
                break;
        }
    }

}
