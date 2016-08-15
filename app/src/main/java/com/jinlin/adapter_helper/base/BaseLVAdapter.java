package com.jinlin.adapter_helper.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.jinlin.adapter_helper.base.interfaces.Adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by J!nl!n on 15/10/19.
 * Copyright © 1990-2015 J!nl!n™ Inc. All rights reserved.
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
public abstract class BaseLVAdapter<T> extends BaseAdapter implements Adapter<T> {
    protected final Context mContext;
    protected final List<T> mDatas;
    protected final int mItemLayoutId;

    public BaseLVAdapter(Context context, int itemLayoutId) {
        this(context, null, itemLayoutId);
    }

    public BaseLVAdapter(Context context, List<T> datas, int itemLayoutId) {
        this.mContext = context;
        this.mDatas = datas == null ? new ArrayList<T>() : new ArrayList<>(datas);
        this.mItemLayoutId = itemLayoutId;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        if (position >= mDatas.size()) return null;
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutResId(getItem(position), position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final T item = getItem(position);
        final ViewHolder viewHolder = getViewHolder(position, convertView, parent);
        convert(viewHolder, position, item);
        viewHolder.setAssociatedObject(item);
        return viewHolder.getConvertView();
    }

//    protected abstract void convert(ViewHolder holder, int position, T item);

    private ViewHolder getViewHolder(int position, View convertView, ViewGroup parent) {
        return ViewHolder.get(mContext, convertView, parent, getItemViewType(position), position);
    }

    @Override
    public boolean isEnabled(int position) {
        return position < mDatas.size();
    }

    @Override
    public int getLayoutResId(T item, int position) {
        return this.mItemLayoutId; // default
    }

    @Override
    public void add(T elem) {
        mDatas.add(elem);
        notifyDataSetChanged();
    }

    @Override
    public void addAll(List<T> elem) {
        mDatas.addAll(elem);
        notifyDataSetChanged();
    }

    @Override
    public void set(T oldElem, T newElem) {
        set(mDatas.indexOf(oldElem), newElem);
    }

    @Override
    public void set(int index, T elem) {
        mDatas.set(index, elem);
        notifyDataSetChanged();
    }

    @Override
    public void remove(T elem) {
        mDatas.remove(elem);
        notifyDataSetChanged();
    }

    @Override
    public void remove(int index) {
        mDatas.remove(index);
        notifyDataSetChanged();
    }

    @Override
    public void replaceAll(List<T> elem) {
        mDatas.clear();
        mDatas.addAll(elem);
        notifyDataSetChanged();
    }

    @Override
    public boolean contains(T elem) {
        return mDatas.contains(elem);
    }

    /**
     * Clear mDatas list
     */
    @Override
    public void clear() {
        mDatas.clear();
        notifyDataSetChanged();
    }

}
