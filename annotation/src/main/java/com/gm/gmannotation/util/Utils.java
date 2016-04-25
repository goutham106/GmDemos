package com.gm.gmannotation.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodManager;

import com.gm.gmannotation.R;


/**
 * Created by gowtham on 18/04/16.
 */
public class Utils {

    public static void hideKeyBoard(Activity activity) {
        try {
            if (activity != null) {
                InputMethodManager input = (InputMethodManager) activity
                        .getSystemService(Activity.INPUT_METHOD_SERVICE);
                input.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
            }
        } catch (Exception ignored) {
        }
    }


    public static int getScreenWidth(Activity activity, String params) {

        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int screenHeight = displaymetrics.heightPixels;
        int screenWidth = displaymetrics.widthPixels;

        if (params.equals("width"))
            return screenWidth;
        else
            return screenHeight;
    }


    public static boolean isNetworkAvailable(Context context) {

        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            if (connectivity == null) {
                return false;
            } else {
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null) {
                    for (NetworkInfo anInfo : info) {
                        if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }
                    }
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;

        }

        return false;
    }

    public static Boolean isMarshmallow() {
        if (Build.VERSION.SDK_INT >= 23)
            return true;
        else
            return false;
    }

    public static void gotoAppSetting(Context context) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        intent.setData(uri);
        context.startActivity(intent);

    }

    public static void showMessageOKCancel(Context context, String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton(context.getString(R.string.ok), okListener)
                .setNegativeButton(context.getString(R.string.cancel), null)
                .create()
                .show();
    }

    public static void showMessageOKCancel(Context context,String titleText, String okText,String cancelText,String message, DialogInterface.OnClickListener okListener,DialogInterface.OnClickListener cancelListener) {
        new AlertDialog.Builder(context)
                .setTitle(titleText)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", cancelListener)
                .create()
                .show();
    }

    /**
     * @param activity
     * @return Boolean values true/false
     * @Description Method to get whether the device is tablet or mobile
     */
    public static boolean isTablet(Activity activity) {

        if (activity.getResources().getString(R.string.device).equals("Tablet")) {
            return true;
        } else {
            return false;
        }
    }


    public static String getCapsWordFromString(String title) {

        return title.substring(0, 1).toUpperCase() + title.substring(1);
    }


}
