package com.innovatube.boilerplate.data;

import com.innovatube.boilerplate.data.model.Repository;
import com.innovatube.boilerplate.data.model.User;
import com.innovatube.boilerplate.data.remote.RemoteDataSource;

import java.util.List;

import rx.Observable;

/**
 * Created by TOIDV on 4/5/2016.
 */

public class GithubRepositoryImpl implements GithubRepository {
    private final RemoteDataSource remoteDataSource;


    public GithubRepositoryImpl(RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }
    @Override
    public Observable<User> getUser(String username) {
        return remoteDataSource.getUser(username);
    }

    @Override
    public Observable<List<Repository>> getUsersRepositories(String username) {
        return remoteDataSource.getUsersRepositories(username);
    }
}

