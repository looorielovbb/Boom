package me.looorielovbb.boom.utils;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

/**
 * Created by Lulei on 2017/6/29.
 */
@GlideModule
public final class BoomGlideModule extends AppGlideModule {

    public BoomGlideModule() {
        super();
    }

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        super.applyOptions(context, builder);
    }

    @Override
    public boolean isManifestParsingEnabled() {
        return super.isManifestParsingEnabled();
    }

    @Override
    public void registerComponents(Context context, Registry registry) {
        super.registerComponents(context, registry);
    }
}
