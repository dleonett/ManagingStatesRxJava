package com.example.danielleonett.myapplication.base;

import android.util.Log;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by daniel.leonett on 16/01/2018.
 */

public abstract class BasePresenter<V extends BaseMvpView> implements BaseMvpPresenter<V> {

    // Constants
    private static final String TAG = BasePresenter.class.getSimpleName();

    // Fields
    private V mMvpView;
    private CompositeDisposable mCompositeDisposable;

    // Methods
    public BasePresenter() {
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onAttach(V mvpView) {
        Log.d(TAG, "onAttach()");

        mMvpView = mvpView;
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "onDetach()");

        mCompositeDisposable.dispose();
        mMvpView = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public V getMvpView() {
        return mMvpView;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

}