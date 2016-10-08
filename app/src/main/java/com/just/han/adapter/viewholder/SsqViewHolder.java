package com.just.han.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.just.han.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HanFL on 2016/10/8.
 * <p>
 * SsqViewHolder
 */

public class SsqViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.expect)
    public TextView expectTv;
    @BindView(R.id.open_time)
    public TextView openTimeTv;
    @BindView(R.id.red_ball_01)
    public TextView redBallTv01;
    @BindView(R.id.red_ball_02)
    public TextView redBallTv02;
    @BindView(R.id.red_ball_03)
    public TextView redBallTv03;
    @BindView(R.id.red_ball_04)
    public TextView redBallTv04;
    @BindView(R.id.red_ball_05)
    public TextView redBallTv05;
    @BindView(R.id.red_ball_06)
    public TextView redBallTv06;
    @BindView(R.id.blue_ball)
    public TextView blueBallTv;

    public SsqViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
