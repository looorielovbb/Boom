package me.looorielovbb.boom.homepage;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lulei on 2017/2/9.
 * time : 10:58
 * date : 2017/2/9
 * mail to lulei4461@gmail.com
 */

public class MainPresenter implements MainContract.Presenter {
    private CompositeSubscription subscriptions = new CompositeSubscription();

    @Override
    public void getListdata() {

    }

    @Override
    public void subscribe() {
    }

    @Override
    public void unsubscribe() {
        subscriptions.clear();
    }
}
