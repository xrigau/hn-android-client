package com.xrigau.hnandroid.presentation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;

import com.xrigau.hnandroid.R;
import com.xrigau.hnandroid.core.model.News;
import com.xrigau.hnandroid.core.model.NewsResponse;
import com.xrigau.hnandroid.presentation.adapter.EmptyAdapter;
import com.xrigau.hnandroid.presentation.adapter.NewsAdapter;
import com.xrigau.hnandroid.util.Navigator;

import static com.xrigau.hnandroid.core.task.TaskFactory.newsTask;

public class NewsListFragment extends HNFragment implements DetachableTaskListener<NewsResponse>, AdapterView.OnItemClickListener {

    private static final String CURRENT_PAGE_KEY = "com.xrigau.hnandroid.CURRENT_PAGE_KEY";
    private static final String NEXT_PAGE_KEY = "com.xrigau.hnandroid.NEXT_PAGE_KEY";

    private String currentPage;
    private String nextPage;

    private View loading;
    private AbsListView list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_news_list, container, false);
        findViews(root);
        list.setOnItemClickListener(this);
        return root;
    }

    private void findViews(View root) {
        loading = root.findViewById(R.id.loading);
        list = (AbsListView) root.findViewById(R.id.list);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        restoreState(savedInstanceState);

        list.setAdapter(new EmptyAdapter());
        startLoading();
        if (savedInstanceState != null) {
            execute(newsTask(currentPage), this);
            return;
        }
        execute(newsTask(nextPage), this);
    }

    private void restoreState(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            return;
        }
        currentPage = savedInstanceState.getString(CURRENT_PAGE_KEY);
        nextPage = savedInstanceState.getString(NEXT_PAGE_KEY);
    }

    private void startLoading() {
        list.setVisibility(View.GONE);
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean isAttached() {
        return isAdded() && !isDetached();
    }

    @Override
    public void onLoadFinished(NewsResponse response, Throwable error) {
        finishLoading();
        if (error(response)) {
            log(error);
            toast(R.string.generic_error_oops);
            return;
        }

        list.setAdapter(new NewsAdapter(response.getNews(), LayoutInflater.from(getActivity()), getResources()));
        currentPage = response.getCurrentPage();
        nextPage = response.getNextPage();
    }

    private boolean error(NewsResponse response) {
        return response == null;
    }

    private void finishLoading() {
        list.setVisibility(View.VISIBLE);
        loading.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        new Navigator(getActivity()).toDetails((News) list.getItemAtPosition(position));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(CURRENT_PAGE_KEY, currentPage);
        outState.putString(NEXT_PAGE_KEY, nextPage);
    }
}
