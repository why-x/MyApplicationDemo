package com.example.edz.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemInspIndexTrainTabAdapter extends BaseAdapter {

    private List<String> objects = new ArrayList<String>();

    private Context context;
    private LayoutInflater layoutInflater;
    int positions;

    public ItemInspIndexTrainTabAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public String getItem(int position) {
        positions = position;
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_insp_index_train_tab, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((String) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(final String object, ViewHolder holder) {
        holder.mediatype.setText(object);
    }

    public void addAll(List<String> trainString) {
        if (trainString != null) {
            objects.addAll(trainString);
        }
        notifyDataSetChanged();
    }

    int updatepo;

    public void update(int position) {

    }

    protected class ViewHolder {
        private TextView mediatype;

        public ViewHolder(View view) {
            mediatype = (TextView) view.findViewById(R.id.mediatype);

        }
    }
}
