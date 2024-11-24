package com.example.checkboxtest;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    ImageView imageView1,imageView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        imageView1=(ImageView)findViewById(R.id.imageView);
        imageView2=(ImageView)findViewById(R.id.imageView2);

        CheckBox chMeat =(CheckBox)findViewById(R.id.checkBox3);
        CheckBox cheCh = (CheckBox)findViewById(R.id.checkBox4);

        chMeat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    imageView1.setImageResource(R.drawable.sand1);
                    imageView1.setVisibility(View.VISIBLE);
                }
                else {
                    imageView1.setImageResource(0);
                    imageView1.setVisibility(View.INVISIBLE);
                }
            }
        });
        cheCh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    imageView2.setImageResource(R.drawable.sand2);
                    imageView2.setVisibility(View.VISIBLE);
                }
                else {
                    imageView2.setImageResource(0);
                    imageView2.setVisibility(View.INVISIBLE);
                }
            }
        });
    }



   /* public void oncheckboxClicked(View v){
        boolean checked = ((CheckBox) v).isChecked();

        switch (v.getId()){
            case R.id.checkBox3:
                if(checked) imageView1.setImageResource(R.drawable.sand1);
                else imageView1.setImageResource(0);
                break;
            case R.id.checkBox4:
                if(checked) imageView2.setImageResource(R.drawable.sand2);
                else imageView2.setImageResource(0);
                break;
        }
    }*/
}