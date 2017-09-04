package com.callor.binding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.callor.binding.databinding.ActivityMainBinding;
import com.callor.binding.databinding.ContentMainBinding;

public class MainActivity extends AppCompatActivity {

    // activity_main.xml 파일을 기준으로 자동으로 생성된 binding class
    ActivityMainBinding binding ;
    ContentMainBinding contentMainBinding ;

//    TextInputEditText txt_name;
//    TextInputEditText txt_tel;
//    TextInputEditText txt_pass;
//    TextInputEditText txt_pass_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        txt_name = (TextInputEditText)findViewById(R.id.txt_layout_name);

//        setContentView(R.layout.activity_main);

        // 객체선언
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        contentMainBinding = binding.contentMain;

        contentMainBinding.txtName.setText("홍길동");
        contentMainBinding.txtTel.setText("01096528085");
        contentMainBinding.txtPass.setText("1234");
        contentMainBinding.txtPassText.setText("abcde");

        contentMainBinding.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = "이름:" + contentMainBinding.txtName.getText().toString() +"\n";
                msg += "전화번호:" + contentMainBinding.txtTel.getText().toString()+"\n";
                msg += "비밀번호1" + contentMainBinding.txtPass.getText().toString()+"\n";
                msg += "비밀번호2" + contentMainBinding.txtPassText.getText().toString();
                Snackbar.make(view,msg,Snackbar.LENGTH_LONG).show();
            }
        });


//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(binding.toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
