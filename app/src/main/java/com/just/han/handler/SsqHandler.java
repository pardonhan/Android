package com.just.han.handler;

import android.os.Handler;
import android.os.Message;

import com.just.han.activity.SsqActivity;

import java.lang.ref.WeakReference;

/**
 * Created by HanFL on 2016/10/8.
 * SsqHandler
 */

public class SsqHandler extends Handler {
    public final int REFRESH_CODE = 1;
    private WeakReference<SsqActivity> weakReference;

    public SsqHandler(WeakReference<SsqActivity> wk) {
        this.weakReference = wk;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        final SsqActivity activity = weakReference.get();
        if (activity == null) {
            return;
        }
        switch (msg.what) {
            case REFRESH_CODE:
                activity.swipeRefreshHelper.refreshComplete();
                activity.ssqAdapter.notifyDataSetChanged();
                break;
        }
    }
}
