package me.looorielovbb.boom.ui.home.movieandbooks.Intheaters;

import java.util.List;

import me.looorielovbb.boom.base.BasePresenter;
import me.looorielovbb.boom.base.BaseView;
import me.looorielovbb.boom.data.bean.douban.MovieInfo;

/**
 * Created by Lulei on 2017/6/30.
 * time : 16:41
 * date : 2017/6/30
 * mail to lulei4461@gmail.com
 */

public interface InTheatersContract {

    interface View extends BaseView<Presenter> {
        void showloading();

        void dismissLoading();

        void showerror(String error);

        void showList(List<MovieInfo> list);
    }

    interface Presenter extends BasePresenter {
        void loaddata(String city);

        void clear();
    }
}
