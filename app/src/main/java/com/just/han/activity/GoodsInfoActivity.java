package com.just.han.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.just.han.R;

import butterknife.ButterKnife;

public class GoodsInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        ButterKnife.bind(this);
    }
}
