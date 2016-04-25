package com.gm.gmannotation.activities;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gm.gmannotation.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.SystemService;
import org.androidannotations.annotations.Touch;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.BooleanRes;
import org.androidannotations.annotations.res.ColorRes;
import org.androidannotations.annotations.res.StringRes;

import java.util.concurrent.TimeUnit;

@EActivity(R.layout.activity_third)
@OptionsMenu(R.menu.home_actvity)
public class ThirdActivity extends AppCompatActivity {

    @ViewById
    Toolbar toolbar;

    @ViewById
    EditText myEditText;

    @ViewById(R.id.myTextView)
    TextView textView;

    @StringRes(R.string.hello)
    String helloFormat;

    @ColorRes
    int androidColor;

    @BooleanRes
    boolean someBoolean;

    @SystemService
    NotificationManager notificationManager;

    @AfterViews
    public void init() {
        setSupportActionBar(toolbar);
    }

    @OptionsItem(R.id.action_settings)
    public void settings() {
        Toast.makeText(ThirdActivity.this, "This is a Test!!!", Toast.LENGTH_SHORT).show();
    }

    @Click
    void myButtonClicked() {
        toolbar.setTitle("Hello!!!");
        String name = myEditText.getText().toString();
        setProgressBarIndeterminateVisibility(true);
        setProgressBarVisibility(true);
    preexe();
        someBackgroundWork(name, 5);
    }
public void preexe(){

}
    @Background
    void someBackgroundWork(String name, long timeToDoSomeLongComputation) {
        try {
            TimeUnit.SECONDS.sleep(timeToDoSomeLongComputation);
        } catch (InterruptedException e) {
        }

        String message = String.format(helloFormat, name);

        updateUi(message, androidColor);

        showNotificationsDelayed();
    }

    @UiThread
    void updateUi(String message, int color) {
        //	setProgressBarIndeterminateVisibility(false);
        textView.setText(message);
        textView.setTextColor(color);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @UiThread(delay = 2000)
    void showNotificationsDelayed() {
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(), 0);

        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Test notification")
                .setContentText("New Notification")
                .setContentIntent(contentIntent)
                .getNotification();

        notificationManager.notify(1, notification);
    }

    @Click
    void startRecyclerActivity() {
       FourthActivity_.intent(ThirdActivity.this).start();
    }

    @Click
    void startListActivity(View v) {
        startActivity(new Intent(this, ListActivity_.class));
    }

    @Touch
    void myTextView(MotionEvent event) {
        Log.d("MyActivity", "myTextView was touched!");
    }



}
