package me.looorielovbb.boom.base;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

/**
 * Created by Lulei on 2017/6/30.
 * time : 15:55
 * date : 2017/6/30
 * mail to lulei4461@gmail.com
 */
//实现Fragment的懒加载
public abstract class LazyLoadFragment extends Fragment {
    protected boolean isViewInitiated;
    protected boolean isDataLoaded;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        prepareRequestData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        prepareRequestData();
    }

    public abstract void requestData();

    public boolean prepareRequestData() {
        return prepareRequestData(false);
    }

    public boolean prepareRequestData(boolean forceUpdate) {
        if (getUserVisibleHint() && isViewInitiated && (!isDataLoaded || forceUpdate)) {
            requestData();
            isDataLoaded = true;
            return true;
        }
        return false;
    }
}
