package com.jinlin.adapter_helper.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.jinlin.adapter_helper.base.interfaces.Adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by J!nl!n on 2016/12/19.
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
public class BaseBindingLVAdapter<T> extends BaseAdapter implements Adapter<T> {

    private final List<T> mDatas;
    private final int mItemLayoutId;
    private final SparseIntArray mTypeMap = new SparseIntArray();
    private final LayoutInflater mInflater;
    private final int mVariableId;

    public BaseBindingLVAdapter(Context context, int itemLayoutId, int variableId) {
        this(context, null, itemLayoutId, variableId);
    }

    protected BaseBindingLVAdapter(Context context, List<T> datas, int itemLayoutId, int variableId) {
        this.mInflater = LayoutInflater.from(context);
        this.mDatas = datas == null ? new ArrayList<T>() : new ArrayList<>(datas);
        this.mItemLayoutId = itemLayoutId;
        this.mVariableId = variableId;
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
        ViewDataBinding dataBinding;
        if (convertView == null) {
            dataBinding = DataBindingUtil.inflate(mInflater, getLayoutResId(getItem(position), position), parent, false);
        } else {
            dataBinding = DataBindingUtil.getBinding(convertView);
        }
        dataBinding.setVariable(mVariableId, mDatas.get(position));
        return dataBinding.getRoot();
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
