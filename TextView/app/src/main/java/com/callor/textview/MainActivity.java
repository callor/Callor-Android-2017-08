package com.callor.textview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // xml 에 있는 textview를 java에서 사용하기 위한 객체 선언
        textView = (TextView)findViewById(R.id.textview1);
        textView.setText("우리나라만세");


    }
}
