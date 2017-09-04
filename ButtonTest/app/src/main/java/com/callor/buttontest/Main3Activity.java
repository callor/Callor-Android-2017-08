package com.callor.buttontest;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main3Activity extends AppCompatActivity {

    TextInputEditText txt_name;
    TextInputEditText txt_age;

    Intent intent ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        txt_name = (TextInputEditText)findViewById(R.id.txt_name);
        txt_age = (TextInputEditText)findViewById(R.id.txt_age);

        intent = getIntent();
        String name = intent.getStringExtra("name");
        int age = intent.getIntExtra("age",-1);

        if(age > 0) {
            txt_name.setText(name);
            txt_age.setText(String.valueOf(age));
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();

        intent.putExtra("name",txt_name.getText().toString());
        intent.putExtra("age",Integer.valueOf(txt_age.getText().toString()));
        setResult(RESULT_OK,intent);
        finish();
    }
}
