package me.looorielovbb.boom.multitype;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;
import me.looorielovbb.boom.R;
import me.looorielovbb.boom.multitype.bean.SubTitle;

/**
 * Created by Lulei on 2017/4/18.
 * time : 14:54
 * date : 2017/4/18
 * mail to lulei4461@gmail.com
 */
public class SubTitleViewBinder extends ItemViewBinder<SubTitle, SubTitleViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater,
                                            @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_sub_title, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull SubTitle title) {
        holder.tvSubtitle.setText(title.subtitle);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.subtitle)
        TextView tvSubtitle;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}
