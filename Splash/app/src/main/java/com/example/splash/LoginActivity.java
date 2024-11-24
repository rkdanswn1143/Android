package com.example.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextUserName;
    private EditText editTextpassword;
    private TextView statusText;
    private Button loginBtn;
    ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        editTextpassword = findViewById(R.id.editTextText2);
        editTextUserName = findViewById(R.id.editTextText);
        statusText = findViewById(R.id.textView2);
        loginBtn = findViewById(R.id.button);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUserName.getText().toString();
                String password = editTextpassword.getText().toString();


                Intent intent = new Intent(LoginActivity.this,LoginSuccesActivity.class);
                intent.putExtra("ID", username);
                intent.putExtra("Password", password);
                launcher.launch(intent);
            }
        });
        launcher = registerForActivityResult(new ActivityResultContract.StartActivityForResult(),

                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        statusText.setText(data.getStringExtra("status"));
                    }
                });
    }
}
