package me.looorielovbb.boom.ui.zhihucomments.conmentlist;

import me.looorielovbb.boom.data.bean.zhihu.CommentBean;
import me.looorielovbb.boom.data.source.DataRepository;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Lulei on 2017/5/22.
 * time : 16:35
 * date : 2017/5/22
 * mail to lulei4461@gmail.com
 */

public class CommentPresenter implements CommentConstract.Presenter {
    private CommentConstract.View mView;
    private DataRepository mDataRepository = DataRepository.getInstance();
    CompositeSubscription subscriptions = new CompositeSubscription();

    public CommentPresenter(CommentConstract.View mView) {
        super();
        this.mView =mView;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void fetchShortCommentInfo(int id) {
        Subscription subscription = mDataRepository
                .getShortComments(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CommentBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CommentBean commentBean) {
                        mView.showList(commentBean.getComments());
                    }
                });
        subscriptions.add(subscription);
    }

    @Override
    public void fetchLongCommentInfo(int id) {
        Subscription subscription = mDataRepository
                .getLongComments(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CommentBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CommentBean commentBean) {
                        mView.showList(commentBean.getComments());
                    }
                });
        subscriptions.add(subscription);
    }
}
