package me.looorielovbb.boom.ui.homepage.meizi;

import android.support.annotation.NonNull;

import java.util.LinkedList;
import java.util.List;

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

    private int mCurrentPage = 0;

    private List<Meizi> mList = new LinkedList<>();

    public MeiziPresenter(@NonNull DataRepository mRepository, @NonNull MeiziContract.View mView) {
        this.mRepository = checkNotNull(mRepository, "mRepository can not be null");
        this.mView = checkNotNull(mView, "mView can not be null");
    }

    @Override
    public void subscribe() {
        if (mList.isEmpty()) {
            loaddata(mCurrentPage);
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
                            return;
                        } else if (list.size() == 0) {
                            mView.showerror("没有新数据~ 喵");
                        } else {
                            mCurrentPage++;
                            mList.addAll(list);
                        }
                        mView.showList(mList);
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
        mList = new LinkedList<>();
    }

    @Override
    public int getCurrentPage(){
        return mCurrentPage;
    }

    @Override
    public void setCurrentPage(int page) {
        this.mCurrentPage = page;
    }
}
