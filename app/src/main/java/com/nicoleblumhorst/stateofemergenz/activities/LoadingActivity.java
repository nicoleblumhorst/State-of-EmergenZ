package com.nicoleblumhorst.stateofemergenz.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.nicoleblumhorst.stateofemergenz.R;
import com.nicoleblumhorst.stateofemergenz.ZApplication;
import com.nicoleblumhorst.stateofemergenz.models.ZombieNews;
import com.nicoleblumhorst.stateofemergenz.services.NewsService;
import com.nicoleblumhorst.stateofemergenz.services.NewsServiceSuccessEvent;
import com.nicoleblumhorst.stateofemergenz.utils.ThreatLevelUtil;
import com.squareup.otto.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by nicoleblumhorst on 1/24/16.
 */
public class LoadingActivity extends AppCompatActivity {

    @Bind(R.id.la_loading_text_view)
    TextView loadingTextView;

    @Bind(R.id.la_retry_button)
    TextView retryButton;

    private Retrofit mRetrofit;
    private NewsService mNewsService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        ButterKnife.bind(this);

        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://nicoleblumhorst.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mNewsService = mRetrofit.create(NewsService.class);

        callService();
    }

    @Override
    public void onResume() {
        super.onResume();
        getZApplication().getBus().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getZApplication().getBus().unregister(this);
    }

    private void callService() {
        loadingTextView.setVisibility(View.VISIBLE);
        retryButton.setVisibility(View.GONE);

        Call<ZombieNews> call = mNewsService.getNews();
        call.enqueue(new Callback<ZombieNews>() {
            @Override
            public void onResponse(Response<ZombieNews> response) {
                getZApplication().getBus().post(new NewsServiceSuccessEvent(response.body()));
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(this.getClass().getSimpleName(), " Service Failure. Error message was: " + t);
                onFailure(t);
            }
        });
    }

    public void onFailure(Throwable t) {
        Log.i(this.getClass().getSimpleName(), "Got a bad response: " + t.getLocalizedMessage());

        loadingTextView.setVisibility(View.GONE);
        retryButton.setVisibility(View.VISIBLE);
    }

    @Subscribe
    public void onSuccessServiceEvent(NewsServiceSuccessEvent event) {
        ZombieNews zombieNews = event.getmZombieNews();
        Log.i(this.getClass().getSimpleName(), "Got a successful response: " + zombieNews.toString());

        getZApplication().setZombieNews(zombieNews);
        getZApplication().setThreatLevel(ThreatLevelUtil.getThreatLevelFromKey(zombieNews.getThreatLevel()));

        Intent intent = new Intent(this, ThreatLevelActivity.class);
        startActivity(intent);
    }


    @OnClick(R.id.la_retry_button)
    public void retryButtonOnClick() {
        callService();
    }

    public ZApplication getZApplication() {
        return (ZApplication) getApplication();
    }

}
