package com.jinlin.adapter_helper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.jinlin.adapter_helper.model.Item;
import com.jinlin.adapter_helper.recylerview.BaseRVAdapter;
import com.jinlin.adapter_helper.recylerview.ViewHolder;

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
public class RecyclerViewActivity extends AppCompatActivity {

    @Bind(R.id.recylerView)
    RecyclerView mRecylerView;
    private List<Item> mDatas;
    private int[] mLayoutIds = {
            R.layout.list_item_view,
            R.layout.list_item_view_type1,
            R.layout.list_item_view_type2
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recylerview);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("RecylerView");

        mDatas = new ArrayList<>();

        for (String imageThumbUrl : Images.imageThumbUrls) {
            mDatas.add(new Item(imageThumbUrl, "Title", "subtitle", "23.59"));
        }

        mRecylerView.setAdapter(new BaseRVAdapter<Item>(this, mDatas, mLayoutIds) {

            @Override
            public int getItemViewType(int position) {
                return position % mLayoutIds.length;
            }

            @Override
            public void convert(ViewHolder holder, final int position, int type, Item item) {
                holder.setText(R.id.tv_subtitle, String.valueOf(type));
                switch (type) {
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

        mRecylerView.setLayoutManager(new LinearLayoutManager(this));

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
