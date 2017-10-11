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
import org.json.JSONArray;
import org.json.JSONObject;

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


public class Room extends Activity
   implements View.OnClickListener {

    HttpPost httppost;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;

    final Context context = this;
    private Button ClickedBtnP1;
    private Button ClickedBtnP2;
    private Button ClickedBtnP3;
    private Button ClickedBtnP4;
    private Button ClickedBtnP5;
    private Button ClickedBtnP6;

    private String movedName;
    private int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        ClickedBtnP1 = (Button) findViewById(R.id.Btn_FirstP);
        ClickedBtnP2 = (Button) findViewById(R.id.Btn_SecondP);
        ClickedBtnP3 = (Button) findViewById(R.id.Btn_ThirdP);
        ClickedBtnP4 = (Button) findViewById(R.id.Btn_FourthP);
        ClickedBtnP5 = (Button) findViewById(R.id.Btn_FifthP);
        ClickedBtnP6 = (Button) findViewById(R.id.Btn_SixthP);

        try {
            httpclient = new DefaultHttpClient();
            httppost = new HttpPost("http://192.168.0.9/hospitalroom/patientlist/patient1.php");
            nameValuePairs = new ArrayList<NameValuePair>(2);
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            response = httpclient.execute(httppost);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            final String response = httpclient.execute(httppost, responseHandler);
            JSONObject json = new JSONObject(response);
            String p_name = json.getString("p_name");
            ClickedBtnP1.setText(p_name);
        }
        catch(Exception e) {}

        try {
            httpclient = new DefaultHttpClient();
            httppost = new HttpPost("http://192.168.0.9/hospitalroom/patientlist/patient2.php");
            nameValuePairs = new ArrayList<NameValuePair>(2);
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            response = httpclient.execute(httppost);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            final String response = httpclient.execute(httppost, responseHandler);
            JSONObject json = new JSONObject(response);
            String p_name = json.getString("p_name");
            ClickedBtnP2.setText(p_name);
        }
        catch(Exception e) {}

        try {
            httpclient = new DefaultHttpClient();
            httppost = new HttpPost("http://192.168.0.9/hospitalroom/patientlist/patient3.php");
            nameValuePairs = new ArrayList<NameValuePair>(2);
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            response = httpclient.execute(httppost);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            final String response = httpclient.execute(httppost, responseHandler);
            JSONObject json = new JSONObject(response);
            String p_name = json.getString("p_name");
            ClickedBtnP3.setText(p_name);
        }
        catch(Exception e) {}

        try {
            httpclient = new DefaultHttpClient();
            httppost = new HttpPost("http://192.168.0.9/hospitalroom/patientlist/patient4.php");
            nameValuePairs = new ArrayList<NameValuePair>(2);
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            response = httpclient.execute(httppost);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            final String response = httpclient.execute(httppost, responseHandler);
            JSONObject json = new JSONObject(response);
            String p_name = json.getString("p_name");
            ClickedBtnP4.setText(p_name);
        }
        catch(Exception e) {}

        try {
            httpclient = new DefaultHttpClient();
            httppost = new HttpPost("http://192.168.0.9/hospitalroom/patientlist/patient5.php");
            nameValuePairs = new ArrayList<NameValuePair>(2);
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            response = httpclient.execute(httppost);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            final String response = httpclient.execute(httppost, responseHandler);
            JSONObject json = new JSONObject(response);
            String p_name = json.getString("p_name");
            ClickedBtnP5.setText(p_name);
        }
        catch(Exception e) {}


        try {
            httpclient = new DefaultHttpClient();
            httppost = new HttpPost("http://192.168.0.9/hospitalroom/patientlist/patient6.php");
            nameValuePairs = new ArrayList<NameValuePair>(2);
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            response = httpclient.execute(httppost);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            final String response = httpclient.execute(httppost, responseHandler);
            JSONObject json = new JSONObject(response);
            String p_name = json.getString("p_name");
            ClickedBtnP6.setText(p_name);
        }
        catch(Exception e) {}


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
                        alert.setMessage("성함");

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
                    myfunc(ClickedBtnP1, "0");
                break;

            case R.id.Btn_SecondP:
                    myfunc(ClickedBtnP2, "1");
                break;

            case R.id.Btn_ThirdP:
                    myfunc(ClickedBtnP3, "2");
                break;

            case R.id.Btn_FourthP:
                    myfunc(ClickedBtnP4, "3");
                break;

            case R.id.Btn_FifthP:
                    myfunc(ClickedBtnP5,"4");
                break;

            case R.id.Btn_SixthP:
                    myfunc(ClickedBtnP6, "5");
                break;

            default:
                break;
        }
    }

    public void myfunc(final Button WhichButton, final String n) {

        if (WhichButton.getText().toString().equals("EMPTY"))
        {
            Toast.makeText(context, "빈자리입니다.", Toast.LENGTH_SHORT).show();
        }
        else {
            final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
                    Room.this);
            //   alertBuilder.setIcon(R.drawable.);
            alertBuilder.setTitle("처리할 업무를 선택해주세요");
            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    Room.this,
                    android.R.layout.select_dialog_singlechoice);

            adapter.add("환자정보 입력");
            adapter.add("환자정보 확인");

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

                            boolean clickedhere=false;

                            if (id == 0) { //환자정보 입력 클릭시 id == 0
                                Intent intent = new Intent(getApplicationContext(), PatientInfo.class);
                                intent.putExtra("PATIENT_NAME", movedName);
                                intent.putExtra("CURRENT_POS", currentPosition);
                                intent.putExtra("Nth",n);
                                startActivity(intent);
                                clickedhere = true;
                            }
                            else    //환자정보 확인 클릭 시 id == 1
                            {
                                if (clickedhere == true || !WhichButton.getText().toString().equals("EMPTY")) {
                                    try {
                                        httpclient = new DefaultHttpClient();
                                        switch (n) {
                                            case "0":
                                                httppost = new HttpPost("http://192.168.0.9/hospitalroom/readpatient/readpatientinfo1.php");
                                                break;
                                            case "1":
                                                httppost = new HttpPost("http://192.168.0.9/hospitalroom/readpatient/readpatientinfo2.php");
                                                break;
                                            case "2":
                                                httppost = new HttpPost("http://192.168.0.9/hospitalroom/readpatient/readpatientinfo3.php");
                                                break;
                                            case "3":
                                                httppost = new HttpPost("http://192.168.0.9/hospitalroom/readpatient/readpatientinfo4.php");
                                                break;
                                            case "4":
                                                httppost = new HttpPost("http://192.168.0.9/hospitalroom/readpatient/readpatientinfo5.php");
                                                break;
                                            case "5":
                                                httppost = new HttpPost("http://192.168.0.9/hospitalroom/readpatient/readpatientinfo6.php");
                                                break;
                                            default:
                                                break;
                                        }
                                        nameValuePairs = new ArrayList<NameValuePair>(8);
                                        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                                        response = httpclient.execute(httppost);
                                        ResponseHandler<String> responseHandler = new BasicResponseHandler();
                                        final String response = httpclient.execute(httppost, responseHandler);
                                        JSONObject json = new JSONObject(response);


                                        String p_name = json.getString("p_name");
                                        String p_sex = json.getString("p_sex");
                                        String p_birth = json.getString("p_birth");
                                        String p_phone = json.getString("p_phone");
                                        String p_start = json.getString("p_start");
                                        String p_end = json.getString("p_end");
                                        String p_memo = json.getString("p_memo");
                                        String p_number = n.toString();


                                        Intent intent = new Intent(getApplicationContext(), PatientInfo.class);
                                        intent.putExtra("PATIENT_NAME", p_name);
                                        intent.putExtra("S", p_sex);
                                        intent.putExtra("B", p_birth);
                                        intent.putExtra("P", p_phone);
                                        intent.putExtra("PS", p_start);
                                        intent.putExtra("PE", p_end);
                                        intent.putExtra("M", p_memo);
                                        intent.putExtra("Nth", p_number);

                                        startActivity(intent);
                                    } catch (Exception e) {
                                        Toast.makeText(context, "환자정보부터 입력해주세요", Toast.LENGTH_SHORT).show();
                                    }
                                    clickedhere=false;
                                } else
                                {
                                    Toast.makeText(context, "환자정보부터 입력해주세요", Toast.LENGTH_SHORT).show();
                                    clickedhere=false;
                                }
                            }
                        }
                    });
            alertBuilder.show();
        }
    }
}