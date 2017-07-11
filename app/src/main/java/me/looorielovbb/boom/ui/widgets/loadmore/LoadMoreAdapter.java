package me.looorielovbb.boom.ui.widgets.loadmore;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.looorielovbb.boom.R;

/**
 * Created by Lulei on 2017/4/6.
 * time : 14:41
 * date : 2017/4/6
 * mail to lulei4461@gmail.com
 */

public abstract class LoadMoreAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_LOAD_MORE = 1;

    protected List<T> items = new ArrayList<>();
    protected boolean isLoadcomplete = false;
    private boolean enableLoadMore;

    public void setList(List<T> items) {
        this.items.clear();
        addItems(items);
    }

    public void addItems(List<T> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public T getItem(int position) {
        if (position < 0 || position > items.size() - 1) {
            return null;
        }
        return items.get(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_LOAD_MORE) {
            View view = View.inflate(parent.getContext(), R.layout.view_footer, null);
            return new LoadMoreViewHolder(view);
        }
        return onCreateItemViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (!enableLoadMore || position != getItemCount() - 1) {
            onBindItemViewHolder(holder, position);
        } else if (holder instanceof LoadMoreViewHolder){
            LoadMoreViewHolder moreViewHolder = (LoadMoreViewHolder) holder;
            onBindLoadMoreHolder(moreViewHolder,position);
        }
    }

    private void onBindLoadMoreHolder(LoadMoreViewHolder moreViewHolder, int position) {
        if (isLoadcomplete) {
            moreViewHolder.tv_load_prompt.setText("已经到底了！ 喵~");
            moreViewHolder.progress.setVisibility(View.GONE);
        } else {
            moreViewHolder.tv_load_prompt.setText("加载中...");
            moreViewHolder.progress.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (enableLoadMore && position == getItemCount() - 1) {
            return VIEW_TYPE_LOAD_MORE;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size() + (enableLoadMore ? 1 : 0);
    }

    public abstract void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position);

    public abstract RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType);

    public abstract void updateLoadingStatus(boolean isLoadcomplete);

    public boolean isEnableLoadMore() {
        return enableLoadMore;
    }

    public void setEnableLoadMore(boolean enableLoadMore) {
        if (this.enableLoadMore == enableLoadMore) {
            return;
        }
        this.enableLoadMore = enableLoadMore;
        notifyDataSetChanged();
    }

}