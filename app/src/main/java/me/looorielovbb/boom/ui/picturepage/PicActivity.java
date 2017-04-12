package me.looorielovbb.boom.ui.picturepage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.looorielovbb.boom.R;
import me.looorielovbb.boom.utils.ToolbarUtils;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PicActivity extends AppCompatActivity {

    public static final String EXTRA_IMAGE_URL = "image_url";
    public static final String EXTRA_IMAGE_TITLE = "image_title";
    public static final String TRANSIT_PIC = "picture";
    protected boolean mIsHidden = false;
    String mImageUrl, mImageTitle;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.appbarlayout) AppBarLayout mAppBar;
    @BindView(R.id.picture) ImageView mImageView;
    @BindView(R.id.photo) PhotoView photoView;

    public static Intent newIntent(Context context, String url, String desc) {
        Intent intent = new Intent(context, PicActivity.class);
        intent.putExtra(PicActivity.EXTRA_IMAGE_URL, url);
        intent.putExtra(PicActivity.EXTRA_IMAGE_TITLE, desc);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);
        ButterKnife.bind(this);
        parseIntent();
        Glide
                .with(this)
                .load(mImageUrl)
                .into(photoView);
        setAppBarAlpha(0.8f);
        ToolbarUtils.initToolBar(this,toolbar,mImageTitle);
        photoView.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
            @Override
            public void onViewTap(View view, float x, float y) {
                hideOrShowToolbar();
            }
        });
    }

    private void setAppBarAlpha(float alpha) {
        mAppBar.setAlpha(alpha);
    }

    private void parseIntent() {
        mImageUrl = getIntent().getStringExtra(EXTRA_IMAGE_URL);
        mImageTitle = getIntent().getStringExtra(EXTRA_IMAGE_TITLE);
    }

    private void hideOrShowToolbar() {
        mAppBar
                .animate()
                .translationY(mIsHidden ? 0 : -mAppBar.getHeight())
                .setInterpolator(new DecelerateInterpolator(2))
                .start();
        mIsHidden = !mIsHidden;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
