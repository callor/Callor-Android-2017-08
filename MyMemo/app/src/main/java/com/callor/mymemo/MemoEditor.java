package com.callor.mymemo;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MemoEditor extends AppCompatActivity {

    TextInputEditText txt_memo = null;
    Button btn_save ;
    Intent mainIntent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_editor);

        txt_memo = (TextInputEditText)findViewById(R.id.txt_memo);
        btn_save = (Button)findViewById(R.id.btn_save);

        // 나를 호출한 intent 정보
        mainIntent = getIntent();

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // mainIntent에게 memo라는 이름으로 txt_memo 에 입력한 text를
                // 되돌려 주겠다.
                mainIntent.putExtra("memo",txt_memo.getText().toString());

                // RESULT_OK 값을 세팅하고, return
                setResult(RESULT_OK,mainIntent);

                // 현재 Activity를 닫는다.
                finish();
            }
        });
    }
}
