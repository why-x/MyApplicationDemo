package com.example.edz.myapplication.liandong;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.example.edz.myapplication.MainActivity;
import com.example.edz.myapplication.R;

import java.util.ArrayList;

/**
 * 三级联动 自定义条件 不联动
 */
public class LianDActivity extends AppCompatActivity {
    private ArrayList<String> food = new ArrayList<>();
    private ArrayList<String> clothes = new ArrayList<>();
    private ArrayList<String> computer = new ArrayList<>();
    private OptionsPickerView pvNoLinkOptions;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lian_d);
        Button btn_no_linkage = (Button) findViewById(R.id.btn_no_linkage);
        text = findViewById(R.id.mytext);
        getNoLinkData();
        btn_no_linkage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initNoLinkOptionsPicker();
            }
        });
    }

    private void initNoLinkOptionsPicker() {
        pvNoLinkOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {

                String str = "food:" + food.get(options1)
                        + "\nclothes:" + clothes.get(options2)
                        + "\ncomputer:" + computer.get(options3);

                Toast.makeText(LianDActivity.this, str, Toast.LENGTH_SHORT).show();
                text.setText(food.get(options1));

            }
        })
                .setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
                    @Override
                    public void onOptionsSelectChanged(int options1, int options2, int options3) {
                        String str = "options1: " + options1 + "\noptions2: " + options2 + "\noptions3: " + options3;
                        Toast.makeText(LianDActivity.this, str, Toast.LENGTH_SHORT).show();
                    }
                })
                .setTitleText("媒体选择")//标题
                .isRestoreItem(false)
//                .setLabels("省", "市", "区")//设置选择的三级单位
                // .setSelectOptions(0, 1, 1)
                .build();
        pvNoLinkOptions.setPicker(food);
//        pvNoLinkOptions.setNPicker(food, clothes, computer);
//        pvNoLinkOptions.setSelectOptions(0, 1, 1);
        pvNoLinkOptions.show();

    }

    private void getNoLinkData() {
        food.add("KFC");
        food.add("MacDonald");
        food.add("Pizza hut");
        clothes.add("缺失");
        clothes.add("损坏");
//        clothes.add("Nike");
//        clothes.add("Adidas");
//        clothes.add("Armani");
        for (int i = 1; i < 201; i++) {
            computer.add(i + "");
        }
//        computer.add("ASUS");
//        computer.add("Lenovo");
//        computer.add("Apple");
//        computer.add("HP");
    }
}
