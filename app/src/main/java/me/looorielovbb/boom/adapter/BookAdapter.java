package me.looorielovbb.boom.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;
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
import me.looorielovbb.boom.ui.home.movieandbooks.bookdetail.BDetailActivity;
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            ImgUtils.LoadNetImg(activity, items.get(position).getImages().getLarge(), viewHolder.ivTopPhoto);
            viewHolder.tvName.setText(items.get(position).getTitle());
            viewHolder.tvRate.setText(activity.getResources().getString(R.string.score)
                    + items.get(position).getRating().getAverage());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    activity.startActivity(new Intent(activity, BDetailActivity.class));
                    Intent intent = new Intent();
                    intent.setClass(activity, BDetailActivity.class);
                    intent.putExtra("book", items.get(position));
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeScaleUpAnimation(
                            v,
                            v.getWidth(),
                            v.getHeight(),
                            0,
                            0);
                    activity.startActivity(intent, options.toBundle());
                }
            });
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
