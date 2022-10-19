package com.example.linearlayoutexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    BluetoothAdapter mBluetoothAdapter;
    Button btnBluetoothOn, btnBluetoothOff;
    Button under_btn_1, under_btn_2;
    TextView tvBluetoothStatus;

    final static int BT_REQUEST_ENABLE = 1;
    final static int BT_MESSAGE_READ = 2;
    final static int BT_CONNECTING_STATUS = 3;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        btnBluetoothOn = findViewById(R.id.btnBluetoothOn);
        btnBluetoothOff = findViewById(R.id.btnBluetoothOff);
        tvBluetoothStatus = findViewById(R.id.tvBluetoothStatus);

        under_btn_1 = findViewById(R.id.under_btn_1);
        under_btn_2 = findViewById(R.id.under_btn_2);

        //블루투스 켜기 메소드 호출
        btnBluetoothOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blueToothOn();
            }
        });
        //블루투스 끄기 메소드 호출
        btnBluetoothOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blueToothOff();
            }
        });

        under_btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, subActivity.class);
                startActivity(intent); // 액티비티 이동
            }

        });
        under_btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, subAcrivity2.class);
                startActivity(intent); // 액티비티 이동
            }

        });
    }

    @SuppressLint("MissingPermission")
    public void blueToothOn() {
        if (mBluetoothAdapter == null){
            Toast.makeText(getApplicationContext(),"블루투스를 지원하지 않는 기기 입니다.",Toast.LENGTH_SHORT).show();
            tvBluetoothStatus.setText("NonActive");
        }
        else if (mBluetoothAdapter.isEnabled()){
            Toast.makeText(getApplicationContext(),"블루투스가 이미 켜져 있습니다.",Toast.LENGTH_SHORT).show();
        }
        else {
            //Toast.makeText(getApplicationContext(), "Bluetooth On",Toast.LENGTH_SHORT).show();
            //Ask user
            Intent intentBluetoothEnable = new Intent(mBluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intentBluetoothEnable, BT_REQUEST_ENABLE);

            //Intercept Status changed (by.broadcast)
            IntentFilter BTIntent = new IntentFilter(mBluetoothAdapter.ACTION_STATE_CHANGED);
            registerReceiver(mBroadCastReceiver, BTIntent);
        }
    }
    @SuppressLint("MissingPermission")
    public void blueToothOff() {
        if (!mBluetoothAdapter.isEnabled()){
            Toast.makeText(getApplicationContext(),"Already OFF",Toast.LENGTH_SHORT).show();
        }
        else if (mBluetoothAdapter.isEnabled()){
            //Toast.makeText(getApplicationContext(), "Bluetooth Off",Toast.LENGTH_SHORT).show();
            mBluetoothAdapter.disable();
        }
    }
    private final BroadcastReceiver mBroadCastReceiver = new BroadcastReceiver(){
      public void onReceive(Context context, Intent intent){
          String action = intent.getAction();

          if (action.equals(mBluetoothAdapter.ACTION_STATE_CHANGED)){
              final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, mBluetoothAdapter.ERROR);

              switch (state){
                  case BluetoothAdapter.STATE_ON:
                      Toast.makeText(getApplicationContext(),"블루투스 ON", Toast.LENGTH_SHORT).show();
                      tvBluetoothStatus.setText("Active");
                  case BluetoothAdapter.STATE_OFF:
                      Toast.makeText(getApplicationContext(),"블루투스 OFF",Toast.LENGTH_SHORT).show();
                  case BluetoothAdapter.STATE_TURNING_ON:
                      Toast.makeText(getApplicationContext(),"블루투스 Turning ON",Toast.LENGTH_SHORT).show();
                      break;
                  case BluetoothAdapter.STATE_TURNING_OFF:
                      Toast.makeText(getApplicationContext(),"블루투스 Turning OFF",Toast.LENGTH_SHORT).show();
                      break;

              }
          }
      }
    };
    @Override
    protected void onDestroy() {
        Toast.makeText(getApplicationContext(),"onDestroy called", Toast.LENGTH_SHORT).show();
        Log.d("onDestroy", "onDestroy called");
        super.onDestroy();
        unregisterReceiver(mBroadCastReceiver);
    }
    }