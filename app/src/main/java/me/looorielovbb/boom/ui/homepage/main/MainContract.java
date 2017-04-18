package me.looorielovbb.boom.ui.homepage.main;

import me.looorielovbb.boom.base.BasePresenter;
import me.looorielovbb.boom.base.BaseView;
import me.looorielovbb.boom.data.bean.zhihu.DailyListBean;

/**
 * Created by Lulei on 2017/4/18.
 * time : 10:07
 * date : 2017/4/18
 * mail to lulei4461@gmail.com
 */

public interface MainContract {

    interface View extends BaseView<MainContract.Presenter> {
        void showloading();

        void dismissLoading();

        void showerror(String error);

        void showList(DailyListBean dailyListBean);

        void loadComplete();
    }

    interface Presenter extends BasePresenter {
        void loadLatestData();

        void loadbeforeData();
    }
}
