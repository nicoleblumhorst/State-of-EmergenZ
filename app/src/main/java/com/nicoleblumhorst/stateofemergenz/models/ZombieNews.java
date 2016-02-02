package com.nicoleblumhorst.stateofemergenz.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by nicoleblumhorst on 1/24/16.
 */
public class ZombieNews implements Parcelable {

    String threatLevel;
    ArrayList<NewsArticle> stories;

    public ZombieNews(String threatLevel, ArrayList<NewsArticle> stories) {
        this.threatLevel = threatLevel;
        this.stories = stories;
    }

    protected ZombieNews(Parcel in) {
        threatLevel = in.readString();
        if (in.readByte() == 0x01) {
            stories = new ArrayList<NewsArticle>();
            in.readList(stories, NewsArticle.class.getClassLoader());
        } else {
            stories = null;
        }
    }

    public String getThreatLevel() {
        return threatLevel;
    }

    public void setThreatLevel(String threatLevel) {
        this.threatLevel = threatLevel;
    }

    public ArrayList<NewsArticle> getStories() {
        return stories;
    }

    public void setStories(ArrayList<NewsArticle> stories) {
        this.stories = stories;
    }

    @Override
    public String toString() {
        return "ZombieNews{" +
                "threatLevel='" + threatLevel + '\'' +
                ", stories=" + stories +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(threatLevel);
        if (stories == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(stories);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ZombieNews> CREATOR = new Parcelable.Creator<ZombieNews>() {
        @Override
        public ZombieNews createFromParcel(Parcel in) {
            return new ZombieNews(in);
        }

        @Override
        public ZombieNews[] newArray(int size) {
            return new ZombieNews[size];
        }
    };
}
