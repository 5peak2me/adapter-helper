package com.gift.android.holiday.freetour.lib;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by J!nl!n on 2017/1/19.
 * Copyright © 1990-2017 J!nl!n™ Inc. All rights reserved.
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
public class ViewHolder {

    /**
     * 存储item中的所有视图控件
     */
    private final SparseArray<View> mViews;

    private int mGroupPosition;

    private int mChildPosition;

    private boolean mIsLastChild;

    private View mConvertView;

    private Context mContext;

    public ViewHolder(Context context, int groupPosition, ViewGroup parent, int layoutId) {
        this.mContext = context;
        this.mGroupPosition = groupPosition;
        this.mChildPosition = -1;
        this.mViews = new SparseArray<>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
    }

    public ViewHolder(Context context, int groupPosition, int childPosition, ViewGroup parent, int layoutId, boolean isLastChild) {
        this.mContext = context;
        this.mGroupPosition = groupPosition;
        this.mChildPosition = childPosition;
        this.mIsLastChild = isLastChild;
        this.mViews = new SparseArray<>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
    }

    /**
     * 根据id获取控件
     *
     * @param viewId 资源ID
     * @param <T>    泛型
     * @return 控件
     */
    public <T extends View> T getView(int viewId) {
        View childView = mViews.get(viewId);
        if (childView == null) {
            childView = mConvertView.findViewById(viewId);
            mViews.put(viewId, childView);
        }
        return (T) childView;
    }

    public static ViewHolder get(Context context, int groupPosition, int childPosition, View convertView,
                                 int layoutId, ViewGroup parent, boolean isGroupView, boolean isLastChild) {
        if (convertView == null) {
            if (isGroupView) {
                return new ViewHolder(context, groupPosition, parent, layoutId);
            } else {
                return new ViewHolder(context, groupPosition, childPosition, parent, layoutId, isLastChild);
            }
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.mGroupPosition = groupPosition;
        holder.mChildPosition = childPosition;
        holder.mIsLastChild = isLastChild;
        return holder;
    }

    /**
     * 用于外界访问convertView
     *
     * @return convertView
     */
    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 用于外界访问组的position
     *
     * @return 组position
     */
    public int getGroupPosition() {
        return mGroupPosition;
    }

    /**
     * 用于外界访问所在组内部的position
     *
     * @return 所在组内部的position，如果该项是group的item，返回-1
     */
    public int getChildPosition() {
        return mChildPosition;
    }

    /**
     * 用于外界访问该项是否是该组最后一个item
     *
     * @return 如果该项是group的item或者该项不是最后一个item，返回false
     */
    public boolean isLastChild() {
        return mIsLastChild;
    }
}
