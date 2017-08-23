package com.callor.todolist;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.callor.todolist.Adapter.DBAdapter;
import com.callor.todolist.DB.DBContract;
import com.callor.todolist.DB.DBHelper;
import com.callor.todolist.databinding.ActivityMainBinding;
import com.callor.todolist.databinding.ContentMainBinding;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


        ActivityMainBinding mainBinding ;
        ContentMainBinding contenBindig ;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
            contenBindig = mainBinding.contentMain ;
            setSupportActionBar(mainBinding.toolbar);

            DBAdapter dbAdapter = new DBAdapter();
            LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
            contenBindig.listBody.setLayoutManager(manager);
            contenBindig.listBody.setAdapter(dbAdapter);
            contenBindig.btnInsert.setOnClickListener(this);

            mainBinding.fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });

        } // onCreate
    // 확인 버튼을 클릭하면 실행할 이벤트 핸들링
//    @Override

    public void onClick(View view) {
        // 할일 EditBox에 아무것도 입력하지 않았으면
        if(contenBindig.tvMemo.getText().toString().isEmpty()) {
            Snackbar.make(view,"할일을 입력한 후 확인 버튼을 눌러 주세요",Snackbar.LENGTH_LONG).show();
            return;
        }
        // 할일을 입력되었으면 Db Insert 작업 시작
        //1. 현재 날짜와 시각을 구하기
        //   1970. 1. 1 00:00:00 초부터 흐른 밀리초 값
        long now = System.currentTimeMillis();

        // 밀리초 값을 Date 형으로 변환하는
        Date curDate = new Date(now);

        // 날짜형식의 값을 원하는 형태의 문자열 날짜로 변환시키는 방법
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String getDate = dateFormat.format(curDate);

        // 날짜형식의 값을 원하는 형태의 문자열 시각으로 변환시키는 방법
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:SS");
        String getTime = timeFormat.format(curDate);

        String strMemo = contenBindig.tvMemo.getText().toString();

        // DB에 Insert 준비
        DBHelper dbHelper = new DBHelper(MainActivity.this);
        SQLiteDatabase dbConn = dbHelper.getWritableDatabase();

        // SQL을 실행할 준비
        ContentValues sqlValues = new ContentValues();
        sqlValues.put(DBContract.ToDoTable.COL_S_DATE,getDate);
        sqlValues.put(DBContract.ToDoTable.COL_S_TIME,getTime);
        sqlValues.put(DBContract.ToDoTable.COL_MEMO,strMemo);

        // Insert SQL을 실행하는 문장
        long newId = dbConn.insert(DBContract.ToDoTable.TABLE_NAME,null,sqlValues);

        String okMessage = "DB Insert OK \n" +
                "ID:" +String.valueOf(newId);
        Snackbar.make(view,okMessage,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }


}
