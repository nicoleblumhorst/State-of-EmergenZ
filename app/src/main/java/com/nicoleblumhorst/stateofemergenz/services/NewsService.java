package com.nicoleblumhorst.stateofemergenz.services;

import com.nicoleblumhorst.stateofemergenz.models.ZombieNews;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by nicoleblumhorst on 1/24/16.
 */
public interface NewsService {

    @GET("resources/news.json")
    Call<ZombieNews> getNews();

}
