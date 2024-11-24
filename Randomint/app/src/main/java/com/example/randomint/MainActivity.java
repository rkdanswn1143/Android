package com.example.randomint;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txtRandInt;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtRandInt = (TextView) findViewById(R.id.textRandom);
        txtRandInt.setText("0");
    }

    public void generateRandomNumber(View view){
        Random rnd = new Random();
        int res = rnd.nextInt(100);


        txtRandInt.setText("" + res);
    }
    /*
    private TextView textViewRandomNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void generateRandomNumber(View view){
        Random random = new Random();
        int randomNumber = random.nextInt(100);


        textViewRandomNumber.setText("난수"+randomNumber);
    }
    */

}