package me.looorielovbb.boom.adapter.decoration;

import android.graphics.Rect;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

/**
 * Created by Long
 * on 2016/9/9.
 */
public class GridMarginDecoration extends RecyclerView.ItemDecoration {

    private final int space;

    public GridMarginDecoration(int space) {
        this.space = space;
    }


    @Override
    public void getItemOffsets(Rect outRect, @NonNull View view,@NonNull RecyclerView parent,
                               @NonNull RecyclerView.State state) {
        outRect.left = space;
        outRect.top = space;
        outRect.bottom = space;
        outRect.right = space;
    }
}
