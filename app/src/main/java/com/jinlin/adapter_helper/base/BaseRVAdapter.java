package com.jinlin.adapter_helper.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

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
public abstract class BaseRVAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Adapter<T> {
    protected final Context mContext;
    private final List<T> mDatas;
    private final int mItemLayoutId;

    public BaseRVAdapter(Context context, int itemLayoutId) {
        this(context, null, itemLayoutId);
    }

    protected BaseRVAdapter(Context context, List<T> datas, int layoutResId) {
        this.mDatas = datas == null ? new ArrayList<T>() : new ArrayList<>(datas);
        this.mContext = context;
        this.mItemLayoutId = layoutResId;
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutResId(getItem(position), position);
    }

    /**
     * 创建ViewHolder时的回调
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final ViewHolder holder = ViewHolder.get(mContext, null, parent, viewType);
        return new RecyclerViewHolder(holder.getConvertView(), holder);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ViewHolder helper = ((RecyclerViewHolder) holder).adapterHelper;
        helper.setAssociatedObject(getItem(position));
        convert(helper, position, getItem(position));
    }

    private T getItem(int position) {
        if (position >= mDatas.size()) return null;
        return mDatas.get(position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

//    protected abstract void convert(ViewHolder holder, int position, T item);

    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    interface OnItemClickListener {
        void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position);
    }

    interface OnItemLongClickListener {
        void onItemLongClick(RecyclerView.ViewHolder viewHolder, View view, int position);
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ViewHolder adapterHelper;

        RecyclerViewHolder(View itemView, final ViewHolder adapterHelper) {
            super(itemView);
            this.adapterHelper = adapterHelper;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != onItemClickListener)
                        onItemClickListener.onItemClick(RecyclerViewHolder.this, v,
                                getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (null != onItemLongClickListener) {
                        onItemLongClickListener.onItemLongClick(RecyclerViewHolder.this, v,
                                getAdapterPosition());
                        return true;
                    }
                    return false;
                }
            });
        }
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
        notifyItemInserted(index);
    }

    @Override
    public void addAll(@NonNull List<T> elem) {
        mDatas.addAll(elem);
        notifyItemRangeInserted(mDatas.size(), elem.size());
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
        remove(mDatas.indexOf(elem));
    }

    @Override
    public void remove(int index) {
        mDatas.remove(index);
        notifyItemRemoved(index);
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
        notifyItemMoved(fromPosition, toPosition);
    }

}
