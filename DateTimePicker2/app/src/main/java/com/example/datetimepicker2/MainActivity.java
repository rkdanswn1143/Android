package com.example.datetimepicker2;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView selectDateText,selectTimeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        selectDateText = findViewById(R.id.textView);
        selectTimeText = findViewById(R.id.textView2);

        Button selectDateButton = findViewById(R.id.button3);
        selectDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        Button selectTimeButton = findViewById(R.id.button4);
        selectTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });
    }

    private void showDatePickerDialog(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(calendar.YEAR);
        int month = calendar.get(calendar.MONTH);
        int dayOfMonth = calendar.get(calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view,int selectYear,int selectedMonth,
                                          int selectedDayOfMonth){
                        String selectDate = selectYear +"-"+ (selectedMonth+1)+"-"+selectedDayOfMonth;
                    selectDateText.setText("선택한 날짜 :"+selectDate);
                    }
                },year,month,dayOfMonth
        );
        datePickerDialog.show();
    }
    private void showTimePickerDialog(){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(calendar.HOUR_OF_DAY);
        int minute = calendar.get(calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String selectTime = hourOfDay + ":" + minute;
                        selectTimeText.setText("선택한 시간:"+selectTime);
                    }
                },hour,minute,true
        );
        timePickerDialog.show();
    }


}

