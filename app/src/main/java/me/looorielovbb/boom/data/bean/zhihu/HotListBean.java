package me.looorielovbb.boom.data.bean.zhihu;

import java.util.List;

/**
 * Created by quantan.liu on 2017/3/21.
 */

public class HotListBean {

    /**
     * news_id : 8701066
     * url : http://news-at.zhihu.com/api/2/news/8701066
     * thumbnail : http://pic1.zhimg.com/f5169eb70e3a6823737dc55fbab051c0.jpg
     * title : 瞎扯 · 如何正确地吐槽
     */

    private List<RecentBean> recent;

    public List<RecentBean> getRecent() {
        return recent;
    }

    public void setRecent(List<RecentBean> recent) {
        this.recent = recent;
    }

    @Override
    public String toString() {
        return "HotListBean{" +
                "recent=" + recent +
                '}';
    }
}
