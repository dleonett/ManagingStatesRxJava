package com.example.danielleonett.myapplication.data;

import java.util.concurrent.Callable;

import io.reactivex.Single;
import io.reactivex.SingleSource;

/**
 * Created by daniel.leonett on 16/01/2018.
 */

public class UserRepository {

    private static UserRepository instance;

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    private UserRepository() {}

    public Single<User> getUser() {
        return Single.defer(new Callable<SingleSource<? extends User>>() {
            @Override
            public SingleSource<? extends User> call() throws Exception {
                User user = new User();
                user.setId(1);
                user.setName("Daniel Leonett");
                user.setCountry("Venezuela");
                return Single.just(user);
            }
        });
    }

}
