package com.example.kyungmin.dps.view.Activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.kyungmin.dps.R;
import com.example.kyungmin.dps.view.entity.EvnetItem;

import java.util.ArrayList;

public class EventAdapter extends BaseAdapter {

    private ArrayList<EvnetItem> mItmes = new ArrayList<>();

    @Override
    public int getCount() {
        return mItmes.size();
    }

    @Override
    public EvnetItem getItem(int position) {
        return mItmes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview, parent, false);
        }
        View view = convertView.findViewById(R.id.imagebutton);
        TextView tv =  convertView.findViewById(R.id.date);

        EvnetItem myItem = getItem(position);

        view.setBackground(myItem.getIcon());
        tv.setText(myItem.getDate());

        return convertView;
    }

    public void addItem(Drawable img, String date) {
        EvnetItem mItme = new EvnetItem();

        mItme.setDate(date);
        mItme.setIcon(img);

        mItmes.add(mItme);
    }

}
