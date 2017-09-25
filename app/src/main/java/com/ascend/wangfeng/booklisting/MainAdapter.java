package com.ascend.wangfeng.booklisting;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengye on 2017/9/25.
 * email 1040441325@qq.com
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{
    private List<Book> mBooks;
    private View mEmptyView;
    public MainAdapter(View view) {
        mBooks =new ArrayList<>();
        mEmptyView=view;
    }
    public void setBooks(List<Book> books){
        mBooks.clear();
        mBooks.addAll(books);
        notifyDataSetChanged();
        if (mBooks.size()==0){
            mEmptyView.setVisibility(View.VISIBLE);
        }else {
            mEmptyView.setVisibility(View.GONE);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTitleTv.setText(mBooks.get(position).getTitle());
        holder.mAuthorTv.setText(mBooks.get(position).getAuthor()+holder.context.getString(R.string.author_prex));

    }

    @Override
    public int getItemCount() {
        return mBooks.size();

    }

    class ViewHolder extends RecyclerView.ViewHolder {
    TextView mTitleTv;
    TextView mAuthorTv;
        Context context;
    public ViewHolder(View itemView) {
        super(itemView);
        context =itemView.getContext();
        mTitleTv =itemView.findViewById(R.id.title);
        mAuthorTv =itemView.findViewById(R.id.author);
    }
}
}
