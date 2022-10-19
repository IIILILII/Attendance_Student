package com.example.linearlayoutexample;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class subAcrivity2 extends AppCompatActivity {

    Button btn_home_2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_acrivity2);

        btn_home_2 = findViewById(R.id.btn_home_2);
        btn_home_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(subAcrivity2.this, MainActivity.class);
                startActivity(intent); // 액티비티 이동
            }
        });

    }
}