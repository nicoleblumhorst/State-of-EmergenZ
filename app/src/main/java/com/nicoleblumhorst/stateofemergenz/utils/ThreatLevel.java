package com.nicoleblumhorst.stateofemergenz.utils;

import com.nicoleblumhorst.stateofemergenz.R;

/**
 * Created by nicoleblumhorst on 1/11/16.
 */
public enum ThreatLevel {

    SEVERE (R.style.SevereTheme, R.color.severe, R.color.severe_light, R.color.severe_dark, R.string.severe, R.drawable.severe),
    HIGH (R.style.HighTheme, R.color.high, R.color.high_light, R.color.high_dark, R.string.high, R.drawable.high),
    ELEVATED (R.style.EleveatedTheme, R.color.elevated, R.color.elevated_light, R.color.elevated_dark, R.string.elevated, R.drawable.elevated),
    GUARDED (R.style.GuardedTheme, R.color.guarded, R.color.guarded_light, R.color.guarded_dark, R.string.guarded, R.drawable.guarded),
    LOW (R.style.LowTheme, R.color.low, R.color.low_light, R.color.low_dark, R.string.low, R.drawable.low);

    private final int theme;
    private final int color;
    private final int colorLight;
    private final int colorDark;
    private final int string;
    private final int drawable;

    ThreatLevel(int theme, int color, int colorLight, int colorDark, int string, int drawable) {
        this.theme = theme;
        this.color = color;
        this.colorLight = colorLight;
        this.colorDark = colorDark;
        this.string = string;
        this.drawable = drawable;
    }

    public int getTheme() {
        return theme;
    }

    public int getColor() {
        return color;
    }

    public int getColorLight() {
        return colorLight;
    }

    public int getColorDark() {
        return colorDark;
    }

    public int getString() {
        return string;
    }

    public int getDrawable() {
        return drawable;
    }

}
