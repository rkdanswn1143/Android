package com.example.tablelayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView textResult;

    EditText editNum1, editNum2;
    Button[] btnNum = new Button[10];

    Integer[] btnIds={R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5,
            R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};

    Button[] btnOp = new Button[4];
    Integer[] btnOpIds = {R.id.btnPlus, R.id.btnMinus, R.id.btnDiv, R.id.btnMul};
    int i;

    String num1, num2, result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        textResult  = (TextView) findViewById(R.id.results);
        editNum1 = (EditText) findViewById(R.id.editText1);
        editNum2 = (EditText) findViewById(R.id.editText2);

        for(i=0; i< btnIds.length; i++){
            btnNum[i] = (Button) findViewById(btnIds[i]);
        }

        for(i=0; i< btnOpIds.length; i++){
            btnOp[i] = (Button) findViewById(btnOpIds[i]);
        }

        for(i=0; i<btnIds.length; i++) {
            final int index;
            index = i;
            btnNum[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (editNum1.isFocused() == true) {
                        num1 = editNum1.getText().toString()
                                + btnNum[index].getText().toString();
                        editNum1.setText(num1);
                    }
                    if (editNum2.isFocused() == true) {
                        num2 = editNum2.getText().toString()
                                + btnNum[index].getText().toString();
                        editNum2.setText(num2);
                    } else  Toast.makeText(getApplicationContext(),
                            "칸을 선택해 주세요", Toast.LENGTH_SHORT).show();
                }
            });
        }

        for(i=0; i<btnOp.length; i++) {
            final int index;
            index = i;
            btnOp[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Cal(index);
                }
            });
        }


    }

    private void Cal(int index) {
        Double n1 = Double.parseDouble(num1);
        Double n2 = Double.parseDouble(num2);
        switch (index) {
            case 0:
                textResult.setText("" + (n1 + n2));
                break;
            case 1:
                textResult.setText("" + (n1 - n2));
                break;
            case 2:
                textResult.setText("" + String.format("%.3f", (n1 / n2)));
                break;
            case 3:
                textResult.setText("" + (n1 * n2));
                break;
        }
    }


}