package com.example.danielleonett.myapplication.base;

public interface BaseMvpPresenter<V extends BaseMvpView> {

    void onAttach(V mvpView);

    void onDetach();

}