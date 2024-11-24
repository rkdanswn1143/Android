package com.example.todolist;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button addButton;
    private LinearLayout todoListLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextText);
        addButton = findViewById(R.id.button);
        todoListLayout = findViewById(R.id.todoLayout);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTodo();
            }
        });
    }

    private void addTodo() {
        String todoText = editText.getText().toString().trim();
        if (!todoText.isEmpty()) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(todoText);
            todoListLayout.addView(checkBox);
            editText.getText().clear();
        }
    }
}