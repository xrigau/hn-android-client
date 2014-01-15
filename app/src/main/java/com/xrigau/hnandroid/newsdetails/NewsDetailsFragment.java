package com.xrigau.hnandroid.newsdetails;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.*;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.TextView;

import com.commonsware.cwac.anddown.AndDown;
import com.novoda.imageloader.NovodaImageLoader;
import com.xrigau.hnandroid.HNFragment;
import com.xrigau.hnandroid.R;
import com.xrigau.hnandroid.core.model.News;
import com.xrigau.hnandroid.core.model.Summary;
import com.xrigau.hnandroid.task.DetachableTaskListener;
import com.xrigau.hnandroid.task.TaskResult;

import static com.xrigau.hnandroid.core.task.TaskFactory.summaryTask;

public class NewsDetailsFragment extends HNFragment implements DetachableTaskListener<Summary> {

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
                .putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_news_subject))
                .putExtra(Intent.EXTRA_TEXT, news.getTitle() + " – " + news.getUrl());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.browser:
                navigate().toExternalAndroidBrowser(news.getUrl());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_news_details, container, false);
        findViews(root);
        return root;
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

        startLoading();
        getActivity().getActionBar().setTitle(news.getTitle());
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
        content.setVisibility(View.GONE);
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
        displaySummary(response);
    }

    private boolean error(TaskResult taskResult) {
        return taskResult.result == null && taskResult.error != null;
    }

    private void displaySummary(Summary response) {
        new NovodaImageLoader.Builder(getActivity()).build().load(response.getImage(), image);
        title.setText(getBestTitle(response));
        setUpMainText(response);
    }

    private void setUpMainText(Summary response) {
        String markdownText = getBestContent(response);
        String htmlText = new AndDown().markdownToHtml(markdownText);
        text.setText(Html.fromHtml(htmlText));
    }

    private String getBestTitle(Summary response) {
        String name = response.getName();
        return TextUtils.isEmpty(name) ? news.getTitle() : name;
    }

    private String getBestContent(Summary response) {
        String text = response.getText();
        String content = TextUtils.isEmpty(text) ? response.getDescription() : text;
        return content == null ? "" : content;
    }

    private void finishLoading() {
        content.setVisibility(View.VISIBLE);
        loading.setVisibility(View.GONE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(NEWS_KEY, news);
    }
}
