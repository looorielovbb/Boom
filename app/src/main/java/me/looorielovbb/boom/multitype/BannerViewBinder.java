package me.looorielovbb.boom.multitype;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;

import me.drakeet.multitype.ItemViewBinder;
import me.looorielovbb.boom.R;
import me.looorielovbb.boom.data.bean.zhihu.TopStoriesBean;
import me.looorielovbb.boom.multitype.bean.Banner;

/**
 * Created by Lulei on 2017/4/17.
 * time : 16:56
 * date : 2017/4/17
 * mail to lulei4461@gmail.com
 */
public class BannerViewBinder extends ItemViewBinder<Banner, BannerViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater,
                                            @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_banner, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder,
                                    @NonNull Banner banner) {
        holder.setImages(banner);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @NonNull
        final ConvenientBanner<TopStoriesBean> convenientBanner;

        ViewHolder(View itemView) {
            super(itemView);
            convenientBanner = itemView.findViewById(R.id.convenientBanner);
        }

        void setImages(final Banner bean) {
            convenientBanner
                    .setPages(new CBViewHolderCreator<TopStoriesBean>() {
                        @Override
                        public Holder<TopStoriesBean> createHolder(View itemView) {
                            return new NetworkImageHolder(itemView);
                        }

                        @Override
                        public int getLayoutId() {
                            return R.layout.view_banner;
                        }
                    }, bean.storiesBeen)
                    .setPageIndicator(new int[]{R.drawable.indicator_unselected,
                            R.drawable.indicator_selected})
                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
            if (!convenientBanner.isTurning()) {
                convenientBanner.startTurning(5 * 1000);
            }
        }

    }
}
