package com.just.han.views;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2016/8/26.
 *
 */
public class CustomListView extends LoadMoreListView{
    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
