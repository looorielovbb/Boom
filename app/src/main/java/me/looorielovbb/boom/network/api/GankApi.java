package me.looorielovbb.boom.network.api;

import me.looorielovbb.boom.data.bean.gank.Base;
import me.looorielovbb.boom.data.bean.gank.Girl;
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
    String GANK = "https://gank.io/api/v2/";

    //category 可接受参数 Article | GanHuo | Girl
    String[] CATAGORY_NAMES = {"Girl", "GanHuo", "Article"};

    String[] TYPES = {"Android", "iOS", "Flutter","Girl"};
    /**
     * 获取分类数据接口
     * @param type 可接受参数 Android | iOS | Flutter | Girl，即分类API返回的类型数据
     * @param count [1, 50]
     * @param page 页码
     * @return 数据流
     */
    @GET("data/category/{category}/type/{type}/page/{page}/count/{count}")
    Observable<Base<Girl>> getGankRes(@Path("category") String category,
                                      @Path("type") String type,
                                      @Path("page") int page,
                                      @Path("count") int count);
}
