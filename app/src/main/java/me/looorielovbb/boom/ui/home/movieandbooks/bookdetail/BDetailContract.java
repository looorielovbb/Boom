package me.looorielovbb.boom.ui.home.movieandbooks.bookdetail;

import me.looorielovbb.boom.base.BasePresenter;
import me.looorielovbb.boom.base.BaseView;
import me.looorielovbb.boom.data.bean.douban.book.BookDetailBean;

/**
 * Created by Lulei on 2017/7/12.
 * time : 10:01
 * date : 2017/7/12
 * mail to lulei4461@gmail.com
 */

public interface BDetailContract {

    interface View extends BaseView<Presenter> {

        void showContent(BookDetailBean bookDetailBean);

        void showError(String e);

    }

    interface Presenter extends BasePresenter {
        void loaddata(String id);
    }
}
