package me.looorielovbb.boom.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v7.widget.RecyclerView;
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

public class MovieComingAdapter extends LoadMoreAdapter<MovieInfo> {
    private Activity context;

    public MovieComingAdapter(Activity activity) {
        this.context = activity;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindItemViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MovieComingAdapter.ViewHolder) {
            final MovieComingAdapter.ViewHolder itemholder = (MovieComingAdapter.ViewHolder) holder;
            final MovieInfo movieInfo = items.get(position);
            ImgUtils.LoadNetImg(context, movieInfo.getImages().getLarge(), itemholder.comingSoonMoviePic);
            itemholder.comingSoonMovieName.setText(movieInfo.getTitle());
            itemholder.comingSoonMovieType.setText("类别：" + movieInfo.getGenres().toString());
            StringBuffer sb = new StringBuffer();
            sb.append("主演：");
            for (int i = 0; i < movieInfo.getCasts().size(); i++) {
                if (i != movieInfo.getCasts().size() - 1) {
                    sb.append(movieInfo.getCasts().get(i).getName() + "/");
                } else {
                    sb.append(movieInfo.getCasts().get(i).getName());
                }
            }
            if (movieInfo.getCasts() != null && movieInfo.getCasts().size() > 0) {
                itemholder.comingSoonMoviePerformer.setText(sb.toString());
            }

            if (movieInfo.getDirectors() != null && movieInfo.getDirectors().size() > 0) {
                itemholder.comingSoonMovieDirectors.setText("导演：" + movieInfo.getDirectors().get(0).getName());
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_coming_movie, parent, false);
        return new MovieComingAdapter.ViewHolder(view);
    }


    public void updateLoadingStatus(boolean isLoadcomplete) {
        this.isLoadcomplete = isLoadcomplete;
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.coming_soon_movie_pic)
        ImageView comingSoonMoviePic;
        @BindView(R.id.coming_soon_movie_name)
        TextView comingSoonMovieName;
        @BindView(R.id.coming_soon_movie_type)
        TextView comingSoonMovieType;
        @BindView(R.id.coming_soon_movie_directors)
        TextView comingSoonMovieDirectors;
        @BindView(R.id.coming_soon_movie_performer)
        TextView comingSoonMoviePerformer;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
