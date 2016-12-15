package com.just.han.activity.animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.just.han.R;

public class AlphaActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageView;
    private Animation alphaAnimation;
    private AlphaAnimation mAlphaAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alpha);
        imageView = (ImageView) findViewById(R.id.imageView3);
        findViewById(R.id.button5).setOnClickListener(this);
        alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);
        mAlphaAnimation = new AlphaAnimation(1, 0.1f);
        mAlphaAnimation.setDuration(2000);
        mAlphaAnimation.setRepeatMode(Animation.REVERSE);
        mAlphaAnimation.setRepeatCount(1);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button5:
                imageView.startAnimation(alphaAnimation);
                break;
        }
    }
}
