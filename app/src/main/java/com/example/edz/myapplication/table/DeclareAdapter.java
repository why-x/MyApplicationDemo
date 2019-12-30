package com.example.edz.myapplication.table;


import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.edz.myapplication.R;

import java.util.List;


public class DeclareAdapter extends RecyclerView.Adapter<DeclareAdapter.MyViewHolder> {

    private List<Object> mDatas;
    private Context mContext;
    private LayoutInflater inflater;

    public DeclareAdapter(Context context, List<Object> datas) {
        this.mContext = context;
        this.mDatas = datas;
        inflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getItemCount() {
        return 30;
    }

    //填充onCreateViewHolder方法返回的holder中的控件
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
            holder.text.setText(position+"");

    }

    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_declare, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    class MyViewHolder extends RecyclerView.ViewHolder { //承载Item视图的子布局
        TextView text;


        public MyViewHolder(View view) {
            super(view);
            text = view.findViewById(R.id.text);

        }
    }


}