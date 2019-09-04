package com.example.edz.myapplication.clickbanner;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edz.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JZVideoPlayerStandard;

public class ShowItemsPicActivity extends AppCompatActivity {
    private ImageView backHome;
    private TextView tittle;
    private ViewPager mViewPager;
    private LayoutInflater layoutInflater;
    private List<View> views = new ArrayList<>();
    private List<String> listData = new ArrayList<>();
    private int flag = 0;
    private Context context = this;
    private TextView page;
    private JZVideoPlayerStandard jzVideoPlayerStandard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_items_pic);
        initWeb();
        initData();

    }

    private void initWeb() {
        //初始化组件
        //初始化组件
//        backHome = (ImageView) findViewById(R.id.normal_title_blue_left_iv);
//        tittle = (TextView) findViewById(R.id.normal_title_blue_center_tv);
//        tittle.setText("相册");
//        backHome.setVisibility(View.VISIBLE);
//        backHome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//
//            }
//        });
        mViewPager = (ViewPager) findViewById(R.id.chat_show_load_image_viewpager);
        page = findViewById(R.id.page);
    }

    private void initData() {
        //初始化数据
        listData = (List<String>) getIntent().getSerializableExtra("datas");
        //LogUtils.v("ChatShowLoadImage:"+listData.size());
        flag = getIntent().getIntExtra("flag", 0);
        LayoutInflater inflater3 = LayoutInflater.from(this);
        if (listData.size() > 0) {
            for (int i = 0; i < listData.size(); i++) {
                final String url = listData.get(i);
                View view = inflater3.inflate(R.layout.chat_show_load_image_item, null);
                ImageView iv = (ImageView) view.findViewById(R.id.chat_show_load_image_download);
                jzVideoPlayerStandard = view.findViewById(R.id.jz);
                views.add(view);
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "下载图片", Toast.LENGTH_SHORT).show();
                        DonwloadSaveImg.donwloadImg(ShowItemsPicActivity.this, url);//iPath

//                        new DownloadImage(ShowItemsPicActivity.this).execute(OkHttpClientManager.photoip + url);
                    }
                });
            }
        }
        ChatViewPagerAdapter cvp = new ChatViewPagerAdapter(this, views, listData);
        mViewPager.setAdapter(cvp);
        if (flag >= listData.size()) {
            flag = 0;
        }
        mViewPager.setCurrentItem(flag);
        page.setText(1 + "/" + listData.size());
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                page.setText(i + 1 + "/" + listData.size());

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        jzVideoPlayerStandard.releaseAllVideos();
    }
}
