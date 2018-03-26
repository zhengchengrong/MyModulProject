package com.zcr.mymodulproject;

import android.app.Application;

import com.luojilab.component.componentlib.router.ui.UIRouter;

/**
 * Created by llz on 2018/3/26.
 */

public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        UIRouter.getInstance().registerUI("app");
    }

}
