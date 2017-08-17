package com.callor.mycalc;

import android.databinding.DataBindingUtil;
import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;

import com.callor.mycalc.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding mainBinding;
    String operator = "";
    double lastNum = 0;
    double dsum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//      setContentView(R.layout.activity_main);
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

//        Button btn_0 = (Button)findViewById(R.id.btn_0);
//        btn_0.setOnClickListener(this);

        mainBinding.btn0.setOnClickListener(this);
        mainBinding.btn1.setOnClickListener(this);
        mainBinding.btn2.setOnClickListener(this);
        mainBinding.btn3.setOnClickListener(this);
        mainBinding.btn4.setOnClickListener(this);
        mainBinding.btn5.setOnClickListener(this);
        mainBinding.btn6.setOnClickListener(this);
        mainBinding.btn7.setOnClickListener(this);
        mainBinding.btn8.setOnClickListener(this);
        mainBinding.btn9.setOnClickListener(this);

        mainBinding.btnDot.setOnClickListener(this);

        mainBinding.btnClear.setOnClickListener(this);
        mainBinding.btnDel.setOnClickListener(this);

        mainBinding.btnPlus.setOnClickListener(this);
        mainBinding.btnMinus.setOnClickListener(this);
        mainBinding.btnTimes.setOnClickListener(this);
        mainBinding.btnDiv.setOnClickListener(this);
        mainBinding.btnEq.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        // 눌린 버튼의 Id 값을 가져 오는 것
        int btnId = view.getId();

        switch (btnId) {
            case R.id.btn_clear :
                break;

            case R.id.btn_plus :
                operator = "+";
                lastNum = Double.parseDouble(mainBinding.tvResult.getText().toString());
                lastNum = Double.valueOf(mainBinding.tvResult.getText().toString());
                mainBinding.tvResult.setText("");
                break;

            case R.id.btn_minus :
                operator = "-";
                lastNum = Double.parseDouble(mainBinding.tvResult.getText().toString());
                lastNum = Double.valueOf(mainBinding.tvResult.getText().toString());
                mainBinding.tvResult.setText("");
                break;

            case R.id.btn_times :
                operator = "*";
                lastNum = Double.parseDouble(mainBinding.tvResult.getText().toString());
                lastNum = Double.valueOf(mainBinding.tvResult.getText().toString());
                mainBinding.tvResult.setText("");
                break;

            case R.id.btn_div :
                operator = "/";
                lastNum = Double.parseDouble(mainBinding.tvResult.getText().toString());
                lastNum = Double.valueOf(mainBinding.tvResult.getText().toString());
                mainBinding.tvResult.setText("");
                break;

            case R.id.btn_eq :

                Double tmp = Double.valueOf(mainBinding.tvResult.getText().toString());
                switch(operator) {
                    case "+" :
                        lastNum += tmp ;
                        break ;
                    case "-" :
                        lastNum -= tmp ;
                        break;
                    case "*" :
                        lastNum *= tmp ;
                        break;
                    case "/" :
                        lastNum /= tmp ;
                        break;
                }
                mainBinding.tvResult.setText(String.valueOf(lastNum));
                operator = "=" ;
                break;

            default:
                String strResult = mainBinding.tvResult.getText().toString();
                /*
                Button tmpBtn = (Button)view ;
                keyIn += tmpBtn.getText();
                */
                String keyIn = ((Button)view).getText().toString();
                strResult += keyIn ;

                int intResult  = Integer.valueOf(strResult);
                strResult = String.valueOf(intResult);
                mainBinding.tvResult.setText(strResult);


        }
    }
}
