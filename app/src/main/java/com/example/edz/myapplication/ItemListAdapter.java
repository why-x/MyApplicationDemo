package com.example.edz.myapplication;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ItemListAdapter extends BaseAdapter {

    private List<Object> objects = new ArrayList<Object>();

    private Context context;
    private LayoutInflater layoutInflater;

    public ItemListAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size()+30;
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_list, null);
            convertView.setTag(new ViewHolder(convertView));
        }
//        initializeViews((Object)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(Object object, ViewHolder holder) {
        //TODO implement
    }

    protected class ViewHolder {
        private TextView mediatype;

        public ViewHolder(View view) {
            mediatype = (TextView) view.findViewById(R.id.mediatype);
        }
    }
}
