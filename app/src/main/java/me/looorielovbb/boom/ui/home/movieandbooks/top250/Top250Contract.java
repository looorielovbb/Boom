package me.looorielovbb.boom.ui.home.movieandbooks.top250;

import java.util.List;

import me.looorielovbb.boom.base.BasePresenter;
import me.looorielovbb.boom.base.BaseView;
import me.looorielovbb.boom.data.bean.douban.MovieInfo;

/**
 * Created by Lulei on 2017/7/3.
 */

public interface Top250Contract {

    interface View extends BaseView<Presenter> {

        void showloading();

        void dismissLoading();

        void showerror(String error);

        void showList(List<MovieInfo> list);

        void loadComplete();
    }

    interface Presenter extends BasePresenter {
        void loaddata(int page);

        void clear();
    }
}
