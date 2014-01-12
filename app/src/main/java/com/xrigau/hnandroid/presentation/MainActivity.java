package com.xrigau.hnandroid.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.xrigau.hnandroid.R;
import com.xrigau.hnandroid.core.model.NewsResponse;
import com.xrigau.hnandroid.core.task.NewsTask;
import com.xrigau.hnandroid.loader.LoaderListener;
import com.xrigau.hnandroid.loader.PostsTaskLoaderCallbacks;
import com.xrigau.hnandroid.presentation.adapter.EmptyAdapter;
import com.xrigau.hnandroid.presentation.adapter.PostsAdapter;

public class MainActivity extends Activity implements LoaderListener<NewsResponse>, AdapterView.OnItemClickListener {

    private static final int NEWS_LOADER_ID = 1;

    private PostsTaskLoaderCallbacks postsTaskLoaderCallbacks;
    private String currentPage;

    private ListView list;
    private View loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restoreState(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        findViews();
        setUpViews();
        postsTaskLoaderCallbacks = new PostsTaskLoaderCallbacks(this, this);

        if (savedInstanceState != null) {
            getLoaderManager().restartLoader(NEWS_LOADER_ID, getLoaderBundle(), postsTaskLoaderCallbacks).forceLoad();
            return;
        }

        getLoaderManager().initLoader(NEWS_LOADER_ID, getLoaderBundle(), postsTaskLoaderCallbacks).forceLoad();
    }

    private void restoreState(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            return;
        }
        currentPage = savedInstanceState.getString(PostsTaskLoaderCallbacks.PAGE, NewsTask.FIRST_PAGE);
    }

    private void findViews() {
        list = (ListView) findViewById(R.id.list);
        loading = findViewById(R.id.loading);
    }

    private void setUpViews() {
        list.setOnItemClickListener(this);
    }

    private Bundle getLoaderBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(PostsTaskLoaderCallbacks.PAGE, currentPage);
        return bundle;
    }

    @Override
    public void onLoadStarted() {
        list.setVisibility(View.GONE);
        loading.setVisibility(View.VISIBLE);
        list.setAdapter(new EmptyAdapter());
    }

    @Override
    public void onLoadFinished(NewsResponse response) {
        list.setAdapter(new PostsAdapter(response.getNews(), LayoutInflater.from(this), getResources()));
        list.setVisibility(View.VISIBLE);
        loading.setVisibility(View.GONE);
        currentPage = response.getCurrentPage();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "Wohooo clicked on some item!", 0).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(PostsTaskLoaderCallbacks.PAGE, currentPage);
    }
}
