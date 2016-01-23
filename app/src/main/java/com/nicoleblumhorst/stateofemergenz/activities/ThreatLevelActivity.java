package com.nicoleblumhorst.stateofemergenz.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nicoleblumhorst.stateofemergenz.R;
import com.nicoleblumhorst.stateofemergenz.ZApplication;
import com.nicoleblumhorst.stateofemergenz.adapters.NavDrawerAdapter;
import com.nicoleblumhorst.stateofemergenz.fragments.AboutFragment;
import com.nicoleblumhorst.stateofemergenz.fragments.BaseFragment;
import com.nicoleblumhorst.stateofemergenz.fragments.NewsFragment;
import com.nicoleblumhorst.stateofemergenz.fragments.ThreatLevelFragment;
import com.nicoleblumhorst.stateofemergenz.models.NavDrawerItem;
import com.nicoleblumhorst.stateofemergenz.utils.ThreatLevel;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ThreatLevelActivity extends AppCompatActivity
        implements BaseFragment.BaseFragmentListener,
                   NavigationView.OnNavigationItemSelectedListener {

    private static final String NAV_DRAWER_SELECTED_ID = "NAV_DRAWER_SELECTED_ID";

    @Bind(R.id.drawer_layout)
    public DrawerLayout drawerLayout;

    @Bind(R.id.navigation_view)
    NavigationView navigationView;

    @Bind(R.id.toolbar)
    public Toolbar toolbar;

    private int mNavDrawerSelectedId;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);

        if (savedInstanceState != null)
            mNavDrawerSelectedId = savedInstanceState.getInt(NAV_DRAWER_SELECTED_ID, R.id.threat_level_menu_item);
        else
            mNavDrawerSelectedId = R.id.threat_level_menu_item;

        // TODO: Set this correctly
        setThreatLevel(ThreatLevel.LOW);

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
                fragment = NewsFragment.newInstance();
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

    public void setThreatLevel(ThreatLevel level) {
        getZApplication().setThreatLevel(level);
    }

    public ThreatLevel getThreatLevel() {
        return getZApplication().getThreatLevel();
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

}
