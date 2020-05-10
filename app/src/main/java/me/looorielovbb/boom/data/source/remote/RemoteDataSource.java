package me.looorielovbb.boom.data.source.remote;


import java.util.List;

import me.looorielovbb.boom.config.Constants;
import me.looorielovbb.boom.data.bean.gank.Base;
import me.looorielovbb.boom.data.bean.gank.Girl;
import me.looorielovbb.boom.data.bean.others.ZhuangbiImage;
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
    public Observable<List<Girl>> getGirl(int page) {
        return ApiFactory.getGankApi().getGankRes(GankApi.CATAGORY_NAMES[0],GankApi.TYPES[3], page, Constants.PAGE_COUNT).map(
                new Func1<Base<Girl>, List<Girl>>() {

                    @Override
                    public List<Girl> call(Base<Girl> girlBase) {
                        return girlBase == null ? null : girlBase.getList();
                    }
                });
    }

    @Override
    public Observable<List<ZhuangbiImage>> getEmoji(String keyword) {
        return ApiFactory.getZhuangbiApi().search(keyword);
    }

}
