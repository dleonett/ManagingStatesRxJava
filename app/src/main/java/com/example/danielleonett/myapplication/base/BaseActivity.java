package com.example.danielleonett.myapplication.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseActivity extends AppCompatActivity implements BaseMvpView {

    private ProgressDialog mProgressDialog;
    private List<String> titleStack;
    private List<String> subtitleStack;
    private BasePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initVars();

        setPresenter(presenter);

        titleStack = new ArrayList<>();
        subtitleStack = new ArrayList<>();

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
    }

    protected void setPresenter(BasePresenter presenter) {
        this.presenter = presenter;
    }

    protected abstract void initVars();

    abstract protected void initViews();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDetach();
        }
    }

    public Context getAppContext() {
        return getApplicationContext();
    }

    public void showToast(int stringResId, Object... objects) {
        showToast(getString(stringResId, objects));
    }

    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(int stringResId, Object... objects) {
        showLongToast(getString(stringResId, objects));
    }

    public void showLongToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading(@StringRes int resId) {
        showLoading(getString(resId));
    }

    @Override
    public void showLoading(String message) {
        hideLoading();
        mProgressDialog.setMessage((message != null) ? message : "Loading...");
        mProgressDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void onBackPressed() {
        if (titleStack.size() > 0) {
            titleStack.remove(titleStack.size() - 1);
            updateTitleFromBackStack();
        }

        if (subtitleStack.size() > 0) {
            subtitleStack.remove(subtitleStack.size() - 1);
            updateTitleFromBackStack();
        }

        super.onBackPressed();
    }

    protected void updateTitleFromBackStack() {
        if (titleStack.size() > 0) {
            getSupportActionBar().setTitle(titleStack.get(titleStack.size() - 1));
        }

        if (subtitleStack.size() > 0) {
            getSupportActionBar().setSubtitle(subtitleStack.get(subtitleStack.size() - 1));
        }
    }

    protected void navigateHorizontalToFragment(Fragment fragment, int containerId, String title,
                                                String tag) {
        titleStack.add(title);

        getSupportFragmentManager()
                .beginTransaction()
                .add(containerId, fragment, tag)
                .commit();

        updateTitleFromBackStack();
    }

    protected void navigateHorizontalToFragment(Fragment fragment, int containerId, String title, String subtitle,
                                                String tag) {
        titleStack.add(title);
        subtitleStack.add(subtitle);

        getSupportFragmentManager()
                .beginTransaction()
                .add(containerId, fragment, tag)
                .commit();

        updateTitleFromBackStack();
    }

    protected void navigateHorizontalReplaceToFragment(Fragment fragment, int containerId, String title,
                                                       String tag) {
        titleStack.add(title);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerId, fragment, tag)
                .commit();

        updateTitleFromBackStack();
    }

    protected void navigateHorizontalReplaceToFragment(Fragment fragment, int containerId, String title,
                                                       String subtitle, String tag) {
        titleStack.add(title);
        subtitleStack.add(subtitle);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerId, fragment, tag)
                .commit();

        updateTitleFromBackStack();
    }

    protected void navigateDownToFragment(Fragment fragment, int containerId, String title,
                                          String tag) {
        titleStack.add(title);

        getSupportFragmentManager()
                .beginTransaction()
                .add(containerId, fragment, tag)
                .addToBackStack("backStack")
                .commit();

        updateTitleFromBackStack();
    }

    protected void navigateDownToFragment(Fragment fragment, int containerId, String title, String subtitle,
                                          String tag) {
        titleStack.add(title);
        subtitleStack.add(subtitle);

        getSupportFragmentManager()
                .beginTransaction()
                .add(containerId, fragment, tag)
                .addToBackStack("backStack")
                .commit();

        updateTitleFromBackStack();
    }

    protected void setOkResult() {
        setResult(RESULT_OK);
    }

    protected void setOkResultWithData(Bundle args) {
        Intent intent = new Intent();
        intent.putExtras(args);

        setResult(RESULT_OK, intent);
    }

}