package com.callor.todolist;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.callor.todolist.Adapter.DBAdapter;
import com.callor.todolist.DB.DBContract;
import com.callor.todolist.DB.DBHelper;
import com.callor.todolist.DB.ToDoListVO;
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

            //--------------------------------------------
            // Recycler View DB 연동하는
            // 데이터를 읽어 cursor로 추출하는 과정
            DBHelper dbHelper = new DBHelper(MainActivity.this);
            Cursor cursor = dbHelper.getDbAll();

            // cursor를 DBAdapter에 보내주고
            DBAdapter dbAdapter = new DBAdapter(MainActivity.this,cursor);

            // Recycler 모양을 관리할 관리자를 설정
            LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);

            // 관리자와 Recycler를 연동
            contenBindig.listBody.setLayoutManager(manager);

            // DBAdapter와 Recycler를 연동
            contenBindig.listBody.setAdapter(dbAdapter);
            //-------------------------------------------------

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

        // 8-23
        DBHelper dbHelper = new DBHelper(MainActivity.this);
        ToDoListVO vo = new ToDoListVO();

        // DB에 Insert 값을 VO에 할당(저장)
        vo.setToDoMemo(contenBindig.tvMemo.getText().toString());

        long newId = dbHelper.saveData(vo);

        String okMessage = "DB Insert OK \n" +
                "ID:" +String.valueOf(newId);
//        Snackbar.make(view,okMessage,Snackbar.LENGTH_SHORT).show();

        // 입력창 비우기
        contenBindig.tvMemo.setText("");

        // 추가후 새로 갱신
        dbHelper = new DBHelper(MainActivity.this);
        DBAdapter ad = new DBAdapter(MainActivity.this,dbHelper.getDbAll());
        contenBindig.listBody.setAdapter(ad);

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
