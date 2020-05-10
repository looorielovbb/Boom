package me.looorielovbb.boom.ui.zhihudetail;

import androidx.annotation.NonNull;

import me.looorielovbb.boom.data.bean.zhihu.DetailExtraBean;
import me.looorielovbb.boom.data.bean.zhihu.ZhihuDetailBean;
import me.looorielovbb.boom.data.source.DataRepository;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static me.looorielovbb.boom.utils.Preconditions.checkNotNull;

/**
 * Created by Lulei on 2017/5/19.
 */

public class ZdetailPresenter implements ZdetailContract.Presenter {
    CompositeSubscription mSubscriptions = new CompositeSubscription();
    @NonNull
    private DataRepository mRepository = DataRepository.getInstance();
    ZdetailContract.View mView;

    public ZdetailPresenter(ZdetailContract.View mView) {
        this.mView = checkNotNull(mView, "mView can not be null");
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }

    @Override
    public void getStoryContent(int id) {
        Subscription subscription = mRepository
                .getZhihuDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ZhihuDetailBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e);
                    }

                    @Override
                    public void onNext(ZhihuDetailBean zhihuDetailBean) {
                        mView.showContent(zhihuDetailBean);
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void getStoryExtras(int id) {
        Subscription subscription = mRepository
                .getDetailExtra(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DetailExtraBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e);
                    }

                    @Override
                    public void onNext(DetailExtraBean detailExtraBean) {
                        mView.showStoryExtras(detailExtraBean);
                    }
                });
        mSubscriptions.add(subscription);
    }
}
