package com.callor.clock;

import android.app.IntentService;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 현재 시각추출
        long now = System.currentTimeMillis();
        // 날짜 객체 생성
        Date date = new Date(now);

        // 시간만 추출하기
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH");
        // 분만 추출
        SimpleDateFormat minFormat = new SimpleDateFormat("mm");
        int curtime = Integer.valueOf(timeFormat.format(date));
        int curMin = Integer.valueOf(minFormat.format(date));

        // 시계다이알로그 띄우기
        TimePickerDialog timePicker = new TimePickerDialog(this,listner,curtime,curMin,false);
        timePicker.show();

    }

    private TimePickerDialog.OnTimeSetListener listner = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int housrOfDay, int minute) {
            Toast.makeText(MainActivity.this,
                    housrOfDay + "시"
                    + minute + "분",Toast.LENGTH_LONG).show();
        }
    };
}
