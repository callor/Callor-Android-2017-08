package com.callor.viewtest;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.callor.viewtest.databinding.ActivityMainBinding;
import com.callor.viewtest.databinding.ContentMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding ;
    ContentMainBinding contentMainBinding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        contentMainBinding = mainBinding.contentMain;
        setSupportActionBar(mainBinding.toolbar);

//        Drawable draw = getResources().getDrawable(R.drawable.custom_progress);
//        contentMainBinding.prog01.setProgressDrawable(draw);

        contentMainBinding.btnOk01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int proValue = Integer.valueOf(contentMainBinding.txtValue.getText().toString());
                if(proValue >= 0 && proValue <= 100) {

                    // 진행상황을 세팅
                    contentMainBinding.prog01.setProgress(proValue);

                }
            }
        });

        mainBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);

//                snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
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
