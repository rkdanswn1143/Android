package com.example.checkboxtest;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ToogleActivty extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_toogle_activty);

        imageView = (ImageView) findViewById(R.id.imageView3);
        imageView.setVisibility(View.INVISIBLE);
    }

    public void OnToggleClicked(View view){
        boolean on = ((ToggleButton)view).isChecked();
        if(on){
            imageView.setImageResource(R.drawable.pic);
            imageView.setVisibility(View.VISIBLE);
        }
        else{
            imageView.setImageResource(0);
            imageView.setVisibility(View.INVISIBLE);
        }
    }
}