package com.things.Widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.things.ImageGetter.ImageGetter;
import com.things.Net.OnResponse;
import com.things.R;

/**
 * Created by asdf on 2017/4/16.
 */

public class NetImageView extends ImageView {

    private static final int TAG_GET_IMAGE = 0;

    private TypedArray typedArray;

    public NetImageView(Context context) {
        super(context);
    }

    public NetImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.typedArray = context.obtainStyledAttributes(attrs, R.styleable.NetImageView);
    }

    public NetImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.typedArray = context.obtainStyledAttributes(attrs, R.styleable.NetImageView);
    }

    public NetImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.typedArray = context.obtainStyledAttributes(attrs, R.styleable.NetImageView);
    }

    public void load() {
        if (this.typedArray == null) {

        } else {
            String url = this.typedArray.getString(R.styleable.NetImageView_url);
            new ImageGetter(TAG_GET_IMAGE, this)
                        .open(url)
                        .send();
        }
    }

    @OnResponse(TAG_GET_IMAGE)
    private void func(Bitmap bitmap) {
        this.setImageBitmap(bitmap);
    }
}
