package com.nicoleblumhorst.stateofemergenz.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by nicoleblumhorst on 1/17/16.
 */
public class BaseFragment extends Fragment {

    protected BaseFragmentListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (BaseFragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement BaseFragmentListener");
        }
    }

    public interface BaseFragmentListener {

        public void showNews();

        public void showLevel();

        public void showInfoDialog();

    }

}
