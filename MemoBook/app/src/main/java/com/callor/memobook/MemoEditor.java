package com.callor.memobook;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.callor.memobook.db.DBHelper;

public class MemoEditor extends AppCompatActivity {

    TextInputEditText txt_memo = null;
    Intent mainIntent = null;
    long _id = -1 ;

    private int keyCode;
    private KeyEvent event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_memo_editor);

        txt_memo = (TextInputEditText)findViewById(R.id.txt_memo);
        mainIntent = getIntent(); // 나를 호출한 Activity를 가리킨다
        _id = mainIntent.getLongExtra("_id",-1);
        String strMemo = mainIntent.getStringExtra("memo");
        txt_memo.setText(strMemo);

    }

    // 뒤로가기 키 하드웨어 설정
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        // 새로 작성
//        if( _id == -1 ) {

            mainIntent.putExtra("memo",txt_memo.getText().toString());
            mainIntent.putExtra("_id",_id); // 수정모드이면 _id != -1, 신규 _id == -1
            setResult(RESULT_OK,mainIntent); // 값을 RETURn
            finish(); // 현재 화면 닫기

//        } else {


//            mainIntent.putExtra("memo",txt_memo.getText().toString());
//            mainIntent.putExtra("_id",_id);
//            setResult(RESULT_OK,mainIntent);
//            finish();
//        }

    }

    // 하드웨어 키를 가로채기
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        return true;
//    }
}
