package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    EditText eText1;
    EditText eText2;
    TextView eText3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        eText1 = (EditText) findViewById(R.id.editTextText);
        eText2 = (EditText) findViewById(R.id.editTextText2);
        eText3 = (TextView) findViewById(R.id.textView4);
    }

    public void cal_plus(View e) {
        String s1 = eText1.getText().toString();
        String s2 = eText2.getText().toString();
        int result = Integer.parseInt(s1) + Integer.parseInt(s2);
        eText3.setText("" + result);
    }

    public void cal_Minus(View e) {
        String s1 = eText1.getText().toString();
        String s2 = eText2.getText().toString();
        int result = Integer.parseInt(s1) - Integer.parseInt(s2);
        eText3.setText("" + result);
    }

    public void cal_gob(View e) {
        String s1 = eText1.getText().toString();
        String s2 = eText2.getText().toString();
        int result = Integer.parseInt(s1) * Integer.parseInt(s2);
        eText3.setText("" + result);
    }

    public void cal_na(View e) {
        String s1 = eText1.getText().toString();
        String s2 = eText2.getText().toString();
        Double result = Double.parseDouble(s1) / Double.parseDouble(s2);
        eText3.setText("" + String.format("%.3f", result));

    }

    public void cal_namu(View e) {
        String s1 = eText1.getText().toString();
        String s2 = eText2.getText().toString();
        int result = Integer.parseInt(s1) % Integer.parseInt(s2);
        eText3.setText("" + result);
    }
}