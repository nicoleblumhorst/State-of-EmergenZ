package com.nicoleblumhorst.stateofemergenz.utils;

import com.nicoleblumhorst.stateofemergenz.R;

/**
 * Created by nicoleblumhorst on 1/11/16.
 */
public enum ThreatLevel {

    SEVERE (Constants.SEVERE, R.string.severe, R.string.severe_description, R.style.SevereTheme, R.color.severe, R.color.severe_light, R.color.severe_dark, R.drawable.severe),
    HIGH (Constants.HIGH, R.string.high, R.string.high_description, R.style.HighTheme, R.color.high, R.color.high_light, R.color.high_dark, R.drawable.high),
    ELEVATED (Constants.ELEVATED, R.string.elevated, R.string.elevated_description, R.style.EleveatedTheme, R.color.elevated, R.color.elevated_light, R.color.elevated_dark, R.drawable.elevated),
    GUARDED (Constants.GUARDED, R.string.guarded, R.string.guarded_description, R.style.GuardedTheme, R.color.guarded, R.color.guarded_light, R.color.guarded_dark, R.drawable.guarded),
    LOW (Constants.LOW, R.string.low, R.string.low_description, R.style.LowTheme, R.color.low, R.color.low_light, R.color.low_dark, R.drawable.low);

    private final String key;
    private final int theme;
    private final int color;
    private final int colorLight;
    private final int colorDark;
    private final int title;
    private final int description;
    private final int drawable;

    ThreatLevel(String key, int title, int description, int theme, int color, int colorLight, int colorDark, int drawable) {
        this.key = key;
        this.theme = theme;
        this.color = color;
        this.colorLight = colorLight;
        this.colorDark = colorDark;
        this.title = title;
        this.description = description;
        this.drawable = drawable;
    }

    public String getKey() { return key; }

    public int getTheme() { return theme; }

    public int getColor() { return color; }

    public int getColorLight() { return colorLight; }

    public int getColorDark() { return colorDark; }

    public int getTitle() { return title; }

    public int getDescription() { return description; }

    public int getColorExtraDark() { return R.color.guarded_extra_dark; }

    public int getDrawable() { return drawable; }

}
