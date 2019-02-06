package com.example.aiword;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

/**
 * Created by Longge on 2018/12/31.
 */

public class OptionAdapter extends ArrayAdapter<UserOptions> {
    private int resourceId;

    public OptionAdapter(Context context, int textViewResourceId, List<UserOptions> objects) {
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        UserOptions userOptions = getItem(position);
        View view;
        ViewHold viewhold;
        if (convertView == null) {
            viewhold = new ViewHold();
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewhold.textView = (TextView) view.findViewById(R.id.options);
            view.setTag(viewhold);
        } else {
            view = convertView;
            viewhold = (ViewHold) view.getTag();
        }
        viewhold.textView.setText(userOptions.getText());
        return view;
    }

    class ViewHold {
        TextView textView;
    }

}
