package com.example.edz.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.danikula.videocache.HttpProxyCacheServer;
import com.example.edz.myapplication.banner.Banner;
import com.example.edz.myapplication.clickbanner.ShowItemsPicActivity;
import com.example.edz.myapplication.clickbanner.twoclickbanner.ImageBigActivity;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JZMediaManager;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * 图片视频轮播
 */
public class MainActivity extends AppCompatActivity {
    private Banner banner;
    private List<String> list;
    MZBannerView mMZBanner;
    private BannerViewHolder bannerViewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        banner = (Banner) findViewById(R.id.banner);
        mMZBanner = (MZBannerView) findViewById(R.id.mzbanner);


        initData();
        initBannerView();
        bannerViewHolder = new BannerViewHolder();
        bannerViewHolder.addList(list);
        mMZBanner.setDelayedTime(1000 * 6000);
        // 设置数据
        mMZBanner.setPages(list, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return bannerViewHolder;
            }
        });
    }

    public static class BannerViewHolder implements MZViewHolder<String> {
        private ImageView mImageView;
        private JZVideoPlayerStandard videoView;

        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
            mImageView = (ImageView) view.findViewById(R.id.banner_image);
            videoView = view.findViewById(R.id.jz);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            videoView.setLayoutParams(lp);
            return view;
        }

        @Override
        public void onBind(final Context context, final int position, String data) {
            // 数据绑定
            if (data.endsWith("mp4")) {
                mImageView.setVisibility(View.GONE);
                videoView.setUp(data, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "");
            } else {
                videoView.setVisibility(View.GONE);
                videoView.releaseAllVideos();
//                JZMediaManager.pause();
                Glide.with(context).load(data).into(mImageView);
                mImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(context, ShowItemsPicActivity.class);
                        intent.putExtra("datas", (Serializable) mlist);//需要传的图片集合
                        intent.putExtra("flag", position);//在第几张图片点击的  打开时直接显示点击的那张图
                        context.startActivity(intent);
//                        Intent intent = new Intent(context, ImageBigActivity.class);
//                        intent.putStringArrayListExtra("imageUrls", mlist);
//                        intent.putExtra("position", position);
//                        context.startActivity(intent);


                    }
                });
            }

        }

        ArrayList mlist = new ArrayList();

        public void addList(List<String> list) {
            if (list != null) {
                mlist.addAll(list);
            }
        }

    }

    private void initData() {
        HttpProxyCacheServer proxy = MApplication.getProxy(getApplicationContext());
        String proxyUrl = proxy.getProxyUrl("http://www.w3school.com.cn/example/html5/mov_bbb.mp4");
        String proxyUrl2 = proxy.getProxyUrl("http://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4");

        list = new ArrayList<>();
//        list.add(proxyUrl);
        list.add("http://img1.imgtn.bdimg.com/it/u=4194723123,4160931506&fm=200&gp=0.jpg");
        list.add("http://ad.12306.cn/app/res/media_material/w1.jpg");
        list.add(proxyUrl2);

    }

    private void initBannerView() {
        banner.setDataList(list);
        banner.setImgDelyed(5000);
        banner.startBanner();
    }

    @Override
    protected void onDestroy() {
        banner.destroy();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMZBanner.pause();
    }
}
