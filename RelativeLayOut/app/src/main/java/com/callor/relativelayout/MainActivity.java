package com.callor.relativelayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    EditText txtNum = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNum = (EditText)findViewById(R.id.text1);
        Button btnCall = (Button)findViewById(R.id.btn_call);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Edit Text에서 전화번호 추출
                String callNum = txtNum.getText().toString();
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + callNum));
                startActivity(callIntent);

            }
        });

        // activity_main.xml 에 등록된 Button 사용준비
        Button btnViewActivity = (Button) findViewById(R.id.btn);

        // 무명 클래스를 이용한 리스너 등록
        btnViewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // MainActivity에서 SubActivity 를 열겠다 라고 선언
                Intent subIntent = new Intent(MainActivity.this, SubActivity.class);
                // 열어라
                startActivity(subIntent);
            }
        });


    }
}
