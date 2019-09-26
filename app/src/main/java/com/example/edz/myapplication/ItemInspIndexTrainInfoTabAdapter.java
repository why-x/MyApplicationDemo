package com.example.edz.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class ItemInspIndexTrainInfoTabAdapter extends RecyclerView.Adapter<ItemInspIndexTrainInfoTabAdapter.ViewHolder> {

    private List<String> objects = new ArrayList<String>();
    private Context context;
    private LayoutInflater layoutInflater;


    public ItemInspIndexTrainInfoTabAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_list, parent, false);
        ItemInspIndexTrainInfoTabAdapter.ViewHolder holder = new ItemInspIndexTrainInfoTabAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mediatype.setText(position + "");
    }


    @Override
    public int getItemCount() {
        return objects.size() + 30;
    }


    protected class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mediatype;


        public ViewHolder(View view) {
            super(view);
            mediatype = (TextView) view.findViewById(R.id.mediatype);

        }
    }
}
