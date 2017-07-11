package me.looorielovbb.boom.ui.home.movieandbooks.top250;

import android.os.Handler;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import me.looorielovbb.boom.config.Constants;
import me.looorielovbb.boom.data.bean.douban.MovieInfo;
import me.looorielovbb.boom.data.bean.douban.MovieListResponse;
import me.looorielovbb.boom.data.source.DataRepository;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static me.looorielovbb.boom.utils.Preconditions.checkNotNull;

/**
 * Created by Lulei on 2017/7/3.
 */

public class Top250Presenter implements Top250Contract.Presenter {
    CompositeSubscription mSubscriptions = new CompositeSubscription();
    @NonNull
    private DataRepository mRepository = DataRepository.getInstance();
    @NonNull
    private Top250Contract.View mView;
    private List<MovieInfo> movieInfoList = new ArrayList<>();

    public Top250Presenter(@NonNull Top250Contract.View mView) {
        this.mView = checkNotNull(mView, "mView can not be null");
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void loaddata(final int page) {
        if (page == 1)
            mView.showloading();
        Subscription subscription = mRepository.getTop250Movie(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieListResponse>() {
                    @Override
                    public void onCompleted() {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mView.dismissLoading();
                            }
                        }, 500);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.dismissLoading();
                        mView.showerror(e.getMessage());
                    }

                    @Override
                    public void onNext(MovieListResponse movieListResponse) {
                        List<MovieInfo> list = movieListResponse.getSubjects();
                        if (list == null) {
                            mView.showerror("没有数据~ 喵");
                        } else if (list.size() == 0) {
                            mView.showerror("没有数据~ 喵");
                            if (!movieInfoList.isEmpty()) {
                                mView.showList(movieInfoList);
                                mView.loadComplete();
                            }
                        } else {

                            movieInfoList.addAll(list);
                            mView.showList(movieInfoList);
                            if (list.size() < Constants.PAGE_COUNT) {
                                mView.loadComplete();
                            }
                        }
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void clear() {
        movieInfoList.clear();
    }
}
