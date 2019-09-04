package com.example.edz.myapplication.table;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


import com.example.edz.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 今日头条tablyout
 */
public class TableActivity extends FragmentActivity {
    private TabLayout tabLayout_shouye;
    private ViewPager viewPager_shouye;
    private List<String> strings = new ArrayList<String>();;
    private List<Fragment> fragments = new ArrayList<Fragment>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        initdate();
        initView();
    }
    private void initView(){
        tabLayout_shouye = (TabLayout)findViewById(R.id.tablayout_shouye);
        viewPager_shouye = (ViewPager)findViewById(R.id.viewpager_ShouYe);
        viewPager_shouye.setAdapter(new TabFragmentShouYeAdapter(fragments,strings,
                getSupportFragmentManager(),this));
        tabLayout_shouye.setupWithViewPager(viewPager_shouye);
        tabLayout_shouye.setTabTextColors(getResources().getColor(R.color.colorPrimaryDark)
                ,getResources().getColor(R.color.colorAccent));
        tabLayout_shouye.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                Toast.makeText(TableActivity.this,"111111",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Toast.makeText(TableActivity.this,"2222222",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Toast.makeText(TableActivity.this,"3333",Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void initdate(){
        Fragment1 fragment1 = new Fragment1();
        fragments.add(fragment1);
        strings.add("推荐");
        Fragment2 fragment2 = new Fragment2();
        fragments.add(fragment2);
        strings.add("热点");
//
//        Fragement3 fragment3 = new Fragement3();
//        fragments.add(fragment3);
//        strings.add("视频");
//        Fragment4 fragment4 = new Fragment4();
//        fragments.add(fragment4);
//        strings.add("西安");
//        Fragment5 fragment5 = new Fragment5();
//        fragments.add(fragment5);
//        strings.add("社会");
//        Fragment6 fragment6 = new Fragment6();
//        fragments.add(fragment6);
//        strings.add("娱乐");
//        Fragment7 fragment7 = new Fragment7();
//        fragments.add(fragment7);
//        strings.add("图片");
    }
}
