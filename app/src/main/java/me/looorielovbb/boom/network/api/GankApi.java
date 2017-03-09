package me.looorielovbb.boom.network.api;

import me.looorielovbb.boom.data.bean.Base;
import me.looorielovbb.boom.data.bean.Meizi;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Lulei on 2017/2/17.
 * time : 15:58
 * date : 2017/2/17
 * mail to lulei4461@gmail.com
 */

public interface GankApi {
    String GANK = "http://gank.io/";
    String[] types = {"福利", "Android", "ios"};

    @GET("api/data/{type}/{count}/{page}")
    Observable<Base<Meizi>> getGankRes(@Path("type") String type,
                                       @Path("count") int count,
                                       @Path("page") int page);
}
