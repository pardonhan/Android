package com.just.han.imageload;

import android.graphics.Bitmap;

/**
 * Created by HanFL on 2016/11/18.
 */

public class DoubleCache {
    ImageCache mMemoryCache = new ImageCache();
    DiskCache diskCache = new DiskCache();

    public Bitmap get(String url) {
        Bitmap bitmap = mMemoryCache.get(url);
        if (bitmap == null) {
            bitmap = diskCache.get(url);
        }
        return bitmap;
    }

    public void put(String url, Bitmap bitmap) {
        mMemoryCache.put(url, bitmap);
        diskCache.put(url, bitmap);
    }
}
