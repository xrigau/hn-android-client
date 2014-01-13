package com.xrigau.hnandroid.newsdetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xrigau.hnandroid.R;
import com.xrigau.hnandroid.core.model.News;
import com.xrigau.hnandroid.core.model.Summary;
import com.xrigau.hnandroid.HNFragment;
import com.xrigau.hnandroid.task.DetachableTaskListener;
import com.xrigau.hnandroid.task.TaskResult;

import static com.xrigau.hnandroid.core.task.TaskFactory.summaryTask;

public class NewsDetailsFragment extends HNFragment implements DetachableTaskListener<Summary> {

    private static final String NEWS_KEY = "com.xrigau.hnandroid.NEWS_KEY";

    private News news;

    private TextView summary;
    private View loading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_news_summary, container, false);
        findViews(root);
        return root;
    }

    private void findViews(View root) {
        summary = (TextView) root.findViewById(R.id.summary);
        loading = root.findViewById(R.id.loading);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        restoreState(savedInstanceState);

        startLoading();
        execute(summaryTask(news.getUrl()), this);
    }

    private void restoreState(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey(NEWS_KEY)) {
            news = (News) savedInstanceState.getSerializable(NEWS_KEY);
        } else if (getActivity().getIntent().hasExtra(NewsDetailsActivity.EXTRA_NEWS)) {
            news = (News) getActivity().getIntent().getSerializableExtra(NewsDetailsActivity.EXTRA_NEWS);
        }
    }

    private void startLoading() {
        summary.setVisibility(View.GONE);
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean isAttached() {
        return isAdded() && !isDetached();
    }

    @Override
    public void onLoadFinished(TaskResult<Summary> taskResult) {
        finishLoading();
        if (error(taskResult)) {
            log(taskResult.error);
            toast(R.string.generic_error_oops);
            return;
        }
        Summary response = taskResult.result;
        summary.setText(response.getDescription());
    }

    private void finishLoading() {
        summary.setVisibility(View.VISIBLE);
        loading.setVisibility(View.GONE);
    }

    private boolean error(TaskResult taskResult) {
        return taskResult.result == null && taskResult.error != null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(NEWS_KEY, news);
    }
}
