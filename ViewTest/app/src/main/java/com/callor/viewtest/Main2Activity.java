package com.callor.viewtest;

import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.callor.viewtest.databinding.ActivityMain2Binding;

public class Main2Activity extends AppCompatActivity {

    ActivityMain2Binding main2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main2 = DataBindingUtil.setContentView(this,R.layout.activity_main2);

        // 위젯을 보이지 않게
        main2.prog01.setVisibility(View.GONE);
        main2.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BarTask barTask = new BarTask();
                barTask.execute();
            }
        });

        main2.btnStart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProcessBar bar = new ProcessBar();
                bar.execute(100);
            }
        });

    }

    // 비동기 클래스
    class BarTask extends AsyncTask<Integer, Integer, Void> {
        @Override
        protected Void doInBackground(Integer... integers) {
            for(int i = 0 ; i < 10000000;i++) {
                if(i % 1000000 == 0) {
                    publishProgress(new Integer(i));
                }
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            main2.prog01.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            main2.prog01.setVisibility(View.GONE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }

    class ProcessBar extends AsyncTask<Integer, Integer, Long> {
        @Override
        protected Long doInBackground(Integer... integers) {
            long total = integers[0]; // excute(x) 넘겨준 값받기
            for(int i = 0 ; i < total ; i++){
                SystemClock.sleep(100); // 1초간 쉬기
                int count = (int)(((i+1) / (float)total) * 100);
                publishProgress(count);
            }
            return null;
        }

        // 시작될때
        @Override
        protected void onPreExecute() {
            main2.prog02.setProgress(0);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
        }

        // publishProgress가 던진 값을 받는다.
        @Override
        protected void onProgressUpdate(Integer... values) {
            main2.prog02.setProgress(values[0]);
            super.onProgressUpdate(values);
        }
    }

}
