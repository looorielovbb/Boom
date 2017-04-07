package me.looorielovbb.boom.ui.homepage.cleaner;

import java.util.List;

import me.looorielovbb.boom.base.BasePresenter;
import me.looorielovbb.boom.base.BaseView;
import me.looorielovbb.boom.data.bean.Meizi;

/**
 * Created by Lulei on 2017/4/6.
 * time : 16:16
 * date : 2017/4/6
 * mail to lulei4461@gmail.com
 */

public interface CleanerContract {
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
