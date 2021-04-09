package me.looorielovbb.boom.network;

import java.io.File;
import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import me.looorielovbb.boom.BoomApp;
import me.looorielovbb.boom.network.api.DoubanApi;
import me.looorielovbb.boom.network.api.GankApi;
import me.looorielovbb.boom.network.api.ZhihuApi;
import me.looorielovbb.boom.network.api.ZhuangbiApi;
import me.looorielovbb.boom.utils.NetWorkUtils;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lulei on 2017/2/17.
 * time : 15:46
 * date : 2017/2/17
 * mail to lulei4461@gmail.com
 */

public class ApiFactory {

    //缓存请求拦截器
    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = chain -> {
        Request request = chain.request();
        if (!NetWorkUtils.isNetworkAvailable(BoomApp.getInstance())) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        Response response = chain.proceed(request);
        if (NetWorkUtils.isNetworkAvailable(BoomApp.getInstance())) {
            int maxAge = 0;
            // 有网络时 设置缓存超时时间0个小时
            response.newBuilder()
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .removeHeader("Boom")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                    .build();
        } else {
            // 无网络时，设置超时为4周
            int maxStale = 60 * 60 * 24 * 28;
            response.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .removeHeader("nyn")
                    .build();
        }
        return response;
    };
    private static DoubanApi doubanApi;
    private static GankApi gankApi;
    private static ZhihuApi zhihuApi;
    private static ZhuangbiApi zhuangbiApi;
    private static OkHttpClient client;
    private static final Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static final RxJavaCallAdapterFactory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    private static void initOkHttpClient() {
        if (client == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

            CookieManager cookieManager = new CookieManager();
            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
            File cacheFile = new File(BoomApp.getInstance().getExternalCacheDir(), "BoomCache");
            Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client = new OkHttpClient.Builder()
                    //设置缓存
                    .cache(cache)
                    .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                    .addInterceptor(loggingInterceptor)
                    //设置cookie
                    .cookieJar(new JavaNetCookieJar(cookieManager))
                    //超时
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .build();
        }
    }

    public static DoubanApi getDoubanApi() {
        initOkHttpClient();
        if (null == doubanApi) {
            Retrofit retrofit = initRetrofitClient(DoubanApi.DOUBAN);
            doubanApi = retrofit.create(DoubanApi.class);
        }
        return doubanApi;
    }

    public static GankApi getGankApi() {
        initOkHttpClient();
        if (null == gankApi) {
            Retrofit retrofit = initRetrofitClient(GankApi.GANK);
            gankApi = retrofit.create(GankApi.class);
        }
        return gankApi;
    }

    public static ZhihuApi getZhihuApi() {
        initOkHttpClient();
        if (null == zhihuApi) {
            Retrofit retrofit = initRetrofitClient(ZhihuApi.ZHIHU);
            zhihuApi = retrofit.create(ZhihuApi.class);
        }
        return zhihuApi;
    }

    public static ZhuangbiApi getZhuangbiApi() {
        initOkHttpClient();
        if (null == zhuangbiApi) {
            Retrofit retrofit = initRetrofitClient(ZhuangbiApi.ZHUANGBI);
            zhuangbiApi = retrofit.create(ZhuangbiApi.class);
        }
        return zhuangbiApi;
    }

    private static Retrofit initRetrofitClient(String host) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(host)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();
    }
}
