package com.just.han.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.just.han.R;
import com.just.han.activity.animation.AlphaActivity;
import com.just.han.activity.animation.ScaleActivity;

public class AnimActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.button:
                intent.setClass(AnimActivity.this, ScaleActivity.class);
                startActivity(intent);
                break;
            case R.id.button2:
                intent.setClass(AnimActivity.this, AlphaActivity.class);
                startActivity(intent);
                break;
            case R.id.button3:
                intent.setClass(AnimActivity.this, ScaleActivity.class);
                startActivity(intent);
                break;
        }
    }
}
