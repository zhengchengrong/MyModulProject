package com.zcr.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.luojilab.router.facade.annotation.RouteNode;

@RouteNode(path = "/loginPage", desc = "登录页面")
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
    }
}
