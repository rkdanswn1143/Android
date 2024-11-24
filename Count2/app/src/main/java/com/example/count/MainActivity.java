package com.example.count;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView textview;
    int count =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        textview =(TextView)findViewById(R.id.textView3);

    }
    public void countup(View e){
        count +=1;
        textview.setText(""+count);
    }
    public void countM (View e){
        count -=1;
        textview.setText(""+count);
    }
}