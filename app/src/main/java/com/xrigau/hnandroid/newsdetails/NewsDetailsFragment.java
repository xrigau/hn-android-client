package com.xrigau.hnandroid.newsdetails;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import com.novoda.imageloader.NovodaImageLoader;
import com.novoda.notils.logger.simple.Log;
import com.xrigau.hnandroid.R;
import com.xrigau.hnandroid.core.model.News;
import com.xrigau.hnandroid.core.model.Summary;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.xrigau.hnandroid.core.task.TaskFactory.fetchSummary;
import static com.xrigau.hnandroid.util.Navigator.navigate;

public class NewsDetailsFragment extends Fragment {

    private static final String NEWS_KEY = "com.xrigau.hnandroid.NEWS_KEY";

    private News news;

    private ImageView image;
    private TextView title;
    private TextView text;
    private View content;
    private View loading;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.activity_news_details, menu);
        setUpShareActionProvider(menu);
    }

    private void setUpShareActionProvider(Menu menu) {
        MenuItem shareItem = menu.findItem(R.id.share);
        ShareActionProvider actionProvider = (ShareActionProvider) shareItem.getActionProvider();
        actionProvider.setShareIntent(getShareIntent());
    }

    private Intent getShareIntent() {
        return new Intent(Intent.ACTION_SEND)
                .setType("text/plain")
                .putExtra(Intent.EXTRA_SUBJECT, news.getTitle())
                .putExtra(Intent.EXTRA_TEXT, getString(R.string.share_news_content, news.getUrl()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.browser:
                navigate(getActivity()).toExternalAndroidBrowser(news.getUrl());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_details, container, false);
    }

    @Override
    public void onViewCreated(View view, final Bundle savedInstanceState) {
        findViews(view);
    }

    private void findViews(View root) {
        image = (ImageView) root.findViewById(R.id.image);
        title = (TextView) root.findViewById(R.id.title);
        text = (TextView) root.findViewById(R.id.text);
        content = root.findViewById(R.id.content);
        loading = root.findViewById(R.id.loading);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        restoreState(savedInstanceState);

        getActivity().getActionBar().setTitle(news.getTitle());
        showLoading();

        loadSummary();
    }

    private void restoreState(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey(NEWS_KEY)) {
            news = (News) savedInstanceState.getSerializable(NEWS_KEY);
        } else if (getActivity().getIntent().hasExtra(NewsDetailsActivity.EXTRA_NEWS)) {
            news = (News) getActivity().getIntent().getSerializableExtra(NewsDetailsActivity.EXTRA_NEWS);
        }
    }

    private void loadSummary() {
        fetchSummary(news.getUrl())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Summary>() {
                    @Override
                    public void onNext(Summary summary) {
                        displaySummary(summary);
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
        content.setVisibility(View.GONE);
        loading.setVisibility(View.VISIBLE);
    }

    private void displaySummary(Summary response) {
        new NovodaImageLoader.Builder(getActivity()).build().load(response.getImage(), image);
        title.setText(response.getTitle());
        setUpMainText(response);
    }

    private void setUpMainText(Summary response) {
        SummaryParser.from(response).into(text);
    }

    private void hideLoading() {
        content.setVisibility(View.VISIBLE);
        loading.setVisibility(View.GONE);
    }

    private void toast(int resourceId) {
        Toast.makeText(getActivity(), resourceId, Toast.LENGTH_SHORT).show();
    }

    private void log(String message, Throwable error) {
        Log.e(message, error);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(NEWS_KEY, news);
    }
}
