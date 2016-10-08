package com.just.han.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.just.han.R;
import com.just.han.adapter.PropertyAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodsInfoActivity extends AppCompatActivity {

    private List<Map<String, String[]>> list = new ArrayList<>();
    private PropertyAdapter propertyAdapter;
    @BindView(R.id.lv_property)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        ButterKnife.bind(this);
        for (int i = 0; i < 3; i++) {
            Map<String, String[]> map = new HashMap<>();
            map.put("type", new String[]{"type" + i});
            String[] strs = new String[8];
            for (int j = 0; j < strs.length; j++) {
                strs[i] = "lable" + j;
            }
            map.put("lable", strs);
            list.add(map);
        }
        propertyAdapter = new PropertyAdapter(this, list);
        listView.setAdapter(propertyAdapter);
        propertyAdapter.notifyDataSetChanged();
    }
}
