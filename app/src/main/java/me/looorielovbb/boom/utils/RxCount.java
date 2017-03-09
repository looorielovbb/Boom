package me.looorielovbb.boom.utils;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;


/**
 * Created by Lulei on 2017/2/14.
 * time : 11:23
 * date : 2017/2/14
 * mail to lulei4461@gmail.com
 */

public class RxCount {
    public static Observable<Integer> countdown(int seconds) {
        if (seconds < 0) seconds = 0;

        final int countTime = seconds;
        return Observable
                .interval(0, 1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<Long, Integer>() {
                    @Override
                    public Integer call(Long increaseTime) {
                        return countTime - increaseTime.intValue();
                    }
                })
                .take(countTime + 1);

    }

}
