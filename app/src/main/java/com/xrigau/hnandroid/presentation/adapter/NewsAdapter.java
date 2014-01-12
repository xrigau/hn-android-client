package com.xrigau.hnandroid.presentation.adapter;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xrigau.hnandroid.R;
import com.xrigau.hnandroid.core.model.NewsList;
import com.xrigau.hnandroid.core.model.News;

public class NewsAdapter extends BaseAdapter {

    private final NewsList news;
    private final LayoutInflater inflater;
    private final Resources resources;

    public NewsAdapter(NewsList news, LayoutInflater inflater, Resources resources) {
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
        updateCommentsVuew(holder, news.getComments());
        holder.time.setText(news.getTimestamp());
    }

    private void updateCommentsVuew(ViewHolder holder, int comments) {
        if (comments == 0) {
            holder.comments.setVisibility(View.GONE);
            return;
        }
        int stringResId = comments == 1 ? R.string.one_comment : R.string.many_comments;
        holder.comments.setText(resources.getString(stringResId, comments));

        holder.comments.setVisibility(View.VISIBLE);
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
