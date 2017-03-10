package com.bignerdranch.android.boboyo.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bignerdranch.android.boboyo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liujingbo on 17/2/24.
 */

public class LoginActivity extends AppCompatActivity{

    private static final String TAG = "LoginActivity";

//    private CollapsingToolbarLayout mToolbarLayout;
    private EditText mUsername;
    private EditText mPassword;
    private Button mLoginBtn;
    private CoordinatorLayout mCoordinatorLayout;
    private CollapsingToolbarLayout mToolbarLayout;
    private AppBarLayout mAppBarLayout;
    private boolean mPermissionEnabled = false;

    private static boolean isSupportHWEncode() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2;
    }

    private boolean isPermissionOK() {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            mPermissionEnabled = true;
            return true;
        }else {
            return checkPermission();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
//        mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
//        mAppBarLayout.setBackgroundResource(R.drawable.background);
        mToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        mToolbarLayout.setBackgroundResource(R.drawable.background);
//        mToolbarLayout.setBackgroundTintMode(PorterDuff.Mode.MULTIPLY);
        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
        mLoginBtn = (Button) findViewById(R.id.login_btn);

        isPermissionOK();

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUsername.getText().toString().equals("") && mPassword.getText().toString().equals("")){
                    Intent intent = new Intent(LoginActivity.this, LiveActivity.class);
                    startActivity(intent);
                }else {
                    Snackbar.make(mCoordinatorLayout, "Login Failed!", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124;

    private boolean checkPermission() {
        boolean ret = true;

        List<String> permissionsNeeded = new ArrayList<String>();
        final List<String> permissionList = new ArrayList<String>();
        if(!addPermission(permissionList, Manifest.permission.CAMERA)) {
            permissionsNeeded.add("CAMERA");
        }
        if(!addPermission(permissionList, Manifest.permission.RECORD_AUDIO)) {
            permissionsNeeded.add("MICROPHONE");
        }
        if(!addPermission(permissionList, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            permissionsNeeded.add("Write external storage");
        }

        if(permissionsNeeded.size() > 0) {
            String message = "You need to grant access to ";
            for(int i = 0; i < permissionsNeeded.size(); i++){
                message = message + ", " + permissionsNeeded.get(i);
            }
            if(!ActivityCompat.shouldShowRequestPermissionRationale(this, permissionList.get(0))) {
                showMessageOKCancel(message,
                        new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(LoginActivity.this, permissionList.toArray(new String[permissionList.size()]),
                                        REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
                            }
                        });
            }else {
                ActivityCompat.requestPermissions(LoginActivity.this, permissionList.toArray(new String[permissionList.size()]),
                        REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
            }
            ret = false;
        }
        return ret;
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private boolean addPermission(List<String> permissionList, String permission){
        boolean ret = true;
        if(ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(permission);
            ret = false;
        }
        return ret;
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(LoginActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    public static boolean verifyPermissions(int[] grantResults) {
        // At least one result must be checked.
        if(grantResults.length < 1){
            return false;
        }

        // Verify that each required permission has been granted, otherwise return false.
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS: {
                if (verifyPermissions(grantResults)) {
                    // All Permissions Granted
                    mPermissionEnabled = true;
                } else {
                    // Permission Denied
                    mPermissionEnabled = false;
                    showToast("Some Permission is Denied");
                }
            }
            break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    void showToast(final String msg) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_LONG).show();
            }
        });
    }
}
