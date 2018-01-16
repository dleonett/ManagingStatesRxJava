package com.example.danielleonett.myapplication.ui.main;

import com.example.danielleonett.myapplication.base.BaseMvpView;
import com.example.danielleonett.myapplication.data.User;

import io.reactivex.Observable;

/**
 * Created by daniel.leonett on 16/01/2018.
 */

public interface MainMvpView extends BaseMvpView {

    Observable<Object> btnGetUserClicks();

    void showUserInfo(User user);

}
