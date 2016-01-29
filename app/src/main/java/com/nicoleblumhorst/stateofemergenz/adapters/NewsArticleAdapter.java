package com.nicoleblumhorst.stateofemergenz.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nicoleblumhorst.stateofemergenz.R;
import com.nicoleblumhorst.stateofemergenz.models.NewsArticle;
import com.nicoleblumhorst.stateofemergenz.utils.ThreatLevel;
import com.nicoleblumhorst.stateofemergenz.utils.ThreatLevelUtil;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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

        final NewsArticle newsArticle = (NewsArticle) getItem(position);
        ViewHolder holder;

        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = mInflater.inflate(R.layout.card_view_news_article, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        if (!TextUtils.isEmpty(newsArticle.getThreatLevel())) {
            ThreatLevel threatLevel = ThreatLevelUtil.getThreatLevelFromKey(newsArticle.getThreatLevel());
            holder.levelColorView.setBackgroundResource(threatLevel.getColor());

        } else {
            holder.levelColorView.setBackgroundResource(ThreatLevel.LOW.getColor());
        }

        holder.headlineTextView.setText(newsArticle.getHeadline());
        holder.highlightTextView.setText(newsArticle.getArticleHighlight());

        if (!TextUtils.isEmpty(newsArticle.getByline()))
            holder.bylineTextView.setText("By: " + newsArticle.getByline());
        else
            holder.bylineTextView.setVisibility(View.GONE);

        DateTime dateTime = new DateTime(newsArticle.getDate());
        if (dateTime != null)
            holder.dateTextView.setText(dateTime.toString(DateTimeFormat.forPattern("MMM dd, yyyy")));
        else
            holder.dateTextView.setVisibility(View.GONE);

        holder.linkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(newsArticle.getUrl()));
                getContext().startActivity(browserIntent);
            }
        });

        return convertView;
    }

    static class ViewHolder {

        @Bind(R.id.cvna_level_color)
        View levelColorView;

        @Bind(R.id.cvna_headline)
        TextView headlineTextView;

        @Bind(R.id.cvna_highlight)
        TextView highlightTextView;

        @Bind(R.id.cvna_byline)
        TextView bylineTextView;

        @Bind(R.id.cvna_date)
        TextView dateTextView;

        @Bind(R.id.cvna_link)
        Button linkButton;

        public ViewHolder(View view) {
            ButterKnife.bind(ViewHolder.this, view);
        }

    }

}
