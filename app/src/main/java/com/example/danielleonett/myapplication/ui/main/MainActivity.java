package com.example.danielleonett.myapplication.ui.main;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.danielleonett.myapplication.R;
import com.example.danielleonett.myapplication.base.BaseActivity;
import com.example.danielleonett.myapplication.data.User;
import com.jakewharton.rxbinding2.view.RxView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hani.momanii.supernova_emoji_library.Actions.EmojIconActions;
import hani.momanii.supernova_emoji_library.Helper.EmojiconEditText;
import io.reactivex.Observable;

public class MainActivity extends BaseActivity implements MainMvpView {

    // Views
    @BindView(R.id.btnGetUser)
    Button btnGetUser;
    @BindView(R.id.labelUserName)
    AppCompatTextView labelUserName;
    @BindView(R.id.labelUserCountry)
    AppCompatTextView labelUserCountry;
    @BindView(R.id.inputMessage)
    EmojiconEditText inputMessage;
    @BindView(R.id.btnShowEmojis)
    ImageView btnShowEmojis;
    @BindView(R.id.rootView)
    LinearLayout rootView;
    EmojIconActions emojIconActions;

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

        emojIconActions = new EmojIconActions(this, rootView, inputMessage, btnShowEmojis);
        emojIconActions.ShowEmojIcon();
        emojIconActions.setKeyboardListener(new EmojIconActions.KeyboardListener() {
            @Override
            public void onKeyboardOpen() {
                Log.e("Keyboard", "open");
            }

            @Override
            public void onKeyboardClose() {
                Log.e("Keyboard", "close");
            }
        });

    }

    @OnClick(R.id.btnSend)
    public void onSendClick() {
        Log.e("Keyboard", inputMessage.getText().toString());
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
