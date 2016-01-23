package com.nicoleblumhorst.stateofemergenz.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nicoleblumhorst.stateofemergenz.R;
import com.nicoleblumhorst.stateofemergenz.utils.WarningLevel;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WarningLevelFragment extends BaseFragment {

    public static final String LEVEL_KEY = "LEVEL_KEY";

    public WarningLevel warningLevel;

    @Bind(R.id.wlf_level_text_view)
    TextView levelTextView;

    public static WarningLevelFragment newInstance(WarningLevel level) {
        Bundle args = new Bundle();
        args.putSerializable(LEVEL_KEY, level);

        WarningLevelFragment fragment = new WarningLevelFragment();
        fragment.setArguments(args);

        return fragment;
    }

    public WarningLevelFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_warning_level, container, false);
        ButterKnife.bind(this, view);

        if (savedInstanceState != null)
            warningLevel = (WarningLevel) savedInstanceState.getSerializable(LEVEL_KEY);
        else
            warningLevel = WarningLevel.LOW;

        levelTextView.setText(getString(warningLevel.getString()));

        return view;
    }

    @OnClick(R.id.wlf_news_button)
    public void newsButtonOnClick() {
        listener.showNews();
    }

}
