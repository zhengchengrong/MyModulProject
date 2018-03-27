package com.luojilab.component.basiclib.utils;

import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import static com.luojilab.component.basiclib.utils.RxTool.getContext;

/**
 * Created by Hao on 16/6/24.
 */
public class RxGlideTools {

    public static void GlideGif(String imgUrl, ImageView view, int resourceId) {
        if (imgUrl.contains(".gif"))
            Glide.with(getContext()).load(imgUrl).asGif()
                    .diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(resourceId).fitCenter()
                    .into(view);
        else
            Glide(imgUrl, view, resourceId);

    }

    public static void GlideNofit(String imgUrl, ImageView view, int resourceId) {
        if (imgUrl.contains(".gif"))
            Glide.with(getContext()).load(imgUrl).asGif()
                    .diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(resourceId)
                    .into(view);
        else
            Glide.with(getContext()).load(imgUrl).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(resourceId).into(view);

    }

    public static void GlideNoId(String imgUrl, ImageView view) {
        if (imgUrl.contains(".gif"))
            Glide.with(getContext()).load(imgUrl).asGif()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(view);
        else
            Glide.with(getContext()).load(imgUrl).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(view);

    }

    public static void Glide(String imgUrl, ImageView view, int resourceId) {
        Glide.with(getContext()).load(imgUrl).diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(resourceId).fitCenter().into(view);
    }

    /**
     * Glide 加载圆行图片
     *
     * @param imgUrl
     * @param view
     * @param resourceId
     */
    public static void GlideRound(String imgUrl, ImageView view, int resourceId) {
        Glide.with(getContext()).load(imgUrl).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(resourceId).centerCrop()
                .into(new BitmapImageViewTarget(view) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(view.getContext().getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        view.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }

    /**
     * Glide 加载圆角图片
     *
     * @param imgUrl
     * @param view
     * @param resourceId
     */
    public static void GlideRounded(String imgUrl, ImageView view, int resourceId, final int px) {
        Glide.with(getContext()).load(imgUrl).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(resourceId).centerCrop()
                .into(new BitmapImageViewTarget(view) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable roundedBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(view.getContext().getResources(), resource);
                        roundedBitmapDrawable.setCornerRadius(px);//设置圆角半径（根据实际需求）
                        roundedBitmapDrawable.setAntiAlias(true);  //设置反走样
                        view.setImageDrawable(roundedBitmapDrawable);
                    }
                });
    }


}
