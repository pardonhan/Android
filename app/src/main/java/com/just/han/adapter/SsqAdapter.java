package com.just.han.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.just.han.R;
import com.just.han.adapter.viewholder.SsqViewHolder;
import com.just.han.model.SsqModel;
import com.just.han.utils.DebugLog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by HanFL on 2016/10/8.
 * <p>
 * SsqAdapter
 */

public class SsqAdapter extends RecyclerView.Adapter<SsqViewHolder> {

    private Context mContext;
    private List<SsqModel> mList;

    public SsqAdapter(Context context, List<SsqModel> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public SsqViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ssq_item, parent, false);
        return new SsqViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SsqViewHolder holder, int position) {
        SsqModel ssqModel = mList.get(position);
        holder.expectTv.setText("第" + ssqModel.getExpect() + "期");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA);
        try {
            Date date = simpleDateFormat.parse(ssqModel.getOpentime());
            holder.openTimeTv.setText(sdf.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String[] ball = ssqModel.getOpencode().split("\\+");
        String[] redBall = ball[0].split(",");
        holder.redBallTv01.setText(redBall[0]);
        holder.redBallTv02.setText(redBall[1]);
        holder.redBallTv03.setText(redBall[2]);
        holder.redBallTv04.setText(redBall[3]);
        holder.redBallTv05.setText(redBall[4]);
        holder.redBallTv06.setText(redBall[5]);
        holder.blueBallTv.setText(ball[1]);
    }

    @Override
    public int getItemCount() {
        DebugLog.d(mList.size());
        return mList.size();
    }
}
