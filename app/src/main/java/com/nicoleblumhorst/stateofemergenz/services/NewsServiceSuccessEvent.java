package com.nicoleblumhorst.stateofemergenz.services;

import com.nicoleblumhorst.stateofemergenz.models.ZombieNews;

/**
 * Created by nicoleblumhorst on 2/3/16.
 */
public class NewsServiceSuccessEvent {

    private ZombieNews mZombieNews;

    public NewsServiceSuccessEvent(ZombieNews zombieNews) {
        this.mZombieNews = zombieNews;
    }

    public ZombieNews getmZombieNews() {
        return mZombieNews;
    }

}
