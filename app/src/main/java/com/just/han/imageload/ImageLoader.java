package com.just.han.imageload;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by HanFL on 2016/11/18.
 */

public class ImageLoader {
    ImageCache mImageCache = new ImageCache();
    DiskCache mDiskCache = new DiskCache();
    DoubleCache doubleCache = new DoubleCache();

    boolean isUseDiskCache = false;
    boolean isUseDoubleCache = false;

    ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());


    public void displayImage(final String imageUrl, final ImageView imageView) {
        Bitmap bitmap = null;
        if (isUseDoubleCache) {
            bitmap = doubleCache.get(imageUrl);
        } else if (isUseDiskCache) {
            bitmap = mDiskCache.get(imageUrl);
        } else {
            bitmap = mImageCache.get(imageUrl);
        }
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            return;
        }
        imageView.setTag(imageUrl);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadImage(imageUrl);
                if (bitmap == null) {
                    return;
                }
                if (imageView.getTag().equals(imageUrl)) {
                    imageView.setImageBitmap(bitmap);
                }
                mImageCache.put(imageUrl, bitmap);
            }
        });

    }

    private Bitmap downloadImage(String imageUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(httpURLConnection.getInputStream());
            httpURLConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public void useDiskCache(boolean useDiskCache) {
        this.isUseDiskCache = useDiskCache;
    }
}
