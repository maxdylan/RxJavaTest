package com.henproj.lt.rxjavatest.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.henproj.lt.rxjavatest.R;
import com.jaeger.library.StatusBarUtil;

import butterknife.BindView;

public abstract class ToolbarBaseActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.subtitle)
    TextView subtitle;

    protected abstract String getSubTitle();

    protected boolean isShowBack() {
        return true;
    }

    private void initToolbar() {
        if (toolbar != null) {
            toolbar.setTitle("");
            subtitle.setText(getSubTitle());
            showBack();
        }
    }

    private void showBack() {
        if (isShowBack()) {
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.mipmap.icon_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable
            PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        StatusBarUtil.setColor(this, getResources().getColor(R.color
                .colorPrimary));
        if (getLayoutId() != 0) {
            initToolbar();
        }
    }
}
