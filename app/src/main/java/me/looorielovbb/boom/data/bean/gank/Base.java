package me.looorielovbb.boom.data.bean.gank;

import java.util.List;

/**
 * Created by Lulei on 2017/2/21.
 * time : 14:56
 * date : 2017/2/21
 * mail to lulei4461@gmail.com
 */

public class Base<T> {
    /**
     * "page": 1,
     * "page_count": 5,
     * "status": 100,
     * "total_counts": 81
     */

    private int page;
    private int pageCount;
    private int status;
    private int totalCounts;
    private List<T> data;

    public List<T> getList() {
        return data;
    }

    public void setList(List<T> list) {
        this.data = list;
    }
}
