package com.xrigau.hnandroid.presentation.fragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.xrigau.hnandroid.R;
import com.xrigau.hnandroid.core.model.News;
import com.xrigau.hnandroid.core.model.NewsResponse;
import com.xrigau.hnandroid.core.task.NewsTask;
import com.xrigau.hnandroid.loader.LoaderListener;
import com.xrigau.hnandroid.loader.NewsTaskLoaderCallbacks;
import com.xrigau.hnandroid.presentation.adapter.EmptyAdapter;
import com.xrigau.hnandroid.presentation.adapter.NewsAdapter;
import com.xrigau.hnandroid.util.Navigator;

public class NewsListFragment extends ListFragment implements LoaderListener<NewsResponse> {

    private static final int NEWS_LOADER_ID = 1;

    private NewsTaskLoaderCallbacks newsTaskLoaderCallbacks;
    private String currentPage;

    private View loading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_news_list, container, false);
        loading = root.findViewById(R.id.loading);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        restoreState(savedInstanceState);

        newsTaskLoaderCallbacks = new NewsTaskLoaderCallbacks(getActivity(), this);

        getLoaderManager().initLoader(NEWS_LOADER_ID, getLoaderBundle(), newsTaskLoaderCallbacks).forceLoad();
    }

    private void restoreState(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            return;
        }
        currentPage = savedInstanceState.getString(NewsTaskLoaderCallbacks.PAGE, NewsTask.FIRST_PAGE);
    }

    private Bundle getLoaderBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(NewsTaskLoaderCallbacks.PAGE, currentPage);
        return bundle;
    }

    @Override
    public void onLoadStarted() {
        startLoading();
        getListView().setAdapter(new EmptyAdapter());
    }

    private void startLoading() {
        getListView().setVisibility(View.GONE);
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoadFinished(NewsResponse response) {
        finishLoading();

        if (error(response)) {
            Toast.makeText(getActivity(), R.string.generic_error_oops, Toast.LENGTH_LONG).show();
            return;
        }

        getListView().setAdapter(new NewsAdapter(response.getNews(), LayoutInflater.from(getActivity()), getResources()));
        currentPage = response.getCurrentPage();
    }

    private boolean error(NewsResponse response) {
        return response == null;
    }

    private void finishLoading() {
        getListView().setVisibility(View.VISIBLE);
        loading.setVisibility(View.GONE);
    }

    @Override
    public void onListItemClick(ListView listView, View v, int position, long id) {
        new Navigator(getActivity()).toDetails((News) listView.getItemAtPosition(position));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(NewsTaskLoaderCallbacks.PAGE, currentPage);
    }
}
