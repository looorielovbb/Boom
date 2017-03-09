package me.looorielovbb.boom.data.bean;

import java.util.List;

/**
 * Created by Lulei on 2017/2/21.
 * time : 14:56
 * date : 2017/2/21
 * mail to lulei4461@gmail.com
 */

public class Base<T> {
    private boolean error;
    private List<T> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<T> getList() {
        return results;
    }

    public void setList(List<T> list) {
        this.results = list;
    }
}
