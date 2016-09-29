package com.just.han.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.just.han.R;
import com.just.han.views.HorizontalListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HorizontalListViewActivity extends AppCompatActivity {

    @BindView(R.id.horizontal_list_view)
    HorizontalListView horizontalListView;
    List<String> list ;
    HorizontalAdapter horizontalAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_list_view);
        ButterKnife.bind(this);
        list = new ArrayList<>(Arrays.asList("手机", "电脑", "打印机", "平板电脑", "电脑主机"));
        horizontalAdapter = new HorizontalAdapter(this);
        horizontalListView.setAdapter(horizontalAdapter);
    }

    class HorizontalAdapter extends BaseAdapter {

        private LayoutInflater inflater;

        public HorizontalAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public String getItem(int i) {
            return list.get(i);
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
                view = inflater.inflate(R.layout.horizontal_list_view_item, viewGroup, false);
                holder = new ViewHolder(view);
                view.setTag(holder);
            }
            holder.textView.setText(getItem(i));
            return view;
        }

        class ViewHolder {
            @BindView(R.id.type_text_view)
            TextView textView;
            @BindView(R.id.type_img_view)
            ImageView imageView;

            public ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
