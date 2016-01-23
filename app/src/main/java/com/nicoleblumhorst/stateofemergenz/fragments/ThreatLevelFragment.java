package com.nicoleblumhorst.stateofemergenz.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nicoleblumhorst.stateofemergenz.R;
import com.nicoleblumhorst.stateofemergenz.utils.ThreatLevel;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ThreatLevelFragment extends BaseFragment {

    public static final String LEVEL_KEY = "LEVEL_KEY";

    public ThreatLevel threatLevel;

    @Bind(R.id.wlf_level_text_view)
    TextView levelTextView;

    public static ThreatLevelFragment newInstance(ThreatLevel level) {
        Bundle args = new Bundle();
        args.putSerializable(LEVEL_KEY, level);

        ThreatLevelFragment fragment = new ThreatLevelFragment();
        fragment.setArguments(args);

        return fragment;
    }

    public ThreatLevelFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_threat_level, container, false);
        ButterKnife.bind(this, view);

        if (savedInstanceState != null)
            threatLevel = (ThreatLevel) savedInstanceState.getSerializable(LEVEL_KEY);
        else
            threatLevel = ThreatLevel.LOW;

        levelTextView.setText(getString(threatLevel.getString()));

        return view;
    }

    @OnClick(R.id.wlf_news_button)
    public void newsButtonOnClick() {
        listener.showNews();
    }

}
