package com.nicoleblumhorst.stateofemergenz;

import android.app.Application;

import com.nicoleblumhorst.stateofemergenz.utils.ThreatLevel;

/**
 * Created by nicoleblumhorst on 1/17/16.
 */
public class ZApplication extends Application {

    private ThreatLevel threatLevel;

    public ThreatLevel getThreatLevel() {
        return threatLevel;
    }

    public void setThreatLevel(ThreatLevel warningLevel) {
        this.threatLevel = warningLevel;
    }

}
