package com.callor.button;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText text1 ;
    Button btnOk ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = (EditText)findViewById(R.id.edittext1);
        btnOk = (Button)findViewById(R.id.btnOk);

        // 버튼 click 이벤트 핸들러
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this,text1.getText(),Toast.LENGTH_SHORT).show();

                /*
                Snackbar sb = Snackbar.make(view,text1.getText(),Snackbar.LENGTH_INDEFINITE);

                // snackbar에 버튼 설정
                sb.setAction("YES", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(MainActivity.this,"반갑습니다",Toast.LENGTH_SHORT).show();
                            }
                });
                sb.show();
                */
                // snackbar에 버튼 설정
                Snackbar.make(view,text1.getText(),Snackbar.LENGTH_INDEFINITE)
                .setAction("YES", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this,"반갑습니다",Toast.LENGTH_SHORT).show();
                    }
                })
                .show();


                /*
                sb.setAction("YES",new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this,"반갑습니다",Toast.LENGTH_SHORT).show();
                    }
                });
                */


            }
        });


    }
}
