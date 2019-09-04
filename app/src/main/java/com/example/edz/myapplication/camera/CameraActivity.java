package com.example.edz.myapplication.camera;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.edz.myapplication.R;
import com.example.edz.myapplication.camera.activity.CaptureImageVideoActivity;
import com.example.edz.myapplication.camera.activity.VideoCameraActivity;
import com.example.edz.myapplication.camera.camera2.MyImage;
import com.example.edz.myapplication.camera.jcamera.util.CheckPermission;
import com.example.edz.myapplication.camera.jcamera.util.FileUtil;

/**
 * 微信点击拍照 长按录制视频
 */
public class CameraActivity extends AppCompatActivity {
    private static final int REQUEST_VIDEO_PERMISSIONS = 1;
    private static final String TAG = "CameraActivity";
    //视频权限
    private static final String[] VIDEO_PERMISSIONS = {
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.MODIFY_AUDIO_SETTINGS,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private ImageView myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        myImage = findViewById(R.id.myimage);
    }
    public void startCapture(View view) {
        if (!hasPermissionsGranted(VIDEO_PERMISSIONS) && CheckPermission.getRecordState() != CheckPermission.STATE_SUCCESS) {
            requestVideoPermissions();
            return;
        }
        if(Build.VERSION.SDK_INT >= 21){
            Intent intent = new Intent(this, VideoCameraActivity.class);
            startActivityForResult(intent, 1);
        }else {
            Intent intent = new Intent();
            intent.setClass(this, CaptureImageVideoActivity.class);
            startActivityForResult(intent, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && data!=null){
            boolean isPhoto = data.getBooleanExtra("isPhoto", false);
            if(!isPhoto) {
                String videoPath = data.getStringExtra("videoPath");

                long totalTime = data.getLongExtra("totalTime", 0);
                int videoWidth = data.getIntExtra("videoWidth", 0);
                int videoHeight = data.getIntExtra("videoHeight", 0);
                if (FileUtil.isNotEmpty(videoPath)) {
                    //TODO 获取视频
                    Toast.makeText(this,"视频文件："+videoPath,Toast.LENGTH_LONG).show();
                    Log.i(TAG, "视频文件："+videoPath);
                }
            }else {
                String imageUrl = data.getStringExtra("imageUrl");
                String imageFile = data.getStringExtra("imagefile");
                String type = data.getStringExtra("type");

//                 file = Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg";// 获取跟目录
//                String  file = MyImage.compressImage(path, imageUrl, 70);
                Glide.with(CameraActivity.this)
                        .load(imageFile)
                        .into(myImage);
//myImage.setim
                //TODO 获取到图片
                Toast.makeText(this,"图片文件："+imageUrl,Toast.LENGTH_LONG).show();
                Log.i(TAG, "图片文件1111："+imageUrl);
                Log.i(TAG, "图片文件2222："+imageFile);

            }
        }
    }

    /********************** 权限相关********************************/

    /**
     * Gets whether you should show UI with rationale for requesting permissions.
     *
     * @param permissions The permissions your app wants to request.
     * @return Whether you can show permission rationale UI.
     */
    private boolean shouldShowRequestPermissionRationale(String[] permissions) {
        for (String permission : permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,permission)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Requests permissions needed for recording video.
     */
    private void requestVideoPermissions() {
        if (shouldShowRequestPermissionRationale(VIDEO_PERMISSIONS)) {
            Toast.makeText(this,"您没有相机和录音权限，请到系统设置里授权",Toast.LENGTH_SHORT).show();
            finish();
        } else {
            ActivityCompat.requestPermissions(this, VIDEO_PERMISSIONS, REQUEST_VIDEO_PERMISSIONS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult");
        if (requestCode == REQUEST_VIDEO_PERMISSIONS) {
            if (grantResults.length == VIDEO_PERMISSIONS.length) {
                for (int result : grantResults) {
                    if (result != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this,"您没有相机和录音权限，请到系统设置里授权",Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
            } else {
                Toast.makeText(this,"您没有相机和录音权限，请到系统设置里授权",Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private boolean hasPermissionsGranted(String[] permissions) {
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
}
