package com.example.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class LoginSuccesActivity extends AppCompatActivity {


    private TextView displayTextView,displayPasswordTextView,statusTextView;
    String id,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_succes);
        displayTextView = findViewById(R.id.textView3);
        displayPasswordTextView = findViewById(R.id.textView5);
        statusTextView = findViewById(R.id.textView6);

        Intent intent = getIntent();
        if(intent!=null){
            id = intent.getStringExtra("ID");
            password = intent.getStringExtra("Password");

            displayTextView.setText("아이디 :"+id);
            displayPasswordTextView.setText("비밀번호 :"+password);

        }
    }

    public void check(View e){
        Intent intent = new Intent();
        if(isUserValid(id,password)){
            intent.putExtra("status","로그인 성공!");
        }
    }
}