package com.xrigau.hnandroid.newslist;

import android.app.Fragment;
import android.os.Bundle;
import android.view.*;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Toast;

import com.novoda.notils.logger.simple.Log;
import com.xrigau.hnandroid.R;
import com.xrigau.hnandroid.core.model.News;
import com.xrigau.hnandroid.core.model.NewsResponse;
import com.xrigau.hnandroid.util.OnNextPageRequestedListener;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.xrigau.hnandroid.core.task.TaskFactory.fetchNews;
import static com.xrigau.hnandroid.util.Navigator.navigate;

public class NewsListFragment extends Fragment implements AdapterView.OnItemClickListener {

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
        setUpList();
        showLoading();

        if (savedInstanceState != null) {
            restoreState(savedInstanceState);
            loadPage(fetchNews(currentPage)); // TODO: Not working properly
        } else {
            loadPage(fetchNews());
        }
    }

    private void restoreState(Bundle savedInstanceState) {
        currentPage = savedInstanceState.getString(CURRENT_PAGE_KEY);
        nextPage = savedInstanceState.getString(NEXT_PAGE_KEY);
    }

    private void setUpList() {
        newsAdapter = new NewsAdapter(LayoutInflater.from(getActivity()), getResources());
        newsAdapter.setOnNextPageRequestedListener(new OnNextPageRequestedListener() { // TODO: Should be an Observer
            @Override
            public void onNextPageRequested() {
                loadPage(fetchNews(nextPage));
            }
        });
        list.setAdapter(newsAdapter);
    }

    private void loadPage(Observable<NewsResponse> newsObservable) {
        newsObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsResponse>() {
                    @Override
                    public void onNext(NewsResponse newsResponse) {
                        newsAdapter.addNews(newsResponse.getNews());
                        nextPage = newsResponse.getNextPage();
                        currentPage = newsResponse.getCurrentPage();
                    }

                    @Override
                    public void onCompleted() {
                        hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideLoading();
                        toast(R.string.generic_error_oops);
                        log("Error downloading news list", e);
                    }
                });
    }

    private void showLoading() {
        list.setVisibility(View.GONE);
        loading.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        list.setVisibility(View.VISIBLE);
        loading.setVisibility(View.GONE);
    }

    private void toast(int resourceId) {
        Toast.makeText(getActivity(), resourceId, Toast.LENGTH_SHORT).show();
    }

    private void log(String message, Throwable error) {
        Log.e(message, error);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        navigate(getActivity()).toDetails((News) list.getItemAtPosition(position));
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
        setUpList();
        showLoading();

        loadPage(fetchNews());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(CURRENT_PAGE_KEY, currentPage);
        outState.putString(NEXT_PAGE_KEY, nextPage);
    }
}
