package com.example.imgtest;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    int scaleTypeIndex=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.imageView);
    }
    public void onScale(View v){
        ImageView.ScaleType[] scaleTypes={
                ImageView.ScaleType.CENTER,
                ImageView.ScaleType.CENTER_CROP,
                ImageView.ScaleType.CENTER_INSIDE,
                ImageView.ScaleType.FIT_CENTER,
                ImageView.ScaleType.FIT_XY
        };
        imageView.setScaleType(scaleTypes[scaleTypeIndex]);
        scaleTypeIndex = (scaleTypeIndex+1) % scaleTypes.length;
    }
    public void changeRotation(View v){
        imageView.setRotation(imageView.getRotation()+45);
    }
    public void onAlpha(View v){
        float alpha = imageView.getAlpha();
        alpha = (alpha == 1.0f) ? 0.5f : 1.0f;
        imageView.setAlpha(alpha);
    }
}