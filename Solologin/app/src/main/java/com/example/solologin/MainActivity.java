package com.example.solologin;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUserName;
    private EditText editTextpassword;
    private EditText editPhone;
    private TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editTextpassword = findViewById(R.id.editTextText2);
        editTextUserName = findViewById(R.id.editTextText);
        editPhone = findViewById(R.id.editTextText3);
        info = findViewById(R.id.textView4);
    }
    public void onClicked(View v){
        String username = editTextUserName.getText().toString();
        String password = editTextpassword.getText().toString();
        String phone = editPhone.getText().toString();

        // 예시로 아이디가 "user"이고 비밀번호가 "password"인 경우에만 로그인 허용
        if (username.equals("user") && password.equals("password")) {
            String userInfo = "아이디: " + username + "\n패스워드: " + password + "\n전화번호: " + phone;
            info.setText(userInfo);
        } else {
            // 아이디 또는 비밀번호가 일치하지 않는 경우에는 로그인 실패 메시지를 출력
            info.setText("아이디 또는 비밀번호가 올바르지 않습니다.");
        }
    }