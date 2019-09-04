package com.example.edz.myapplication;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.MediaController;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_list);
        mylist = findViewById(R.id.listview);
        mylist.setLayoutManager(new LinearLayoutManager(this));
        ItemInspIndexTrainInfoTabAdapter adapter = new ItemInspIndexTrainInfoTabAdapter(this);
        mylist.setAdapter(adapter);
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
