package com.things.Picasso;

import android.content.Context;
import android.widget.ImageView;

import com.things.ImageGetter.ImageGetter;
import com.things.Net.OnResponse;

/**
 * Created by asdf on 2017/4/16.
 */

public class Picasso {

    private Context context;

    public static Picasso with(Context context) {
        return new Picasso(context);
    }

    private Picasso(Context context) {
        this.context = context;
    }

    public ImageLoader load(String url) {
        return new ImageLoader(url);
    }

    public static class ImageLoader {
        private ImageView imageView;

        public ImageLoader(String url) {
            new ImageGetter("into", this)
                    .open(url)
                    .send();
        }

        @OnResponse("into")
        private void func(ImageGetter imageGetter) {
            if (this.imageView == null) return;
            this.imageView.setImageBitmap(imageGetter.getBitmap());
        }

        public void into(ImageView view) {
            this.imageView = view;
        }
    }
}
