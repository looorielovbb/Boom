package me.looorielovbb.boom.ui.homepage.meizi;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import me.looorielovbb.boom.config.Constants;
import me.looorielovbb.boom.data.bean.Meizi;
import me.looorielovbb.boom.data.source.DataRepository;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static me.looorielovbb.boom.utils.Preconditions.checkNotNull;

/**
 * Created by Lulei on 2017/2/9.
 * time : 10:58
 * date : 2017/2/9
 * mail to lulei4461@gmail.com
 */

public class MeiziPresenter implements MeiziContract.Presenter {

    CompositeSubscription mSubscriptions = new CompositeSubscription();
    @NonNull private DataRepository mRepository;
    @NonNull private MeiziContract.View mView;

    private List<Meizi> mList = new ArrayList<>();

    public MeiziPresenter(@NonNull DataRepository mRepository, @NonNull MeiziContract.View mView) {
        this.mRepository = checkNotNull(mRepository, "mRepository can not be null");
        this.mView = checkNotNull(mView, "mView can not be null");
    }

    @Override
    public void subscribe() {
        if (mList.isEmpty()) {
            mView.showloading();
            loaddata(1);
        }
    }

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
                            if (list.size() < Constants.PAGE_COUNT){
                                mView.loadComplete();
                            }
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
    public void clearListData() {
        mList.clear();
    }

}
