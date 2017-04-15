package me.looorielovbb.boom.utils;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import me.looorielovbb.boom.R;


/**
 * Created by Lulei on 2016/12/8.
 * time : 20:06
 * date : 2016/12/8
 * mail to lulei4461@gmail.com
 */

public class ImgUtils {
    public static void LoadLocalImg(Context context,
                                    @DrawableRes Integer url,
                                    ImageView imageView) {
        Glide.with(context).load(url)//图片地址
             .placeholder(R.drawable.loading)//占位图
             .error(R.drawable.error)//加载出错显示的图片
             .crossFade()//淡入效果
             .into(imageView);
    }

    public static void LoadNetImg(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url)//图片地址
             .placeholder(R.drawable.loading)//占位图
             .error(R.drawable.error)//加载出错显示的图片
             .crossFade()//淡入效果
             .diskCacheStrategy(DiskCacheStrategy.NONE)
             .centerCrop()
             .into(imageView);
    }



    public static void LoadNetImgForAvatar(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url)//图片地址
             .placeholder(R.drawable.avatar)//占位图
             .error(R.drawable.avatar)//加载出错显示的图片
             .crossFade()//淡入效果
             .into(imageView);
    }

    public static void LoadNetGif(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url)//图片地址
             .asGif()//asGif加载Gif动态图，asBitmap可以将Gif或者视频（没试过）解码成bitmap
             .placeholder(R.drawable.loading)//占位图
             .error(R.drawable.error)//加载出错显示的图片
             .crossFade()//淡入效果
             .into(imageView);
    }



}
