package com.xrigau.hnandroid.newslist;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xrigau.hnandroid.R;
import com.xrigau.hnandroid.core.model.News;
import com.xrigau.hnandroid.core.model.NewsList;
import com.xrigau.hnandroid.core.model.NewsResponse;
import com.xrigau.hnandroid.util.LoadingAdapter;

import rx.functions.Action1;
import rx.observers.Observers;

class NewsAdapter extends LoadingAdapter<String> {

    private final NewsList news = new NewsList();
    private final LayoutInflater inflater;
    private final Resources resources;

    private String currentPage;
    private String nextPage;

    NewsAdapter(LayoutInflater inflater, Resources resources, Action1<? super String> observer) {
        super(Observers.create(observer));
        this.inflater = inflater;
        this.resources = resources;
    }

    @Override
    public int getCount() {
        return news.size();
    }

    @Override
    public News getItem(int position) {
        return news.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    void addNews(NewsResponse newsResponse) {
        news.addAll(newsResponse.getNews());
        currentPage = newsResponse.getCurrentPage();
        nextPage = newsResponse.getNextPage();
        notifyDataSetChanged();
    }

    @Override
    public View createView(int position, ViewGroup parent) {
        View convertView = inflater.inflate(R.layout.news_list_item, parent, false);
        convertView.setTag(new ViewHolder(convertView));
        return convertView;
    }

    @Override
    public View setUpView(View view, int position) {
        ViewHolder holder = ViewHolder.from(view.getTag());
        News news = getItem(position);
        populateView(holder, news);
        return view;
    }

    private void populateView(ViewHolder holder, News news) {
        holder.title.setText(news.getTitle());
        holder.domain.setText(news.getDomain());
        updateCommentsView(holder, news.getComments());
        holder.time.setText(news.getTimestamp());
    }

    private void updateCommentsView(ViewHolder holder, int comments) {
        String formatted = resources.getQuantityString(R.plurals.comments, comments, comments);
        holder.comments.setText(formatted);
    }

    @Override
    protected String getNextPage() {
        return nextPage;
    }

    public NewsResponse asNewsResponse() {
        return new NewsResponse(news, currentPage, nextPage);
    }

    private static class ViewHolder {

        private final TextView title;
        private final TextView domain;
        private final TextView comments;
        private final TextView time;

        public ViewHolder(View view) {
            title = (TextView) view.findViewById(R.id.title);
            domain = (TextView) view.findViewById(R.id.domain);
            comments = (TextView) view.findViewById(R.id.comments);
            time = (TextView) view.findViewById(R.id.time);
        }

        public static ViewHolder from(Object tag) {
            return (ViewHolder) tag;
        }
    }
}
