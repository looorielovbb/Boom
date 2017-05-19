package me.looorielovbb.boom.ui.zhihudetail;

import me.looorielovbb.boom.base.BasePresenter;
import me.looorielovbb.boom.base.BaseView;
import me.looorielovbb.boom.data.bean.zhihu.DetailExtraBean;
import me.looorielovbb.boom.data.bean.zhihu.ZhihuDetailBean;

/**
 * Created by Lulei on 2017/5/19.
 */

public interface ZdetailContract {
    interface View extends BaseView<Presenter> {
        void showContent(ZhihuDetailBean zhihuDetailBean);

        void showStoryExtras(DetailExtraBean detailExtraBean);

        void showError(Throwable e);
    }

    interface Presenter extends BasePresenter {
        void getStoryContent(int id);

        void getStoryExtras(int id);
    }
}
