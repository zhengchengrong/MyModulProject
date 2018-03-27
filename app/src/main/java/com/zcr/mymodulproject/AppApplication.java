package com.zcr.mymodulproject;

import android.app.Application;

import com.luojilab.component.basiclib.utils.RxTool;
import com.luojilab.component.basicres.BaseApplication;
import com.luojilab.component.componentlib.router.ui.UIRouter;

/**
 * Created by llz on 2018/3/26.
 */

public class AppApplication extends BaseApplication {
    private static AppApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        UIRouter.getInstance().registerUI("app");
        RxTool.init(this);
        this.app = this;
    }

    public static AppApplication getInstance() {
        return app;
    }
}
