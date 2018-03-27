package com.luojilab.component.basiclib.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.luojilab.component.basiclib.R;
import com.luojilab.component.basiclib.widget.EmptyLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by long on 2016/8/19.
 * 基类Activity
 */
public abstract class BaseFragment<T extends IBasePresenter> extends com.trello.rxlifecycle2.components.support.RxFragment implements IBaseView,EmptyLayout.OnRetryListener {

    /**
     * 把 EmptyLayout 放在基类统一处理，@Nullable 表明 View 可以为 null，详细可看 ButterKnife
     */
    @Nullable
    protected EmptyLayout mEmptyLayout;
    /**
     * 把 Presenter 提取到基类需要配合基类的 initInjector() 进行注入，如果继承这个基类则必定要提供一个 Presenter 注入方法，
     * 该APP所有 Presenter 都是在 Module 提供注入实现，也可以选择提供另外不带 Presenter 的基类
     */
    @Inject
    protected T mPresenter;

    protected  View fragmentView;
    protected Activity mActivity;
    /**
     * Dagger 注入
     */
    protected abstract void initInjector();

    /**
     * 初始化视图控件
     */
    protected abstract void initViews();

    /**
     * 更新视图控件
     */
    protected abstract void updateViews(boolean isRefresh);


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragmentView = inflater.inflate(attachLayoutRes(),container,false);
        ButterKnife.bind(this, fragmentView);
        mActivity = getActivity();
        mEmptyLayout = (EmptyLayout) mActivity.findViewById(R.id.empty_layout);
        initInjector();
        initViews();
        updateViews(false);
        return fragmentView;
    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @LayoutRes
    protected abstract int attachLayoutRes();

    @Override
    public void showLoading() {
        if (mEmptyLayout != null) {
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_LOADING);
        }
    }

    @Override
    public void hideLoading() {
        if (mEmptyLayout != null) {
            mEmptyLayout.hide();
        }
    }

    // 网络连接错误
    @Override
    public void showNetError() {
        if (mEmptyLayout != null) {
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_NO_NET);
            mEmptyLayout.setRetryListener(this);
        }
    }

    @Override
    public void showEmpty() {
        if (mEmptyLayout != null) {
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_NO_DATA);
        }
    }

    // 如果网络连接错误，点击后重新向后台申请数据
    @Override
    public void onRetry() {
        updateViews(false);
    }


/*    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.<T>bindToLifecycle();
    }*/

}
