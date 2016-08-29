package com.just.han.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.just.han.CustomAdapter;
import com.just.han.R;
import com.just.han.views.CustomGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by [Just] on 2016/8/22 14:11.
 * GridView 练习
 */

public class GridViewActivity extends AppCompatActivity {

    @BindView(R.id.layout_)
    LinearLayout linearLayout;

   /* @BindView(R.id.swip_refresh)
    SwipeRefreshLayout swipeRefreshLayout;*/

    @BindView(R.id.grid_view)
    CustomGridView customGridView;

    List<String> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        ButterKnife.bind(this);
        list = new ArrayList<>();
        int s = 0;
        for (int i = 0; i < 100; i++) {
            s++;
            list.add(s + "");
        }
        CustomAdapter customAdapter = new CustomAdapter(GridViewActivity.this, list);
        customGridView.setAdapter(customAdapter);
        customGridView.deferNotifyDataSetChanged();

    }
}
