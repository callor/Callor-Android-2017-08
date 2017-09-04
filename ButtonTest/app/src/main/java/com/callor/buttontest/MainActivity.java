package com.callor.buttontest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn01 ;
    Button btn02 ;
    Button btn03 ;

    static final int REQ_CODE = 1;
    static final int REQ_EDIT_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        btn01 = (Button)findViewById(R.id.btn_01);
        btn02 = (Button)findViewById(R.id.btn_02);
        btn03 = (Button)findViewById(R.id.btn_03);

        btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // main2 화면을 호출하기 위한
                Intent main2 = new Intent(MainActivity.this,Main2Activity.class);
                // 단순 화면열기
//                startActivity(main2);
                // 다른 화면에 어떤 값을 전달하고 그 결과를 받고 싶을때,

//                int key =; // 여러개의 서브 화면을 열어야 할경우
                            // 키 값을 달리 하고,
                            // 결과를 수신할때 그 키값으로 어떤 화면이 호출되었는가 판별
                startActivityForResult(main2,REQ_CODE);
//                Toast.makeText(MainActivity.this,"1번 버튼 클릭",Toast.LENGTH_SHORT).show();
            }
        });
        btn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent main3 = new Intent(MainActivity.this,Main3Activity.class);
                main3.putExtra("name","이몽룡");
                main3.putExtra("age",20);
                startActivityForResult(main3,REQ_EDIT_CODE);
//
//
//     Toast.makeText(MainActivity.this,"2번 버튼 클릭",Toast.LENGTH_SHORT).show();
            }
        });
        btn03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"3번 버튼 클릭",Toast.LENGTH_SHORT).show();
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            if(requestCode == REQ_CODE) {
                String msg = "이름:" + data.getStringExtra("name") + "\n";
                msg += "나이:" + String.valueOf(data.getIntExtra("age",-1));
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
            if(requestCode == REQ_EDIT_CODE) {
                String msg = "이름은 : " + data.getStringExtra("name") + "\n";
                msg += "나이는 : " + String.valueOf(data.getIntExtra("age",-1));
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
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
