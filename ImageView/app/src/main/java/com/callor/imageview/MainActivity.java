package com.callor.imageview;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.callor.imageview.databinding.ActivityMainBinding;
import com.callor.imageview.databinding.ContentMainBinding;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;
    private ContentMainBinding conteBinding;

    private static final int REQ_CODE_SELECT_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        conteBinding = mainBinding.contentMain;

        // 쓰기 권한이 없으면 권한을 획득하도록 팝업 띄우기
        if(ActivityCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            String[] strReq = new String[] {
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    "저장 권한이 필요합니다"
            };
            ActivityCompat.requestPermissions(MainActivity.this,strReq,1000);
        }

        setSupportActionBar(mainBinding.toolbar);
        mainBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // phone에 있는 기본겔러리를 호출하는 암시적 인텐트
                Intent imgIntent = new Intent(Intent.ACTION_PICK);
                imgIntent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                imgIntent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(imgIntent,REQ_CODE_SELECT_IMAGE);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_CODE_SELECT_IMAGE) {
            if(resultCode == RESULT_OK) {
                // 겔러리에서 보내온 파일이름으로 부터 실제 이미를 가져오기
                try {
                    Bitmap imgBitMap = MediaStore.Images.Media.getBitmap(
                            getContentResolver(),data.getData()
                    );
                    conteBinding.myImage.setImageBitmap(imgBitMap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 점 3개를 클릭하면 서브메뉴를 열기
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // 서브메뉴 항목을 클릭했을때 할 event
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_main2) {

            Intent subIntent = new Intent(MainActivity.this,Main2Activity.class);
            startActivity(subIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
