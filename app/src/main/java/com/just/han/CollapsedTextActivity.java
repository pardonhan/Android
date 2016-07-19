package com.just.han;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.just.han.views.CollapsedTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollapsedTextActivity extends AppCompatActivity {

    @BindView(R.id.collapsed_view)
    CollapsedTextView collapsedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsed_text);
        ButterKnife.bind(this);
        collapsedTextView.setText(getResources().getString(R.string.context));
    }
}
