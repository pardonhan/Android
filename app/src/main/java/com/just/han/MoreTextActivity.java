package com.just.han;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.just.han.views.MoreTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoreTextActivity extends AppCompatActivity {

    @BindView(R.id.mtv_view)
    MoreTextView moreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_text);
        ButterKnife.bind(this);
        moreTextView.setText(getResources().getString(R.string.context));
    }
}
