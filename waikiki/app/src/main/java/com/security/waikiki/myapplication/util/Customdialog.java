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

public class Customdialog extends Dialog {

    private String dialogtitel;
    private String dialogmesgase;

    TextView title, mesgase, confirm;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customdialog);

        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        findViewById(R.id.dialog_confirm).setOnClickListener(mOnclickListener);

        title = findViewById(R.id.dialog_title);
        mesgase = findViewById(R.id.dialog_mesgase);

        title.setText(dialogtitel);
        mesgase.setText(dialogmesgase);
    }

    public Customdialog(@NonNull Context context, String title, String content,
                        View.OnClickListener singleListener) {
        super(context);
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


