package me.looorielovbb.boom.ui.home.zhihu;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.MultiTypeAdapter;
import me.looorielovbb.boom.R;
import me.looorielovbb.boom.data.bean.zhihu.StoriesBean;
import me.looorielovbb.boom.multitype.BannerViewBinder;
import me.looorielovbb.boom.multitype.DailyNewsViewBinder;
import me.looorielovbb.boom.multitype.SubTitleViewBinder;
import me.looorielovbb.boom.multitype.bean.Banner;
import me.looorielovbb.boom.multitype.bean.SubTitle;
import me.looorielovbb.boom.utils.ToastUtils;


public class ZhihuFragment extends Fragment implements  SwipeRefreshLayout.OnRefreshListener,
        ZhihuContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView rvMain;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    private MultiTypeAdapter adapter;
    ZhihuContract.Presenter mPresenter;


    public ZhihuFragment() {

    }

    public static ZhihuFragment newInstance() {
        return new ZhihuFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zhihu, container, false);
        ButterKnife.bind(this, view);
        setPresenter(new ZhihuPresenter(this));
        initView();
        return view;
    }

    private void initView() {
        refreshLayout.setOnRefreshListener(this);
        adapter = new MultiTypeAdapter();
        adapter.register(Banner.class, new BannerViewBinder());
        adapter.register(SubTitle.class,new SubTitleViewBinder());
        adapter.register(StoriesBean.class,new DailyNewsViewBinder(this));

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rvMain.setLayoutManager(manager);
        rvMain.setAdapter(adapter);
        rvMain.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                View lastchildView = recyclerView.getLayoutManager().getChildAt(recyclerView.getLayoutManager().getChildCount() - 1);
                int lastChildBottomY = lastchildView.getBottom();
                int recyclerBottomY = recyclerView.getBottom() - recyclerView.getPaddingBottom();
                int lastPosition = recyclerView.getLayoutManager().getPosition(lastchildView);

                if (lastChildBottomY == recyclerBottomY && newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastPosition == recyclerView.getLayoutManager().getItemCount() - 1) {
                    mPresenter.loadbeforeData();
                }
            }

        });

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void onRefresh() {
        mPresenter.clear();
        mPresenter.loadLatestData();
    }

    @Override
    public void showloading() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void dismissLoading() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void showerror(String error) {
        ToastUtils.show(error);
    }

    @Override
    public void showList(List<Object> listdata) {
        adapter.setItems(listdata);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(ZhihuContract.Presenter presenter) {
        if (presenter != null) {
            this.mPresenter = presenter;
        } else {
            throw new NullPointerException("presenter can not be null");
        }
    }
}
