package com.nicoleblumhorst.stateofemergenz.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.nicoleblumhorst.stateofemergenz.R;
import com.nicoleblumhorst.stateofemergenz.utils.ThreatLevel;
import com.nicoleblumhorst.stateofemergenz.utils.ThreatLevelUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ThreatLevelFragment extends BaseFragment {

    public static final String LEVEL_KEY = "LEVEL_KEY";

    public ThreatLevel threatLevel;

    @Bind(R.id.wlf_level_text_view)
    TextView levelTextView;

    @Bind(R.id.wlf_info_button)
    Button infoButton;

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

        if (getArguments() != null)
            threatLevel = (ThreatLevel) getArguments().getSerializable(LEVEL_KEY);
        else if (savedInstanceState != null)
            threatLevel = (ThreatLevel) savedInstanceState.getSerializable(LEVEL_KEY);
        else
            threatLevel = ThreatLevel.LOW;

        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/BOYCOTT_.ttf");
        levelTextView.setTypeface(typeFace);
        levelTextView.setText(threatLevel.getTitle());
        levelTextView.setTextSize(ThreatLevelUtil.getThreatLevelFontSize(threatLevel));

        if (ThreatLevelUtil.isThreatLevelDarkTheme(threatLevel))
            infoButton.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.ic_help_outline_black_36dp));
        else
            infoButton.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.ic_help_outline_white_36dp));

        view.setBackgroundResource(threatLevel.getColorLight());

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putSerializable(LEVEL_KEY, threatLevel);
    }

    @OnClick(R.id.wlf_news_button)
    public void newsButtonOnClick() {
        listener.showNews();
    }

    @OnClick(R.id.wlf_info_button)
    public void infoButtonOnClick() {
        listener.showInfoDialog();
    }

}
