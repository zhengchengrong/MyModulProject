package com.zcr.login.applike;

import com.luojilab.component.basicres.BaseApplication;
import com.luojilab.component.componentlib.applicationlike.IApplicationLike;
import com.luojilab.component.componentlib.router.Router;
import com.luojilab.component.componentlib.router.ui.UIRouter;
import com.luojilab.componentservice.readerbook.ReadBookService;

/**
 * Created by llz on 2018/3/27.
 */

public class LoginAppLike implements IApplicationLike {

    UIRouter uiRouter = UIRouter.getInstance();


    @Override
    public void onCreate() {
        uiRouter.registerUI("login");
    }

    @Override
    public void onStop() {
        uiRouter.unregisterUI("login");
    }
}
