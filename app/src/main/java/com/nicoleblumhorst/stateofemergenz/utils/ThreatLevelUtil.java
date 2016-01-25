package com.nicoleblumhorst.stateofemergenz.utils;

/**
 * Created by nicoleblumhorst on 1/24/16.
 */
public class ThreatLevelUtil {

    public static ThreatLevel getThreatLevelFromKey(String key) {
        switch (key) {
            case Constants.LOW:
                return ThreatLevel.LOW;
            case Constants.GUARDED:
                return ThreatLevel.GUARDED;
            case Constants.ELEVATED:
                return ThreatLevel.ELEVATED;
            case Constants.HIGH:
                return ThreatLevel.HIGH;
            case Constants.SEVERE:
                return ThreatLevel.SEVERE;
            default:
                return ThreatLevel.LOW;
        }
    }

}
