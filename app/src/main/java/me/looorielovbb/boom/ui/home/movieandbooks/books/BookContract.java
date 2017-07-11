package me.looorielovbb.boom.ui.home.movieandbooks.books;

import java.util.List;

import me.looorielovbb.boom.base.BasePresenter;
import me.looorielovbb.boom.base.BaseView;
import me.looorielovbb.boom.data.bean.douban.book.BooksBean;

/**
 * Created by Lulei on 2017/7/10.
 * time : 14:41
 * date : 2017/7/10
 * mail to lulei4461@gmail.com
 */

public interface BookContract {

    interface View extends BaseView<Presenter> {
        void showloading();

        void dismissLoading();

        void showerror(String error);

        void showList(List<BooksBean> list);

        void loadComplete();
    }

    interface Presenter extends BasePresenter {
        void loaddata(String tag, int page);

        void clear();
    }
}
