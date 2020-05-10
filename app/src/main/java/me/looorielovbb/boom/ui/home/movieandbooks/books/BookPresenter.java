package me.looorielovbb.boom.ui.home.movieandbooks.books;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import me.looorielovbb.boom.config.Constants;
import me.looorielovbb.boom.data.bean.douban.book.BooksBean;
import me.looorielovbb.boom.data.source.DataRepository;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static me.looorielovbb.boom.utils.Preconditions.checkNotNull;

/**
 * Created by Lulei on 2017/7/11.
 * time : 16:37
 * date : 2017/7/11
 * mail to lulei4461@gmail.com
 */

public class BookPresenter implements BookContract.Presenter {

    CompositeSubscription mSubscriptions = new CompositeSubscription();
    @NonNull
    private DataRepository mRepository = DataRepository.getInstance();
    @NonNull
    private BookContract.View mView;
    private List<BooksBean> booksBeen = new ArrayList<>();

    public BookPresenter(@NonNull BookContract.View mView) {
        this.mView = checkNotNull(mView, "mView can not be null");
    }

    @Override
    public void loaddata(String tag, int page) {
        if (page == 1)
            mView.showloading();
        Subscription subscription = mRepository.searchBooks(tag, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<BooksBean>>() {
                    @Override
                    public void onCompleted() {
                        mView.dismissLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.dismissLoading();
                        mView.showerror(e.getMessage());
                    }

                    @Override
                    public void onNext(List<BooksBean> booksBeen) {
                        if (booksBeen == null) {
                            mView.showerror("没有数据~ 喵");
                        } else if (booksBeen.size() == 0) {
                            mView.showerror("没有数据~ 喵");
                            if (!booksBeen.isEmpty()) {
                                mView.showList(booksBeen);
                                mView.loadComplete();
                            }
                        } else {
                            BookPresenter.this.booksBeen.addAll(booksBeen);
                            mView.showList(BookPresenter.this.booksBeen);
                            if (booksBeen.size() < Constants.PAGE_COUNT) {
                                mView.loadComplete();
                            }
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void clear() {

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }
}
