package me.looorielovbb.boom.ui.zhihucomments.conmentlist;

import java.util.List;

import me.looorielovbb.boom.base.BasePresenter;
import me.looorielovbb.boom.base.BaseView;
import me.looorielovbb.boom.data.bean.zhihu.Comment;

/**
 * Created by Lulei on 2017/5/22.
 * time : 16:33
 * date : 2017/5/22
 * mail to lulei4461@gmail.com
 */

public interface CommentConstract {

    interface View extends BaseView<CommentConstract.Presenter> {
        void showList(List<Comment> comments);
    }

    interface Presenter extends BasePresenter {
        void fetchShortCommentInfo(int id);

        void fetchLongCommentInfo(int id);
    }
}
