package me.looorielovbb.boom.data.bean.zhihu;

import java.util.List;


public class BeforeDailyBean {

    /**
     * date : 20170102
     * stories : [{"images":["http://pic4.zhimg.com/5615a788a32c2cc9469f825c4622879b.jpg"],"type":0,"id":9115087,"ga_prefix":"010222","title":"小事 · 悲喜无常"},"..."]
     */

    private String date;
    private List<StoriesBean> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

}
