package com.gm.gmannotation.app;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import com.gm.gmannotation.util.ImagePicker;

import java.io.File;

/**
 * Created by Gowtham on 4/18/2016.
 */
public class GmAnnotationApp extends Application {

    public static GmAnnotationApp gmAnnotationApp = null;

    @Override
    public void onCreate() {
        super.onCreate();

        gmAnnotationApp = this;
        initImgpic(getApplicationContext());

    }


    public static GmAnnotationApp getInstance() {
        return gmAnnotationApp;
    }

    public static File getCacheDirectory() {
        return gmAnnotationApp.getCacheDir();
    }

    public static void initImgpic(Context context) {

        String path = "/Android/data/"
                + context.getApplicationContext().getPackageName()
                + "/ProfilePic";

        ImagePicker.getInstance(context).initTempFilePath(path);

        ImagePicker.getInstance(context).initBitmapConfig(Bitmap.CompressFormat.PNG, 60);

    }

}
