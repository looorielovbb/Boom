package me.looorielovbb.boom.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;

import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;

import me.looorielovbb.boom.config.Constants;
import me.looorielovbb.boom.utils.PreferencesUtils;

/**
 * Created by Lulei on 2017/2/9.
 * time : 10:17
 * date : 2017/2/9
 * mail to lulei4461@gmail.com
 */

public class BoomApp extends MultiDexApplication {

    @SuppressLint("StaticFieldLeak") public static Context appCtx;

    @Override
    public void onCreate() {
        super.onCreate();
        appCtx = getApplicationContext();
        XLog.init(LogLevel.ALL);
        //当前主题读取
        if (PreferencesUtils.getBoolean(this, Constants.THEME_MODE, false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        //腾讯bugly 初始化
//        CrashReport.initCrashReport(getApplicationContext(), Constants.ID.BUGLY, BuildConfig.DEBUG);
//        CrashReport.setIsDevelopmentDevice(getApplicationContext(), BuildConfig.DEBUG);
        //异常配置
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(this);
    }
}
