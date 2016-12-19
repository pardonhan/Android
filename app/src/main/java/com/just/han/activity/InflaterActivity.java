package com.just.han.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.just.han.R;

public class InflaterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inflater);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_inflater);
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.activity_inflater_view, layout);

    }
}
