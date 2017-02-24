package me.looorielovbb.boom.data.source.remote;


import me.looorielovbb.boom.config.Constants;
import me.looorielovbb.boom.data.bean.Base;
import me.looorielovbb.boom.data.bean.Meizi;
import me.looorielovbb.boom.data.source.DataSource;
import me.looorielovbb.boom.data.source.ResponseListener;
import me.looorielovbb.boom.network.ApiFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Lulei on 2017/2/21.
 * time : 15:39
 * date : 2017/2/21
 * mail to lulei4461@gmail.com
 */

public class RemoteDataSource implements DataSource {
    private static RemoteDataSource INSTANCE;

    private RemoteDataSource() {

    }

    public static RemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource();
        }
        return INSTANCE;
    }


    @Override
    public void getGirls(int page,final ResponseListener listener) {
         ApiFactory
                .getGankApi()
                .getGankRes("福利", Constants.PAGE_COUNT, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Base<Meizi>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e);
                    }

                    @Override
                    public void onNext(Base<Meizi> meiziBase) {
                        listener.onSuccess(meiziBase);
                    }
                });
    }


}
