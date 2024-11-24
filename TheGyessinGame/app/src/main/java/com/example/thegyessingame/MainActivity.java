package com.example.thegyessingame;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textView2;
    private EditText editTextText;
    private int answer;
    private ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextText = findViewById(R.id.editTextText);
        textView2 = findViewById(R.id.textView2);
        imageView2 = findViewById(R.id.imageView2);


         Random random = new Random();
        answer = random.nextInt(100);
    }
    public void GuessBtn(View e) {
        String guessNum = editTextText.getText().toString();
        int guessNum2 = Integer.parseInt(guessNum);
        if (guessNum2 > answer) {
            String result = "Low!!";
            textView2.setText(result);
            imageView2.setImageResource(R.drawable.rabbit);
        } else if (guessNum2 < answer) {
            String result = "High!!!";
            textView2.setText(result);
            imageView2.setImageResource(R.drawable.dog);
        } else {
            String result = "정답입니다!";
            textView2.setText(result);
            imageView2.setImageResource(R.drawable.horse);
        }
    }
}
