package me.looorielovbb.boom.ui.home.movieandbooks.bookdetail;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import me.looorielovbb.boom.data.bean.douban.book.BookDetailBean;
import me.looorielovbb.boom.data.bean.douban.book.BooksBean;
import me.looorielovbb.boom.data.source.DataRepository;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static me.looorielovbb.boom.utils.Preconditions.checkNotNull;

/**
 * Created by Lulei on 2017/7/12.
 * time : 10:05
 * date : 2017/7/12
 * mail to lulei4461@gmail.com
 */

public class BDetailPresenter implements BDetailContract.Presenter {

    CompositeSubscription mSubscriptions = new CompositeSubscription();
    @NonNull
    private DataRepository mRepository = DataRepository.getInstance();
    @NonNull
    private BDetailContract.View mView;
    private List<BooksBean> booksBeen = new ArrayList<>();

    public BDetailPresenter(@NonNull BDetailContract.View mView) {
        this.mView = checkNotNull(mView, "mView can not be null");
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void loaddata(String id) {
        Subscription subscription = mRepository.getBookDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BookDetailBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(BookDetailBean bookDetailBean) {
                        mView.showContent(bookDetailBean);
                    }
                });
    }
}
