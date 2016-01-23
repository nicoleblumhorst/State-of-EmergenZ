package com.nicoleblumhorst.stateofemergenz.models;

/**
 * Created by nicoleblumhorst on 1/23/16.
 */
public class NavDrawerItem {

    private Integer mIcon;
    private String mName;

    public NavDrawerItem (Integer icon, String name) {
        super();
        this.mIcon = icon;
        this.mName = name;
    }

    public Integer getIcon() {
        return mIcon;
    }

    public void setIcon(Integer icon) {
        this.mIcon = icon;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }
}
