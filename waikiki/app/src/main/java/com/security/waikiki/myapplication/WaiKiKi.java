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

    public static void showDialog(Context context, String title, String message, DialogInterface.OnDismissListener dismissListener) {
        new Customdialog(context,title, message, dismissListener).show();
    }

    public static void showDialog(Context context, String message, DialogInterface.OnDismissListener dismissListener) {
        new Customdialog(context, message, dismissListener).show();
    }
}
