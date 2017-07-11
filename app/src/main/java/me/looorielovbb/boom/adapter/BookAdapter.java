package me.looorielovbb.boom.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.looorielovbb.boom.R;
import me.looorielovbb.boom.data.bean.douban.book.BooksBean;
import me.looorielovbb.boom.ui.widgets.loadmore.LoadMoreAdapter;
import me.looorielovbb.boom.utils.ImgUtils;

/**
 * Created by Lulei on 2017/7/11.
 * time : 17:32
 * date : 2017/7/11
 * mail to lulei4461@gmail.com
 */

public class BookAdapter extends LoadMoreAdapter<BooksBean> {
    private Activity activity;

    public BookAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            ImgUtils.LoadNetImg(activity, items.get(position).getImages().getLarge(), viewHolder.ivTopPhoto);
            viewHolder.tvName.setText(items.get(position).getTitle());
            viewHolder.tvRate.setText(String.valueOf(items.get(position).getRating().getAverage()));
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new ViewHolder(v);
    }


    public void updateLoadingStatus(boolean isLoadcomplete) {
        this.isLoadcomplete = isLoadcomplete;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_top_photo)
        ImageView ivTopPhoto;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_rate)
        TextView tvRate;
        @BindView(R.id.ll_item_top)
        LinearLayout llItemTop;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
