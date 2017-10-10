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
        ClickedBtnMain7 = (Button) findViewById(R.id.Btn_Main7);

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
                WhatChosen(ClickedBtnMain1,"1");
                break;

            case R.id.Btn_Main2:
                WhatChosen(ClickedBtnMain2,"2");
                break;

            case R.id.Btn_Main3:
                WhatChosen(ClickedBtnMain3, "3");
                break;

            case R.id.Btn_Main4:
                WhatChosen(ClickedBtnMain4, "4");
                break;

            case R.id.Btn_Main5:
                WhatChosen(ClickedBtnMain5,"5");
                break;

            case R.id.Btn_Main6:
                WhatChosen(ClickedBtnMain6,"6");
                break;

            case R.id.Btn_Main7:
                WhatChosen(ClickedBtnMain7,"7");
                break;

            default:
                break;
        }
    }

    public void WhatChosen(final Button B, final String floor)
    {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
                SignIn.this);
        //   alertBuilder.setIcon(R.drawable.);
        alertBuilder.setTitle("병실을 선택해주세요");

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                SignIn.this,
                android.R.layout.select_dialog_singlechoice);

        for(int i =1; i<6; i++)
        {
            adapter.add(floor+"0"+i+"호");
        }

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
                        String field = B.getText().toString();
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
    }
}
