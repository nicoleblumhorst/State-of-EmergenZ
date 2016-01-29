package com.nicoleblumhorst.stateofemergenz.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;

import com.nicoleblumhorst.stateofemergenz.R;
import com.nicoleblumhorst.stateofemergenz.ZApplication;
import com.nicoleblumhorst.stateofemergenz.fragments.AboutFragment;
import com.nicoleblumhorst.stateofemergenz.fragments.BaseFragment;
import com.nicoleblumhorst.stateofemergenz.fragments.NewsFragment;
import com.nicoleblumhorst.stateofemergenz.fragments.ThreatLevelFragment;
import com.nicoleblumhorst.stateofemergenz.utils.ThreatLevel;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ThreatLevelActivity extends AppCompatActivity
        implements BaseFragment.BaseFragmentListener,
                   NavigationView.OnNavigationItemSelectedListener {

    private static final String NAV_DRAWER_SELECTED_ID = "NAV_DRAWER_SELECTED_ID";

    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Bind(R.id.navigation_view)
    NavigationView navigationView;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private int mNavDrawerSelectedId;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(getThreatLevel().getTheme());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, getThreatLevel().getColorDark()));
        }

        setContentView(R.layout.activity_threat_level);
        ButterKnife.bind(this);

        if (savedInstanceState != null)
            mNavDrawerSelectedId = savedInstanceState.getInt(NAV_DRAWER_SELECTED_ID, R.id.threat_level_menu_item);
        else
            mNavDrawerSelectedId = R.id.threat_level_menu_item;

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        navigationView.setNavigationItemSelectedListener(this);

        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);

        drawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        showFragment(mNavDrawerSelectedId);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);
        mNavDrawerSelectedId = menuItem.getItemId();
        drawerLayout.closeDrawer(GravityCompat.START);
        showFragment(menuItem.getItemId());
        return true;
    }

    private void showFragment(int fragmentId) {
        Fragment fragment = null;
        switch (fragmentId) {
            case R.id.threat_level_menu_item:
                fragment = ThreatLevelFragment.newInstance(getThreatLevel());
                break;
            case R.id.news_menu_item:
                fragment = NewsFragment.newInstance(getZApplication().getZombieNews().getStories());
                break;
            case R.id.about_menu_item:
                fragment = AboutFragment.newInstance();
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(NAV_DRAWER_SELECTED_ID, mNavDrawerSelectedId);
    }

    public ZApplication getZApplication() {
        return (ZApplication) getApplication();
    }

    public ThreatLevel getThreatLevel() {
        ThreatLevel level = getZApplication().getThreatLevel();

        if (level == null) {
            Intent intent = new Intent(this, LoadingActivity.class);
            startActivity(intent);
        }

        return level;
    }

    @Override
    public void showNews() {
        mNavDrawerSelectedId = R.id.news_menu_item;
        navigationView.setCheckedItem(mNavDrawerSelectedId);
        showFragment(mNavDrawerSelectedId);
    }

    @Override
    public void showLevel() {
        mNavDrawerSelectedId = R.id.threat_level_menu_item;
        navigationView.setCheckedItem(mNavDrawerSelectedId);
        showFragment(mNavDrawerSelectedId);
    }

    @Override
    public void showInfoDialog() {

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(getLayoutInflater().inflate(R.layout.fragment_threat_level_info_dialog, null))
                .setNegativeButton(R.string.close,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        }
                )
                .create();
        dialog.show();
    }

}
