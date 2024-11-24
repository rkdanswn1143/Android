package com.example.a202095002;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class AnimalActivity extends AppCompatActivity {
    private ImageView imageView;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);

        imageView = findViewById(R.id.imageView3);
        radioGroup = findViewById(R.id.Radio);

        // 앱 시작 시 이미지뷰를 보이지 않도록 설정
        imageView.setVisibility(View.INVISIBLE);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton radioButton = findViewById(checkedId);
                if (radioButton != null) {

                    if (checkedId == R.id.rBtnDog) {
                        imageView.setImageResource(R.drawable.dog);
                    } else if (checkedId == R.id.rBtnCat) {
                        imageView.setImageResource(R.drawable.cat);
                    } else if (checkedId == R.id.rBtnRabbit) {
                        imageView.setImageResource(R.drawable.rabbit);
                    }

                    // 라디오 버튼을 선택할 때마다 이미지뷰를 보이도록 설정
                    imageView.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
