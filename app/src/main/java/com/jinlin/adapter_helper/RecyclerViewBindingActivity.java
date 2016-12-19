package com.jinlin.adapter_helper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.jinlin.adapter_helper.base.BaseBindingRVAdapter;
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
public class RecyclerViewBindingActivity extends AppCompatActivity {

    @Bind(R.id.recylerView)
    RecyclerView mRecylerView;
    private List<Item> mDatas;
    private int mLayoutIds = R.layout.list_item_view_databinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recylerview);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("RecyclerViewBinding");

        mDatas = new ArrayList<>();

        for (String imageThumbUrl : Images.imageThumbUrls) {
            mDatas.add(new Item(imageThumbUrl, "Title", "subtitle", "23.59"));
        }
        mRecylerView.addItemDecoration(new Divider(this));
        mRecylerView.setAdapter(new BaseBindingRVAdapter<Item>(this, mDatas, mLayoutIds, BR.item) {
            @Override
            public int getLayoutResId(Item item, int position) {
//                switch (position % 3) {
//                    case 1:
//                        return R.layout.list_item_view_type1_databinding;
//                    case 2:
//                        return R.layout.list_item_view_type2_databinding;
//                    case 0:
//                    default:
                        return super.getLayoutResId(item, position);
//                }
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
