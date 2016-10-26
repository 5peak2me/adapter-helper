package com.jinlin.adapter_helper.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.jinlin.adapter_helper.base.interfaces.Adapter;

import java.util.ArrayList;
import java.util.Collections;
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
    private final List<T> mDatas;
    private final int mItemLayoutId;
    private SparseIntArray mTypeMap = new SparseIntArray();

    public BaseLVAdapter(Context context, int itemLayoutId) {
        this(context, null, itemLayoutId);
    }

    protected BaseLVAdapter(Context context, List<T> datas, int itemLayoutId) {
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
        int layoutResId = getLayoutResId(getItem(position), position);
        int type = mTypeMap.get(layoutResId, -1);
        if (type == -1) {
            type = mTypeMap.size();
            mTypeMap.put(layoutResId, type);
        }
        return type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final T item = getItem(position);
        final ViewHolder viewHolder = getViewHolder(position, convertView, parent);
        viewHolder.setAssociatedObject(item);

        convert(viewHolder, position, item);
        return viewHolder.getConvertView();
    }

//    protected abstract void convert(ViewHolder holder, int position, T item);

    private ViewHolder getViewHolder(int position, View convertView, ViewGroup parent) {
        return ViewHolder.get(mContext, convertView, parent, getLayoutResId(getItem(position), position), position);
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
    public void add(@NonNull T elem) {
        mDatas.add(elem);
        notifyDataSetChanged();
    }

    @Override
    public void add(int index, @NonNull T elem) {
        mDatas.add(index, elem);
        notifyDataSetChanged();
    }

    @Override
    public void addAll(@NonNull List<T> elem) {
        mDatas.addAll(elem);
        notifyDataSetChanged();
    }

    @Override
    public void set(@NonNull T oldElem, @NonNull T newElem) {
        set(mDatas.indexOf(oldElem), newElem);
    }

    @Override
    public void set(int index, @NonNull T elem) {
        mDatas.set(index, elem);
        notifyDataSetChanged();
    }

    @Override
    public void remove(@NonNull T elem) {
        mDatas.remove(elem);
        notifyDataSetChanged();
    }

    @Override
    public void remove(int index) {
        mDatas.remove(index);
        notifyDataSetChanged();
    }

    @Override
    public void replaceAll(@NonNull List<T> elem) {
        mDatas.clear();
        mDatas.addAll(elem);
        notifyDataSetChanged();
    }

    @Override
    public boolean contains(@NonNull T elem) {
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

    @Override
    public void move(int fromPosition, int toPosition) {
        Collections.swap(mDatas, fromPosition, toPosition);
        notifyDataSetChanged();
    }
}
