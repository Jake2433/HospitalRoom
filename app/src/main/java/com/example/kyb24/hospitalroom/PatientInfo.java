package com.example.kyb24.hospitalroom;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.PhoneNumberUtils;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
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
 * Created by kyb24 on 2017-10-10.
 */

public class PatientInfo extends Activity {

    private TextView PName;
    private EditText PSex;
    private EditText PBirth;
    private EditText PPhone;
    private EditText PStart;
    private EditText PEnd;
    private EditText PMemo;

    public String Nth;

    private Button FirstButton;

    HttpPost httppost;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;
    ProgressDialog dialog = null;

    private int SelectedPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_patientinfo);

        Intent intent = getIntent();

        Nth = intent.getStringExtra("Nth");

        PName = (TextView)findViewById(R.id.ET_NameP);
        PName.setText(intent.getStringExtra("PATIENT_NAME"));

        SelectedPos = intent.getIntExtra("CURRENT_POS",0);

        PSex = (EditText)findViewById(R.id.ET_SexP);
        PSex.setText(intent.getStringExtra("S"));
        PBirth = (EditText)findViewById(R.id.ET_BirthP);
        PBirth.setText(intent.getStringExtra("B"));

        PPhone = (EditText) findViewById(R.id.ET_PhoneP);
        PPhone.setText(intent.getStringExtra("P"));
        PPhone.setInputType(InputType.TYPE_CLASS_PHONE);
        PPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        PStart = (EditText)findViewById(R.id.ET_StartP);
        PStart.setText(intent.getStringExtra("PS"));
        PEnd = (EditText)findViewById(R.id.ET_EndP);
        PEnd.setText(intent.getStringExtra("PE"));
        PMemo = (EditText)findViewById(R.id.ET_MemoP);
        PMemo.setText(intent.getStringExtra("M"));
    }

    public void insert(View view) {
        String SName = PName.getText().toString();
        String SSex = PSex.getText().toString();
        String SBirth = PBirth.getText().toString();
        String SPhone = PPhone.getText().toString();
        String SStart = PStart.getText().toString();
        String SEnd = PEnd.getText().toString();
        String SMemo = PMemo.getText().toString();
        String SNth = Nth.toString();

        insertoToDatabase(SName, SSex, SBirth, SPhone, SStart, SEnd, SMemo, SNth);

      //  FirstButton.setText(SName);
        Intent intent = new Intent(getApplicationContext(), Room.class);
        intent.putExtra("COMPLETE_NAME", SName);
        intent.putExtra("COMPLETE_POS",SelectedPos);
        startActivity(intent);

    }

    private void insertoToDatabase(String SName, String SSex, String SBirth, String SPhone, String SStart, String SEnd, String SMemo, String SNth) {
        class InsertData extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(PatientInfo.this, "Please Wait", null, true, true);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }
            @Override
            protected String doInBackground(String... params) {

                try {
                    String SName = (String) params[0];
                    String SSex = (String) params[1];
                    String SBirth = (String) params[2];
                    String SPhone = (String) params[3];
                    String SStart = (String) params[4];
                    String SEnd = (String) params[5];
                    String SMemo = (String)params[6];
                    String SNth = (String)params[7];

                    String link = "http://192.168.0.9/hospitalroom/addpatient.php";
                    String data = URLEncoder.encode("Name", "UTF-8") + "=" + URLEncoder.encode(SName, "UTF-8");
                    data += "&" + URLEncoder.encode("Sex", "UTF-8") + "=" + URLEncoder.encode(SSex, "UTF-8");
                    data += "&" + URLEncoder.encode("Birth", "UTF-8") + "=" + URLEncoder.encode(SBirth, "UTF-8");
                    data += "&" + URLEncoder.encode("Phone", "UTF-8") + "=" + URLEncoder.encode(SPhone, "UTF-8");
                    data += "&" + URLEncoder.encode("Start", "UTF-8") + "=" + URLEncoder.encode(SStart, "UTF-8");
                    data += "&" + URLEncoder.encode("End", "UTF-8") + "=" + URLEncoder.encode(SEnd, "UTF-8");
                    data += "&" + URLEncoder.encode("Memo", "UTF-8") + "=" + URLEncoder.encode(SMemo, "UTF-8");
                    data += "&" + URLEncoder.encode("Nth", "UTF-8") + "=" + URLEncoder.encode(SNth, "UTF-8");

                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                    wr.write(data);
                    wr.flush();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    // Read Server Response
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    return sb.toString();
                } catch (Exception e) {
                    return new String("Exception: " + e.getMessage());
                }
            }
        }
        InsertData task = new InsertData();
        task.execute(SName, SSex, SBirth, SPhone, SStart, SEnd, SMemo, SNth);
    }

    public void DupChk(View view)
    {
        try {
            httpclient = new DefaultHttpClient();
            httppost = new HttpPost("http://192.168.0.9/hospitalroom/duplication.php");
            nameValuePairs = new ArrayList<NameValuePair>(1);
            nameValuePairs.add(new BasicNameValuePair("userid", PName.getText().toString()));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            response = httpclient.execute(httppost);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            final String response = httpclient.execute(httppost, responseHandler);


            if (response.equalsIgnoreCase("User Found")) {

                AlertDialog.Builder alert = new AlertDialog.Builder(PatientInfo.this);
                alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();     //닫기
                    }
                });
                alert.setMessage("이미 병실에 등록되어 있습니다.");
                alert.show();
            }
            else {
            }
        }
        catch(Exception e)
        {
            dialog.dismiss();
            System.out.println("Exception : " + e.getMessage());
        }
    }
}