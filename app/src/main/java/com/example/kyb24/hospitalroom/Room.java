package com.example.kyb24.hospitalroom;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kyb24 on 2017-10-02.
 */

/*
public class Room extends Activity
   implements View.OnClickListener {



    final Context context = this;
    private Button ClickedBtnP1;
    private Button ClickedBtnP2;
    private Button ClickedBtnP3;
    private Button ClickedBtnP4;
    private Button ClickedBtnP5;
    private Button ClickedBtnP6;

    private String movedName;
    private int currentPosition;
*/

public class Room extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);


/*

        ClickedBtnP1 = (Button) findViewById(R.id.Btn_FirstP);
        ClickedBtnP2 = (Button) findViewById(R.id.Btn_SecondP);
        ClickedBtnP3 = (Button) findViewById(R.id.Btn_ThirdP);
        ClickedBtnP4 = (Button) findViewById(R.id.Btn_FourthP);
        ClickedBtnP5 = (Button) findViewById(R.id.Btn_FifthP);
        ClickedBtnP6 = (Button) findViewById(R.id.Btn_SixthP);

        Intent intent = getIntent();
        if(intent.getIntExtra("COMPLETE_POS",0) != 0)
        {
            switch(intent.getIntExtra("COMPLETE_POS",0))
            {
                case 1:
                    ClickedBtnP1.setText(intent.getStringExtra("COMPLETE_NAME"));
                    break;
                case 2:
                    ClickedBtnP2.setText(intent.getStringExtra("COMPLETE_NAME"));
                    break;
                case 3:
                    ClickedBtnP3.setText(intent.getStringExtra("COMPLETE_NAME"));
                    break;
                case 4:
                    ClickedBtnP4.setText(intent.getStringExtra("COMPLETE_NAME"));
                    break;
                case 5:
                    ClickedBtnP5.setText(intent.getStringExtra("COMPLETE_NAME"));
                    break;
                case 6:
                    ClickedBtnP6.setText(intent.getStringExtra("COMPLETE_NAME"));
                    break;
                default:
                    break;
            }
        }

        // inputName = (EditText)findViewById(R.id.ET_NameP);

        // 클릭 이벤트
        ClickedBtnP1.setOnClickListener(this);
        ClickedBtnP2.setOnClickListener(this);
        ClickedBtnP3.setOnClickListener(this);
        ClickedBtnP4.setOnClickListener(this);
        ClickedBtnP5.setOnClickListener(this);
        ClickedBtnP6.setOnClickListener(this);
    }

    public void CliAdd(View v) {


        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
                Room.this);
        //   alertBuilder.setIcon(R.drawable.);
        alertBuilder.setTitle("처리할 업무를 선택해주세요");
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                Room.this,
                android.R.layout.select_dialog_singlechoice);


        if (ClickedBtnP1.getText().toString().equals("EMPTY")) {
            adapter.add("자리1");
        }
        if (ClickedBtnP2.getText().toString().equals("EMPTY")) {
            adapter.add("자리2");
        }
        if (ClickedBtnP3.getText().toString().equals("EMPTY")) {
            adapter.add("자리3");
        }
        if (ClickedBtnP4.getText().toString().equals("EMPTY")) {
            adapter.add("자리4");
        }
        if (ClickedBtnP5.getText().toString().equals("EMPTY")) {
            adapter.add("자리5");
        }
        if (ClickedBtnP6.getText().toString().equals("EMPTY")) {
            adapter.add("자리6");
        }
        else if(!ClickedBtnP1.getText().toString().equals("EMPTY")
            && !ClickedBtnP2.getText().toString().equals("EMPTY")
            && !ClickedBtnP3.getText().toString().equals("EMPTY")
            && !ClickedBtnP4.getText().toString().equals("EMPTY")
            && !ClickedBtnP5.getText().toString().equals("EMPTY")
            && !ClickedBtnP6.getText().toString().equals("EMPTY")) {
            alertBuilder.setMessage("이용가능한 자리가 없습니다.");
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

                        final int count = id;

                        AlertDialog.Builder alert = new AlertDialog.Builder(Room.this);

                        alert.setTitle("입실환자 정보입력");
                        alert.setMessage("환자성함");

                        final EditText name = new EditText(Room.this);
                        alert.setView(name);

                        alert.setNegativeButton("no",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            }
                        });

                        alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                String username = name.getText().toString();
                                movedName = username;


                                switch (adapter.getItem(count))
                                {
                                    case "자리1":
                                        ClickedBtnP1.setText(username);
                                        currentPosition = 1;
                                        break;
                                    case "자리2":
                                        ClickedBtnP2.setText(username);
                                        currentPosition = 2;
                                        break;
                                    case "자리3":
                                        ClickedBtnP3.setText(username);
                                        currentPosition = 3;
                                        break;
                                    case "자리4":
                                        ClickedBtnP4.setText(username);
                                        currentPosition = 4;
                                        break;
                                    case "자리5":
                                        ClickedBtnP5.setText(username);
                                        currentPosition = 5;
                                        break;
                                    case "자리6":
                                        ClickedBtnP6.setText(username);
                                        currentPosition = 6;
                                        break;
                                    default:
                                        break;
                                }
                            }
                        });
                        alert.show();
                    }
                });
        alertBuilder.show();

    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.Btn_FirstP:
                    myfunc(ClickedBtnP1);
                break;

            case R.id.Btn_SecondP:
                    myfunc(ClickedBtnP2);
                break;

            case R.id.Btn_ThirdP:
                    myfunc(ClickedBtnP3);
                break;

            case R.id.Btn_FourthP:
                    myfunc(ClickedBtnP4);
                break;

            case R.id.Btn_FifthP:
                    myfunc(ClickedBtnP5);
                break;

            case R.id.Btn_SixthP:
                    myfunc(ClickedBtnP6);
                break;

            default:
                break;
        }
    }

    public void myfunc(final Button WhichButton) {

        if (WhichButton.getText().toString().equals("EMPTY"))
        {
            Toast.makeText(context, "빈자리입니다.", Toast.LENGTH_SHORT).show();
        }
        else {
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
                    Room.this);
            //   alertBuilder.setIcon(R.drawable.);
            alertBuilder.setTitle("처리할 업무를 선택해주세요");
            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    Room.this,
                    android.R.layout.select_dialog_singlechoice);

            adapter.add("환자정보");
            adapter.add("퇴실");

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
                            Intent intent = new Intent(getApplicationContext(),PatientInfo.class);
                            intent.putExtra("PATIENT_NAME",movedName);
                            intent.putExtra("CURRENT_POS",currentPosition);
                            startActivity(intent);
                        }
                    });
            alertBuilder.show();
        }
        */
    }

}