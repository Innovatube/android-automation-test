package com.innovatube.boilerplate.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.innovatube.boilerplate.R;
import com.innovatube.boilerplate.data.model.User;
import com.innovatube.boilerplate.ui.base.BaseActivityWithDialog;
import com.innovatube.boilerplate.ui.repo.RepositoryActivity;
import com.innovatube.boilerplate.utils.EspressoIdlingResource;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivityWithDialog implements HomeMvpView {

    @BindView(R.id.edit_username)
    EditText mUsernameEditText;
    @BindView(R.id.image_avatar)
    ImageView mAvatarImageView;
    @BindView(R.id.text_name)
    TextView mUserNameTextView;
    @BindView(R.id.text_bio)
    TextView mBioTextView;
    @BindView(R.id.text_login)
    TextView mLoginTextView;
    @BindView(R.id.rl_user)
    RelativeLayout mUserLayout;

    @Inject
    HomePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        mPresenter.attachView(this);
    }

    @Override
    protected void setupDialogTitle() {

    }

    @Override
    public void viewRepository(String username) {
        Intent intent = new Intent(this, RepositoryActivity.class);
        intent.putExtra(RepositoryActivity.ARG_USERNAME, username);
        startActivity(intent);
    }

    @Override
    public void showUser(User user) {
        mUserLayout.setVisibility(View.VISIBLE);
        mUserNameTextView.setText(user.getName());
        mBioTextView.setText(user.getBio());
        mLoginTextView.setText(user.getLogin());
        Glide.with(this)
                .load(user.getAvatarUrl())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(mAvatarImageView);
    }

    @OnClick(R.id.rl_user)
    public void onClick() {
        mPresenter.viewRepository(mLoginTextView.getText().toString());
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    @OnClick(R.id.button_search)
    public void searchUser() {
        if (TextUtils.isEmpty(mUsernameEditText.getText().toString())) {
            mUsernameEditText.setHint("Username can not empty");
            return;
        }
        mPresenter.getUser(mUsernameEditText.getText().toString());
    }
}
