package br.com.doggerz.firebaseauthteste.Util;

import android.graphics.Bitmap;

import com.squareup.picasso.Transformation;

public class CircleTransform implements Transformation {

    @Override
    public Bitmap transform(Bitmap source) {
        return ImageUtil.getCircularBitmapImage(source);
    }

    @Override
    public String key() {
        return "circle-image";
    }
}