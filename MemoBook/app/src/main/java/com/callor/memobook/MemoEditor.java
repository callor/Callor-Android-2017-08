package com.callor.memobook;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.callor.memobook.db.DBHelper;

public class MemoEditor extends AppCompatActivity {

    TextInputEditText txt_memo = null;
    Intent mainInten = null;
    long _id = -1 ;

    private int keyCode;
    private KeyEvent event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_memo_editor);

        txt_memo = (TextInputEditText)findViewById(R.id.txt_memo);
        mainInten = getIntent(); // 나를 호출한 Activity를 가리킨다
        _id = mainInten.getLongExtra("_id",-1);
        String strMemo = mainInten.getStringExtra("memo");
        txt_memo.setText(strMemo);

    }

    // 뒤로가기 키 하드웨어 설정
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        // 새로 작성
        if( _id == -1 ) {
            mainInten.putExtra("memo",txt_memo.getText().toString());
            setResult(RESULT_OK,mainInten); // 값을 RETURn
            finish(); // 현재 화면 닫기
        } else {
            DBHelper dbHelper = new DBHelper(MemoEditor.this);
            dbHelper.update(_id,txt_memo.getText().toString());
            finish();
        }
    }

    // 하드웨어 키를 가로채기
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        return true;
//    }
}
