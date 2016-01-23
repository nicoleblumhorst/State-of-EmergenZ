package com.nicoleblumhorst.stateofemergenz;

import android.app.Application;

import com.nicoleblumhorst.stateofemergenz.utils.WarningLevel;

/**
 * Created by nicoleblumhorst on 1/17/16.
 */
public class ZApplication extends Application {

    private WarningLevel warningLevel;

    public WarningLevel getWarningLevel() {
        return warningLevel;
    }

    public void setWarningLevel(WarningLevel warningLevel) {
        this.warningLevel = warningLevel;
    }

}
