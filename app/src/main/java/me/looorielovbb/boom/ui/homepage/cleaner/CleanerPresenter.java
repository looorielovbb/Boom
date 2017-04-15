package me.looorielovbb.boom.ui.homepage.cleaner;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import me.looorielovbb.boom.data.bean.gank.Meizi;
import me.looorielovbb.boom.data.source.DataRepository;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static me.looorielovbb.boom.utils.Preconditions.checkNotNull;

/**
 * Created by Lulei on 2017/4/6.
 * time : 16:31
 * date : 2017/4/6
 * mail to lulei4461@gmail.com
 */

public class CleanerPresenter implements CleanerContract.Presenter{
    CompositeSubscription mSubscriptions = new CompositeSubscription();
    private final DataRepository mRepository;
    private final CleanerContract.View mView;

    public CleanerPresenter(@NonNull DataRepository mRepository, @NonNull CleanerContract.View mView) {
        this.mRepository = checkNotNull(mRepository, "mRepository can not be null");
        this.mView = checkNotNull(mView, "mView can not be null");
    }

    private List<Meizi> mList = new ArrayList<>();


    @Override
    public void loaddata(int page) {
        Subscription subscription = mRepository
                .getMeizi(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Meizi>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.dismissLoading();
                        mView.showerror(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Meizi> list) {
                        mView.dismissLoading();
                        if (list == null) {

                        } else if (list.size() == 0) {
                            mView.showerror("没有数据~ 喵");
                            if(!mList.isEmpty()){
                                mView.showList(mList);
                                mView.loadComplete();
                            }
                        } else {
                            mList.addAll(list);
                            mView.showList(mList);
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void clearListData() {
        mList.clear();
    }

    @Override
    public void subscribe() {
        if (mList.isEmpty()){
            mView.showloading();
            loaddata(1);
        }
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }
}
