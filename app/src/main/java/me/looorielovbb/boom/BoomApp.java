package me.looorielovbb.boom;

import android.app.Application;
import androidx.appcompat.app.AppCompatDelegate;


import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;
import com.tencent.bugly.crashreport.CrashReport;

import me.looorielovbb.boom.config.Constants;
import me.looorielovbb.boom.utils.CrashHandler;
import me.looorielovbb.boom.utils.PreferencesUtils;

public class BoomApp extends Application {

    private static BoomApp mAppInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppInstance = this;
        XLog.init(LogLevel.ALL);
        //当前主题读取
        if (PreferencesUtils.getBoolean(this, Constants.THEME_MODE, false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        //腾讯bugly 初始化
        CrashReport.initCrashReport(getApplicationContext(), Constants.ID.BUGLY, BuildConfig.DEBUG);
        CrashReport.setIsDevelopmentDevice(getApplicationContext(), BuildConfig.DEBUG);
        //异常配置
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
    }

    @Override
    public void onTerminate() {
        mAppInstance = null;
        super.onTerminate();
    }

    public static BoomApp getInstance() {
        return mAppInstance;
    }
}
