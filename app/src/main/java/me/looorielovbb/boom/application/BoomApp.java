package me.looorielovbb.boom.application;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;

/**
 * Created by Lulei on 2017/2/9.
 * time : 10:17
 * date : 2017/2/9
 * mail to lulei4461@gmail.com
 */

public class BoomApp extends MultiDexApplication {

    @SuppressLint("StaticFieldLeak")
    public static Context appCtx;

    @Override
    public void onCreate() {
        super.onCreate();
        appCtx = getApplicationContext();
        XLog.init(LogLevel.ALL);
    }
}
