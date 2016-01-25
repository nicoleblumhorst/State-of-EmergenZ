package com.nicoleblumhorst.stateofemergenz.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.nicoleblumhorst.stateofemergenz.R;
import com.nicoleblumhorst.stateofemergenz.adapters.NewsArticleAdapter;
import com.nicoleblumhorst.stateofemergenz.models.NewsArticle;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewsFragment extends BaseFragment {

    public static final String NEWS_ARTICLES_KEY = "NEWS_ARTICLES_KEY";

    @Bind(R.id.nf_news_list_view)
    ListView newsArticlesListView;

    private List<NewsArticle> mNewsArticles;
    private NewsArticleAdapter mAdapter;

    public static NewsFragment newInstance(ArrayList<NewsArticle> newsArticles) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(NEWS_ARTICLES_KEY, newsArticles);

        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);

        return fragment;
    }

    public NewsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, view);

        if (getArguments() != null)
            mNewsArticles = (ArrayList<NewsArticle>) getArguments().getSerializable(NEWS_ARTICLES_KEY);
        else if (savedInstanceState != null)
            mNewsArticles = (ArrayList<NewsArticle>) savedInstanceState.getSerializable(NEWS_ARTICLES_KEY);
        else
            mNewsArticles = new ArrayList<NewsArticle>();

        mAdapter = new NewsArticleAdapter(getContext(), mNewsArticles);
        newsArticlesListView.setAdapter(mAdapter);
        return view;
    }
}
