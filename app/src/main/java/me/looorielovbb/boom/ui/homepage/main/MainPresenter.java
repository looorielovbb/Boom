package me.looorielovbb.boom.ui.homepage.main;

import java.util.ArrayList;
import java.util.List;

import me.looorielovbb.boom.data.bean.zhihu.BeforeDailyBean;
import me.looorielovbb.boom.data.bean.zhihu.DailyListBean;
import me.looorielovbb.boom.data.source.DataRepository;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lulei on 2017/4/18.
 * time : 10:09
 * date : 2017/4/18
 * mail to lulei4461@gmail.com
 */

public class MainPresenter implements MainContract.Presenter {

    MainContract.View mView;
    CompositeSubscription mSubscriptions = new CompositeSubscription();
    DataRepository Mdata = DataRepository.getInstance();
    private List<Object> mItems = new ArrayList<>();
    private String mDate;

    public MainPresenter(MainContract.View view) {
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
                .subscribe(new Subscriber<DailyListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showerror(e.getMessage());
                    }

                    @Override
                    public void onNext(DailyListBean dailyListBean) {
                        if (dailyListBean != null) {

                            mDate = dailyListBean.getDate();
                            mItems.add(dailyListBean);
//                    mItems.add(dailyListBean.getStories());
                            mView.showList(dailyListBean);
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
                .subscribe(new Subscriber<BeforeDailyBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BeforeDailyBean beforeDailyBean) {
                        if (beforeDailyBean != null) {
                            mDate = beforeDailyBean.getDate();
                            mItems.add(beforeDailyBean.getStories());
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void unsubscribe() {

    }
}
