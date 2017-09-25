package com.ascend.wangfeng.booklisting;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>>{
    RecyclerView mListView;
    private MainAdapter adapter;
    private SwipeRefreshLayout mScrollView;
    private TextView mEmptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (RecyclerView) findViewById(R.id.list);
        mEmptyView = (TextView) findViewById(R.id.empty);
        adapter = new MainAdapter(mEmptyView);
        mListView.setLayoutManager(new LinearLayoutManager(this));
        mListView.setAdapter(adapter);
        getSupportLoaderManager().restartLoader(0,null,this);

        mScrollView = (SwipeRefreshLayout) findViewById(R.id.scroll);
        mScrollView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mScrollView.setRefreshing(true);
                getSupportLoaderManager().restartLoader(0,null,MainActivity.this);
            }
        });


    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        return new MainLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {
        mScrollView.setRefreshing(false);
        if(data==null||data.size()==0){
            Toast.makeText(this,getString(R.string.wifi_error),Toast.LENGTH_SHORT).show();
        }
        adapter.setBooks(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {

    }
}
