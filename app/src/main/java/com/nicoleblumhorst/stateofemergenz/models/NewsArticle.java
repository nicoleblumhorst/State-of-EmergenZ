package com.nicoleblumhorst.stateofemergenz.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nicoleblumhorst on 1/24/16.
 */
public class NewsArticle implements Parcelable {

    String headline;
    String byline;
    String date;
    String credits;
    String articleHighlight;
    String url;

    public NewsArticle(String headline, String byline, String date, String credits, String articleHighlight, String url) {
        this.headline = headline;
        this.byline = byline;
        this.date = date;
        this.credits = credits;
        this.articleHighlight = articleHighlight;
        this.url = url;
    }

    protected NewsArticle(Parcel in) {
        headline = in.readString();
        byline = in.readString();
        date = in.readString();
        credits = in.readString();
        articleHighlight = in.readString();
        url = in.readString();
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getArticleHighlight() {
        return articleHighlight;
    }

    public void setArticleHighlight(String articleHighlight) {
        this.articleHighlight = articleHighlight;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "NewsArticle{" +
                "headline='" + headline + '\'' +
                ", byline='" + byline + '\'' +
                ", date='" + date + '\'' +
                ", credits='" + credits + '\'' +
                ", articleHighlight='" + articleHighlight + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(headline);
        dest.writeString(byline);
        dest.writeString(date);
        dest.writeString(credits);
        dest.writeString(articleHighlight);
        dest.writeString(url);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<NewsArticle> CREATOR = new Parcelable.Creator<NewsArticle>() {
        @Override
        public NewsArticle createFromParcel(Parcel in) {
            return new NewsArticle(in);
        }

        @Override
        public NewsArticle[] newArray(int size) {
            return new NewsArticle[size];
        }
    };
}
