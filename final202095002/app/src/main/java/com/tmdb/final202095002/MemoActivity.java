package com.tmdb.final202095002;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MemoActivity extends AppCompatActivity {

    private EditText etFilename, etContent;
    private Button btnRead, btnWrite, btnDelete;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo);


        etFilename = findViewById(R.id.et_filename);
        etContent = findViewById(R.id.et_content);
        btnRead = findViewById(R.id.btn_read);
        btnWrite = findViewById(R.id.btn_write);
        btnDelete = findViewById(R.id.btn_delete);

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readFile();
            }
        });

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeFile();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteFile();
            }
        });
    }

    private void readFile() {
        String filename = etFilename.getText().toString();

        if (filename.isEmpty()) {
            Toast.makeText(this, "파일 이름을 입력하세요", Toast.LENGTH_SHORT).show();
            return;
        }

        File file = new File(getFilesDir(), filename);
        if (file.exists()) {
            try (FileInputStream fis = openFileInput(filename)) {
                byte[] buffer = new byte[(int) file.length()];
                fis.read(buffer);
                String content = new String(buffer);
                etContent.setText(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "파일이 존재하지 않음", Toast.LENGTH_SHORT).show();
        }
    }

    private void writeFile() {
        String filename = etFilename.getText().toString();
        String content = etContent.getText().toString();

        if (filename.isEmpty()) {
            Toast.makeText(this, "파일 이름을 입력하세요", Toast.LENGTH_SHORT).show();
            return;
        }

        try (FileOutputStream fos = openFileOutput(filename, MODE_PRIVATE)) {
            fos.write(content.getBytes());
            Toast.makeText(this, "저장되었습니다", Toast.LENGTH_SHORT).show();
            etFilename.setText("");
            etContent.setText("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteFile() {
        String filename = etFilename.getText().toString();

        if (filename.isEmpty()) {
            Toast.makeText(this, "파일 이름을 입력하세요", Toast.LENGTH_SHORT).show();
            return;
        }

        File file = new File(getFilesDir(), filename);
        if (file.exists()) {
            if (file.delete()) {
                Toast.makeText(this, "삭제되었습니다", Toast.LENGTH_SHORT).show();
                etContent.setText("");
            } else {
                Toast.makeText(this, "삭제에 실패했습니다", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "없는 파일입니다", Toast.LENGTH_SHORT).show();
        }
    }
}
