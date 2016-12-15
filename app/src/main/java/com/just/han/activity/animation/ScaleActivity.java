package com.just.han.activity.animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.just.han.R;

public class ScaleActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageView;
    private Button button;
    private Animation scaleAnimation;
    private ScaleAnimation mScaleAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale);
        imageView = (ImageView) findViewById(R.id.imageView2);
        button = (Button) findViewById(R.id.button4);
        button.setOnClickListener(this);
        scaleAnimation = AnimationUtils.loadAnimation(ScaleActivity.this, R.anim.scale_anim);

        mScaleAnimation = new ScaleAnimation(0.0f, 1.4f, 0.0f, 1.4f);
        mScaleAnimation.setDuration(2000);
        mScaleAnimation.setRepeatCount(1);
        mScaleAnimation.setRepeatMode(Animation.REVERSE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button4:
                imageView.startAnimation(scaleAnimation);
                break;
        }
    }
}
