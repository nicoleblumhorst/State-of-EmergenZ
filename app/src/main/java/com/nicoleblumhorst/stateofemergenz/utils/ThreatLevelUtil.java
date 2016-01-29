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

    public static boolean isThreatLevelDarkTheme(ThreatLevel threatLevel) {
        switch (threatLevel.getKey()) {
            case Constants.ELEVATED:
            case Constants.HIGH:
                return true;
            default:
                return false;

        }
    }


    // TODO: FIX IT! I know this is terrible practice.
    public static int getThreatLevelFontSize(ThreatLevel threatLevel) {
        switch (threatLevel.getKey()) {
            case Constants.LOW:
            case Constants.HIGH:
                return 150;
            case Constants.SEVERE:
                return 110;
            default:
                return 80;
        }
    }

}
