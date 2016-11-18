package com.just.han.imageload;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by HanFL on 2016/11/18.
 */

public class MemoryCache implements ImageCaches {
    private LruCache<String, Bitmap> mMemoryCache;

    public MemoryCache() {
    }

    @Override
    public Bitmap get(String url) {
        return mMemoryCache.get(url);
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mMemoryCache.put(url, bitmap);
    }
}
