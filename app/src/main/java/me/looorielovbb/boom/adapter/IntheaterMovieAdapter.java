package me.looorielovbb.boom.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.looorielovbb.boom.R;
import me.looorielovbb.boom.data.bean.douban.MovieInfo;
import me.looorielovbb.boom.utils.ImgUtils;

/**
 * Created by Lulei on 2017/6/30.
 * time : 17:06
 * date : 2017/6/30
 * mail to lulei4461@gmail.com
 */

public class IntheaterMovieAdapter extends RecyclerView.Adapter<IntheaterMovieAdapter.ViewHolder> {

    private List<MovieInfo> movieInfoList = new ArrayList<>();
    private Context context;

    public void setMovieInfoList(List<MovieInfo> movieInfoList) {
        this.movieInfoList = movieInfoList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (movieInfoList == null || movieInfoList.isEmpty())
            return;

        final MovieInfo movieInfo = movieInfoList.get(i);
        ImgUtils.LoadNetImg(context, movieInfo.getImages().getLarge(), viewHolder.movieItemPhoto);
        viewHolder.movieItemName.setText(movieInfo.getTitle());
        viewHolder.topMovieItemScore.setText("" + movieInfo.getRating().getAverage());
        viewHolder.ratingBarHots.setRating((float) movieInfo.getRating().getAverage());

        final int[] x = new int[1];
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, MovieDetailActivity.class);
//                int[] location = new int[2];
//                view.getLocationInWindow(location);
//                final int cy = location[1] + view.getHeight() / 2;
//
//                intent.putExtra("X", x[0]);
//                intent.putExtra("Y", cy);
//                intent.putExtra("ID", movieInfo.getId());
//                context.startActivity(intent);
            }
        });

        viewHolder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                x[0] = (int) motionEvent.getX();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieInfoList == null ? 0 : movieInfoList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.movie_item_photo)
        ImageView movieItemPhoto;
        @BindView(R.id.movie_item_name)
        TextView movieItemName;
        @BindView(R.id.ratingBar_hots)
        AppCompatRatingBar ratingBarHots;
        @BindView(R.id.top_movie_item_score)
        TextView topMovieItemScore;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
