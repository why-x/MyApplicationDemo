package com.example.edz.myapplication.dianzan;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.edz.myapplication.R;
import com.github.chrisbanes.photoview.PhotoView;

public class DianActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dian);
//        PhotoView photoView = findViewById(R.id.image_photo);
//        Glide.with(this).load("http://img1.imgtn.bdimg.com/it/u=4194723123,4160931506&fm=200&gp=0.jpg").into(photoView);
////        photoView.setImageURI(Uri.parse("http://img1.imgtn.bdimg.com/it/u=4194723123,4160931506&fm=200&gp=0.jpg"));
//        photoView.setMaximumScale(2);

    }
}
