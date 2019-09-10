package com.example.edz.myapplication.clickbanner;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.edz.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JZMediaManager;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by Administrator on 2017/11/9.
 */

public class ChatViewPagerAdapter extends PagerAdapter {

    private List<View> views;
    private Context context;
    private List<String> listData = new ArrayList<>();

    public ChatViewPagerAdapter(Context context1, List<View> views1, List<String> listData1) {
        this.context = context1;
        this.views = views1;
        listData = listData1;
    }

    @Override
    public int getCount() {
        if (views != null) {
            return views.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        View view = views.get(position);
        PinchImageView iv = (PinchImageView) view.findViewById(R.id.chat_show_load_image_item_im);
        JZVideoPlayerStandard jz = (JZVideoPlayerStandard) view.findViewById(R.id.jz);
        if (listData.get(position).endsWith("mp4")) {
            iv.setVisibility(View.GONE);
            jz.setUp(listData.get(position), JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "");
        } else {
            jz.setVisibility(View.GONE);
            //暂停jz播放
            jz.releaseAllVideos();
            Glide.with(context)
                    .load(listData.get(position))
                    .fitCenter()
                    .placeholder(R.drawable.placeholder_photo)
                    .error(R.drawable.erro_image)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(iv);
        }
        return views.get(position);
    }
}