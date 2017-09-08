package com.callor.imageview;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.callor.imageview.databinding.ActivityMain2Binding;
import com.callor.imageview.databinding.ContentMain2Binding;

import java.io.File;
import java.io.IOException;

public class Main2Activity extends AppCompatActivity {

    private ActivityMain2Binding main2Binding ;
    private ContentMain2Binding content2Binding ;

    private static final String TEMP_FILE_NAME = "tempFile.jpg";
    private static final String TYPE_IMAGE = "image/*";
    private static final int REQ_IMAGE_CROP = 2;

    private Uri tempImageUri ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        main2Binding = DataBindingUtil.setContentView(this,R.layout.activity_main2);
        content2Binding = main2Binding.contentMain;



        setSupportActionBar(main2Binding.toolbar);
        main2Binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tempImageUri = Uri.fromFile(getTempFile());
                Intent imgIntent = new Intent(Intent.ACTION_GET_CONTENT,null);
                imgIntent.setType(TYPE_IMAGE);

                // 사진에 사람얼굴이 있으면 얼굴 인식 하지 말라
                imgIntent.putExtra("nofaceDetection",true);
                // crop 도구 보이기
                imgIntent.putExtra("crop","true");
                imgIntent.putExtra("scale",true);

                // crop 된 다음에 저장할 파일에 대한 정보
                imgIntent.putExtra(MediaStore.EXTRA_OUTPUT,tempImageUri);
                imgIntent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
                startActivityForResult(imgIntent,REQ_IMAGE_CROP);
            }
        });
    }

    // crop 된 이미지를 임시로 저장할 공간과 파일에 대한 정보 획득
    private File getTempFile() {
        // Environment.getExternalStorageDirectory() : phone에 있는 사용자 저장 가능 공간 폴더 이름
        File file = new File(Environment.getExternalStorageDirectory(),TEMP_FILE_NAME);
        try {
            file.createNewFile();
        } catch (IOException e) {
            Toast.makeText(Main2Activity.this,"임시 저장 파일 생성 오류!!",Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
        }
        return file;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        if(resultCode == RESULT_OK) {
//            if(requestCode == REQ_IMAGE_CROP) {
//
//            }
//        }
        if(resultCode != RESULT_OK) return ;
        if(requestCode == REQ_IMAGE_CROP) {
            File tempFile = getTempFile();

            // BitMapFactory : V14 이전에는 BitmapDrawable 이던 것이 변경
            Bitmap imgBitMap = BitmapFactory.decodeFile(tempFile.toString());
//            Bitmap imgBitMap = BitmapFactory.decodeFile(Uri.parse(data.getData().toString());
            content2Binding.myImage.setImageBitmap(imgBitMap);
        }
    }
}
