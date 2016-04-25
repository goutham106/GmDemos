package com.gm.retrofit.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.gm.retrofit.R;
import com.gm.retrofit.webservice.interfaces.DataSelectionCallback;


public class OfflineDialog {

    private Activity activity;
    private Dialog dialog;
    private DataSelectionCallback dataSelectionCallback;
    private AlertDialog.Builder alertDialogBuilder;
    public static AlertDialog myAlertDialog;


    public OfflineDialog(Activity act, DataSelectionCallback dsCallback) {
        this.activity = act;
        this.dataSelectionCallback = dsCallback;

//        if (activity.isDestroyed() || activity.isFinishing()) {
//            return;
//        } else if (myAlertDialog != null) {
//            if (myAlertDialog.isShowing()) {
//                myAlertDialog.dismiss();
//                myAlertDialog = null;
//            }
//        }

    }

    public void showOfflineAlert(final String errorMsg) {
        if (myAlertDialog != null) {
            if (myAlertDialog.isShowing()) {
                myAlertDialog.dismiss();
                myAlertDialog = null;
            }
        }

        alertDialogBuilder = new AlertDialog.Builder(activity, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder
                .setMessage(errorMsg)
                .setPositiveButton(activity.getString(R.string.ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (Utils.isNetworkAvailable(activity)) {
                            dialog.dismiss();
                            dataSelectionCallback.onReConnect();

                        }
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


    public void showOfflineDialog(final String errorMsg, final String buttonText) {

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    //dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                    dialog = new Dialog(activity, android.R.style.Theme_Wallpaper);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.offline_layout);
                    TextView errorText = (TextView) dialog.findViewById(R.id.refresh_error_msg_alert_txt);
                    errorText.setText(errorMsg);
                    dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                    dialog.setCancelable(false);
                    //dialog.setCanceledOnTouchOutside(false);

                    Button okButton = (Button) dialog.findViewById(R.id.refresh_button);
                    okButton.setText(buttonText);
                    okButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (Utils.isNetworkAvailable(activity)) {
                                dialog.dismiss();
                                dataSelectionCallback.onReConnect();

                            }
                        }
                    });
                    if (!activity.isFinishing())
                        dialog.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }
}
