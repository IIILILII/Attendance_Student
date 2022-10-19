package com.example.linearlayoutexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class subActivity extends AppCompatActivity {
    private ListView list;
    Button bt_home;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        list = (ListView)findViewById(R.id.list);

        List<String> data = new ArrayList<>();

       ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,data);
       list.setAdapter(adapter);

       data.add("강의명, 강의 시간, 교수 들어가야 함 (누르면 버튼 형식으로 출석체크 할 수있게)");

        bt_home = findViewById(R.id.bt_home);
        bt_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(subActivity.this, MainActivity.class);
                startActivity(intent); // 액티비티 이동
            }
        });
    }
}