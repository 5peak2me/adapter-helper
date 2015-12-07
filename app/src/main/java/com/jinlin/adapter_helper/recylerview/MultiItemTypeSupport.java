package com.jinlin.adapter_helper.recylerview;

public interface MultiItemTypeSupport<T> {

    int getLayoutId(int viewType);

    int getItemViewType(int position, T t);

}