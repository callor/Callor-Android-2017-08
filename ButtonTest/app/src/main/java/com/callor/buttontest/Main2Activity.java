package com.callor.buttontest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    Button btn01;
    Button btn02;
    Button btn03;

    Intent intent ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // 호출한 곳의 정보를 수신하기 위한 객체
        intent = getIntent();

        btn01 = (Button)findViewById(R.id.btn_01);
        btn02 = (Button)findViewById(R.id.btn_02);
        btn03 = (Button)findViewById(R.id.btn_03);

        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);
        btn03.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        // 나를 호출한 intent에게 리턴할 값 설정
        intent.putExtra("name","홍길동");
        intent.putExtra("age",23);
        setResult(RESULT_OK,intent);
        finish(); // 현재 화면 닫기
    }
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        String msg = "";
        if(viewId == R.id.btn_01) {
            msg = "1번 버튼";
        } else if (viewId == R.id.btn_02) {
            msg = "2번 버튼";
        } else if (viewId == R.id.btn_03) {
            msg = "3번 버튼";
        }
        Toast.makeText(Main2Activity.this,msg + " 클릭",Toast.LENGTH_SHORT).show();
    }

}
