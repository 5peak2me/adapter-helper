# adapter-helper
a common adapter for AdapterView&amp;RecylerView just like base-adapter-helper(https://github.com/JoanZapata/base-adapter-helper)

![](https://github.com/5peak2me/adapter-helper/blob/master/gif/demo.gif)![](https://github.com/5peak2me/adapter-helper/blob/master/gif/demo2.gif)

also U can custom your own action in ViewHolder.java like this 

```java
public ViewHolder setTextColor(int viewId, int color) {
    TextView tv = getView(viewId);
    tv.setTextColor(color);
    return this;
}
```

And U can easily use it like this for ListView&GridView

```java
mListView.setAdapter(new BaseLVAdapter<Item>(this, mDatas, R.layout.list_item_view) {
    @Override
    protected void convert(ViewHolder holder, final int position, Item item) {
        holder.setImageByUrl(R.id.iv, item.getUrl());
        holder.setText(R.id.tv_title, item.getTitle());
        holder.setText(R.id.tv_subtitle, item.getSubTitle());
        holder.setText(R.id.tv_time, item.getTime());
        holder.setOnClickListener(R.id.iv, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "click:image position = " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
});
```
for RecylerView
```java
mRecylerView.setAdapter(new BaseRVAdapter<Item>(this, mDatas, R.layout.list_item_view) {
    @Override
    protected void convert(ViewHolder holder, final int position, Item item) {
        holder.setImageByUrl(R.id.iv, item.getUrl());
        holder.setText(R.id.tv_title, item.getTitle());
        holder.setText(R.id.tv_subtitle, item.getSubTitle());
        holder.setText(R.id.tv_time, item.getTime());
        holder.setOnClickListener(R.id.iv, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "click:image position = " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
});
```

and from now on, it support multiple types of layout.U can easily find it in code like this.U must override getItemViewType method to tell adapter whitch u want show and mLayoutIds is a array of layout ids.
```
mListView.setAdapter(new BaseLVAdapter<Item>(this, mDatas, mLayoutIds) {
    @Override
    public int getItemViewType(int position) {
        return position % mLayoutIds.length;
    }

    @Override
    public void convert(ViewHolder holder, final int position, int type, Item item) {
        holder.setText(R.id.tv_subtitle, String.valueOf(type));
        switch (type) {
            case 0:
            case 1:
                holder.setImageByUrl(R.id.iv, item.getUrl());
                break;
            case 2:
                holder.setCircleImageByUrl(R.id.iv, item.getUrl());
                break;
        }
        holder.setText(R.id.tv_title, item.getTitle());
//                holder.setText(R.id.tv_subtitle, item.getSubTitle());
        holder.setText(R.id.tv_time, item.getTime());
        holder.setOnClickListener(R.id.iv, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "click:image position = " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
});
```



