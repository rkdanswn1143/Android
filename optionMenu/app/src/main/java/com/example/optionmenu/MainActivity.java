package com.example.optionmenu;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    LinearLayout layout;
    TextView textView;

    @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.textView);
        registerForContextMenu(textView);


        layout = (LinearLayout) findViewById(R.id.layout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);

        menu.add(0,1,0,"배경색 :RED");
        menu.add(0,2,0,"배경색 :GREEN");
        menu.add(0,3,0,"배경색 :BLUE");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       int id = item.getItemId();
       if(id == 1){
           layout.setBackgroundColor(Color.BLUE);
           return true;
       }
       else if(id == 2){
           layout.setBackgroundColor(Color.GREEN);
           return true;
        }
        if(id == 3){
            layout.setBackgroundColor(Color.RED);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}