package com.callor.todolist;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.callor.todolist.databinding.ActivityMainBinding;
import com.callor.todolist.databinding.ContentMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding ;
    ContentMainBinding contenBindig ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // DataBinding
//      setContentView(R.layout.activity_main);
        // activity_main.xml 을 binding
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        // activity_main.xml에 include content_main.xml binding
        contenBindig = mainBinding.contentMain ;

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mainBinding.toolbar);
        DBAdapter dbAdapter = new DBAdapter();

//        RecyclerView list_body = (RecyclerView)findViewById(R.id.list_body);
        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
        contenBindig.listBody.setLayoutManager(manager);
        contenBindig.listBody.setAdapter(dbAdapter);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        mainBinding.fab.setOnClickListener(new View.OnClickListener() {
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
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
