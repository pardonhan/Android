package com.just.han.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.just.han.R;
import com.just.han.utils.DebugLog;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BottomNavigationActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener, View.OnClickListener {

    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;

    int lastSelectedPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        ButterKnife.bind(this);

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.home_page_img, "首页").setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.drawable.shop_cart_img, "购物车").setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.drawable.personal_info_img, "个人中心").setActiveColorResource(R.color.orange))
                .setFirstSelectedPosition(lastSelectedPosition > 2 ? 2 : lastSelectedPosition)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(int position) {
        lastSelectedPosition = position;
        DebugLog.d(lastSelectedPosition);
        switch (position) {
            case 0:

                break;
            case 1:

                break;
            case 2:

                break;
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public void onClick(View v) {

    }
}
