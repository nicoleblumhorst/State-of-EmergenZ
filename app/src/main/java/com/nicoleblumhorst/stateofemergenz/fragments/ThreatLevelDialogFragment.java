package com.nicoleblumhorst.stateofemergenz.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.nicoleblumhorst.stateofemergenz.R;

/**
 * Created by nicoleblumhorst on 2/1/16.
 */
public class ThreatLevelDialogFragment extends DialogFragment {

    public static final String TAG = ThreatLevelDialogFragment.class.getSimpleName();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        return new AlertDialog.Builder(getActivity())
                .setView(R.layout.fragment_threat_level_info_dialog)
                .setPositiveButton(R.string.close, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                })
                .create();
    }

}
