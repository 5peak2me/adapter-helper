package com.jinlin.adapter_helper.base.interfaces;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by J!nl!n on 15/10/19.
 * Copyright © 1990-2015 J!nl!n™ Inc. All rights reserved.
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
public interface Adapter<T> {

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param holder   A fully initialized helper.
     * @param position The position of the item within the adapter's data set of the item whose view
     *                 we want.
     * @param item     The item that needs to be displayed.
     */
//    void convert(ViewHolder holder, int position, T item);

    /**
     * Implement this method and use the helper to set layout res.
     *
     * @param item     The item that needs to be displayed.
     * @param position The position of the item within the adapter's data set of the item whose view
     *                 we want.
     * @return layout resId
     */
    int getLayoutResId(T item, int position);

    /**
     * Implement this method to add a item into list.
     *
     * @param elem The item that needs to be added.
     */
    void add(@NonNull T elem);

    /**
     * Implement this method to add a item by index.
     *
     * @param index the index we need remove.
     * @param elem  new item.
     */
    void add(int index, @NonNull T elem);

    /**
     * Implement this method to add a new list into the original list.
     *
     * @param elem new list.
     */
    void addAll(@NonNull List<T> elem);

    /**
     * Implement this method to replace a item with another item.
     *
     * @param oldElem old item.
     * @param newElem new item.
     */
    void set(@NonNull T oldElem, @NonNull T newElem);

    /**
     * Implement this method to replace a item by index.
     *
     * @param index the index we need remove.
     * @param elem  new item.
     */
    void set(int index, @NonNull T elem);

    /**
     * Implement this method to remove a item.
     *
     * @param elem the item we need remove.
     */
    void remove(@NonNull T elem);

    /**
     * Implement this method to remove a item by index.
     *
     * @param index the index we need remove.
     */
    void remove(int index);

    /**
     * Implement this method to replace the list by another list.
     *
     * @param elem other list.
     */
    void replaceAll(@NonNull List<T> elem);

    /**
     * Implement this method to judge the list has contains the item.
     *
     * @param elem The item that needs to be judged.
     * @return the result of contains judge.
     */
    boolean contains(@NonNull T elem);

    /**
     * Implement this method to clear list.
     */
    void clear();

    /**
     * Implement this method to move list item.
     *
     * @param fromPosition start positiom
     * @param toPosition   end positiom
     */
    void move(int fromPosition, int toPosition);

}
