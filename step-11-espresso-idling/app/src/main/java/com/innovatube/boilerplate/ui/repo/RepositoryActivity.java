package com.innovatube.boilerplate.ui.repo;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.innovatube.boilerplate.R;
import com.innovatube.boilerplate.data.model.Repository;
import com.innovatube.boilerplate.ui.base.BaseActivityWithDialog;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepositoryActivity extends BaseActivityWithDialog implements RepositoryMvpView {

    public static final String ARG_USERNAME = "Username";
    @BindView(R.id.recycler_repository)
    RecyclerView recyclerRepository;
    RepositoryAdapter mAdapter;

    @Inject
    RepositoryPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);
        ButterKnife.bind(this);
        getActivityComponent().inject(this);
        mPresenter.attachView(this);
        mAdapter = new RepositoryAdapter();
        recyclerRepository.setAdapter(mAdapter);
        recyclerRepository.setLayoutManager(new LinearLayoutManager(this));
        String username = getIntent().getStringExtra(ARG_USERNAME);
        setTitle(username);
        mPresenter.getUsersRepositories(username);

    }

    @Override
    protected void setupDialogTitle() {

    }

    @Override
    public void showRepos(List<Repository> repositories) {
        mAdapter.addRepos(repositories);
    }
}
