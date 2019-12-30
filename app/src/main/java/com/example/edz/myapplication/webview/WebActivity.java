package com.example.edz.myapplication.webview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.edz.myapplication.R;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;

public class WebActivity extends AppCompatActivity {
    AgentWeb mAgentWeb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent((LinearLayout) findViewById(R.id.parent), new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go("http://www.jd.com");
//        修改 AgentWeb 默认的背景色
        FrameLayout frameLayout = mAgentWeb.getWebCreator().getWebParentLayout();
        frameLayout.setBackgroundColor(Color.BLACK);
    }


    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }
}
