package com.android.class2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyActivity extends Activity
{

    public static final int CAMERA_REQUEST_CODE = 10232;
    private Button startCameraBtn;
    private ImageView imageView;
    private File albumDir;
    private String fullImagePath;
    private String thumbnailImagePath;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initUI();
        initImageDir();
    }

    private void initImageDir() {
        albumDir = new File(Environment.getExternalStorageDirectory() +
                File.separator + "android_course_class2");
        if (!albumDir.exists()) {
            albumDir.mkdirs();
        }
    }

    private void initUI() {
        startCameraBtn = (Button) findViewById(R.id.start_camera_btn);
        imageView = (ImageView) findViewById(R.id.image_view);
        startCameraBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startCamera();
            }
        });
    }


    private void startCamera() {
        createImageFile();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(fullImagePath)));

//        startActivity(intent);
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }

    private void createImageFile() {
        try {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String imageFileName = "camera_class2" + timeStamp;

            File fullImage = new File(
                    albumDir, imageFileName + ".jpeg"
            );
            File thumbnailImage = new File(
                    albumDir, imageFileName + ".jpeg"
            );

            fullImagePath = fullImage.getAbsolutePath();
            thumbnailImagePath = thumbnailImage.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) return;
        if (requestCode == CAMERA_REQUEST_CODE ) {
//            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
//            imageView.setImageBitmap(bitmap);
            Bitmap thumbnailBitmap = ImageUtils.decodeBitmapFromFile(fullImagePath, 100, 100);
            imageView.setImageBitmap(thumbnailBitmap);
        }
    }
}
