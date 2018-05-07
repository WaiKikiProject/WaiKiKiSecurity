package com.example.kyungmin.dps.view.Activity;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.example.kyungmin.dps.R;


public class EventActivity extends AppCompatActivity {

    private ListView mListView;
    EventAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        actionBar.setTitle("침입기록");
        actionBar.setBackgroundDrawable(new ColorDrawable(0xFFF89D88));
        setSupportActionBar(toolbar);

        mListView = (ListView) findViewById(R.id.list);


       setListView();

    }

    private void setListView() {

        mAdapter = new EventAdapter();

        for (int i = 0; i < 10; i++) {
            mAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_burst_mode_black_24dp), "2018-03-03");
        }

        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(EventActivity.this, EventInfoActivity.class);
                intent.putExtra("date",mAdapter.getItem(position).getDate());
                startActivity(intent);
            }
        });
    }
}
