package com.callor.textinputeditor;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.InterpolatorRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    TextInputLayout ti_1;
    TextInputLayout ti_2;
    AppCompatEditText et_1;
    AppCompatEditText et_2;
    AppCompatButton btnOk ;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ti_1 = (TextInputLayout) findViewById(R.id.ti_1);
        ti_2 = (TextInputLayout) findViewById(R.id.ti_2);

        et_1 = (AppCompatEditText) findViewById(R.id.et_1);
        et_2 = (AppCompatEditText) findViewById(R.id.et_2);

        btnOk = (AppCompatButton)findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date d = new Date();
                SimpleDateFormat hh = new SimpleDateFormat("HH");
                SimpleDateFormat mm = new SimpleDateFormat("mm");
                String sh = hh.format(d);
                String sm = mm.format(d);
                TimePickerDialog dialog
                        = new TimePickerDialog(MainActivity.this, listener, Integer.parseInt(sh),
                                Integer.parseInt(sm), false);
                dialog.show();
            }
        });

        ti_1.setCounterEnabled(true);
        ti_2.setCounterEnabled(true);


        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }



    private TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // 설정버튼 눌렀을 때
            Toast.makeText(getApplicationContext(), hourOfDay + "시 " + minute + "분", Toast.LENGTH_SHORT).show();
        }
    };


}
