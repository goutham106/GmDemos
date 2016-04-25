package com.gm.retrofit.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.text.Html;
import android.view.Gravity;
import android.widget.TextView;

import com.gm.retrofit.webservice.RetrofitCallbackPOJO;
import com.gm.retrofit.webservice.interfaces.DataSelectionCallback;

import java.util.List;


@SuppressWarnings("ALL")
public class DataListDialogView {

    private DataSelectionCallback dataSelectionCallback;
    private int selectedView;
    private AlertDialog.Builder alertDialogBuilder = null;
    CharSequence[] charSequenceItems;

    public static AlertDialog myAlertDialog;

    public DataListDialogView(Activity activity) {
        if (activity.isDestroyed() || activity.isFinishing()) { // or call isFinishing() if min sdk version < 17
            return;
        }
        else if (myAlertDialog != null) {
            if (myAlertDialog.isShowing()) {
                myAlertDialog.dismiss();
                myAlertDialog = null;
            }
        }

    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public AlertDialog.Builder setValues(String titleName, Activity act, DataSelectionCallback callback, List<String> stringList, int whichView, int defaultSelectedPos) {


        this.dataSelectionCallback = callback;
        selectedView = whichView;

        final CharSequence[] charSequenceItems = stringList.toArray(new CharSequence[stringList.size()]);


        alertDialogBuilder = new AlertDialog.Builder(act, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
        alertDialogBuilder.setTitle(titleName);
        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder
                .setSingleChoiceItems(charSequenceItems, defaultSelectedPos, null)
                .setPositiveButton("DONE", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                        int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                        alertDialogBuilder.setSingleChoiceItems(charSequenceItems, selectedPosition, null);
                        dataSelectionCallback.dataSelection(selectedView, selectedPosition);
                    }
                });


        return alertDialogBuilder;
    }


    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void setDoubleBtnAlert(int whichView, Activity activity, DataSelectionCallback callback, String msg, String buttonNameOne, String buttonNameTwo) {

        if (myAlertDialog != null && myAlertDialog.isShowing()) {
            myAlertDialog.dismiss();
            myAlertDialog = null;
        }

        selectedView = whichView;
        alertDialogBuilder = new AlertDialog.Builder(activity, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
        alertDialogBuilder.setCancelable(false);
        this.dataSelectionCallback = callback;

        alertDialogBuilder

                .setMessage(Html.fromHtml(msg))
                .setPositiveButton(buttonNameOne, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        dataSelectionCallback.alertPositiveBtn(selectedView, 0);

                    }
                })
                .setNegativeButton(buttonNameTwo, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        dataSelectionCallback.alertNegativeBtn(selectedView, 0);
                    }
                });

     runUiThread(activity);

    }

    public void setSingleBtnAlert(int whichView, final Activity activity, DataSelectionCallback callback, String msg, String btnName) {


        if (myAlertDialog != null) {
            if (myAlertDialog.isShowing()) {
                myAlertDialog.dismiss();
                myAlertDialog = null;
            }

        }

        alertDialogBuilder = new AlertDialog.Builder(activity, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
        alertDialogBuilder.setCancelable(false);
        selectedView = whichView;
        this.dataSelectionCallback = callback;

        alertDialogBuilder

                .setMessage(Html.fromHtml(msg))
                .setPositiveButton(btnName, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        dataSelectionCallback.alertPositiveBtn(selectedView, 0);
                    }
                });

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!activity.isFinishing()) {
                    myAlertDialog = alertDialogBuilder.create();
                    myAlertDialog.show();
                    TextView textView = (TextView) myAlertDialog.findViewById(android.R.id.message);
                    textView.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL);
                }
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void setSingleBtnAlertWithTitle(int whichView, Activity activity, DataSelectionCallback callback, String msg, String btnName, String titleName) {

        if (myAlertDialog != null && myAlertDialog.isShowing()) {
            myAlertDialog.dismiss();
            myAlertDialog = null;
        }

        alertDialogBuilder = new AlertDialog.Builder(activity, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
        alertDialogBuilder.setCancelable(false);
        selectedView = whichView;
        this.dataSelectionCallback = callback;

        alertDialogBuilder

                .setTitle(Html.fromHtml(titleName))
                .setMessage(Html.fromHtml(msg))
                .setPositiveButton(btnName, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        dataSelectionCallback.alertPositiveBtn(selectedView, 0);
                    }
                });

     runUiThread(activity);

    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void setDoubleBtnAlertWithTitle(int whichView, Activity activity, DataSelectionCallback callback, String msg, String btnNameOne, String btnNameTwo, String titleName) {

       try {
           if (this.myAlertDialog != null && this.myAlertDialog.isShowing()) {
               this.myAlertDialog.dismiss();
               myAlertDialog = null;
           }

           alertDialogBuilder = new AlertDialog.Builder(activity, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
           alertDialogBuilder.setCancelable(false);
           selectedView = whichView;
           this.dataSelectionCallback = callback;

           alertDialogBuilder

                   .setTitle(Html.fromHtml(titleName))
                   .setMessage(Html.fromHtml(msg))
                   .setPositiveButton(btnNameOne, new DialogInterface.OnClickListener() {
                       public void onClick(DialogInterface dialog, int which) {
                           dialog.dismiss();
                           dataSelectionCallback.alertPositiveBtn(selectedView, 0);
                       }
                   })
                   .setNegativeButton(btnNameTwo, new DialogInterface.OnClickListener() {
                       public void onClick(DialogInterface dialog, int which) {
                           dialog.dismiss();
                           dataSelectionCallback.alertNegativeBtn(selectedView, 0);
                       }
                   });
           runUiThread(activity);
       }catch (Exception e){}
        finally {
           this.myAlertDialog = null;
       }

    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public AlertDialog.Builder setValuesWithoutDoneBtn(String titleName, Activity act, DataSelectionCallback callback, final List<String> stringList, int whichView, int defaultSelectedPos) {

        this.dataSelectionCallback = callback;
        selectedView = whichView;

        charSequenceItems = stringList.toArray(new CharSequence[stringList.size()]);

        alertDialogBuilder = new AlertDialog.Builder(act);
        alertDialogBuilder.setTitle(titleName);
        alertDialogBuilder.setCancelable(true);

        alertDialogBuilder.setSingleChoiceItems(charSequenceItems, defaultSelectedPos, listener);
        return alertDialogBuilder;
    }

    private final DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int position) {
            dialogInterface.dismiss();
            alertDialogBuilder.setSingleChoiceItems(charSequenceItems, position, listener);
            dataSelectionCallback.dataSelection(selectedView, position);

        }
    };

    private void runUiThread(final Activity activity){
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(!activity.isFinishing()) {
                    myAlertDialog = alertDialogBuilder.create();
                    myAlertDialog.show();
                }
            }
        });
    }


    public void displaySingleBtnAlert(final RetrofitCallbackPOJO callbackPOJO , final int statusCode , String errorMsg , String btn1){

        if (myAlertDialog != null) {
            if (myAlertDialog.isShowing()) {
                myAlertDialog.dismiss();
                myAlertDialog = null;
            }
        }

        alertDialogBuilder = new AlertDialog.Builder(callbackPOJO.activity, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
        alertDialogBuilder.setCancelable(false);
        selectedView = callbackPOJO.requestCode;
        this.dataSelectionCallback = callbackPOJO.dataSelectionCallback;

        alertDialogBuilder
                .setMessage(Html.fromHtml(errorMsg))
                .setPositiveButton(btn1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        dataSelectionCallback.alertPositiveBtn(selectedView , statusCode);
                    }
                });

        callbackPOJO.activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(!callbackPOJO.activity.isFinishing()) {
                    myAlertDialog = alertDialogBuilder.create();
                    myAlertDialog.show();
                    TextView textView = (TextView) myAlertDialog.findViewById(android.R.id.message);
                    textView.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL);
                }
            }
        });
    }

    public void displayDoubleBtnAlert(RetrofitCallbackPOJO callbackPOJO , final int statusCode ,String errorMsg ,String btn1, String btn2){
        if (myAlertDialog != null && myAlertDialog.isShowing()) {
            myAlertDialog.dismiss();
            myAlertDialog = null;
        }

        alertDialogBuilder = new AlertDialog.Builder(callbackPOJO.activity, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
        alertDialogBuilder.setCancelable(false);
        selectedView = callbackPOJO.requestCode;
        this.dataSelectionCallback = callbackPOJO.dataSelectionCallback;

        alertDialogBuilder

                .setMessage(Html.fromHtml(errorMsg))
                .setPositiveButton(btn1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        dataSelectionCallback.alertPositiveBtn(selectedView, statusCode);

                    }
                })
                .setNegativeButton(btn2, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        dataSelectionCallback.alertNegativeBtn(selectedView, statusCode);
                    }
                });

        runUiThread(callbackPOJO.activity);
    }

    public void displaySingleBtnWithTitle(final RetrofitCallbackPOJO callbackPOJO , final int statusCode , String titleName ,
                                          String errorMsg , String btn){

        if (myAlertDialog != null) {
            if (myAlertDialog.isShowing()) {
                myAlertDialog.dismiss();
                myAlertDialog = null;
            }
        }

        alertDialogBuilder = new AlertDialog.Builder(callbackPOJO.activity, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
        alertDialogBuilder.setCancelable(false);
        selectedView = callbackPOJO.requestCode;
        this.dataSelectionCallback = callbackPOJO.dataSelectionCallback;

        alertDialogBuilder
                .setTitle(Html.fromHtml(titleName))
                .setMessage(Html.fromHtml(errorMsg))
                .setPositiveButton(btn, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        dataSelectionCallback.alertPositiveBtn(selectedView, statusCode);
                    }
                });

        callbackPOJO.activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(!callbackPOJO.activity.isFinishing()) {
                    myAlertDialog = alertDialogBuilder.create();
                    myAlertDialog.show();
                    TextView textView = (TextView) myAlertDialog.findViewById(android.R.id.message);
                    textView.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL);
                }
            }
        });
    }


    public void displaySingleBtnAlert1(final RetrofitCallbackPOJO callbackPOJO , final int statusCode , String errorMsg , String btn1){

        if (myAlertDialog != null) {
            if (myAlertDialog.isShowing()) {
                myAlertDialog.dismiss();
                myAlertDialog = null;
            }
        }

        alertDialogBuilder = new AlertDialog.Builder(callbackPOJO.activity, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
        alertDialogBuilder.setCancelable(false);
        selectedView = callbackPOJO.requestCode;
        this.dataSelectionCallback = callbackPOJO.dataSelectionCallback;

        alertDialogBuilder
                .setMessage(Html.fromHtml(errorMsg))
                .setPositiveButton(btn1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        dataSelectionCallback.alertPositiveBtn(selectedView , statusCode);
                    }
                });

        callbackPOJO.activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(!callbackPOJO.activity.isFinishing()) {
                    myAlertDialog = alertDialogBuilder.create();
                    myAlertDialog.show();
                    TextView textView = (TextView) myAlertDialog.findViewById(android.R.id.message);
                    textView.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL);
                }
            }
        });
    }


}
