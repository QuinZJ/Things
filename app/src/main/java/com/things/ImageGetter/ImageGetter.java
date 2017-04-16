package com.things.ImageGetter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.things.Net.NetRequest;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by asdf on 2017/4/16.
 */

public class ImageGetter extends NetRequest {
    private Bitmap bitmap;


    public ImageGetter(String tag, Object context) {
        super(tag, context);
    }

    @Override
    public void handleInputStream(InputStream inputStream) {
        this.bitmap = BitmapFactory.decodeStream(inputStream);
    }

    @Override
    public void handleOutputStream(OutputStream outputStream) {

    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
