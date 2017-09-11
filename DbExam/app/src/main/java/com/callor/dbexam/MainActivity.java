package com.callor.dbexam;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.callor.dbexam.databinding.ActivityMainBinding;
import com.callor.dbexam.databinding.ContentMainBinding;
import com.callor.dbexam.db.DBHelper;
import com.callor.dbexam.db.MemberListView;
import com.callor.dbexam.db.MemberVO;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding ;
    ContentMainBinding contentBinding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        contentBinding = mainBinding.contentMain ;

        contentBinding.btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUserId = contentBinding.userId.getText().toString();
                if(strUserId.isEmpty()) {
                    Toast.makeText(MainActivity.this,"사용자 ID를 입력하세요",Toast.LENGTH_SHORT).show();
                    return;
                }

                String strPass = contentBinding.userPassword.getText().toString();
                String strPassCheck = contentBinding.userPasswordCheck.getText().toString();
                if(strPass.isEmpty()) {
                    Toast.makeText(MainActivity.this,"비밀번호를 입력하세요",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(strPassCheck.isEmpty()) {
                    Toast.makeText(MainActivity.this,"비밀번호를 한번 더 입력하세요",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!strPass.equals(strPassCheck)) {
                    Toast.makeText(MainActivity.this,
                            "비밀번호 항목이 서로 일치하지 않습니다",Toast.LENGTH_SHORT).show();
                    return;
                }

                // DB저장을 하는 부분

                // 저장할 값을 insert 에게 보내기 위해 VO에 담는다.
                MemberVO vo = new MemberVO();
                vo.setUserId(strUserId);
                vo.setUserPassword(strPass);
                vo.setUserEmail(contentBinding.userEmail.getText().toString());
                vo.setUserPhone(contentBinding.userPhone.getText().toString());

                DBHelper dbHelper = new DBHelper(MainActivity.this);
                long newId = dbHelper.insert(vo);

                if(newId > 0 ) {
                    Toast.makeText(MainActivity.this,
                            "회원정보 등록 성공 \n"
                                    +"Insert Key : " + String.valueOf(newId),Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this,"회원정보 등록 실패" ,Toast.LENGTH_SHORT).show();
                }
            }
        });

        contentBinding.btnIdCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId = contentBinding.userId.getText().toString();
                DBHelper dbHelper = new DBHelper(MainActivity.this);
                if(dbHelper.idCheck(userId)) {
                    Toast.makeText(MainActivity.this,"이미 등록된 ID 입니다",Toast.LENGTH_SHORT).show();
                }
            }
        });


        contentBinding.btnEmailCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = contentBinding.userEmail.getText().toString();
                DBHelper dbHelper = new DBHelper(MainActivity.this);
                if(dbHelper.emailCheck(userEmail)) {
                    Toast.makeText(MainActivity.this,"이미 등록된 Email 주소입니다",Toast.LENGTH_SHORT).show();
                }
            }
        });


        contentBinding.btnListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listIntent = new Intent(MainActivity.this, MemberListView.class);
                startActivity(listIntent);
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
}
