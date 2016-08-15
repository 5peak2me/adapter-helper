package com.jinlin.adapter_helper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.jinlin.adapter_helper.base.BaseLVAdapter;
import com.jinlin.adapter_helper.base.ViewHolder;
import com.jinlin.adapter_helper.model.Item;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by J!nl!n on 15/10/28.
 * Copyright © 1990-2015 J!nl!n™ Inc. All rights reserved.
 * <p/>
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting
 * 　　　　┃　　　┃    神兽保佑,代码无bug
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━感觉萌萌哒━━━━━━
 */
public class ListViewActivity extends AppCompatActivity {

    @Bind(R.id.listView)
    ListView mListView;
    private List<Item> mDatas;
    private int mLayoutIds = R.layout.list_item_view;
//    private int[] mLayoutIds = {
//            R.layout.list_item_view,
//            R.layout.list_item_view_type1,
//            R.layout.list_item_view_type2
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        ButterKnife.bind(this);

        init();
    }

    private void init() {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("ListView");

        mDatas = new ArrayList<>();

        for (String imageThumbUrl : Images.imageThumbUrls) {
            mDatas.add(new Item(imageThumbUrl, "Title", "subtitle", "23.59"));
        }

        mListView.setAdapter(new BaseLVAdapter<Item>(this, mDatas, R.layout.list_item_view) {

            @Override
            public void convert(ViewHolder holder, final int position, Item item) {
                holder.setText(R.id.tv_subtitle, String.valueOf(getItemViewType(position)));
                switch (position % 3) {
                    case 0:
                    case 1:
                        holder.setImageByUrl(R.id.iv, item.getUrl());
                        break;
                    case 2:
                        holder.setCircleImageByUrl(R.id.iv, item.getUrl());
                        break;
                }
                holder.setText(R.id.tv_title, item.getTitle());
//                holder.setText(R.id.tv_subtitle, item.getSubTitle());
                holder.setText(R.id.tv_time, item.getTime());
                holder.setOnClickListener(R.id.iv, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "click:image position = " + position, Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewActivity.this, "click:" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
