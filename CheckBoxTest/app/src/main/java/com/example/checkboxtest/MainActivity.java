package com.example.checkboxtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class    MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Button btnCheck = (Button) findViewById(R.id.btnCheck);
        Button btnCheck1 = (Button) findViewById(R.id.button3);
        Button btnCheck2 = (Button)findViewById(R.id.button) ;
        Button btnCheck3 = (Button)findViewById(R.id.button2);
        Button btnCheck4 = (Button)findViewById(R.id.button5);
        Button btnCheck5 = (Button)findViewById(R.id.button7);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
                startActivity(intent);
            }
        });
        btnCheck1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),thridActivity.class);
                startActivity(intent);
            }
        });
        btnCheck2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ToogleActivty.class);
                startActivity(intent);
            }
        });
        btnCheck3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),StartActivity.class);
                startActivity(intent);
            }
        });
        btnCheck4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),TodoActivity.class);
                startActivity(intent);
            }
        });
        btnCheck5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),BanH.class);
                startActivity(intent);
            }
        });

    }
    private static final int REQUEST_CODE_SECOND_ACTIVITY = 1;
    // SecondActivity로부터 반환된 데이터 처리
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_SECOND_ACTIVITY) {
            if (resultCode == RESULT_OK) {
                // 반환된 문자열 가져오기
                String returnedText = data.getStringExtra("result");
                // 가져온 문자열 출력 또는 원하는 처리 수행
                // 예: Toast 메시지 출력
                Toast.makeText(this, returnedText, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
