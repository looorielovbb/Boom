package me.looorielovbb.boom.multitype;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

import java.util.ArrayList;
import java.util.List;

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
public class BannerViewBinder
        extends ItemViewBinder<Banner, BannerViewBinder.ViewHolder> {



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
        final ConvenientBanner convenientBanner;

        ViewHolder(View itemView) {
            super(itemView);
            convenientBanner = (ConvenientBanner) itemView.findViewById(R.id.convenientBanner);
        }

        @SuppressWarnings("unchecked")
        void setImages(final Banner bean) {

            List<String> imgurls = new ArrayList<>();
            for (TopStoriesBean img : bean.storiesBeen) {
                imgurls.add(img.getImage());
            }
            convenientBanner
                    .setPages(new CBViewHolderCreator<NetworkImageHolder>() {
                        @Override
                        public NetworkImageHolder createHolder() {
                            return new NetworkImageHolder(bean.storiesBeen);
                        }
                    }, imgurls)
                    .setPageIndicator(new int[]{R.drawable.indicator_unselected,
                            R.drawable.indicator_selected})
                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
            if (!convenientBanner.isTurning()) {
                convenientBanner.startTurning(10 * 1000);
            }
        }

    }
}
