package me.looorielovbb.boom.ui.home.movieandbooks.Intheaters;

import android.os.Handler;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import me.looorielovbb.boom.data.bean.douban.MovieInfo;
import me.looorielovbb.boom.data.bean.douban.MovieListResponse;
import me.looorielovbb.boom.data.source.DataRepository;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static me.looorielovbb.boom.utils.Preconditions.checkNotNull;

/**
 * Created by Lulei on 2017/6/30.
 * time : 16:44
 * date : 2017/6/30
 * mail to lulei4461@gmail.com
 */

public class InTheatersPresenter implements InTheatersContract.Presenter {
    CompositeSubscription mSubscriptions = new CompositeSubscription();
    @NonNull
    private DataRepository mRepository = DataRepository.getInstance();
    @NonNull
    private InTheatersContract.View mView;
    private List<MovieInfo> movieInfoList = new ArrayList<>();

    public InTheatersPresenter(@NonNull InTheatersContract.View mView) {
        this.mView = checkNotNull(mView, "mView can not be null");
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        mView.dismissLoading();
        mSubscriptions.clear();
    }

    @Override
    public void loaddata(String city) {
        Subscription subscription = mRepository
                .getInTheatersMovie(city)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.showloading();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieListResponse>() {
                    @Override
                    public void onCompleted() {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mView.dismissLoading();
                            }
                        }, 1000);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showerror(e.getMessage());
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mView.dismissLoading();
                            }
                        }, 1000);
                    }

                    @Override
                    public void onNext(MovieListResponse movieListResponse) {
                        movieInfoList = movieListResponse.getSubjects();
                        mView.showList(movieInfoList);
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void clear() {
        movieInfoList.clear();
    }
}
