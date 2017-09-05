package com.callor.phoneaction;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.media.MediaCodec;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.callor.phoneaction.databinding.ActivityMainBinding;
import com.callor.phoneaction.databinding.ContentMainBinding;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding mainBinding;
    ContentMainBinding contentBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        contentBinding = mainBinding.contentMain;

        // 현재 phone이 롤리팝 이상일 경우만 권한 요청
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 권한 허가 되지 않았으면
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED) {

                String[] strReq = new String[] {
                        Manifest.permission.CALL_PHONE,
                        "전화걸기 권한이 필요합니다"
                };
                ActivityCompat.requestPermissions(this,strReq,1000);
            }
        }

        setSupportActionBar(mainBinding.toolbar);

        contentBinding.btnCall.setOnClickListener(this);
        contentBinding.btnSendMain.setOnClickListener(this);
        contentBinding.btnSendSms.setOnClickListener(this);

        mainBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if(viewId == R.id.btn_call) phoneCall();
        if(viewId == R.id.btn_send_main) sendEmail();
        if(viewId == R.id.btn_send_sms) smsViewSend(); // 창으로 보기
//            sendSms(); // 바로보내기
    }
    // 메시지 창을 띄워서 보내는 방법
    public void smsViewSend() {
        String phoneNum = contentBinding.txtPhone.getText().toString();
        String smsString = contentBinding.txtSms.getText().toString();

        Uri  uri = Uri.parse("smsto:" + phoneNum);
        Intent smsIntent = new Intent(Intent.ACTION_SENDTO,uri);
        smsIntent.putExtra("sms_body",smsString);
        startActivity(smsIntent);
    }

    // 바로 전송하기
    public void sendSms() {
        // 입력박스의 전화번호 꺼내기
        String phoneNum = contentBinding.txtPhone.getText().toString();
        String smsString = contentBinding.txtSms.getText().toString();

        String SENT = "SMS_SENT";
        String DELVERED =  "SMS_DELEVERED";

        // 숫자만 입력받겠다
        if(!Pattern.matches("^[0-9]*$",phoneNum)) {
            Toast.makeText(MainActivity.this,
                    "전화번호는 숫자만 입력하세요",Toast.LENGTH_SHORT).show();
            return ;
        }
        if(smsString.isEmpty()) {
            Toast.makeText(MainActivity.this,"보낼문자를 입력하세요",Toast.LENGTH_SHORT).show();
            return ;
        }
        //
        PendingIntent sendIntent = PendingIntent.getBroadcast(this,0,new Intent(SENT),0);
        PendingIntent delivery = PendingIntent.getBroadcast(this,0,new Intent(DELVERED),0);
        // 전송확인을 하기위한 부분
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // 결과 수신
                switch (getResultCode()) {
                    case Activity.RESULT_OK :
                        Toast.makeText(MainActivity.this,"문자메시지가 전송되었습니다",Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE :
                        Toast.makeText(MainActivity.this,"전송실패",Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE :
                        Toast.makeText(MainActivity.this,"서비스지역 아님",Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF :
                        Toast.makeText(MainActivity.this,"무선데이터 잠김",Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(MainActivity.this,"무선데이터 유닛 없음",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(MainActivity.this,"결과 모름",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        },new IntentFilter(SENT));
        //
        // 실제 메시지 보내는 부분
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNum,null,smsString,sendIntent,delivery);
        //
    }

    public void sendEmail(){

        String email = contentBinding.txtEmail.getText().toString();
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(MainActivity.this,"이메일 형식이 잘못되었습니다",Toast.LENGTH_SHORT).show();
            return;
        }

        String[] emails = new String[]{ email };
        Intent mailIntent = new Intent(Intent.ACTION_SEND); // Email 보내기 Intent
        mailIntent.setType("plaine/text");
        mailIntent.putExtra(Intent.EXTRA_EMAIL,emails);
        mailIntent.putExtra(Intent.EXTRA_SUBJECT,"[내가보내는 메일]");
        mailIntent.putExtra(Intent.EXTRA_TEXT,"\n\n" + "안녕하세요");

        startActivity(mailIntent);
    };

    public void phoneCall(){
        // 입력박스의 전화번호 꺼내기
        String phoneNum = contentBinding.txtPhone.getText().toString();
        Intent phoneInten = null;

        // 숫자만 입력받겠다
        if(!Pattern.matches("^[0-9]*$",phoneNum)) {
            Toast.makeText(MainActivity.this,
                    "전화번호는 숫자만 입력하세요",Toast.LENGTH_SHORT).show();
            return ;
        }

        if(phoneNum.isEmpty()) {
            // 비어있는 전화걸기 창 호출
            phoneInten = new Intent(Intent.ACTION_CALL_BUTTON);
        } else {
            // TextBox에 입력된 전화번호를 전화걸기에 전송하고
            // 전화걸기 창을 호출
//            phoneInten = new Intent(Intent.ACTION_DIAL); // 다이얼창
            phoneInten = new Intent(Intent.ACTION_CALL); // 바로걸기
            phoneInten.setData(Uri.parse("tel:" + phoneNum));
        }

        // phone의 전화걸기 App 호출
//        phoneInten = new Intent(Intent.ACTION_DIAL);
        startActivity(phoneInten);

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
