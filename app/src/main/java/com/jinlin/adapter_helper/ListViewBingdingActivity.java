package com.jinlin.adapter_helper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jinlin.adapter_helper.base.BaseBindingLVAdapter;
import com.jinlin.adapter_helper.model.ItemViewModel;
import com.jinlin.adapter_helper.model.MovieItem;
import com.jinlin.adapter_helper.model.MovieItem.Subjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
public class ListViewBingdingActivity extends AppCompatActivity {

    @Bind(R.id.listView)
    ListView mListView;
    private List<ItemViewModel> mDatas;
    private int mLayoutIds = R.layout.list_item_view_databinding;

    private BaseBindingLVAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        ButterKnife.bind(this);

        init();
        initData();
    }

    private void init() {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("ListViewBingding");

        mDatas = new ArrayList<>();

        mListView.setAdapter(mAdapter = new BaseBindingLVAdapter<ItemViewModel>(this, mLayoutIds, BR.item) {
            @Override
            public int getLayoutResId(ItemViewModel item, int position) {
                switch (position % 3) {
                    case 1:
                        return R.layout.list_item_view_type1_databinding;
                    case 2:
                        return R.layout.list_item_view_type2_databinding;
                    case 0:
                    default:
                        return super.getLayoutResId(item, position);
                }
            }

            @Override
            public int getViewTypeCount() {
                return 3;
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewBingdingActivity.this, "click:" + position, Toast.LENGTH_SHORT).show();
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

    private static final String URL = "https://api.douban.com/v2/movie/top250?start=0&count=20";

    public void initData() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS).build();
        Request.Builder requestBuilder = new Request.Builder().url(URL);
        // 可以省略，默认是GET请求
        requestBuilder.method("GET", null);
        Request request = requestBuilder.build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                Gson gson = new Gson();
                MovieItem data = gson.fromJson(str, MovieItem.class);
                if (data != null && data.subjects != null && data.subjects.size() >= 0) {
                    for (Subjects subject : data.subjects) {
                        mDatas.add(new ItemViewModel(subject.title, subject.original_title, subject.images.medium, subject.year));
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mAdapter.addAll(mDatas);
                            }
                        });
                    }
                }
            }
        });
    }
}
