package com.hhl.flowlayoutdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.hhl.library.FlowTagLayout;
import com.hhl.library.OnTagClickListener;
import com.hhl.library.OnTagSelectListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private FlowTagLayout mMobileFlowTagLayout;
    private TagAdapter<String> mMobileTagAdapter;
    List<String> dataSource = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        mMobileFlowTagLayout = (FlowTagLayout) findViewById(R.id.mobile_flow_layout);
        mMobileTagAdapter = new TagAdapter<>(this);
        mMobileFlowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI);
        mMobileFlowTagLayout.setAdapter(mMobileTagAdapter);
        mMobileFlowTagLayout.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                if (selectedList != null && selectedList.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (int i : selectedList) {
                        sb.append(parent.getAdapter().getItem(i));
                        sb.append(":");
                    }
                    Snackbar.make(parent, "Hello:" + sb.toString(), Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }else{
                    Snackbar.make(parent, "Hi : "+selectedList.size(), Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }
        });


        initMobileData();

        findViewById(R.id.bt_clear_all)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMobileFlowTagLayout.clearAllOption();
                        mMobileTagAdapter.notifyDataSetChanged();
                    }
                });

        findViewById(R.id.bt_add).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMobileTagAdapter.onlyAdd("Test");
                        mMobileTagAdapter.notifyDataSetChanged();
                    }
                });
    }

    private void initMobileData() {
        dataSource.add("Santosh");
        dataSource.add("Girish");
        dataSource.add("Mayank");
        dataSource.add("Moudipa");
        dataSource.add("Satya");
        dataSource.add("Aalishan");
        mMobileTagAdapter.onlyAddAll(dataSource);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
