package com.example.kyb24.hospitalroom;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import com.example.kyb24.hospitalroom.Bluetooth.DataListActivity;

/**
 * Created by kyb24 on 2017-11-01.
 */

public class FirstScreen extends Activity {


    ImageButton mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_firstscreen);

        //버튼 등록
        mBtn = (ImageButton) findViewById(R.id.Btn_menu);
    }

    //버튼이 눌렸을때 여기로옴

    public void mOnClick(View v) {

        MenuBuilder menuBuilder = new MenuBuilder(this);
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.popupmenu, menuBuilder);
        MenuPopupHelper optionsMenu = new MenuPopupHelper(this, menuBuilder, v);
        optionsMenu.setForceShowIcon(true);

        menuBuilder.setCallback(new MenuBuilder.Callback() {
            @Override
            public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.popup_family:
                        WhatChosen("정형외과", "1");
                        break;

                    case R.id.popup_moms:
                        WhatChosen("산부인과", "2");
                        break;

                    case R.id.popup_neural:
                        WhatChosen("신경외과", "3");
                        break;

                    case R.id.popup_eyes:
                        WhatChosen("치과", "4");
                        break;

                    case R.id.popup_earsandnose:
                        WhatChosen("이비인후과", "5");
                        break;

                    case R.id.popup_skin:
                        WhatChosen("성형외과", "6");
                        break;

                    case R.id.popup_chest:
                        WhatChosen("흉부외과", "7");
                        break;

                    case R.id.popup_etc:
                        WhatChosen("기타", "8");
                        break;
                    default:
                        return false;
                }
                return false;
            }

            @Override
            public void onMenuModeChange(MenuBuilder menu) {
            }
        });

        optionsMenu.show();

    }

    public void WhatChosen(final String name, final String floor)
    {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
                FirstScreen.this);
        //   alertBuilder.setIcon(R.drawable.);
        alertBuilder.setTitle("병실을 선택해주세요");

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                FirstScreen.this,
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
                        String strName = adapter.getItem(id);
                        AlertDialog.Builder innBuilder = new AlertDialog.Builder(
                                FirstScreen.this);
                        innBuilder.setMessage(strName+"를 선택하셨습니다.");
                        innBuilder.setTitle(name);
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
