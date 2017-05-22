package me.looorielovbb.boom.ui.home.meizi;

import java.util.List;

import me.looorielovbb.boom.base.BasePresenter;
import me.looorielovbb.boom.base.BaseView;
import me.looorielovbb.boom.data.bean.gank.Meizi;

/**
 * Created by Lulei on 2017/2/9.
 * time : 10:45
 * date : 2017/2/9
 * mail to lulei4461@gmail.com
 */

public interface MeiziContract {
    interface View extends BaseView<Presenter> {
        void showloading();

        void dismissLoading();

        void showerror(String error);

        void showList(List<Meizi> list);

        void loadComplete();
    }

    interface Presenter extends BasePresenter {
        void loaddata(int page);

        void clearListData();
    }
}