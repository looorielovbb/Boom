package me.looorielovbb.boom.ui.home.movieandbooks.books;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.looorielovbb.boom.R;
import me.looorielovbb.boom.adapter.BookAdapter;
import me.looorielovbb.boom.base.LazyLoadFragment;
import me.looorielovbb.boom.config.Constants;
import me.looorielovbb.boom.data.bean.douban.book.BooksBean;
import me.looorielovbb.boom.ui.widgets.loadmore.OnVerticalScrollListener;
import me.looorielovbb.boom.ui.widgets.loadmore.SupportLoadMoreGridLayoutManager;
import me.looorielovbb.boom.utils.ToastUtils;
import me.looorielovbb.boom.widget.StatusViewLayout;

/**
 * Created by Lulei on 2017/7/10.
 * time : 14:32
 * date : 2017/7/10
 * mail to lulei4461@gmail.com
 */

public class BookFragment extends LazyLoadFragment implements BookContract.View, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.stateview)
    StatusViewLayout stateview;
    Unbinder unbinder;
    BookAdapter adapter;
    private BookContract.Presenter mPresenter;
    private int mCurrentPage;

    public static BookFragment newInstance() {
        Bundle args = new Bundle();
        BookFragment fragment = new BookFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void requestData() {
        mCurrentPage = 1;
        mPresenter.loaddata(Constants.DEFAULT_BOOKS, mCurrentPage);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        setPresenter(new BookPresenter(this));
        initView();
        return view;
    }

    private void initView() {
        refreshLayout.setOnRefreshListener(this);
        stateview.setOnRetryListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });
        adapter = new BookAdapter(getActivity());
        //设置图片
        SupportLoadMoreGridLayoutManager layout = new SupportLoadMoreGridLayoutManager(
                getContext(), 3);
        recyclerView.setLayoutManager(layout);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new OnVerticalScrollListener() {
            @Override
            public void onScrolledDownToLastItem() {
                super.onScrolledDownToLastItem();
                if (mCurrentPage == 1) {
                    mCurrentPage++;
                    adapter.updateLoadingStatus(false);
                    mPresenter.loaddata(Constants.DEFAULT_BOOKS, mCurrentPage);
                    //Count 为item数量加上Bottom Item 若为其他值 最后一页没有加载10条
                } else if (adapter.getItemCount() % Constants.PAGE_COUNT == 1) {
                    mCurrentPage++;
                    adapter.updateLoadingStatus(false);
                    mPresenter.loaddata(Constants.DEFAULT_BOOKS, mCurrentPage);
                } else {
                    adapter.updateLoadingStatus(true);
                }
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
    public void showList(List<BooksBean> list) {
        adapter.setList(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadComplete() {
        adapter.updateLoadingStatus(true);
    }

    @Override
    public void setPresenter(BookContract.Presenter presenter) {
        if (presenter != null) {
            this.mPresenter = presenter;
        } else {
            throw new NullPointerException("presenter can not be null");
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.clear();
        mCurrentPage = 1;
        mPresenter.loaddata(Constants.DEFAULT_BOOKS, mCurrentPage);
    }
}
