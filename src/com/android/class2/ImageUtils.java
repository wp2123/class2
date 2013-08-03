package com.android.class2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageUtils {
    public static Bitmap decodeBitmapFromFile(String path, int reqWidth,
                                              int reqHeight) {

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        final int height = options.outHeight;
        final int width = options.outWidth;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        int scale = 1;

        if (height > reqHeight) {
            scale = Math.round((float) height / (float) reqHeight);
        }
        int expectedWidth = width / scale;
        if (expectedWidth > reqWidth) {
            scale = Math.round((float) width / (float) reqWidth);
        }
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }
}
