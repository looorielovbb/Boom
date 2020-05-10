package me.looorielovbb.boom.data.source;

import androidx.annotation.NonNull;

import java.util.List;

import me.looorielovbb.boom.config.Constants;
import me.looorielovbb.boom.data.bean.douban.MovieListResponse;
import me.looorielovbb.boom.data.bean.douban.book.BookBean;
import me.looorielovbb.boom.data.bean.douban.book.BookDetailBean;
import me.looorielovbb.boom.data.bean.douban.book.BooksBean;
import me.looorielovbb.boom.data.bean.gank.Girl;
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
import rx.functions.Func1;

/**
 * Created by Lulei on 2017/2/21.
 * time : 15:32
 * date : 2017/2/21
 * mail to lulei4461@gmail.com
 */

public class DataRepository implements DataSource {
    private static DataRepository INSTANCE;

    @NonNull
    private LocalDataSource mLocalDataSource;
    @NonNull
    private RemoteDataSource mRemoteDataSource;

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
    public Observable<List<Girl>> getGirl(int page) {
        return mRemoteDataSource.getGirl(page);
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
    public Observable<DailyListBean> getLatestDaily() {
        return ApiFactory.getZhihuApi().getDailyList();
    }

    public Observable<BeforeDailyBean> getBeforeDaily(String date) {
        return ApiFactory.getZhihuApi().getBeforeDaily(date);
    }

    public Observable<ZhihuDetailBean> getZhihuDetail(int id) {
        return ApiFactory.getZhihuApi().getDetailInfo(id);
    }

    public Observable<DetailExtraBean> getDetailExtra(int id) {
        return ApiFactory.getZhihuApi().getDetailExtraInfo(id);
    }

    public Observable<CommentBean> getLongComments(int id) {
        return ApiFactory.getZhihuApi().getLongCommentInfo(id);
    }

    public Observable<CommentBean> getShortComments(int id) {
        return ApiFactory.getZhihuApi().getShortCommentInfo(id);
    }

     /*
    * 豆瓣电影
    * */

    public Observable<MovieListResponse> getInTheatersMovie(String city) {
        return ApiFactory.getDoubanApi().getInTheatersMovie(city);
    }

    public Observable<MovieListResponse> getComingSoonMovie(int page) {
        return ApiFactory.getDoubanApi().getComingSoonMovie(Constants.PAGE_COUNT * (page - 1),
                Constants.PAGE_COUNT);
    }

    public Observable<MovieListResponse> getTop250Movie(int page) {
        return ApiFactory.getDoubanApi().getTop250Movie(Constants.PAGE_COUNT * (page - 1),
                Constants.PAGE_COUNT);
    }

    /*
   * 豆瓣图书
   * */
    public Observable<List<BooksBean>> searchBooks(String tag, int page) {
        return ApiFactory.getDoubanApi().getBook(tag, Constants.PAGE_COUNT_GRID * (page - 1),
                Constants.PAGE_COUNT_GRID).map(new Func1<BookBean, List<BooksBean>>() {
            @Override
            public List<BooksBean> call(BookBean bookBean) {
                return bookBean == null ? null : bookBean.getBooks();
            }
        });
    }

    public Observable<BookDetailBean> getBookDetail(String id) {
        return ApiFactory.getDoubanApi().getBookDetail(id);
    }
}
