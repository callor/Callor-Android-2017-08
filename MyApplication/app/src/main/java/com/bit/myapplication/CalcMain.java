package com.bit.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by callor on 2017-08-17.
 */

public class CalcMain extends AppCompatActivity implements View.OnClickListener {

        double lastNumber = 0;
        String operator = "";
        double num1 = 0 ;

        long lsum = 0; // 정수연산을 위한 변수
        double dsum = 0; // 실수연산을 위한 변수

        TextView tv_result;

        Button btn_clear;
        Button btn_del;
        Button btn_plus;
        Button btn_minus;
        Button btn_times;
        Button btn_div;
        Button btn_eq;
        Button btn_dot;

        Button btn_0;
        Button btn_1;
        Button btn_2;
        Button btn_3;
        Button btn_4;
        Button btn_5;
        Button btn_6;
        Button btn_7;
        Button btn_8;
        Button btn_9;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            tv_result = (TextView) findViewById(R.id.tv_result);

            btn_clear = (Button) findViewById(R.id.btn_clear);
            btn_del = (Button) findViewById(R.id.btn_del);
            btn_plus = (Button) findViewById(R.id.btn_plus);
            btn_minus = (Button) findViewById(R.id.btn_minus);
            btn_times = (Button) findViewById(R.id.btn_times);
            btn_div = (Button) findViewById(R.id.btn_div);
            btn_eq = (Button) findViewById(R.id.btn_eq);
            btn_dot = (Button) findViewById(R.id.btn_dot);

            btn_0 = (Button) findViewById(R.id.btn_0);
            btn_1 = (Button) findViewById(R.id.btn_1);
            btn_2 = (Button) findViewById(R.id.btn_2);
            btn_3 = (Button) findViewById(R.id.btn_3);
            btn_4 = (Button) findViewById(R.id.btn_4);
            btn_5 = (Button) findViewById(R.id.btn_5);
            btn_6 = (Button) findViewById(R.id.btn_6);
            btn_7 = (Button) findViewById(R.id.btn_7);
            btn_8 = (Button) findViewById(R.id.btn_8);
            btn_9 = (Button) findViewById(R.id.btn_9);

            btn_clear.setOnClickListener(this);
            btn_dot.setOnClickListener(this);

            btn_plus.setOnClickListener(this);
            btn_minus.setOnClickListener(this);
            btn_times.setOnClickListener(this);
            btn_div.setOnClickListener(this);

            btn_eq.setOnClickListener(this);

            btn_0.setOnClickListener(this);
            btn_1.setOnClickListener(this);
            btn_2.setOnClickListener(this);
            btn_3.setOnClickListener(this);
            btn_4.setOnClickListener(this);
            btn_5.setOnClickListener(this);
            btn_6.setOnClickListener(this);
            btn_7.setOnClickListener(this);
            btn_8.setOnClickListener(this);
            btn_9.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            // view.getId() : +id/btn 이름 가져오기
            switch (view.getId()) {

                // clear 버튼이 눌리면
                // 전체 삭제
                case R.id.btn_clear:
                    Log.d("버튼이벤트", Integer.toString(view.getId()));
                    tv_result.setText("");
                    lastNumber = 0;
                    break;

                // 현재 입력된 값만 삭제
                case R.id.btn_del:
                    if (tv_result.getText().toString() != "") {
                        tv_result.setText("");
                    }
                    break;

                case R.id.btn_plus:
                    lastNumber = Double.valueOf(tv_result.getText().toString());
                    tv_result.setText("");
                    operator = "+";

                /*
                long lvalue = Long.parseLong(value); // 문자열을 long으로 변환 시키기
                lsum += lvalue ;
                tv_result.setText(Long.toString(lsum));
                */
                    break;

                case R.id.btn_minus:
                    lastNumber = Double.valueOf(tv_result.getText().toString());
                    tv_result.setText("");
                    operator = "-";
                    break;

                case R.id.btn_times:
                    lastNumber = Double.valueOf(tv_result.getText().toString());
                    tv_result.setText("");
                    operator = "*";
                    break;

                case R.id.btn_div:
                    lastNumber = Double.valueOf(tv_result.getText().toString());
                    tv_result.setText("");
                    operator = "/";
                    break;

                case R.id.btn_eq:
                    calcFunc();
                    break;

                // 소수점입력
                // 이미 입력이 있으면 추가 입력 금지
                case R.id.btn_dot :
                    lastNumber = Double.valueOf(tv_result.getText().toString());
//                if(!lastNumber.contains(".")) {
//                    tv_result.setText(lastNumber + ".");
//                }
                    break ;
                // 숫자버튼이면
                default:
////                lastNumber = Double.parseDouble(tv_result.getText().toString());
//                if(!operator.isEmpty()) {
//                    tv_result.setText("");
//                    operator = "";
//                }
                    String strResult = tv_result.getText().toString();
                    strResult += ((Button) view).getText(); // 기존 TextView에 입력된 숫자 + 새로운 숫자
                    tv_result.setText(strResult);
                    break ;

            } // end switch

        }

        public void calcFunc() {
            switch (operator){
                case "+" :
                    dsum = lastNumber + Double.valueOf(tv_result.getText().toString());
                    break ;
                case "-" :
                    dsum = lastNumber - Double.valueOf(tv_result.getText().toString());
                    break ;
                case "*" :
                    dsum = lastNumber * Double.valueOf(tv_result.getText().toString());
                    break ;
                case "/" :
                    dsum = lastNumber / Double.valueOf(tv_result.getText().toString());
                    break ;
            }
            tv_result.setText(String.valueOf(dsum));
            if(lastNumber != dsum) {

            }
        }
    }



