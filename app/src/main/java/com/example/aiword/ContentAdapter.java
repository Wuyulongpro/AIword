package com.example.aiword;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

/**
 * Created by Longge on 2018/12/31.
 */


public class ContentAdapter extends BaseAdapter {

    private Context context;
    private List<DrawerContent> list;

    public ContentAdapter(Context context, List<DrawerContent> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (list != null) {
            return list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHold viewhold;
        if (convertView == null) {
            viewhold = new ViewHold();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.drawer_item, null);
            convertView.setTag(viewhold);
        } else {
            viewhold = (ViewHold) convertView.getTag();
        }

        viewhold.imageView = (ImageView) convertView
                .findViewById(R.id.item_imageview);
        viewhold.textView = (TextView) convertView.findViewById(R.id.item_textview);
        viewhold.imageView.setImageResource(list.get(position).getImageView());
        viewhold.textView.setText(list.get(position).getText());
        return convertView;
    }

    class ViewHold {
        public ImageView imageView;
        public TextView textView;
    }

}