package com.gift.android.holiday.freetour.lib;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.List;

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
public abstract class BaseExpandableLVAdapter<C, G extends IBaseExpandableDataSource<C>> extends BaseExpandableListAdapter {

    protected Context mContext;

    private List<G> mDataSource;

    private int mGroupLayoutId;

    private int mChildLayoutId;

    public BaseExpandableLVAdapter(Context context, List<G> dataSource, @LayoutRes int groupLayoutId, @LayoutRes int childLayoutId) {
        if (mGroupLayoutId == -1 || mChildLayoutId == -1)
            throw new IllegalStateException("You must override getLayoutId to returm layout id");
        this.mContext = context;
        this.mDataSource = dataSource;
        this.mGroupLayoutId = groupLayoutId;
        this.mChildLayoutId = childLayoutId;
    }

    public BaseExpandableLVAdapter(Context context, List<G> dataSource) {
        this(context, dataSource, -1, -1);
    }

    @Override
    public int getGroupCount() {
        return mDataSource.size();
    }

    @Override
    public int getGroupType(int groupPosition) {
        return super.getGroupType(groupPosition);
    }

    @Override
    public int getGroupTypeCount() {
        return super.getGroupTypeCount();
    }

    @Override
    public int getChildType(int groupPosition, int childPosition) {
        return super.getChildType(groupPosition, childPosition);
    }

    @Override
    public int getChildTypeCount() {
        return super.getChildTypeCount();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mDataSource.get(groupPosition).getChildList().size();
    }

    @Override
    public G getGroup(int groupPosition) {
        return mDataSource.get(groupPosition);
    }

    @Override
    public C getChild(int groupPosition, int childPosition) {
        return mDataSource.get(groupPosition).getChildList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = ViewHolder.get(mContext, groupPosition, -1, convertView, mGroupLayoutId, parent, true, false);
        convertGroupView(viewHolder, groupPosition, getGroup(groupPosition));
        return viewHolder.getConvertView();
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = ViewHolder.get(mContext, childPosition, childPosition, convertView, mChildLayoutId, parent, false, isLastChild);
        convertChildView(viewHolder, groupPosition, childPosition, getGroup(groupPosition), getChild(groupPosition, childPosition));
        return viewHolder.getConvertView();
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    /**
     * 更新groupItem的UI
     */
    protected abstract void convertGroupView(ViewHolder viewHolder, int groupPositon, G groupData);

    /**
     * 更新childItem的UI
     */
    protected abstract void convertChildView(ViewHolder viewHolder, int groupPositon, int childPositon, G groupData, C childData);

    @LayoutRes
    public int getLayoutId(boolean isGroupView, int groupPositon, int childPositon, G groupData, C childData) {
        return isGroupView ? mGroupLayoutId : mChildLayoutId;
    }
}
