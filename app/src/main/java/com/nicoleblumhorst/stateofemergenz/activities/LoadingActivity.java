package com.nicoleblumhorst.stateofemergenz.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.nicoleblumhorst.stateofemergenz.R;
import com.nicoleblumhorst.stateofemergenz.ZApplication;
import com.nicoleblumhorst.stateofemergenz.models.ZombieNews;
import com.nicoleblumhorst.stateofemergenz.services.NewsService;
import com.nicoleblumhorst.stateofemergenz.utils.ThreatLevel;
import com.nicoleblumhorst.stateofemergenz.utils.ThreatLevelUtil;

import org.w3c.dom.Text;

import javax.xml.transform.Templates;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by nicoleblumhorst on 1/24/16.
 */
public class LoadingActivity extends AppCompatActivity implements Callback<ZombieNews> {

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

    private void callService() {
        loadingTextView.setVisibility(View.VISIBLE);
        retryButton.setVisibility(View.GONE);

        Call<ZombieNews> call = mNewsService.getNews();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Response<ZombieNews> response) {
        ZombieNews zombieNews = response.body();
        if (zombieNews == null) {
            Log.i(this.getClass().getSimpleName(), "There was a problem calling the service");
            loadingTextView.setVisibility(View.GONE);
            retryButton.setVisibility(View.VISIBLE);
            return;
        }
        Log.i(this.getClass().getSimpleName(), "Got a successful response: " + zombieNews.toString());

        getZApplication().setZombieNews(zombieNews);
        getZApplication().setThreatLevel(ThreatLevelUtil.getThreatLevelFromKey(zombieNews.getThreatLevel()));

        Intent intent = new Intent(this, ThreatLevelActivity.class);
        startActivity(intent);
    }

    @Override
    public void onFailure(Throwable t) {
        Log.i(this.getClass().getSimpleName(), "Got a bad response: " + t.getLocalizedMessage());

        loadingTextView.setVisibility(View.GONE);
        retryButton.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.la_retry_button)
    public void retryButtonOnClick() {
        callService();
    }

    public ZApplication getZApplication() {
        return (ZApplication) getApplication();
    }

}
