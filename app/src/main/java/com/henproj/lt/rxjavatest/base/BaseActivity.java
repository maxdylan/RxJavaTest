package com.henproj.lt.rxjavatest.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.henproj.lt.rxjavatest.R;

import butterknife.ButterKnife;

/**
 * Created by Tong on 2018/4/26.
 * Activity基类
 */

public abstract class BaseActivity extends AppCompatActivity {

    /**
     * @return 获取布局id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化布局ui
     *
     * @param savedInstanceBundle
     */
    protected abstract void initView(Bundle savedInstanceBundle);

    /**
     * 界面初始化前的准备工作
     */
    protected void beforeInit() {

    }

    /**
     * 子类可改变状态栏的透明状态
     *
     * @return
     */
    protected boolean isStatusBarTranslucent() {
        return false;
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable
            PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        beforeInit();
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
            initView(savedInstanceState);
        }
    }

    private void initSystemBarTint() {
        Window window = getWindow();
        if (isStatusBarTranslucent()) {
            // 设置状态来全透明
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // 版本大于21时候的操作
                window.clearFlags(WindowManager.LayoutParams
                        .FLAG_TRANSLUCENT_STATUS);
                window.getDecorView().setSystemUiVisibility(View
                        .SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View
                        .SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams
                        .FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                window.addFlags(WindowManager.LayoutParams
                        .FLAG_TRANSLUCENT_STATUS);
            }
        } else {// 未设置状态栏全透明，使用沉浸式
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // 5.0以上使用原生api
                window.clearFlags(WindowManager.LayoutParams
                        .FLAG_TRANSLUCENT_STATUS);
                window.addFlags(WindowManager.LayoutParams
                        .FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(statusBarColor());
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                // TODO 4.4的状态栏沉浸式
            }
        }
    }

    protected int statusBarColor() {
        return R.color.colorPrimary;
    }

    private Toast mToast = null;

    /**
     * 显示toast的基本方法，duration是Toast.LENGTH_SHORT
     *
     * @param msg
     */
    protected void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }
}
