package com.callor.memobook;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.callor.memobook.adapter.MemoViewAdapter;
import com.callor.memobook.db.DBHelper;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView)findViewById(R.id.memo_list);
        DBHelper dbHelper = new DBHelper(MainActivity.this);
        Cursor cursor = dbHelper.getListAll();
        Log.d("Main",String.valueOf(cursor.getCount()));

        MemoViewAdapter adapter = new MemoViewAdapter(MainActivity.this,cursor);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 현재 화면에서 다른 화면(Activity) 열기 위한 절차
                // 다른화면을 객체로 생성
                Intent memoEditor = new Intent(MainActivity.this,MemoEditor.class);

                // 화면 열기
                // startActivity(memoEditor); // 단순히 화면을 열때
                                    // Intent req_code = 1
                startActivityForResult(memoEditor,1); // 다른 화면(Activity) 와 DATa를 교환하고자 할때

//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }


    // 호출된 Activity가 리턴하면 자동 호출되는 이벤트 메서드
    @Override
    protected void onActivityResult(int req, int res, Intent resultData) {
        super.onActivityResult(req,res,resultData);
        if(res == RESULT_OK) {
            if(req == 1) {

                // DB에 Insert 작업
                DBHelper dbHelper = new DBHelper(MainActivity.this);
                String strMemo = resultData.getStringExtra("memo");
                long newId = dbHelper.saveMemo(strMemo);

                // DB를 다시 읽어서 화면갱신
                MemoViewAdapter adapter = new MemoViewAdapter(MainActivity.this,
                        dbHelper.getListAll()
                        );
                recyclerView.setAdapter(adapter);
                Toast.makeText(MainActivity.this,
                        String.valueOf(newId)+" : "+strMemo, Toast.LENGTH_LONG).show();
            }
        }
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
