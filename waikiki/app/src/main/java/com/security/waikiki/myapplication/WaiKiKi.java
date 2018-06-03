package com.security.waikiki.myapplication;

import android.content.Context;
import android.content.DialogInterface;

import com.security.waikiki.myapplication.util.Customdialog;

public class WaiKiKi {

    public static final int SIGN_IN = 0x0001;
    public static final int INSTALL = 0x0002;

    public static final String PUSH_BROADCASTMESSAGE = "PUSH_BROADCAST";

    public static final int SUCCESS_SIGN_UP_CODE = 210;
    public static final int EXIST_EMAIL_CODE = 401;
    public static final int NOT_EXIST_EMAIL_CODE = 402;
    public static final int DISS_MATCH_LOGIN_CODE = 403;
    public static final int NOT_MATCH_NAME_CODE = 404;
    public static final int NOT_EXISTDEIVCE_CODE = 405;
    public static final int NOT_EXIST_USER_CODE = 406;
    public static final int NOT_INSTALL_USER_CODE = 408;
    public static final int ALEADY_INSTALL_DEVICE_CODE = 409;
    public static final int ALEADY_INSTALL_MASTER_CODE = 410;
    public static final int NOT_INSTALL_MASTER_CODE = 411;
    public static final int NOT_EXIST_EVENT_CODE = 450;
    public static final int DATABASE_ERROR_CODE = 460;
    public static final int DATABASE_OVERLAP_CODE = 470;
    public static final int NOT_DISTMATCH_ERROR_CODE = 500;



    public static void showDialog(Context context, String title, String message, DialogInterface.OnDismissListener dismissListener) {
        new Customdialog(context,title, message, dismissListener).show();
    }

    public static void showDialog(Context context, String message, DialogInterface.OnDismissListener dismissListener) {
        new Customdialog(context, message, dismissListener).show();
    }
}
