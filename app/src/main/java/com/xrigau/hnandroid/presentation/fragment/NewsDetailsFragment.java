package com.xrigau.hnandroid.presentation.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.xrigau.hnandroid.R;
import com.xrigau.hnandroid.core.model.News;
import com.xrigau.hnandroid.core.model.Summary;
import com.xrigau.hnandroid.loader.LoaderListener;
import com.xrigau.hnandroid.loader.SummaryTaskLoaderCallbacks;
import com.xrigau.hnandroid.presentation.NewsDetailsActivity;

public class NewsDetailsFragment extends Fragment implements LoaderListener<Summary> {

    private static final int SUMMARY_LOADER_ID = 2;

    private SummaryTaskLoaderCallbacks summaryTaskLoaderCallbacks;
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

        summaryTaskLoaderCallbacks = new SummaryTaskLoaderCallbacks(getActivity(), this);

        getLoaderManager().initLoader(SUMMARY_LOADER_ID, getLoaderBundle(), summaryTaskLoaderCallbacks).forceLoad();
    }

    private void restoreState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            news = (News) savedInstanceState.getSerializable(NewsDetailsActivity.EXTRA_NEWS);
        } else if (getActivity().getIntent().hasExtra(NewsDetailsActivity.EXTRA_NEWS)) {
            news = (News) getActivity().getIntent().getSerializableExtra(NewsDetailsActivity.EXTRA_NEWS);
        }
    }

    private Bundle getLoaderBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(SummaryTaskLoaderCallbacks.URL, news.getUrl());
        return bundle;
    }

    @Override
    public void onLoadStarted() {
        startLoading();
    }

    private void startLoading() {
        summary.setVisibility(View.GONE);
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoadFinished(Summary response) {
        finishLoading();
        if (response == null) {
            Toast.makeText(getActivity(), R.string.generic_error_oops, Toast.LENGTH_LONG).show();
            return;
        }
        summary.setText(response.getDescription());
    }

    private void finishLoading() {
        summary.setVisibility(View.VISIBLE);
        loading.setVisibility(View.GONE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(NewsDetailsActivity.EXTRA_NEWS, news);
    }
}
