package com.example.edz.myapplication;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class ScrollListActivity extends AppCompatActivity {
    List<String> listq = new ArrayList<>();
    private RecyclerView mylist;
    private NestedScrollView mscroll;
    private FloatingActionButton returnTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_list);
        mscroll = findViewById(R.id.scroll);
        returnTop = findViewById(R.id.fab);
        mylist = findViewById(R.id.listview);

        mylist.setLayoutManager(new LinearLayoutManager(this));
        ItemInspIndexTrainInfoTabAdapter adapter = new ItemInspIndexTrainInfoTabAdapter(this);
        mylist.setAdapter(adapter);
        mylist.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //获得recyclerView的线性布局管理器
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                //获取到第一个item的显示的下标  不等于0表示第一个item处于不可见状态 说明列表没有滑动到顶部 显示回到顶部按钮
                int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();
                // 当不滚动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    // 判断是否滚动超过一屏
                    if (firstVisibleItemPosition == 0) {
                        returnTop.hide();
                    } else {
                        //显示回到顶部按钮
                        returnTop.show();
                        returnTop.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                recyclerView.scrollToPosition(0);
                            }
                        });

                    }//获取RecyclerView滑动时候的状态
                }
//                else if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {//拖动中
//                    returnTop.hide();
//                }
            }
        });
//        ItemListAdapter adapter=new ItemListAdapter(this);
//        mylist.setAdapter(adapter);


//        VideoView video = findViewById(R.id.myvideo);
//        MediaController mediaController = new MediaController(this);
//
//        String uri = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
//        video.setVideoURI(Uri.parse(uri));
//        video.setMediaController(mediaController);
//        mediaController.setMediaPlayer(video);
//        video.requestFocus();
//        video.start();
//
//        JZVideoPlayerStandard jzVideoPlayerStandard = (JZVideoPlayerStandard) findViewById(R.id.videoplayer);
//        jzVideoPlayerStandard.setUp("http://192.168.6.128:8080/upload/inspection/ea77772e1c8447e091aa7f00a1f52621.mp4",
//                JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL,
//                " ");
//
//
//        String tmp="123,234,123,234,567,789,adb,asc,dcf,adb";
//        String[] commercial_accounts=tmp.split(",");
//        List<String> list = new ArrayList<String>();
//        for (int i=0; i<commercial_accounts.length; i++) {
//            if(!list.contains(commercial_accounts[i])){
//                //去除重复的字符串
//                list.add(commercial_accounts[i]);
//            }
//        }
//        String ss=list.toString();
//        ss= ss.replace("["," ");
//        ss=ss.replace("]"," ");
//        Log.i("dd", "onCreate: "+ss);


    }

    public void topclick(View view) {
        Toast.makeText(this, "dsss", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
//        if (JZVideoPlayer.backPress()) {
//            return;
//        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        JZVideoPlayer.releaseAllVideos();
    }
}
