package me.looorielovbb.boom.ui.home.zhihu;

import java.util.ArrayList;
import java.util.List;

import me.looorielovbb.boom.data.bean.zhihu.BeforeDailyBean;
import me.looorielovbb.boom.data.bean.zhihu.DailyListBean;
import me.looorielovbb.boom.data.source.DataRepository;
import me.looorielovbb.boom.multitype.bean.Banner;
import me.looorielovbb.boom.multitype.bean.SubTitle;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lulei on 2017/4/18.
 * time : 10:09
 * date : 2017/4/18
 * mail to lulei4461@gmail.com
 */

public class ZhihuPresenter implements ZhihuContract.Presenter {

    ZhihuContract.View mView;
    CompositeSubscription mSubscriptions = new CompositeSubscription();
    DataRepository Mdata = DataRepository.getInstance();
    private List<Object> mItems = new ArrayList<>();
    private String mDate;

    public ZhihuPresenter(ZhihuContract.View view) {
        this.mView = view;
    }

    @Override
    public void subscribe() {
        if (mItems.isEmpty()) {
            loadLatestData();
        }
    }

    @Override
    public void loadLatestData() {
        Subscription subscription = Mdata
                .getLatestDaily()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.showloading();
                    }
                })
                .subscribe(new Subscriber<DailyListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.dismissLoading();
                        mView.showerror(e.getMessage());
                    }

                    @Override
                    public void onNext(DailyListBean dailyListBean) {
                        mView.dismissLoading();
                        if (dailyListBean != null) {
                            if (mItems.containsAll(dailyListBean.getStories())) {
                                return;
                            }
                            mDate = dailyListBean.getDate();
                            mItems.add(new Banner(dailyListBean.getTop_stories()));
                            mItems.add(new SubTitle(dailyListBean.getDate()));
                            mItems.addAll(dailyListBean.getStories());
                            mView.showBanner(dailyListBean.getTop_stories());
                            mView.showList(mItems);
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void loadbeforeData() {
        Subscription subscription = Mdata
                .getBeforeDaily(mDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.showloading();
                    }
                })
                .subscribe(new Subscriber<BeforeDailyBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.dismissLoading();
                        mView.showerror(e.getMessage());
                    }

                    @Override
                    public void onNext(BeforeDailyBean beforeDailyBean) {
                        mView.dismissLoading();
                        if (beforeDailyBean != null) {
                            mDate = beforeDailyBean.getDate();
                            mItems.add(new SubTitle(beforeDailyBean.getDate()));
                            mItems.addAll(beforeDailyBean.getStories());
                            mView.showList(mItems);
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void unsubscribe() {
        mView.dismissLoading();
        mSubscriptions.clear();
    }

    @Override
    public void clear() {
        mItems.clear();
    }
}
