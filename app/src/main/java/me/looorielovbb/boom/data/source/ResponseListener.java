package me.looorielovbb.boom.data.source;

/**
 * Created by Lulei on 2017/2/24.
 * time : 16:52
 * date : 2017/2/24
 * mail to lulei4461@gmail.com
 */

public interface ResponseListener {
    void onSuccess(Object t);

    void onError(Throwable e);
}
