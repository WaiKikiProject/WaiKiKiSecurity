package com.security.waikiki.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;


public class Customdialog extends Dialog {

    private String dialogtitel;
    private String dialogmesgase;

    TextView title, mesgase, confirm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customdialog);

//        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
//        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
//        lpWindow.dimAmount = 0.3f;
//        getWindow().setAttributes(lpWindow);


        findViewById(R.id.dialog_confirm).setOnClickListener(mOnclickListener);

        title = findViewById(R.id.dialog_title);
        mesgase = findViewById(R.id.dialog_mesgase);

        title.setText(dialogtitel);
        mesgase.setText(dialogmesgase);
    }

    public Customdialog(@NonNull Context context, String title, String content,
                        View.OnClickListener singleListener) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.dialogtitel = title;
        this.dialogmesgase = content;
    }



    private View.OnClickListener mOnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dismiss();
        }
    };
}


