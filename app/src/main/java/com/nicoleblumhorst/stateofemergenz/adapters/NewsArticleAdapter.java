package com.nicoleblumhorst.stateofemergenz.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nicoleblumhorst.stateofemergenz.R;
import com.nicoleblumhorst.stateofemergenz.models.NewsArticle;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nicoleblumhorst on 1/23/16.
 */
public class NewsArticleAdapter extends ArrayAdapter<NewsArticle> {

    private LayoutInflater mInflater;

    public NewsArticleAdapter(Context mContext, List<NewsArticle> data) {
        super(mContext, R.layout.card_view_news_article, data);
        mInflater = LayoutInflater.from(getContext());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        NewsArticle newsArticle = (NewsArticle) getItem(position);
        ViewHolder holder;

        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = mInflater.inflate(R.layout.card_view_news_article, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder.headlineTextView.setText(newsArticle.getHeadline());
        holder.highlightTextView.setText(newsArticle.getArticleHighlight());

        if (!TextUtils.isEmpty(newsArticle.getByline()))
            holder.bylineTextView.setText("By: " + newsArticle.getByline());
        else
            holder.bylineTextView.setVisibility(View.GONE);

        return convertView;
    }

    static class ViewHolder {

        @Bind(R.id.cvna_headline)
        TextView headlineTextView;

        @Bind(R.id.cvna_highlight)
        TextView highlightTextView;

        @Bind(R.id.cvna_byline)
        TextView bylineTextView;

        public ViewHolder(View view) {
            ButterKnife.bind(ViewHolder.this, view);
        }

    }

}
