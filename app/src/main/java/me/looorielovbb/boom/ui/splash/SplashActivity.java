package me.looorielovbb.boom.ui.splash;

import android.content.Intent;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.looorielovbb.boom.R;
import me.looorielovbb.boom.base.BaseActivity;
import me.looorielovbb.boom.ui.home.MainActivity;
import me.wangyuwei.particleview.ParticleView;


public class SplashActivity extends BaseActivity {


    @BindView(R.id.particleview)
    ParticleView mParticleview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        mParticleview.setOnParticleAnimListener(new ParticleView.ParticleAnimListener() {
            @Override
            public void onAnimationEnd() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mParticleview.startAnim();
//        Rx方法计时器
//        subscription = RxCount.countdown(3)
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe(new Action0() {
//                    @Override
//                    public void call() {
//
//                    }
//                }).subscribe(new Subscriber<Integer>() {
//                    @Override
//                    public void onCompleted() {
//                        tvSkip.setText("跳过");
//                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
//                        finish();
//                    }
//
//                    @Override
//                    public void onError(Throwable throwable) {
//
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                        tvSkip.setText(String.valueOf("跳过" + integer));
//                    }
//                });
    }


    @Override
    public void onBackPressed() {

    }
}
