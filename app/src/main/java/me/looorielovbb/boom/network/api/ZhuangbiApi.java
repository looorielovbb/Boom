package me.looorielovbb.boom.network.api;

import java.util.List;

import me.looorielovbb.boom.data.bean.others.ZhuangbiImage;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Lulei on 2017/4/13.
 * time : 16:51
 * date : 2017/4/13
 * mail to lulei4461@gmail.com
 */

public interface ZhuangbiApi {
    String ZHUANGBI = "https://www.zhuangbi.info/";

    @GET("search")
    Observable<List<ZhuangbiImage>> search(@Query("q") String query);
}
