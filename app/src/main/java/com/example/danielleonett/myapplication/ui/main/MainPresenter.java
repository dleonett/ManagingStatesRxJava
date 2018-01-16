package com.example.danielleonett.myapplication.ui.main;

import android.util.Log;

import com.example.danielleonett.myapplication.base.BasePresenter;
import com.example.danielleonett.myapplication.data.User;
import com.example.danielleonett.myapplication.data.UserRepository;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by daniel.leonett on 16/01/2018.
 */

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements
        MainMvpPresenter<V> {

    private static final String TAG = MainPresenter.class.getSimpleName();

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);

        subscribeToViewEvents();
    }

    private void subscribeToViewEvents() {
        subscribeToGetUserClicks();
    }

    private void subscribeToGetUserClicks() {
        ObservableTransformer<Object, UiStateModel> getUser =
                new ObservableTransformer<Object, UiStateModel>() {
                    @Override
                    public ObservableSource<UiStateModel> apply(Observable<Object> userClicks) {
                        return userClicks
                                .flatMap(new Function<Object, ObservableSource<UiStateModel>>() {
                                    @Override
                                    public ObservableSource<UiStateModel> apply(Object ignored) throws Exception {
                                        return UserRepository.getInstance().getUser()
                                                .subscribeOn(Schedulers.io())
                                                .delay(3, TimeUnit.SECONDS)
                                                .toObservable()
                                                .map(new Function<User, UiStateModel>() {
                                                    @Override
                                                    public UiStateModel apply(User user) throws Exception {
                                                        return UiStateModel.success(user);
                                                    }
                                                })
                                                .onErrorReturn(new Function<Throwable, UiStateModel>() {
                                                    @Override
                                                    public UiStateModel apply(Throwable throwable) throws Exception {
                                                        return UiStateModel.failure(throwable.getMessage());
                                                    }
                                                })
                                                .observeOn(AndroidSchedulers.mainThread())
                                                .startWith(UiStateModel.inProgress());
                                    }
                                });
                    }
                };

        getCompositeDisposable().add(
                getMvpView().btnGetUserClicks()
                        .compose(getUser)
                        .subscribe(new Consumer<UiStateModel>() {
                            @Override
                            public void accept(UiStateModel uiStateModel) throws Exception {
                                // onNext
                                Log.d(TAG, "onNext");

                                if (uiStateModel.isInProgress()) {
                                    getMvpView().showLoading("Getting user...");
                                }
                                if (uiStateModel.isSuccess()) {
                                    getMvpView().hideLoading();
                                    getMvpView().showUserInfo((User) uiStateModel.getData());
                                }
                                if (uiStateModel.isError()) {
                                    getMvpView().hideLoading();
                                    getMvpView().showToast(uiStateModel.getErrorMessage());
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                // onError
                                Log.d(TAG, "onError");
                            }
                        })
        );
    }

}
