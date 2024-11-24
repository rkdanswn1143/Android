package com.example.checkboxtest;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class thridActivity extends AppCompatActivity {
    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_thrid);

       layout = (LinearLayout)findViewById(R.id.layout);
    }
    public void OnRadioBtnCliked(View v){
        boolean checkd = ((RadioButton)v).isChecked();

        switch (v.getId()){
            case R.id.RedBtn:
                if(checkd){
                    layout.setBackgroundColor(Color.RED);
                }
                break;
            case R.id.blueBtn:
                if(checkd)
                {
                    layout.setBackgroundColor(Color.BLUE);
                }
                break;
        }
    }
}