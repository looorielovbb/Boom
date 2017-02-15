package me.looorielovbb.boom.splashpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.looorielovbb.boom.R;
import me.looorielovbb.boom.base.BaseActivity;
import me.looorielovbb.boom.config.Constants;
import me.looorielovbb.boom.homepage.MainActivity;
import me.looorielovbb.boom.utils.PreferencesUtils;
import me.looorielovbb.boom.utils.RxCount;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;

import static me.looorielovbb.boom.application.BoomApp.appCtx;

public class SplashActivity extends BaseActivity {

    @BindView(R.id.tv_skip)
    TextView tvSkip;
    Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        //初始化组件

        if (PreferencesUtils.getBoolean(appCtx, Constants.THEME_MODE, false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        subscription = RxCount.countdown(3)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {

                    }
                }).subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        tvSkip.setText("跳过");
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        tvSkip.setText(String.valueOf("跳过" + integer));
                    }
                });
    }

    @OnClick(R.id.tv_skip)
    public void onClick() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void onBackPressed() {

    }
}
