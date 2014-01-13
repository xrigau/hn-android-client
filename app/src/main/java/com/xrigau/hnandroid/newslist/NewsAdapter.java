package com.xrigau.hnandroid.newslist;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xrigau.hnandroid.R;
import com.xrigau.hnandroid.core.model.News;
import com.xrigau.hnandroid.core.model.NewsList;
import com.xrigau.hnandroid.util.LoadingAdapter;

class NewsAdapter extends LoadingAdapter {

    private final NewsList news;
    private final LayoutInflater inflater;
    private final Resources resources;

    NewsAdapter(NewsList news, LayoutInflater inflater, Resources resources) {
        this.news = news;
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
        return getItem(position).hashCode();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.news_list_item, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }

        ViewHolder holder = ViewHolder.from(convertView.getTag());
        News news = getItem(position);
        populateView(holder, news);

        return convertView;
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
