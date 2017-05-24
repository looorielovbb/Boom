package me.looorielovbb.boom.ui.home.zhihu;

import java.util.List;

import me.looorielovbb.boom.base.BasePresenter;
import me.looorielovbb.boom.base.BaseView;
import me.looorielovbb.boom.data.bean.zhihu.TopStoriesBean;

/**
 * Created by Lulei on 2017/4/18.
 * time : 10:07
 * date : 2017/4/18
 * mail to lulei4461@gmail.com
 */

public interface ZhihuContract {

    interface View extends BaseView<ZhihuContract.Presenter> {
        void showloading();

        void dismissLoading();

        void showerror(String error);

        void showBanner(List<TopStoriesBean> storiesBeen);

        void showList(List<Object> listdata);

    }

    interface Presenter extends BasePresenter {
        void loadLatestData();

        void loadbeforeData();

        void clear();
    }
}
