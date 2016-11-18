package com.just.han.imageload;

import android.graphics.Bitmap;

/**
 * Created by HanFL on 2016/11/18.
 */

public interface ImageCaches {
    Bitmap get(String url);

    void put(String url, Bitmap bitmap);
}
