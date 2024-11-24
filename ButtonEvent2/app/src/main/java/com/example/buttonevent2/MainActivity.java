package com.example.buttonevent2;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView clothingImageView;
    private Button[] buttons = new Button[4];
    private int[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};
    private Integer[] btnIds = {R.id.button, R.id.button2, R.id.button3, R.id.button4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        clothingImageView = (ImageView) findViewById(R.id.clothingImageView);
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = (Button) findViewById(btnIds[i]); // btnIds[i]로 수정
        }
        for (int i = 0; i < buttons.length; i++) {
            final int index = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clothingImageView.setBackgroundColor(colors[index]);
                }
            });
        }
    }
}

