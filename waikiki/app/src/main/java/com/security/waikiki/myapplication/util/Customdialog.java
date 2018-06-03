package com.security.waikiki.myapplication.util;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.security.waikiki.myapplication.R;
import com.security.waikiki.myapplication.WaiKiKi;
import com.security.waikiki.myapplication.view.activity.SignInActivity;
import com.security.waikiki.myapplication.view.activity.SignUpActivity;

public class Customdialog extends Dialog {

    String title,message;
    TextView mTextTitle, mTextMessage;
    OnDismissListener mDismissListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customdialog);

        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        findViewById(R.id.dialog_confirm).setOnClickListener(dOnclickListener);

        mTextTitle = findViewById(R.id.dialog_title);
        mTextMessage = findViewById(R.id.dialog_mesgase);

        mTextTitle.setText(title);
        mTextMessage.setText(message);
    }

    public Customdialog(@NonNull Context context) {
        super(context);
    }

    public Customdialog(@NonNull Context context, String title, String message, OnDismissListener dismissListener) {
        super(context);
        this.title = title;
        this.message = message;
        mDismissListener = dismissListener;
    }

    public Customdialog(@NonNull Context context, String message, OnDismissListener dismissListener) {
        super(context);
        this.title =context.getString(R.string.app_name);
        this.message = message;
        mDismissListener = dismissListener;
    }


    private View.OnClickListener dOnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mDismissListener.onDismiss(Customdialog.this);
            dismiss();
        }
    };


}


