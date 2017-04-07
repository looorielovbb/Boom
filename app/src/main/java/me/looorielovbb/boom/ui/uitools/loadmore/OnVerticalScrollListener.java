package me.looorielovbb.boom.ui.uitools.loadmore;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Lulei on 2017/4/6.
 * time : 14:47
 * date : 2017/4/6
 * mail to lulei4461@gmail.com
 */

public abstract class OnVerticalScrollListener extends RecyclerView.OnScrollListener {

    private boolean lastItemVisible;

    @Override
    public final void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (!recyclerView.canScrollVertically(-1)) {
            onScrolledToTop();
        } else if (!recyclerView.canScrollVertically(1)) {
            onScrolledToBottom();
        } else if (dy < 0) {
            onScrolledUp();
            lastItemVisible = isLastItemVisible(recyclerView);
        } else if (dy > 0) {
            onScrolledDown();
            boolean isLastItemVisible = isLastItemVisible(recyclerView);
            if (lastItemVisible != isLastItemVisible) {
                lastItemVisible = isLastItemVisible;
                if (lastItemVisible) {
                    onScrolledDownToLastItem();
                }
            }
        }
    }

    private boolean isLastItemVisible(RecyclerView recyclerView) {
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            final int lastVisibleItemPosition
                    = ((LinearLayoutManager) recyclerView.getLayoutManager())
                    .findLastVisibleItemPosition();
            //这里需要加一让加载触发的更早一点，因为快速滑到最后一个时，
            // 最后一个item已经可见但是Position 却没有显示正确
            return lastVisibleItemPosition +1 == recyclerView
                                                      .getAdapter()
                                                      .getItemCount() - 1;
        }
        return false;
    }

    public void onScrolledDownToLastItem() {
    }

    public void onScrolledUp() {

    }

    public void onScrolledDown() {

    }

    public void onScrolledToTop() {

    }

    public void onScrolledToBottom() {

    }
}
