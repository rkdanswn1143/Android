package com.example.logintest;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextusername;
    private EditText editTextpasswd;
    private EditText editTextPhoneNumber;
    private TextView TextViewUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editTextusername = findViewById(R.id.editText);
        editTextpasswd = findViewById(R.id.editPasswd);
        editTextPhoneNumber = findViewById(R.id.editPhone);
        TextViewUserInfo = findViewById(R.id.textView);

    }

    public void onSignupButtonClick (View view){
        String name= editTextusername.getText().toString();
        String passwd = editTextpasswd.getText().toString();
        String phonenumber = editTextPhoneNumber.getText().toString();


        String userInfo = "아이디: " + name+"\n패스워드"+passwd+"\n전화번호"+phonenumber;
        TextViewUserInfo.setText(userInfo);

    }
}