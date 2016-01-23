package com.nicoleblumhorst.stateofemergenz.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nicoleblumhorst.stateofemergenz.R;
import com.nicoleblumhorst.stateofemergenz.ZApplication;
import com.nicoleblumhorst.stateofemergenz.adapters.NavDrawerAdapter;
import com.nicoleblumhorst.stateofemergenz.fragments.AboutFragment;
import com.nicoleblumhorst.stateofemergenz.fragments.BaseFragment;
import com.nicoleblumhorst.stateofemergenz.fragments.NewsFragment;
import com.nicoleblumhorst.stateofemergenz.fragments.WarningLevelFragment;
import com.nicoleblumhorst.stateofemergenz.models.NavDrawerItem;
import com.nicoleblumhorst.stateofemergenz.utils.WarningLevel;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class WarningLevelActivity extends AppCompatActivity
        implements BaseFragment.BaseFragmentListener {

    @Bind(R.id.drawer_layout)
    public DrawerLayout drawerLayout;

    @Bind(R.id.left_drawer)
    public ListView drawerList;

    @Bind(R.id.toolbar)
    public Toolbar toolbar;

    private NavDrawerAdapter mAdapter;
    private String[] mNavDrawerItems;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mNavDrawerItems = getResources().getStringArray(R.array.nav_drawer_items);
//        drawerList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mNavDrawerItems));

        mAdapter = new NavDrawerAdapter(this, R.layout.drawer_list_item, createNavDrawerList());
        drawerList.setAdapter(mAdapter);

        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });

        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                this.syncState();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                this.syncState();
            }
        };
        drawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

    }

    private ArrayList<NavDrawerItem> createNavDrawerList() {
        ArrayList<NavDrawerItem> navDrawerItems = new ArrayList<>();

        navDrawerItems.add(new NavDrawerItem(null, getString(R.string.nav_drawer_threat_level)));
        navDrawerItems.add(new NavDrawerItem(null, getString(R.string.nav_drawer_news)));
        navDrawerItems.add(new NavDrawerItem(null, getString(R.string.nav_drawer_app_info)));

        return navDrawerItems;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("***", "onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("***", "onResume()");
    }

    private void selectItem(int position) {

        Fragment fragment = WarningLevelFragment.newInstance(getWarningLevel());

//        switch (position) {
//            case 0:
//                fragment = WarningLevelFragment.newInstance(getWarningLevel());
//                break;
//            case 1:
//                fragment = NewsFragment.newInstance();
//                break;
//            case 2:
//                fragment = AboutFragment.newInstance();
//                break;
//
//            default:
//                break;
//        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                           .replace(R.id.container, fragment)
                           .commit();

            drawerList.setItemChecked(position, true);
//            drawerList.setSelection(position);
            drawerLayout.closeDrawer(drawerList);

        }
    }

    public ZApplication getZApplication() {
        return (ZApplication) getApplication();
    }

    public void setWarningLevel(WarningLevel level) {
        getZApplication().setWarningLevel(level);
    }

    public WarningLevel getWarningLevel() {
//        return WarningLevel.LOW;
        return getZApplication().getWarningLevel();
    }

    public void showNews() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, NewsFragment.newInstance())
                .commit();
    }

    public void showLevel() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, WarningLevelFragment.newInstance(getWarningLevel()))
                .commit();
    }

}
