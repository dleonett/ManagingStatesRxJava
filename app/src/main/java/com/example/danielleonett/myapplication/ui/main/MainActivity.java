package com.example.danielleonett.myapplication.ui.main;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.widget.Button;

import com.example.danielleonett.myapplication.R;
import com.example.danielleonett.myapplication.base.BaseActivity;
import com.example.danielleonett.myapplication.data.User;
import com.jakewharton.rxbinding2.view.RxView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

public class MainActivity extends BaseActivity implements MainMvpView {

    // Views
    @BindView(R.id.btnGetUser)
    Button btnGetUser;
    @BindView(R.id.labelUserName)
    AppCompatTextView labelUserName;
    @BindView(R.id.labelUserCountry)
    AppCompatTextView labelUserCountry;

    // Fields
    private MainMvpPresenter<MainMvpView> presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        presenter.onAttach(this);
    }

    @Override
    protected void initVars() {
        presenter = new MainPresenter<>();
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
    }

    @Override
    public Observable<Object> btnGetUserClicks() {
        return RxView.clicks(btnGetUser);
    }

    @Override
    public void showUserInfo(User user) {
        labelUserName.setText(user.getName());
        labelUserCountry.setText(user.getCountry());
    }
}
