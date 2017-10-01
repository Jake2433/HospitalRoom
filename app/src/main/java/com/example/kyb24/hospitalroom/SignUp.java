package com.example.kyb24.hospitalroom;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.DuplicateFormatFlagsException;
import java.util.List;

/**
 * Created by kyb24 on 2017-09-26.
 */

public class SignUp extends Activity {

    private EditText editTextId;
    private EditText editTextPw;
    private EditText editTextPw2;
    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextPhone;

    private TextView textViewPwCheck;
    private TextView textViewDupCheck;

    HttpPost httppost;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;
    ProgressDialog dialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editTextId = (EditText) findViewById(R.id.SignUp_ID);
        editTextPw = (EditText) findViewById(R.id.SignUp_PW);
        editTextPw2 = (EditText) findViewById(R.id.SignUp_PW2);
        editTextName = (EditText) findViewById(R.id.SignUp_Name);
        editTextEmail = (EditText) findViewById(R.id.SignUp_Email);
        editTextPhone = (EditText) findViewById(R.id.SignUp_Phone);

        textViewPwCheck = (TextView)findViewById(R.id.tv_pwcheck);
        textViewDupCheck = (TextView)findViewById(R.id.tv_dupresult);

    }
    public void insert(View view) {
        String Id = editTextId.getText().toString();
        String Pw = editTextPw.getText().toString();
        String Pw2 = editTextPw2.getText().toString();
        String Name = editTextName.getText().toString();
        String Email = editTextEmail.getText().toString();
        String Phone = editTextPhone.getText().toString();

        if(Pw.equals(Pw2))
        {
            textViewPwCheck.setText("비밀번호가 일치합니다");
            insertoToDatabase(Id, Pw, Name, Email, Phone);
        }
        else
        {
            textViewPwCheck.setText("비밀번호가 일치하지 않습니다");
        }
    }
    private void insertoToDatabase(String Id, String Pw, String Name, String Email, String Phone) {
        class InsertData extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(SignUp.this, "Please Wait", null, true, true);
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
                    String Id = (String) params[0];
                    String Pw = (String) params[1];
                    String Name = (String) params[2];
                    String Email = (String) params[3];
                    String Phone = (String) params[4];

                    String link = "http://192.168.0.9/hospitalroom/signup.php";
                    String data = URLEncoder.encode("Id", "UTF-8") + "=" + URLEncoder.encode(Id, "UTF-8");
                    data += "&" + URLEncoder.encode("Pw", "UTF-8") + "=" + URLEncoder.encode(Pw, "UTF-8");
                    data += "&" + URLEncoder.encode("Name", "UTF-8") + "=" + URLEncoder.encode(Name, "UTF-8");
                    data += "&" + URLEncoder.encode("Email", "UTF-8") + "=" + URLEncoder.encode(Email, "UTF-8");
                    data += "&" + URLEncoder.encode("Phone", "UTF-8") + "=" + URLEncoder.encode(Phone, "UTF-8");

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
        task.execute(Id, Pw, Name, Email, Phone);
    }

    public void DupChk(View view)
    {
    //    String Id = editTextId.getText().toString();

        try {
            httpclient = new DefaultHttpClient();
            httppost = new HttpPost("http://192.168.0.9/hospitalroom/duplication.php");
            nameValuePairs = new ArrayList<NameValuePair>(1);
            nameValuePairs.add(new BasicNameValuePair("userid", editTextId.getText().toString()));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            response = httpclient.execute(httppost);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            final String response = httpclient.execute(httppost, responseHandler);
        /*    System.out.println("Response : " + response);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textViewDupCheck.setText("Response from PHP : " + response);
                    dialog.dismiss();
                }
            });*/

            if (response.equalsIgnoreCase("User Found")) {
             /*   runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       // Toast.makeText(SignUp.this, "Login Success", Toast.LENGTH_SHORT).show();
                    }
                });*/

                textViewDupCheck.setText("이미 존재합니다");

              //  startActivity((new Intent(MainActivity.this, SignIn.class)));
             //   finish();
            }
            else {
               // Toast.makeText(SignUp.this, "Login Fail", Toast.LENGTH_SHORT).show();
                textViewDupCheck.setText("사용하실 수 있는 아이디입니다");
            }
        }
        catch(Exception e)
        {
            dialog.dismiss();
            System.out.println("Exception : " + e.getMessage());

        }
    }
}
