package com.nicoleblumhorst.stateofemergenz.utils;

import com.nicoleblumhorst.stateofemergenz.R;

/**
 * Created by nicoleblumhorst on 1/11/16.
 */
public enum WarningLevel {

    SEVERE (R.color.severe, R.string.severe),
    HIGH (R.color.high, R.string.high),
    ELEVATED (R.color.elevated, R.string.elevated),
    GUARDED (R.color.guarded, R.string.guarded),
    LOW (R.color.low, R.string.low);

    private final int color;
    private final int string;

    WarningLevel(int color, int string) {
        this.color = color;
        this.string = string;
    }

    public int getColor() {
        return color;
    }

    public int getString() {
        return string;
    }

}
