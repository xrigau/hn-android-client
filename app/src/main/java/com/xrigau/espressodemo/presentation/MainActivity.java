package com.xrigau.espressodemo.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.xrigau.espressodemo.R;
import com.xrigau.espressodemo.core.model.PostList;
import com.xrigau.espressodemo.loader.LoaderListener;
import com.xrigau.espressodemo.loader.PostsTaskLoaderCallbacks;
import com.xrigau.espressodemo.presentation.adapter.EmptyAdapter;
import com.xrigau.espressodemo.presentation.adapter.PostsAdapter;

public class MainActivity extends Activity implements LoaderListener<PostList>, AdapterView.OnItemClickListener {

    private static final int NEWS_LOADER = 1;

    private PostsTaskLoaderCallbacks postsTaskLoaderCallbacks;

    private ListView list;
    private View loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setUpViews();
        postsTaskLoaderCallbacks = new PostsTaskLoaderCallbacks(this, this);

        if (savedInstanceState != null) {
            getLoaderManager().restartLoader(NEWS_LOADER, Bundle.EMPTY, postsTaskLoaderCallbacks).forceLoad();
            return;
        }

        getLoaderManager().initLoader(NEWS_LOADER, Bundle.EMPTY, postsTaskLoaderCallbacks).forceLoad();
    }

    private void findViews() {
        list = (ListView) findViewById(R.id.list);
        loading = findViewById(R.id.loading);
    }

    private void setUpViews() {
        list.setOnItemClickListener(this);
    }

    @Override
    public void onLoadStarted() {
        list.setVisibility(View.GONE);
        loading.setVisibility(View.VISIBLE);
        list.setAdapter(new EmptyAdapter());
    }

    @Override
    public void onLoadFinished(PostList posts) {
        list.setAdapter(new PostsAdapter(posts, LayoutInflater.from(this), getResources()));
        list.setVisibility(View.VISIBLE);
        loading.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "Wohooo clicked on some item!", 0).show();
    }
}
