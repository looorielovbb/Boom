package me.looorielovbb.boom.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.looorielovbb.boom.R;
import me.looorielovbb.boom.data.bean.douban.MovieInfo;
import me.looorielovbb.boom.ui.widgets.loadmore.LoadMoreAdapter;
import me.looorielovbb.boom.utils.ImgUtils;

/**
 * Created by Lulei on 2017/7/3.
 */

public class MovieTop250Adapter extends LoadMoreAdapter<MovieInfo> {
    private Activity context;

    public MovieTop250Adapter(Activity activity) {
        this.context = activity;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindItemViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MovieTop250Adapter.ViewHolder) {
            final MovieTop250Adapter.ViewHolder itemholder = (MovieTop250Adapter.ViewHolder) holder;
            final MovieInfo movieInfo = items.get(position);
            ImgUtils.LoadNetImg(context, movieInfo.getImages().getLarge(), itemholder.topMovieItemIcon);
            itemholder.topMovieItemName.setText(movieInfo.getTitle());
            itemholder.topMovieItemOriginalName.setText(movieInfo.getOriginal_title());
            itemholder.topMovieItemDate.setText(context.getResources().getString(R.string.start_data) + movieInfo.getYear());
            itemholder.topMovieItemScore.setText(context.getResources().getString(R.string.score) + movieInfo.getRating().getAverage());
            if (position == 0) {
                itemholder.ranking.setTextColor(ContextCompat.getColor(context, R.color.md_orange_A700));
            } else if (position == 1) {
                itemholder.ranking.setTextColor(ContextCompat.getColor(context, R.color.md_orange_A200));

            } else if (position == 2) {
                itemholder.ranking.setTextColor(ContextCompat.getColor(context, R.color.md_orange_A100));

            } else {
                itemholder.ranking.setTextColor(ContextCompat.getColor(context, android.R.color.darker_gray));

            }

            itemholder.ranking.setText(position + 1 + "");
            StringBuffer sb = new StringBuffer();
            sb.append("主演：");
            for (int i = 0; i < movieInfo.getCasts().size(); i++) {
                if (i != movieInfo.getCasts().size() - 1) {
                    sb.append(movieInfo.getCasts().get(i).getName() + "/");
                } else {
                    sb.append(movieInfo.getCasts().get(i).getName());
                }
            }


            itemholder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = PicActivity.newIntent(context, movieInfo.getUrl(),
//                            DateUtils.getDateS(movieInfo.getDate()));
//                    ActivityOptionsCompat options = ActivityOptionsCompat.makeScaleUpAnimation(
//                            v,
//                            v.getWidth(),
//                            v.getHeight(),
//                            v.getWidth(),
//                            v.getHeight());
//                    context.startActivity(intent, options.toBundle());

                }
            });
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_top250_movie, parent, false);
        return new MovieTop250Adapter.ViewHolder(view);
    }


    public void updateLoadingStatus(boolean isLoadcomplete) {
        this.isLoadcomplete = isLoadcomplete;
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.ranking)
        TextView ranking;
        @BindView(R.id.top_movie_item_icon)
        ImageView topMovieItemIcon;
        @BindView(R.id.top_movie_item_name)
        TextView topMovieItemName;
        @BindView(R.id.top_movie_item_original_name)
        TextView topMovieItemOriginalName;
        @BindView(R.id.top_movie_item_date)
        TextView topMovieItemDate;
        @BindView(R.id.top_movie_item_score)
        TextView topMovieItemScore;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
