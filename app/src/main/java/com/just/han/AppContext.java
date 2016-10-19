package com.just.han;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

import org.xutils.x;

/**
 * Created by Just on 2016/7/19.
 * <p>
 * AppContext
 */
public class AppContext extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
        /**
         * 第三个参数为SDK调试模式开关，调试模式的行为特性如下：
         * 输出详细的Bugly SDK的Log；
         * 每一条Crash都会被立即上报；
         * 自定义日志将会在Logcat中输出。
         * 建议在测试阶段建议设置成true，发布时设置为false。
         */
        CrashReport.initCrashReport(getApplicationContext(), "12d1c1a86b", true);//为了保证运营数据的准确性，建议不要在异步线程初始化Bugly。
    }
}
