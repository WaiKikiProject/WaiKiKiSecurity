package com.example.kyungmin.dps.view.entity;

import android.graphics.drawable.Drawable;

public class EvnetItem {

    private String date;
    private Drawable icon;

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
