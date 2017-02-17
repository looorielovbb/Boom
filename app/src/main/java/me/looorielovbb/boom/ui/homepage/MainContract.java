package me.looorielovbb.boom.ui.homepage;

import me.looorielovbb.boom.base.BasePresenter;
import me.looorielovbb.boom.base.BaseView;

/**
 * Created by Lulei on 2017/2/9.
 * time : 10:45
 * date : 2017/2/9
 * mail to lulei4461@gmail.com
 */

public interface MainContract {
    interface View extends BaseView<Presenter>{
        void showloading();

        void showerror();

    }

    interface Presenter extends BasePresenter{
        void getListdata();
    }
}
