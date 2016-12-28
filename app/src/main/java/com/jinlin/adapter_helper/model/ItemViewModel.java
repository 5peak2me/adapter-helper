package com.jinlin.adapter_helper.model;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by J!nl!n on 2016/12/28.
 * Copyright © 1990-2016 J!nl!n™ Inc. All rights reserved.
 * <p>
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
public class ItemViewModel extends BaseObservable {

    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> subTitle = new ObservableField<>();
    public ObservableField<String> time = new ObservableField<>();
    public ObservableField<String> url = new ObservableField<>();

    public ItemViewModel(String title, String original_title, String url, String year) {
        this.title.set(title);
        this.subTitle.set(original_title);
        this.time.set(year);
        this.url.set(url);
    }

    @BindingAdapter({"android:src"})
    public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
}
