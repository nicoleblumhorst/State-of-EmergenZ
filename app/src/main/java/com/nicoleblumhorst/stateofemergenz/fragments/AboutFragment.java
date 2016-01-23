package com.nicoleblumhorst.stateofemergenz.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nicoleblumhorst.stateofemergenz.R;

import butterknife.ButterKnife;

/**
 * Created by nicoleblumhorst on 1/23/16.
 */
public class AboutFragment extends BaseFragment {


    public static NewsFragment newInstance() {
        Bundle args = new Bundle();

        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);

        return fragment;
    }

    public AboutFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        ButterKnife.bind(this, view);

        return view;
    }
}
