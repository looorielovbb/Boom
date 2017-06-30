package me.looorielovbb.boom.data.source;

import android.support.annotation.NonNull;

import java.util.List;

import me.looorielovbb.boom.data.bean.douban.MovieListResponse;
import me.looorielovbb.boom.data.bean.gank.Meizi;
import me.looorielovbb.boom.data.bean.others.ZhuangbiImage;
import me.looorielovbb.boom.data.bean.zhihu.BeforeDailyBean;
import me.looorielovbb.boom.data.bean.zhihu.CommentBean;
import me.looorielovbb.boom.data.bean.zhihu.DailyListBean;
import me.looorielovbb.boom.data.bean.zhihu.DetailExtraBean;
import me.looorielovbb.boom.data.bean.zhihu.ZhihuDetailBean;
import me.looorielovbb.boom.data.source.local.LocalDataSource;
import me.looorielovbb.boom.data.source.remote.RemoteDataSource;
import me.looorielovbb.boom.network.ApiFactory;
import rx.Observable;

/**
 * Created by Lulei on 2017/2/21.
 * time : 15:32
 * date : 2017/2/21
 * mail to lulei4461@gmail.com
 */

public class DataRepository implements DataSource {
    private static DataRepository INSTANCE;

    @NonNull private LocalDataSource mLocalDataSource;
    @NonNull private RemoteDataSource mRemoteDataSource;

    private DataRepository(@NonNull LocalDataSource mLocalDataSource,
                           @NonNull RemoteDataSource mRemoteDataSource) {
        this.mLocalDataSource = mLocalDataSource;
        this.mRemoteDataSource = mRemoteDataSource;
    }

    public static DataRepository getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new DataRepository(LocalDataSource.getInstance(),
                                          RemoteDataSource.getInstance());
        }
        return INSTANCE;
    }

    /*
    * Gank
    * */
    @Override
    public Observable<List<Meizi>> getMeizi(int page) {
        return mRemoteDataSource.getMeizi(page);
    }

    /*
    * 装逼大全
    * */
    @Override
    public Observable<List<ZhuangbiImage>> getEmoji(String keyword) {
        return mRemoteDataSource.getEmoji(keyword);
    }

    /*
    * 知乎
    * */
    public Observable<DailyListBean> getLatestDaily(){
        return ApiFactory.getZhihuApi().getDailyList();
    }

    public Observable<BeforeDailyBean> getBeforeDaily(String date){
        return ApiFactory.getZhihuApi().getBeforeDaily(date);
    }

    public Observable<ZhihuDetailBean> getZhihuDetail(int id){
        return ApiFactory.getZhihuApi().getDetailInfo(id);
    }

    public Observable<DetailExtraBean> getDetailExtra(int id){
        return ApiFactory.getZhihuApi().getDetailExtraInfo(id);
    }

    public Observable<CommentBean> getLongComments(int id){
        return ApiFactory.getZhihuApi().getLongCommentInfo(id);
    }

    public Observable<CommentBean> getShortComments(int id ){
        return ApiFactory.getZhihuApi().getShortCommentInfo(id);
    }

     /*
    * 豆瓣
    * */

    public Observable<MovieListResponse> getInTheatersMovie(String city) {
        return ApiFactory.getDoubanApi().getInTheatersMovie(city);
    }


}
