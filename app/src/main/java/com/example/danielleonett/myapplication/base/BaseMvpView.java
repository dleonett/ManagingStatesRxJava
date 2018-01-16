package com.example.danielleonett.myapplication.base;

import android.content.Context;
import android.support.annotation.StringRes;

public interface BaseMvpView {

    Context getAppContext();

    void showLoading(@StringRes int resId);

    void showLoading(String message);

    void hideLoading();

    void showToast(@StringRes int resId, Object... objects);

    void showToast(String message);

    void showLongToast(@StringRes int resId, Object... objects);

    void showLongToast(String message);

}