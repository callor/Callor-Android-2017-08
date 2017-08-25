package com.callor.mymemo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.callor.mymemo.Adapter.MemoViewAdapter;
import com.callor.mymemo.db.DBHelper;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 데이터를 읽고
        DBHelper dbHelper = new DBHelper(MainActivity.this);
        Cursor cursor = dbHelper.getListAll();

        // Adapter에게 전달하고
        // Adapter를 recylerView와 연결하고
        recyclerView.setAdapter(new MemoViewAdapter(this,cursor));

        // Layout을 관리하는 관리자를 설정
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        // 관리자와 recyclerView를 연결
        recyclerView.setLayoutManager(layoutManager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // MemoActivity를 열어주는 동작
                Intent memoIntent = new Intent(MainActivity.this,MemoEditor.class);
//                startActivity(memoIntent);

                // sub Activity와 값을 주고 받기 위한
                // 1 : 임의의 값으로 내가 호출한 Intent인가를 나타내는 일종의 key
                startActivityForResult(memoIntent,1);

//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

            }
        });
    }

    // startActivityResult 로 sub Activity를 호출한 후에
    // subActivity가 return하는 값을 받기 위한 메서드
    protected void onActivityResult(int req, int res, Intent resultData) {
        super.onActivityResult(req,res,resultData);
        if(res == RESULT_OK) {
            if(req == 1) { // 내가 호출한 Activity가 맞냐?
                Toast.makeText(MainActivity.this,
                        resultData.getStringExtra("memo"),Toast.LENGTH_LONG).show();
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
