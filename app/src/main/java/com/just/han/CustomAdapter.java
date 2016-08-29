package com.just.han;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by [Just] on 2016/8/22 14:35.
 * adapter
 */
public class CustomAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> stringList;

    public CustomAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.stringList = list;
    }

    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public Object getItem(int i) {
        return stringList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_grid_view, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        holder.textView.setText(stringList.get(i));
        return view;
    }

    class ViewHolder {
        @BindView(R.id.list_text_view)
        TextView textView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
