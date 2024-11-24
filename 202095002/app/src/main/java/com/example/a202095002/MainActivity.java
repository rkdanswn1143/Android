package com.example.a202095002;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button btnfood = findViewById(R.id.button);
        Button btnani = findViewById(R.id.button2);
        Button btnhome = findViewById(R.id.button3);
        Button btnexit = findViewById(R.id.button4);

        btnfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FoodActivity.class);
                startActivity(intent);
            }
        });

        btnani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AnimalActivity.class);
                startActivity(intent);
            }
        });

        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void onClicked(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://computer.silla.ac.kr"));
        startActivity(intent);
    }
}
