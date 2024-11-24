package com.example.checkboxtest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {

    private RatingBar ratingBar;
    private TextView value;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_start);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        button = (Button) findViewById(R.id.button4);
        value = (TextView) findViewById(R.id.textView4);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                float rating = ratingBar.getRating();
                value.setText("score : " + rating);
            }
        });
    }
}