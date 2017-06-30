package me.looorielovbb.boom.network.api;

import me.looorielovbb.boom.data.bean.douban.CastDetail;
import me.looorielovbb.boom.data.bean.douban.CastPhoto;
import me.looorielovbb.boom.data.bean.douban.CastWork;
import me.looorielovbb.boom.data.bean.douban.Comment;
import me.looorielovbb.boom.data.bean.douban.MovieDetail;
import me.looorielovbb.boom.data.bean.douban.MovieListResponse;
import me.looorielovbb.boom.data.bean.douban.MoviePhoto;
import me.looorielovbb.boom.data.bean.douban.Review;
import me.looorielovbb.boom.data.bean.douban.WeeklyMovieInfo;
import me.looorielovbb.boom.data.bean.douban.book.BookBean;
import me.looorielovbb.boom.data.bean.douban.book.BookDetailBean;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Lulei on 2017/2/17.
 * time : 15:58
 * date : 2017/2/17
 * mail to lulei4461@gmail.com
 */

public interface DoubanApi {
    String DOUBAN = "https://api.douban.com/";

    /*
     * 图书
     * * */

    @GET("v2/book/search")
    Observable<BookBean> getBook(@Query("tag") String tag, @Query("start") int start, @Query("count") int count);

    @GET("v2/book/{id}")
    Observable<BookDetailBean> getBookDetail(@Path("id") String id);

    /*
     * 电影
     * * */

    @GET("v2/movie/top250")
    Observable<MovieListResponse> getTop250Movie(@Query("start") int start, @Query("count") int count);

    @GET("v2/movie/in_theaters")
    Observable<MovieListResponse> getInTheatersMovie(@Query("city") String city);

    @GET("v2/movie/coming_soon")
    Observable<MovieListResponse> getComingSoonMovie(@Query("start") int start, @Query("count") int count);

    @GET("v2/movie/weekly")
    Observable<WeeklyMovieInfo> getWeeklyMovie(@Query("apikey") String apikey);

    @GET("v2/movie/new_movies")
    Observable<MovieListResponse> getNewMovies(@Query("apikey") String apikey);

    @GET("v2/movie/search")
    Observable<MovieListResponse> getSearchMovieList(@Query("start") int start, @Query("q") String keyWords, @Query("apikey") String apikey);

    @GET("v2/movie/subject/{id}")
    Observable<MovieDetail> getSubject(@Path("id") String id, @Query("apikey") String apikey);

    @GET("v2/movie/subject/{id}/reviews")
    Observable<Review> getReview(@Path("id") String id, @Query("apikey") String apikey);

    @GET("v2/movie/subject/{id}/comments")
    Observable<Comment> getComment(@Path("id") String id, @Query("apikey") String apikey);

    @GET("v2/movie/subject/{id}/photos")
    Observable<MoviePhoto> getMoviePhoto(@Path("id") String id, @Query("start") int start, @Query("apikey") String apikey);

    @GET("v2/movie/celebrity/{id}")
    Observable<CastDetail> getCastDetail(@Path("id") String id, @Query("apikey") String apikey);

    @GET("v2/movie/celebrity/{id}/photos")
    Observable<CastPhoto> getCastPhoto(@Path("id") String id, @Query("start") int start, @Query("apikey") String apikey);

    @GET("v2/movie/celebrity/{id}/works")
    Observable<CastWork> getCastWorks(@Path("id") String id, @Query("start") int start, @Query("apikey") String apikey);

}
