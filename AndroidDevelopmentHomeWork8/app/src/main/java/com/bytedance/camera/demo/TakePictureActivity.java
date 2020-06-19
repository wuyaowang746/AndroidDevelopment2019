package com.bytedance.camera.demo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.bytedance.camera.demo.utils.Utils;

import java.io.File;

public class TakePictureActivity extends AppCompatActivity {

    private ImageView imageView;
    private File imgFile;
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    private static final int REQUEST_EXTERNAL_STORAGE = 101;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);
        imageView = findViewById(R.id.img);
        findViewById(R.id.btn_picture).setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(TakePictureActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(TakePictureActivity.this,
                    Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                //todo 在这里申请相机、存储的权限
                ActivityCompat.requestPermissions(TakePictureActivity.this,new String[]{
                        Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE
                },1);
                Log.d("tt", "permision ");
            } else {
                takePicture();
            }
        });

    }

    private void takePicture() {
        //todo 打开相机
        Intent takePhotoIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        imgFile= Utils.getOutputMediaFile(Utils.MEDIA_TYPE_IMAGE);
        if(imgFile!=null){
            Uri uri= FileProvider.getUriForFile(this,"com.bytedance.camera.demo",imgFile);
            takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
            startActivityForResult(takePhotoIntent,REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            setPic();
        }
    }

    private void setPic() {
        int tagretW=imageView.getWidth();
        int targetH=imageView.getHeight();
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeFile(imgFile.getAbsolutePath(),options);
        int photoW=options.outWidth;
        int photoH=options.outHeight;
        int scaleFactor=Math.min(photoW/tagretW,photoH/targetH);
        options.inJustDecodeBounds=false;
        options.inSampleSize=scaleFactor;
        options.inPurgeable=true;
        Bitmap bitmap=BitmapFactory.decodeFile(imgFile.getAbsolutePath(),options);
        Utils.rotateImage(bitmap,imgFile.getAbsolutePath());
        imageView.setImageBitmap(bitmap);
        //todo 根据imageView裁剪
        //todo 根据缩放比例读取文件，生成Bitmap

        //todo 如果存在预览方向改变，进行图片旋转

        //todo 如果存在预览方向改变，进行图片旋转
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE: {
                //todo 判断权限是否已经授予

                break;
            }
        }
    }
}
