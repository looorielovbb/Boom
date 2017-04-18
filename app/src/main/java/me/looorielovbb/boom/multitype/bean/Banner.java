package me.looorielovbb.boom.multitype.bean;

import java.util.List;

import me.looorielovbb.boom.data.bean.zhihu.TopStoriesBean;

/**
 * Created by Lulei on 2017/4/18.
 * time : 14:49
 * date : 2017/4/18
 * mail to lulei4461@gmail.com
 */

public class Banner {
    public List<TopStoriesBean> storiesBeen;

    public Banner(List<TopStoriesBean> storiesBeen) {
        this.storiesBeen = storiesBeen;
    }
}
