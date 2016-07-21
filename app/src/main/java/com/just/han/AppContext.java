package com.just.han;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Just on 2016/7/19.
 */
public class AppContext extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
