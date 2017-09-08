package com.callor.memobook;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.callor.memobook.adapter.MemoViewAdapter;
import com.callor.memobook.db.DBHelper;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    RecyclerView recyclerView = null;
    SwipeRefreshLayout swipeRefreshLayout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swip_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = (RecyclerView)findViewById(R.id.memo_list);
        DBHelper dbHelper = new DBHelper(MainActivity.this);
        Cursor cursor = dbHelper.getListAll();
        Log.d("Main",String.valueOf(cursor.getCount()));

        MemoViewAdapter adapter = new MemoViewAdapter(MainActivity.this,cursor);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

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

    // 화면이 다시 나타날때 호출되는 method
    @Override
    protected void onResume() {
        super.onResume();

        DBHelper dbHelper = new DBHelper(MainActivity.this);
        // DB를 다시 읽어서 화면갱신
        MemoViewAdapter adapter = new MemoViewAdapter(MainActivity.this,
                dbHelper.getListAll()
        );
        // 실제 필요없는 코드
        // 다른 어플뒤로 감춰졌다가 나타나면 오류가 발생하는 경우가 있어 넣어주는 코드
        recyclerView = (RecyclerView)findViewById(R.id.memo_list) ;
        recyclerView.setAdapter(adapter);
    }

    // 호출된 Activity가 리턴하면 자동 호출되는 이벤트 메서드
    @Override
    protected void onActivityResult(int req, int res, Intent resultData) {
        super.onActivityResult(req,res,resultData);
        if(res == RESULT_OK) {
            // Main에서 추가 버튼을 클릭하고 Edit를 호출한 후 되돌아 왔을때
            DBHelper dbHelper = new DBHelper(MainActivity.this);
            if(req == 1) {

                // DB에 Insert 작업
                String strMemo = resultData.getStringExtra("memo");
                dbHelper.saveMemo(strMemo);
//                long newId = dbHelper.saveMemo(strMemo);
                // 수정 후 되돌아 오면
            } else if(req == 2) {
                String strMemo = resultData.getStringExtra("memo");
                long _id = resultData.getLongExtra("_id",-1);
                Log.d("_ID",String.valueOf(_id));
                if(_id != -1) {
                    dbHelper.update(_id,strMemo);
                }
            }
        }
    }

    private final long FINISH_INTERVAL_TIME = 1000 ; // 1초
    private long backPressedTime = 0;
    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;
        if( intervalTime >= 0 && FINISH_INTERVAL_TIME >= intervalTime) {
            // 종료하기
            super.onBackPressed();
        } else {
            Toast.makeText(MainActivity.this,"한번더 뒤로가기를 누르면 앱을 종료합니다",Toast.LENGTH_LONG).show();
            backPressedTime = tempTime;
        }
    }

    ItemTouchHelper.SimpleCallback simpleCallback
            = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return true ;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            final int position = viewHolder.getAdapterPosition();
            final long _id = Long.valueOf(viewHolder.itemView.getTag().toString());

            AlertDialog.Builder alertDialogBulder
                    = new AlertDialog.Builder(MainActivity.this);

            alertDialogBulder.setTitle("메모삭제"); // 제목표시
            alertDialogBulder.setMessage("정말!! 메모를 삭제하시겠습니까")
                    .setCancelable(false)
                    .setPositiveButton("삭제", // ok 버튼의 표시이름
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    DBHelper dbHelper = new DBHelper(MainActivity.this);
                                    dbHelper.dbDelete(_id);

                                    Cursor cursor = dbHelper.getListAll();
                                    MemoViewAdapter adapter = new MemoViewAdapter(MainActivity.this,cursor);
                                    recyclerView.setAdapter(adapter);
                                }
                            })
                    .setNegativeButton("취소",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    // 취소버튼을 클릭했을때 할일..
                                }
                            });
            AlertDialog alertDialog = alertDialogBulder.create();
            alertDialog.show();
            recyclerView.getAdapter().notifyDataSetChanged();
        }
    };


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

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }
}
