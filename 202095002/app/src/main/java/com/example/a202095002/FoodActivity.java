package com.example.a202095002;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class FoodActivity extends AppCompatActivity {
    private List<String> selectedFoods;
    private TextView resultTextView;
    private CheckBox[] checkBoxes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        selectedFoods = new ArrayList<>();
        resultTextView = findViewById(R.id.textView3);

        Button completeButton = findViewById(R.id.button5);
        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSelectedFoods();
            }
        });

        Button resetButton = findViewById(R.id.button6);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetSelection();
            }
        });

        // 체크박스 배열 초기화
        checkBoxes = new CheckBox[]{
                findViewById(R.id.checkBox),
                findViewById(R.id.checkBox2),
                findViewById(R.id.checkBox3),
                findViewById(R.id.checkBox4),
                findViewById(R.id.checkBox5),
                findViewById(R.id.checkBox6)
        };
    }

    private void updateSelectedFoods() {
        selectedFoods.clear();

        for (CheckBox checkBox : checkBoxes) {
            if (checkBox.isChecked()) {
                selectedFoods.add(checkBox.getText().toString());
            }
        }

        updateResultText();
    }

    private void updateResultText() {
        StringBuilder result = new StringBuilder("결과 :");
        for (String food : selectedFoods) {
            result.append(" ").append(food);
        }
        resultTextView.setText(result.toString());
    }

    private void resetSelection() {
        for (CheckBox checkBox : checkBoxes) {
            checkBox.setChecked(false);
        }

        resultTextView.setText("결과 :");
    }
}
