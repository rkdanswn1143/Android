package com.example.listview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        String[] data = {"APPLE","Apricot","Avocado","Banana","B;aclberry","Blueberry",
        "Cherry","Coconut","Cranberry","Grape Raisin","HoneyDew","JackFruit","Lemon","Lime","Mango","WaterMelon"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,data);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectItem = data[position];
                Toast.makeText(getApplicationContext(),"선택한 항목 "+selectItem,Toast.LENGTH_SHORT).show();
            }
        });
    }

}