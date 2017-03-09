package me.looorielovbb.boom.data.source.remote;


import java.util.List;

import me.looorielovbb.boom.config.Constants;
import me.looorielovbb.boom.data.bean.Base;
import me.looorielovbb.boom.data.bean.Meizi;
import me.looorielovbb.boom.data.source.DataSource;
import me.looorielovbb.boom.network.ApiFactory;
import me.looorielovbb.boom.network.api.GankApi;
import rx.Observable;
import rx.functions.Func1;


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
    public Observable<List<Meizi>> getMeizi(int page) {
        return ApiFactory
                .getGankApi()
                .getGankRes(GankApi.types[0], Constants.PAGE_COUNT, page)
                .map(new Func1<Base<Meizi>, List<Meizi>>() {

                    @Override
                    public List<Meizi> call(Base<Meizi> meiziBase) {
                        if (meiziBase == null) {
                            return null;
                        }
                        return meiziBase.getList();
                    }
                });
    }


}
