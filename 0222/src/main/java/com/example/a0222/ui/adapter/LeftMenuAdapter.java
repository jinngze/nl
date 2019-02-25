package com.example.a0222.ui.adapter;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.a0222.R;

import javax.xml.validation.TypeInfoProvider;

public class LeftMenuAdapter extends BaseAdapter {

    private String[] menus = new String[]{
            "预约", "首页", "我的"
    };

    private Context mContext;

    public LeftMenuAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return menus.length + 1;
    }

    private final int ITEM_COUTN = 2;
    private final int IMAGE_TYPE = 0;
    private final int TEXT_TYPE = 1;

    @Override
    public int getViewTypeCount() {
        return ITEM_COUTN;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? IMAGE_TYPE : TEXT_TYPE;
    }

    @Override
    public String getItem(int position) {
        //预防postion == 0的情况
        if (position == 0) {
            return null;
        }
        return menus[position - 1];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    getItemViewType(position) == IMAGE_TYPE ? R.layout.menu_image_item : R.layout.menu_text_item
                    , parent, false);
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //
        if (getItemViewType(position) == TEXT_TYPE) {
            viewHolder.bindData(getItem(position));
        }

        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            imageView = itemView.findViewById(R.id.icon);
            textView = itemView.findViewById(R.id.text);
            itemView.setTag(this);
        }

        public void bindData(String text) {
            textView.setText(text);
        }

        public void displayIcon() {

        }
    }
}
