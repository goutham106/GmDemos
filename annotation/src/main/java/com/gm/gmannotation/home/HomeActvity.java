package com.gm.gmannotation.home;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.gm.gmannotation.R;
import com.gm.gmannotation.app.GmAnnotationApp;
import com.gm.gmannotation.customview.CustomTextView;
import com.gm.gmannotation.customview.RoundImageView;
import com.gm.gmannotation.util.ImagePicker;
import com.gm.gmannotation.util.Utils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;

@EActivity(R.layout.activity_home_actvity)
@OptionsMenu(R.menu.home_actvity)
public class HomeActvity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @ViewById
    Toolbar toolbar;

    @ViewById
    FloatingActionButton fab;

    @ViewById(R.id.drawer_layout)
    DrawerLayout drawer;

    @ViewById
    NavigationView nav_profile;

    RoundImageView userImageView;
    CustomTextView userNameTextView, userEmailTextView;

    private static final int REQUEST_CODE_PICTURE = 1;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    @AfterViews
    public void init() {
        setSupportActionBar(toolbar);

        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (drawerView.getId() == R.id.nav_profile) {
                    navProfileHeaderinit();

                    userNameTextView.setText(R.string.user_name);

                    userEmailTextView.setText(R.string.user_email);


                    if (ImagePicker.getInstance().storeImageFile(getApplicationContext(), "Profilepic").exists()) {
                        userImageView.setImageURI(Uri.fromFile(ImagePicker.getInstance().storeImageFile(getApplicationContext(), "Profilepic")));
                    }
                    userImageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!Utils.isMarshmallow())
                                clickOnProfilePhoto();
                            else
                                singlePermissionWrapper();
                        }
                    });
                }
            }
        };

        drawer.setDrawerListener(toggle);

        toggle.syncState();

        nav_profile.setNavigationItemSelectedListener(this);

    }

    public void navProfileHeaderinit() {
        View headerLayout = nav_profile.getHeaderView(0);
        userNameTextView = (CustomTextView) headerLayout.findViewById(R.id.userNameTextView);
        userEmailTextView = (CustomTextView) headerLayout.findViewById(R.id.userEmailTextView);
        userImageView = (RoundImageView) headerLayout.findViewById(R.id.userImageView);
    }

    @Override
    public void onBackPressed() {
        if (drawer != null) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }
    }

    @OptionsItem
    public void action_settings(){
        Toast.makeText(HomeActvity.this, "Test", Toast.LENGTH_SHORT).show();
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        if (drawer != null) {
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }


    public void clickOnProfilePhoto() {
        Intent pickIntent = new Intent();
        pickIntent.setType("image/*");
        pickIntent.setAction(Intent.ACTION_GET_CONTENT);
        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String pickTitle = getString(R.string.choice_Title);
        Intent chooserIntent = Intent.createChooser(pickIntent, pickTitle);
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{takePhotoIntent});

        startActivityForResult(chooserIntent, REQUEST_CODE_PICTURE);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri selectedImageUri = null;
        Bitmap bitmap = null;
        if (requestCode == REQUEST_CODE_PICTURE && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                return;
            }
            if (data.getData() != null) {
                if (Utils.isTablet(HomeActvity.this)) {
                    bitmap = (Bitmap) data.getExtras().get("data");
                    selectedImageUri = data.getData();
                } else
                    selectedImageUri = data.getData();
            } else if (data.getData() == null) {
                bitmap = (Bitmap) data.getExtras().get("data");
            }
            try {
                if (bitmap != null) {
                    selectedImageUri = ImagePicker.getInstance().storeImage(bitmap, "Profilepic");
                } else if (selectedImageUri != null) {
                    selectedImageUri = ImagePicker.getInstance().storeImage(ImagePicker.getInstance().getThumbnail(selectedImageUri), "Profilepic");
                }

                Log.e("PATH", ImagePicker.getInstance().getPath(selectedImageUri));

                userImageView.setImageDrawable(null);
                userImageView.setImageURI(selectedImageUri);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void singlePermissionWrapper() {
        int hasWriteContactsPermission = ContextCompat.checkSelfPermission(HomeActvity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(HomeActvity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Utils.showMessageOKCancel(HomeActvity.this, getString(R.string.help), getString(R.string.ok), getString(R.string.cancel), getString(R.string.help_text),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(HomeActvity.this,
                                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                        REQUEST_CODE_ASK_PERMISSIONS);
                            }
                        }, null);
                return;
            }
            ActivityCompat.requestPermissions(HomeActvity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE_ASK_PERMISSIONS);
            return;
        }


        clickOnProfilePhoto();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    GmAnnotationApp.initImgpic(getApplicationContext());
                    clickOnProfilePhoto();
                } else {
                    // Permission Denied
                    Utils.gotoAppSetting(HomeActvity.this);
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

}
