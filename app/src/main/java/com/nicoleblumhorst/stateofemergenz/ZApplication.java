package com.nicoleblumhorst.stateofemergenz;

import android.app.Application;

import com.nicoleblumhorst.stateofemergenz.models.ZombieNews;
import com.nicoleblumhorst.stateofemergenz.services.NewsService;
import com.nicoleblumhorst.stateofemergenz.utils.ThreatLevel;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by nicoleblumhorst on 1/17/16.
 */
public class ZApplication extends Application {

    private ThreatLevel threatLevel;
    private ZombieNews zombieNews;
    private static Bus bus = new Bus(ThreadEnforcer.MAIN);

    public ThreatLevel getThreatLevel() {
        return threatLevel;
    }

    public void setThreatLevel(ThreatLevel warningLevel) {
        this.threatLevel = warningLevel;
    }

    public ZombieNews getZombieNews() {
        return zombieNews;
    }

    public void setZombieNews(ZombieNews zombieNews) {
        this.zombieNews = zombieNews;
    }

    public Bus getBus() {
        return bus;
    }

}
