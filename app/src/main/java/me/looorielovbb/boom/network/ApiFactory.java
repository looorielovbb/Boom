package me.looorielovbb.boom.network;

import java.util.concurrent.TimeUnit;

import me.looorielovbb.boom.network.api.DoubanApi;
import me.looorielovbb.boom.network.api.GankApi;
import me.looorielovbb.boom.network.api.ZhihuApi;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lulei on 2017/2/17.
 * time : 15:46
 * date : 2017/2/17
 * mail to lulei4461@gmail.com
 */

public class ApiFactory {

    private static DoubanApi doubanApi;
    private static GankApi gankApi;
    private static ZhihuApi zhihuApi;
    private static OkHttpClient client;
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();

    private static void initOkHttpClient() {
        if (client == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .retryOnConnectionFailure(true)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .build();
        }
    }


    public static DoubanApi getDoubanApi() {
        initOkHttpClient();
        if (null == doubanApi) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(DoubanApi.DOUBAN)
                    .addConverterFactory(gsonConverterFactory)
                    .build();
            doubanApi = retrofit.create(DoubanApi.class);
        }
        return doubanApi;
    }

    public static GankApi getGankApi() {
        initOkHttpClient();
        if (null == gankApi) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(GankApi.GANK)
                    .addConverterFactory(gsonConverterFactory)
                    .build();
            gankApi = retrofit.create(GankApi.class);
        }
        return gankApi;
    }

    public static ZhihuApi getZhihuApi() {
        initOkHttpClient();
        if (null == zhihuApi) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(ZhihuApi.ZHIHU)
                    .addConverterFactory(gsonConverterFactory)
                    .build();
            zhihuApi = retrofit.create(ZhihuApi.class);
        }
        return zhihuApi;
    }
}
