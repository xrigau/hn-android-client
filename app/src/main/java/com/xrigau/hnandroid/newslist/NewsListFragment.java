package com.xrigau.hnandroid.newslist;

import android.os.Bundle;
import android.view.*;
import android.widget.AbsListView;
import android.widget.AdapterView;

import com.xrigau.hnandroid.HNFragment;
import com.xrigau.hnandroid.R;
import com.xrigau.hnandroid.core.model.News;
import com.xrigau.hnandroid.core.model.NewsResponse;
import com.xrigau.hnandroid.task.TaskListener;
import com.xrigau.hnandroid.task.TaskResult;
import com.xrigau.hnandroid.util.OnNextPageRequestedListener;

import static com.xrigau.hnandroid.core.task.TaskFactory.newsTask;

public class NewsListFragment extends HNFragment implements TaskListener<NewsResponse>, AdapterView.OnItemClickListener {

    private static final String CURRENT_PAGE_KEY = "com.xrigau.hnandroid.CURRENT_PAGE_KEY";
    private static final String NEXT_PAGE_KEY = "com.xrigau.hnandroid.NEXT_PAGE_KEY";

    private String currentPage;
    private String nextPage;

    private View loading;
    private AbsListView list;
    private NewsAdapter newsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_news_list, container, false);
        findViews(root);
        list.setOnItemClickListener(this);
        setHasOptionsMenu(true);
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

        setUpList();
        startLoading();
        if (savedInstanceState != null) {
            execute(newsTask(currentPage));
            return;
        }
        loadNextPage();
    }

    private void restoreState(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            return;
        }
        currentPage = savedInstanceState.getString(CURRENT_PAGE_KEY);
        nextPage = savedInstanceState.getString(NEXT_PAGE_KEY);
    }

    private void setUpList() {
        newsAdapter = new NewsAdapter(LayoutInflater.from(getActivity()), getResources());
        newsAdapter.setOnNextPageRequestedListener(new OnNextPageRequestedListener() {
            @Override
            public void onNextPageRequested() {
                loadNextPage();
            }
        });
        list.setAdapter(newsAdapter);
    }

    private void loadNextPage() {
        execute(newsTask(nextPage));
    }

    private void startLoading() {
        list.setVisibility(View.GONE);
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoadFinished(TaskResult<NewsResponse> taskResult) {
        finishLoading();
        if (error(taskResult)) {
            log(taskResult.error);
            toast(R.string.generic_error_oops);
            return;
        }

        NewsResponse response = taskResult.result;
        newsAdapter.addNews(response.getNews());
        currentPage = response.getCurrentPage();
        nextPage = response.getNextPage();
    }

    private boolean error(TaskResult taskResult) {
        return taskResult.result == null && taskResult.error != null;
    }

    private void finishLoading() {
        list.setVisibility(View.VISIBLE);
        loading.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        navigate().toDetails((News) list.getItemAtPosition(position));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.activity_news_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.refresh) {
            reload();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void reload() {
        clearCache();
        execute(newsTask());
        setUpList();
        startLoading();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(CURRENT_PAGE_KEY, currentPage);
        outState.putString(NEXT_PAGE_KEY, nextPage);
    }
}
