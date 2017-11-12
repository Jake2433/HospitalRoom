package com.example.kyb24.hospitalroom;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kyb24.hospitalroom.Bluetooth.DeviceListActivity;

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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    private ImageView ClickedIvP1;
    private ImageView ClickedIvP2;
    private ImageView ClickedIvP3;
    private ImageView ClickedIvP4;
    private ImageView ClickedIvP5;
    private ImageView ClickedIvP6;

    private String movedName;
    private int currentPosition;

    private static final int CONNECTED = 1;
    private static final int DISCONNECTED = 2;
    private static final int MESSAGE_READ = 3;
    private static final int MESSAGE_WRITE = 4;

    private static final int REQUEST_CONNECT_DEVICE = 1;

    private ArrayAdapter<String> messageAdapter;
    private String value;
    private TextView tv;

    private BluetoothAdapter bluetoothAdapter;
    private BluetoothChat chat;
    private Button buttonConnect;

    private UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_room);

        if ((bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()) == null) {
            Toast.makeText(Room.this, "Bluetooth is not supported", Toast.LENGTH_SHORT).show();
        }
        // ACCESS_COARSE_LOCATION or ACCESS_FINE_LOCATION permission is required for Bluetooth from Marshmallow
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }

        buttonConnect = (Button) findViewById(R.id.button_connect);
        buttonConnect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (bluetoothAdapter.isEnabled() == false) {
                    // Request to enable bluetooth
                    startActivity(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE));
                    return;
                }
                if (chat == null) {
                    // Launch DeviceListActivity to search bluetooth device
                    startActivityForResult(new Intent(Room.this, DeviceListActivity.class), REQUEST_CONNECT_DEVICE);
                } else {
                    chat.close();
                }
            }
        });

        messageAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        tv = (TextView)findViewById(R.id.tv_value);

        ClickedBtnP1 = (Button) findViewById(R.id.Btn_FirstP);
        ClickedBtnP2 = (Button) findViewById(R.id.Btn_SecondP);
        ClickedBtnP3 = (Button) findViewById(R.id.Btn_ThirdP);
        ClickedBtnP4 = (Button) findViewById(R.id.Btn_FourthP);
        ClickedBtnP5 = (Button) findViewById(R.id.Btn_FifthP);
        ClickedBtnP6 = (Button) findViewById(R.id.Btn_SixthP);

        ClickedIvP1 = (ImageView) findViewById(R.id.Iv_FirstP);
        ClickedIvP2 = (ImageView) findViewById(R.id.Iv_SecondP);
        ClickedIvP3 = (ImageView) findViewById(R.id.Iv_ThirdP);
        ClickedIvP4 = (ImageView) findViewById(R.id.Iv_FourthP);
        ClickedIvP5 = (ImageView) findViewById(R.id.Iv_FifthP);
        ClickedIvP6 = (ImageView) findViewById(R.id.Iv_SixthP);

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

        ClickedIvP1.setOnClickListener(this);
        ClickedIvP2.setOnClickListener(this);
        ClickedIvP3.setOnClickListener(this);
        ClickedIvP4.setOnClickListener(this);
        ClickedIvP5.setOnClickListener(this);
        ClickedIvP6.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (chat != null) {
            chat.close();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            // When DeviceListActivity returns with a device to connect
            case REQUEST_CONNECT_DEVICE:
                if (resultCode == Activity.RESULT_OK) {
                    // MAC address
                    String address = data.getExtras().getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);

                    BluetoothDevice device = bluetoothAdapter.getRemoteDevice(address);
                    BluetoothSocket socket;

                    try {
                        socket = device.createRfcommSocketToServiceRecord(uuid);
                    } catch (IOException e) {
                        break;
                    }
                    chat = new BluetoothChat(socket, handler);
                    chat.start();
                }
                break;
        }
    }

    // The Handler that gets information back from the BluetoothChat
    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CONNECTED:
                    buttonConnect.setText("Disconnect");
                    break;
                case DISCONNECTED:
                    buttonConnect.setText("Connect to bluetooth device");
                    chat = null;
                    break;
                case MESSAGE_READ:
                    try {
                        // Encoding with "EUC-KR" to read 한글
                        messageAdapter.add("< " + new String((byte[]) msg.obj, 0, msg.arg1, "EUC-KR"));
                        value = "< " + new String((byte[]) msg.obj, 0, msg.arg1, "EUC-KR");
                        tv.setText(value);

                    } catch (UnsupportedEncodingException e) {
                    }
                    break;
                case MESSAGE_WRITE:
                    messageAdapter.add("> " + new String((byte[]) msg.obj));
                    value = "> " + new String((byte[]) msg.obj);
                    break;
            }
        }
    };

    // This class connect with a bluetooth device and perform data transmissions when connected.
    private class BluetoothChat extends Thread {
        private BluetoothSocket socket;
        private Handler handler;
        private InputStream inputStream;
        private OutputStream outputStream;

        public BluetoothChat(BluetoothSocket socket, Handler handler) {
            this.socket = socket;
            this.handler = handler;
        }

        public void run() {
            try {
                socket.connect();
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
            } catch (Exception e) {
                close();
                return;
            }
            handler.obtainMessage(CONNECTED, -1, -1).sendToTarget();

            while (true) {
                try {
                    byte buffer[] = new byte[1024];

                    int bytes = 0;

                    // Read single byte until '\0' is found
                    for (; (buffer[bytes] = (byte) inputStream.read()) != '\0'; bytes++) ;
                    handler.obtainMessage(MESSAGE_READ, bytes, -1, buffer).sendToTarget();
                } catch (IOException e) {
                    close();
                    break;
                }
            }
        }

        public void close() {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                }
                handler.obtainMessage(DISCONNECTED, -1, -1).sendToTarget();
            }
        }

        public void send(byte[] buffer) {
            try {
                outputStream.write(buffer);
                outputStream.write('\n');
                handler.obtainMessage(MESSAGE_WRITE, buffer.length, -1, buffer).sendToTarget();
            } catch (IOException e) {
                close();
            }
        }
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
        /*    case R.id.Btn_blth:
                CliBluetooth(v);
                break;*/
            case R.id.Btn_FirstP:
                    myfunc(ClickedBtnP1, "0");
                break;
            case R.id.Iv_FirstP:
                myfunc(ClickedBtnP1,"0");
                break;

            case R.id.Btn_SecondP:
                    myfunc(ClickedBtnP2, "1");
                break;
            case R.id.Iv_SecondP:
                myfunc(ClickedBtnP2,"1");
                break;

            case R.id.Btn_ThirdP:
                    myfunc(ClickedBtnP3, "2");
                break;
            case R.id.Iv_ThirdP:
                myfunc(ClickedBtnP3,"2");
                break;

            case R.id.Btn_FourthP:
                    myfunc(ClickedBtnP4, "3");
                break;
            case R.id.Iv_FourthP:
                myfunc(ClickedBtnP4,"3");
                break;

            case R.id.Btn_FifthP:
                    myfunc(ClickedBtnP5,"4");
                break;
            case R.id.Iv_FifthP:
                myfunc(ClickedBtnP5,"4");
                break;

            case R.id.Btn_SixthP:
                    myfunc(ClickedBtnP6, "5");
                break;
            case R.id.Iv_SixthP:
                myfunc(ClickedBtnP6,"5");
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