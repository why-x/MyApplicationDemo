package com.example.edz.myapplication.验证码;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.example.edz.myapplication.R;
import com.example.edz.myapplication.验证码.PhoneCode;

/**
 * 验证码输入框 4位数
 */
public class YanZhengmaActivity extends AppCompatActivity {
    PhoneCode pc_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yan_zhengma);
         TextView tv_brief;



        pc_1 = (PhoneCode) findViewById(R.id.pc_1);
        //注册事件回调（根据实际需要，可写，可不写）
        pc_1.setOnInputListener(new PhoneCode.OnInputListener() {
            @Override
            public void onSucess(String code) {
                //TODO: 例如底部【下一步】按钮可点击
            }

            @Override
            public void onInput() {
                //TODO:例如底部【下一步】按钮不可点击
            }
        });
    }

    private void test() {
        //获得验证码
        String phoneCode = pc_1.getPhoneCode();
        //......
        //......
        //更多操作
    }
}
