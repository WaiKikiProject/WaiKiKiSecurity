package com.security.waikiki.myapplication.view.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import com.security.waikiki.myapplication.R;

public class RootParentActivity extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusbar(Toolbar toolbar)
    {

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);


        setToolbarHeight(toolbar);
    }

    private void setToolbarHeight(Toolbar toolbar)
    {
        int statusheight = getStatusBarHeight();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int toolbarheight = (int) (getResources().getDimension(R.dimen.toolbar_height));

        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) toolbar.getLayoutParams();
        params.height = statusheight + toolbarheight;
        toolbar.setLayoutParams(params);
        toolbar.setPadding(0, statusheight, 0, 0);
    }

    private int getStatusBarHeight()
    {
        int height = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");

        if (resourceId > 0)
        {
            height = getResources().getDimensionPixelSize(resourceId);
        }
        return height;
    }

}
